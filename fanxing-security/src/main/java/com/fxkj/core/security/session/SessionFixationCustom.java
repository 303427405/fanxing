package com.fxkj.core.security.session;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.util.Assert;

import com.fxkj.core.security.MyUserDetailInfo;

public class SessionFixationCustom extends SessionFixationProtectionStrategy
		implements MessageSourceAware {
	protected MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();
	private final SessionRegistry sessionRegistry;
	private boolean exceptionIfMaximumExceeded = false;
	private int maximumSessions = 1;

	/**
	 * @param sessionRegistry
	 *            the session registry which should be updated when the
	 *            authenticated session is changed.
	 */
	public SessionFixationCustom(SessionRegistry sessionRegistry) {
		Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null");
		super.setAlwaysCreateSession(true);
		this.sessionRegistry = sessionRegistry;
	}

	/**
	 * In addition to the steps from the superclass, the sessionRegistry will be
	 * updated with the new session information.
	 */
	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		checkAuthenticationAllowed(authentication, request);

		// Allow the parent to create a new session if necessary
		super.onAuthentication(authentication, request, response);
		String ip = ((WebAuthenticationDetails) authentication.getDetails())
				.getRemoteAddress();
		MyUserDetailInfo user = null;
		if (authentication.getPrincipal() instanceof MyUserDetailInfo) {
			user = (MyUserDetailInfo) authentication.getPrincipal();
			user.setIp(ip);
		}
		sessionRegistry.registerNewSession(request.getSession().getId(), user);
	}

	private void checkAuthenticationAllowed(Authentication authentication,
			HttpServletRequest request) throws AuthenticationException {
		String ip = ((WebAuthenticationDetails) authentication.getDetails())
				.getRemoteAddress();
		MyUserDetailInfo user = null;
		if (authentication.getPrincipal() instanceof MyUserDetailInfo) {
			user = (MyUserDetailInfo) authentication.getPrincipal();
			user.setIp(ip);
		}
		final List<SessionInformation> sessions = sessionRegistry
				.getAllSessions(user, false);

		int sessionCount = sessions.size();
		int allowedSessions = getMaximumSessionsForThisUser(authentication);

		if (sessionCount < allowedSessions) {
			// They haven't got too many login sessions running at present
			return;
		}

		if (allowedSessions == -1) {
			// We permit unlimited logins
			return;
		}

		if (sessionCount == allowedSessions) {
			HttpSession session = request.getSession(false);

			if (session != null) {
				// Only permit it though if this request is associated with one
				// of the already registered sessions
				for (SessionInformation si : sessions) {
					if (si.getSessionId().equals(session.getId())) {
						return;
					}
				}
			}
			// If the session is null, a new one will be created by the parent
			// class, exceeding the allowed number
		}
		boolean sameip = false;
		// 判断用户是否同一ip登录
		for (SessionInformation si : sessions) {
			MyUserDetailInfo user_old = (MyUserDetailInfo) si.getPrincipal();
			if (user.equals(user_old) && user_old.getIp().equals(ip)) {
				sameip = true;
				break;
			}
		}

		allowableSessionsExceeded(sessions, allowedSessions, sessionRegistry,
				sameip);
	}

	/**
	 * Method intended for use by subclasses to override the maximum number of
	 * sessions that are permitted for a particular authentication. The default
	 * implementation simply returns the <code>maximumSessions</code> value for
	 * the bean.
	 *
	 * @param authentication
	 *            to determine the maximum sessions for
	 *
	 * @return either -1 meaning unlimited, or a positive integer to limit
	 *         (never zero)
	 */
	protected int getMaximumSessionsForThisUser(Authentication authentication) {
		return maximumSessions;
	}

	/**
	 * Allows subclasses to customise behaviour when too many sessions are
	 * detected.
	 *
	 * @param sessions
	 *            either <code>null</code> or all unexpired sessions associated
	 *            with the principal
	 * @param allowableSessions
	 *            the number of concurrent sessions the user is allowed to have
	 * @param registry
	 *            an instance of the <code>SessionRegistry</code> for subclass
	 *            use
	 *
	 */
	protected void allowableSessionsExceeded(List<SessionInformation> sessions,
			int allowableSessions, SessionRegistry registry, boolean sameip)
			throws SessionAuthenticationException {
		// 如果IP不相同，则页面弹出错误窗口；如果IP相同，后登录的session信息覆盖先登录的session信息(防止用户非正常关闭浏览器导致登录不上)
		if (!sameip && (exceptionIfMaximumExceeded || (sessions == null))) {
			throw new SessionAuthenticationException(messages.getMessage(
					"ConcurrentSessionControlStrategy.exceededAllowed",
					new Object[] { Integer.valueOf(allowableSessions) },
					"Maximum sessions of {0} for this principal exceeded"));
		}

		// Determine least recently used session, and mark it for invalidation
		SessionInformation leastRecentlyUsed = null;

		for (SessionInformation session : sessions) {
			if ((leastRecentlyUsed == null)
					|| session.getLastRequest().before(
							leastRecentlyUsed.getLastRequest())) {
				leastRecentlyUsed = session;
			}
		}

		leastRecentlyUsed.expireNow();
	}

	/**
	 * Sets the <tt>exceptionIfMaximumExceeded</tt> property, which determines
	 * whether the user should be prevented from opening more sessions than
	 * allowed. If set to <tt>true</tt>, a
	 * <tt>SessionAuthenticationException</tt> will be raised.
	 *
	 * @param exceptionIfMaximumExceeded
	 *            defaults to <tt>false</tt>.
	 */
	public void setExceptionIfMaximumExceeded(boolean exceptionIfMaximumExceeded) {
		this.exceptionIfMaximumExceeded = exceptionIfMaximumExceeded;
	}

	/**
	 * Sets the <tt>maxSessions</tt> property. The default value is 1. Use -1
	 * for unlimited sessions.
	 *
	 * @param maximumSessions
	 *            the maximimum number of permitted sessions a user can have
	 *            open simultaneously.
	 */
	public void setMaximumSessions(int maximumSessions) {
		Assert.isTrue(
				maximumSessions != 0,
				"MaximumLogins must be either -1 to allow unlimited logins, or a positive integer to specify a maximum");
		this.maximumSessions = maximumSessions;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	@Override
	public final void setAlwaysCreateSession(boolean alwaysCreateSession) {
		if (!alwaysCreateSession) {
			throw new IllegalArgumentException(
					"Cannot set alwaysCreateSession to false when concurrent session "
							+ "control is required");
		}
	}
}