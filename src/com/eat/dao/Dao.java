package com.eat.dao;

import java.util.List;
import java.sql.ResultSetMetaData;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

public class Dao {
	//数据库连接
	/**
	 * //也可以将连接数据库的信息写到配置文件
	 * ，通过读配置文件设置driver/url/user/pass
	 * @return
	 */
	private Connection getConn() {
		Connection con = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
//	java.sql.Connection   orcl数据库名
			// 连接数据库
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eat?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true","root","123456");	
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return con;
	}
	
	//语句查询数据库的某个value（如sid=1,name='fdf'）是否存在
	public boolean isExist(String sql) {
		Connection con = getConn();
		try {
			//该方法用于创建一个statememt对象，封装sql语句发送到数据库
			Statement st = con.createStatement();
			//执行这条sql语句，ResultSet rs存放的是数据库中，返回来的数据结果
			ResultSet rs=st.executeQuery(sql); 
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		} finally {
			if (con != null) {//关闭con,这样才能释放该Statement对象占用的资源
				try {
					con.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
    //
	public boolean exeUpdate(String sql) {
		Connection con = getConn();
		try {
			//该方法用于创建一个Statement对象，封装SQL语句发送给数据库
			Statement st = con.createStatement();
			 /*返回更新影响的行数,就是你这个SQL查询更新了多少行
	           如果为0, 表示不返回任何内容的 SQL 语句*/
			st.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return false;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	//哈希表   列表
			public List getData(String sql) {
				Connection conn=getConn();//获取数据库的连接
				List lst=new ArrayList<>();
				try {
					Statement st=conn.createStatement();//该方法用于创建一个Statement对象，封装SQL语句发送给数据库
					ResultSet rs=st.executeQuery(sql);//是执行这条sql语言，ResultSet rs存放的是从数据库中，返回来的数据结果
					ResultSetMetaData md=rs.getMetaData();//得到结果集列的属性，得到结果集(rs)的结构，比如字段数、字段名等。
					int cnt=md.getColumnCount();//getColumnCount获取列的数量
					//rs.next时，游标最先是指向第一条记录前的位置，所以第一次rs.next后，游标指向的正好是第一条记录（如果有的话）
					//要判断rs的结果是否为空，只要使用一次rs.next方法就行了，如果它返回为false，则证明rs的结果为null
					while (rs.next()) {
						//hashTable：他们都可以将可以key和value结合起来构成键值对通过put(key,value)方法保存起来，然后通过get(key)方法获取相对应的value值。
						Map<String, String> map=new Hashtable<>();//创建对象
						//for循环输出查询结果
						for (int i = 1; i <=cnt; i++) {
						  //调用的getColumnName返回的是数据库表字段的真实名字
							//rs.getString(i)查询记录的字段的值value
							//rs.getString(i) ：为获取结果集当前行的第i列数据。

							map.put(md.getColumnName(i), rs.getString(i));
						}
						lst.add(map);//map对象添加到集合
					}
				} catch (Exception e) {//捕获异常
					// TODO: handle exception
					System.err.println(e.getMessage());
				}finally {
					
				}
				return lst;
			}
	
	//student
	public int count(String sql) {
		Connection con = getConn();
		String s="0";
		try {
			//
			Statement st = con.createStatement();
			//
			ResultSet rs=st.executeQuery(sql);
			//rs.next
			if (rs.next()) {
				//rs.getString(n）
				s=rs.getString(1);
			}
			return Integer.parseInt(s);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return 0;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
