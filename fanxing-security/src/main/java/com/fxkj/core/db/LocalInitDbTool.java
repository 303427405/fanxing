package com.fxkj.core.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.fxkj.core.db.core.InitDbUtil;
import com.fxkj.core.utils.ProperitiesUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class LocalInitDbTool {
	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("rawtypes")
	static Map map = new HashMap(); // 用来保存已经删除了的表的集合
	@SuppressWarnings("rawtypes")
	static Map filterMap = new HashMap(); // 用来保存需要过滤的表的集合

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, IOException, InstantiationException,
			IllegalAccessException, SAXException, ParserConfigurationException {
		Properties props = System.getProperties(); // 获得系统属性集
		String osName = props.getProperty("os.name"); // 操作系统名称
		if (osName != null && osName.startsWith("Windows")) {
			// 连接数据库信息的配置文件
			Properties properties = new Properties();
			InputStream in = LocalInitDbTool.class
					.getResourceAsStream("/config/jdbc/dev/jdbc.properties");
			if (in == null) {
				System.err.println("数据库配置加载失败！");
				return;
			}
			properties.load(in);
			in.close();
			String url, uid, pwd, driverName;
			url = properties.getProperty("jdbc.url");
			uid = properties.getProperty("jdbc.username");
			pwd = properties.getProperty("jdbc.password");
			driverName = properties.getProperty("jdbc.driverClassName");
			// 删除该用户下所有表（除了需要过滤的）,并获得删除语句
			findDeleteTableSQL(url, uid, pwd, driverName);

			String[] sqlList = ProperitiesUtil.getInstance()
					.getValue("sqlName").split(";");
			System.out.println("..........初始化本地数据库................");
			for (int i = 0; i < sqlList.length; i++) {
				System.out.println("执行本地数据库sql: " + sqlList[i]);
				InputStream is = LocalInitDbTool.class
						.getResourceAsStream("/sql/" + sqlList[i]);
				if (is != null) {
					execute(url, uid, pwd, is, driverName);
				} else {
					System.err.println("本地数据库sql执行失败：" + sqlList[i]);
				}
			}
			System.out.println("..........本地数据库初始化完毕................");
			boolean flg = InitDbUtil.initSystemDb("dev");
			if (flg == true) {
				String sql = ProperitiesUtil.getInstance().getValue(
						"appointLocalSql");
				if (sql != null && !"".equals(sql)) {
					executeAppointSql(sql, url, uid, pwd, driverName);
					System.out
							.println("..........本地数据库指定sql执行完毕................");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "线上数据库不允许初始化！", "警告",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static Connection getConnection(String url, String uid, String pwd,
			String driverName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		Class.forName(driverName).newInstance();
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return cn;
	}

	/**
	 * 执行指定sql
	 * 
	 * @param sql
	 * @param url
	 * @param uid
	 * @param pwd
	 * @param driverName
	 */
	public static void executeAppointSql(String sql, String url, String uid,
			String pwd, String driverName) {
		Connection cn = null;
		Statement stmt = null;
		try {
			cn = getConnection(url, uid, pwd, driverName);
			cn.setAutoCommit(false);
			stmt = cn.createStatement();
			String stra[] = sql.split(";");
			for (int n = 0; n < stra.length; n++) {
				if (stra[n] != null && !"".equals(stra[n])) {
					stmt.addBatch(stra[n] + ";");
				}
			}
			stmt.executeBatch();
			cn.commit();
		} catch (SQLException s) {
			System.err.println(s.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			closeConn(cn, stmt, null);
		}

	}

	/*
	 * 批量执行sql
	 */
	public static void execute(String url, String uid, String pwd,
			InputStream inputStream, String driverName) {
		Connection cn = null;
		try {
			cn = getConnection(url, uid, pwd, driverName);
			// 此处调用setAutoCommit(false)指定要在事务中提交
			cn.setAutoCommit(false);
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		Statement stmt = null;
		// 读取文件
		try {
			stmt = cn.createStatement();
			// FileReader fileReader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuffer buffer = new StringBuffer();
			String temp = null;
			while ((temp = bReader.readLine()) != null) {
				if (!"".equals(temp) && !temp.startsWith("--")) {
					buffer.append(new String(temp.getBytes(), "utf-8") + "\r\n");
				}
			}
			bReader.close();
			String str = buffer.toString();
			// 分隔行
			String stra[] = str.split(";");
			for (int n = 0; n < stra.length; n++) {
				if (stra[n] != null && !"".equals(stra[n])
						&& !"\r\n".equals(stra[n])) {
					stmt.addBatch(stra[n] + ";");
				}

			}
			stmt.executeBatch();
			cn.commit();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			closeConn(cn, stmt, null);
		}
	}

	/**
	 * 初始化需要过滤表的集合(该表如果是别的表的外键关联表也有可能被删除)
	 */
	@SuppressWarnings("unused")
	private static void initFilterMap(String url, String uid, String pwd,
			String driverName) {
	}

	/**
	 * 获得该用户的所有表,并删除表（除了要过滤的表）
	 */
	@SuppressWarnings("resource")
	private static void findDeleteTableSQL(String url, String uid, String pwd,
			String driverName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection(url, uid, pwd, driverName);
			stmt = conn.createStatement();
			String sql = "show TABLES";
			// 找到该链接用户的所有表
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String tabName = rs.getString(1);
				// getString("table_name");
				// 如果map中包含了表名,说明已经删除过了
				// 如果filterMap中包含了表名,则不删除
				try {
					if (!map.containsKey(tabName)
							&& !filterMap.containsKey(tabName)) {
						printDeleteTableSQL(tabName, url, uid, pwd, driverName);
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			int count = 0;
			rs = stmt.executeQuery("show tables");
			while (rs.next()) {
				count++;
			}
			if (count > 0) {
				findDeleteTableSQL(url, uid, pwd, driverName);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			closeConn(conn, stmt, rs);
		}
	}

	/**
	 * 
	 * 删除表，并将删除表的语句输出到控制台(记录后方便在数据库客户端执行)
	 * 
	 * @param l
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static void printDeleteTableSQL(String tableName, String url,
			String uid, String pwd, String driverName) {
		String sql = "DROP TABLE " + tableName.toUpperCase();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection(url, uid, pwd, driverName);
			stmt = conn.createStatement();
			stmt.execute(sql);
			// 将删除语句输出到控制台
			map.put(tableName, null);
		} catch (MySQLIntegrityConstraintViolationException e) {
			String error = e.toString();
			System.out.println(error);

			int p1 = error.indexOf("(");
			int p2 = error.indexOf("FOREIGN KEY");
			error = error.substring(p1, p2);

			int p3 = error.indexOf("CONSTRAINT");
			error = error.substring(p3);
			error = error.replaceAll("CONSTRAINT", "").replaceAll(" ", "")
					.replaceAll("`", "");

			// 截取错误信息得到外键约束名称
			String fk_constraints = error;
			System.out.println("fk_constraints" + fk_constraints);

			deleteTableNameFromFK(fk_constraints, url, uid, pwd, driverName);
			// 删除外键约束表后,就可以将本表删掉
			printDeleteTableSQL(tableName, url, uid, pwd, driverName);

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			closeConn(conn, stmt, null);
		}
	}

	/**
	 * 删除通过外键约束找到的有子记录的表
	 * 
	 * @param fk_constraints
	 */
	private static void deleteTableNameFromFK(String fk_constraints,
			String url, String uid, String pwd, String driverName) {
		String sql = "select table_name from information_schema.REFERENTIAL_CONSTRAINTS  where constraint_schema='laidong8v5' and constraint_name='"
				+ fk_constraints + "'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection(url, uid, pwd, driverName);
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String tabN = rs.getString("table_name");
				printDeleteTableSQL(tabN, url, uid, pwd, driverName);// 递归
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			closeConn(conn, stat, rs);
		}
	}

	/*
	 * 关闭连接
	 */
	public static void closeConn(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (rs != null) {
				if (!rs.isClosed()) {
					conn.close();
				}
			}
			if (stat != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
			}
			if (conn != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
