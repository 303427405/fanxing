package com.fxkj.core.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.common.ApplicationContextUtils;
import com.fxkj.core.utils.StringUtil;
import com.fxkj.dictionary.entity.Dictionary;
import com.fxkj.dictionary.service.DictionaryService;
import com.fxkj.enums.EnabledEnum;
import com.fxkj.security.dao.PermissionsDao;
import com.fxkj.security.entity.Permissions;

public class InitDbUtil {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> dmap = new HashMap<Integer, Integer>();
	static Integer pId = null;

	/**
	 * 
	 * @param activeProfiles  运行环境（qa,dev）
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static boolean initSystemDb(String activeProfiles) throws ParserConfigurationException,
			IOException, SAXException {

		ApplicationContext app = getApp(activeProfiles);
		PermissionsDao permissionsDao = (PermissionsDao) app
				.getBean("permissionsDao");

		DictionaryService dictionaryService = (DictionaryService) app
				.getBean("dictionaryService");

		Resource[] perResources = getPermisssionResource(app);
		System.out.println("************正在初始化权限***********");
		readPermissionConfigXml(perResources, permissionsDao);
		System.out.println("************权限初始化结束***********");

		Resource[] dicResources = getDicResource(app);
		System.out.println("************正在初始化字典项***********");
		readDictionaryXml(dicResources, dictionaryService);
		System.out.println("************字典项初始化结束***********");

		return true;

	}

	private static Document getDocument(InputStream file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(file);
		return doc;
	}

	/**
	 * 读取系统权限文件
	 */
	public static List<Permissions> readPermissionConfigXml(
			Resource[] resources, PermissionsDao permissionsDao)
			throws ParserConfigurationException, SAXException, IOException {
		for (Resource r : resources) {
			System.out.println("正在读取：" + r.getFilename());
			Document doc = getDocument(r.getInputStream());
			Element root = doc.getDocumentElement();
			if (root == null)
				return null;
			NodeList nodes = root.getElementsByTagName("pc");
			if (nodes == null)
				return null;
			getPermissionListByNodeList(nodes, permissionsDao);
			map = new HashMap<Integer, Integer>();
			pId = null;
		}
		return null;
	}

	/**
	 * 读取系统字典项文件
	 */
	public static List<Dictionary> readDictionaryXml(Resource[] resources,
			DictionaryService dictionaryService)
			throws ParserConfigurationException, SAXException, IOException {
		for (Resource r : resources) {
			System.out.println("正在读取：" + r.getFilename());
			Document doc = getDocument(r.getInputStream());
			Element root = doc.getDocumentElement();
			if (root == null)
				return null;
			NodeList nodes = root.getElementsByTagName("dc");
			if (nodes == null)
				return null;
			getDictionaryListByNode(nodes, dictionaryService);
			dmap = new HashMap<Integer, Integer>();
		}
		return null;
	}

	public static void getPermissionListByNodeList(NodeList nodes,
			PermissionsDao permissionsDao) {
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nodes.item(i);
				Permissions p = new Permissions();
				p.setName(e.getAttribute("name"));
				p.setBtnFlg(e.getAttribute("flg"));
				p.setEnabled(EnabledEnum.ENABLED.getCode());
				p.setIcon(e.getAttribute("icon"));
				p.setLeaf(!"".equals(e.getAttribute("leaf")) ? Integer
						.valueOf(e.getAttribute("leaf")) : null);
				p.setParentId(map.get(p.getLeaf() - 1));

				if (p.getLeaf() > 1) {
					Permissions temp = permissionsDao
							.getPermissionsForSimpleById(map.get(p.getLeaf() - 1));
					p.setNamePath(temp.getNamePath() + ">>" + p.getName());
				} else {
					p.setNamePath(p.getName());
				}
				p.setType(!"".equals(e.getAttribute("type")) ? Integer
						.valueOf(e.getAttribute("type")) : null);
				p.setWebUrl(StringUtil.isNotEmpty(e.getAttribute("url")) ? e
						.getAttribute("url") : null);
				p.setBgColor(StringUtil.isNotEmpty(e.getAttribute("color")) ? e
						.getAttribute("color") : null);

				pId = permissionsDao.addPerssions(p);
				map.put(p.getLeaf(), pId);
				NodeList nodeList = nodes.item(i).getChildNodes();
				getPermissionListByNodeList(nodeList, permissionsDao);
			}
		}
	}

	public static void getDictionaryListByNode(NodeList nodes,
			DictionaryService dictionaryService) {
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nodes.item(i);
				Dictionary d = new Dictionary();
				d.setName(e.getAttribute("name"));
				d.setLeaf(Integer.valueOf(e.getAttribute("leaf")));
				d.setEnabled(EnabledEnum.ENABLED.getCode());
				d.setCode(!"".equals(e.getAttribute("code")) ? e
						.getAttribute("code") : null);
				d.setParentId(dmap.get(d.getLeaf() - 1));
				d.setSequence(!"".equals(e.getAttribute("sequence")) ? Integer
						.valueOf(e.getAttribute("sequence")) : null);
				MsgUtil msg = dictionaryService.checkCodeRepeatByLeaf(
						d.getCode(), d.getLeaf(), d.getParentId());
				if (msg.getSuccess() == true) {
					dictionaryService.addDictionary(d);
					dmap.put(d.getLeaf(), d.getId());
					NodeList nodeList = nodes.item(i).getChildNodes();
					getDictionaryListByNode(nodeList, dictionaryService);
				} else {
					System.err.println("字典项：" + d.getName() + "code重复！");
				}
			}
		}
	}

	public static ApplicationContext getApp(String activeProfiles) {
		return ApplicationContextUtils.getApplicationContext(activeProfiles);
	}

	public static Resource[] getPermisssionResource(ApplicationContext app)
			throws IOException {
		Resource[] resources = app
				.getResources("classpath*:**/*_permisssion.xml");
		return resources;

	}

	public static Resource[] getDicResource(ApplicationContext app)
			throws IOException {
		Resource[] resources = app
				.getResources("classpath*:**/*_dictionary.xml");
		return resources;

	}

	// public static void main(String[] args) {
	// try {
	// InitDbUtil.initSystemDb();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
}
