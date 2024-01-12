package renshu1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsDAO {
	//接続用の情報をフィールドに定数として定義
	private static final String RDB_DRIVE="org.h2.Driver";
	private static final String URL="jdbc:h2:~/rpgdb";
	private static final String USER="sa";
	private static final String PASSWD="";
	
	public static ArrayList<Item> findByMinimumPrice(int price){
		
		ArrayList<Item> resultList = new ArrayList<>();
		//ドライバのロード
		try {
			Class.forName(RDB_DRIVE);
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
		
		Connection con = null;
		try {
			//DBに接続処理
			con = DriverManager.getConnection(URL,USER,PASSWD);
			//SQLの構築
			PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM ITEMS WHERE PRICE >= ?");
			pstmt.setInt(1, price);
			
			//SQLの実行
			ResultSet rs = pstmt.executeQuery();
			
			//処理結果を格納
			while(rs.next()) {
				Item work = new Item();
				work.setName(rs.getString("NAME"));
				work.setPrice(rs.getInt("PRICE"));
				work.setWeight(rs.getInt("WEIGHT"));
				resultList.add(work);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			//DBの切断処理
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultList;
	}
}
