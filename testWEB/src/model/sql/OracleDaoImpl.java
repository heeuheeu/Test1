package model.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.domain.vo.MemberVO;

public class OracleDaoImpl implements OracleDao { 	
	// dao ����. ���忡���� ���� interface�� implements�ϰ� �ִ� Ŭ����

	// ���߿� static ������� Ư�� ���Ϸ� ĸ��ȭ
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // ����Ŭ�� ����̹� ��Ű�� ���
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	public static final String USER = "hr";
	public static final String PASSWD = "hr";
	
	public OracleDaoImpl() {
		try{
			Class.forName(DRIVER); // ����̹� �ε�. �ҷ����� �޼ҵ�
			System.out.println("Loading ok");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public int insertRow(MemberVO member) {
		
		System.out.println("Dao insertRow");
		
		// ���� �غ�
		int flag = 0; // ��ȯ���� int ���̹Ƿ� resultset �� �ƴ� int�� flag ����
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertSQL = "insert into incmember values(?,?,?,?,?,?)";
		
		try{
	
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, member.getId()); 
			pstmt.setString(2, member.getPwd()); 
			pstmt.setString(3, member.getName()); 
			pstmt.setString(4, member.getHiredate()); 
			pstmt.setInt(5, member.getSalary()); 
			pstmt.setString(6, member.getDept());
			
			// excuteUpdate�� �ޱ�
			flag = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. close
				if(conn != null)  	conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public int updateRow(MemberVO member) {

		System.out.println("Dao updateRow");
		
		// ���� �غ�
		int flag = 0; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		String updateSQL = "update incmember set dept = ? where id = ? ";		
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, member.getDept());
			pstmt.setString(2, member.getId());
			
			flag = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. close
				if(conn != null)  	conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public int deleteRow(MemberVO member) {
		
		System.out.println("Dao deleteRow");
		
		// ���� �غ�
		int flag = 0; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		String deleteSQL = "delete from incmember where id = ? ";		
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, member.getId());
			
			// excuteUpdate�� �ޱ�
			flag = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. close
				if(conn != null)  	conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public MemberVO loginRow(MemberVO member) {
		// TODO Auto-generated method stub
		
		System.out.println("Dao loginRow");
		
		// ���� �غ�
		MemberVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String loginSQL = "select * from incmember " // ������ ���� ����!!!!!!!!
				+ "where id = ? and pwd = ?";
		
		try{
			// ���⿡ ���� ���� �ۼ�
			
			// 2. Connection
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			// 3. Statement : SQL�� ���� �� �ִ� �׸� �غ�
			pstmt = conn.prepareStatement(loginSQL);
			
			// sql���� ����ǥ (���� ����)�� id��, pwd �� �ֱ�
			pstmt.setString(1, member.getId()); // id �� �ֱ�
			pstmt.setString(2, member.getPwd()); // pwd �� �ֱ�
			
			// 4. Execute - select 
			// DML�� ���� executeUpdate, select �� ���� executeQuery
			rset = pstmt.executeQuery();
			
			// 5. Resultset Handling
			// rset - Ŀ���� ������ �޼ҵ�
			if(rset.next()) { // �÷��� �ϳ��� ����. �̰��� ��ü�� ��´�.
				result = new MemberVO ( 
						rset.getString(1), 	rset.getString(2), 
						rset.getString(3), 	rset.getString(4), 
						rset.getInt(5), rset.getString(6)
						);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. close
				if(conn != null)  	conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public List<MemberVO> selectRow() {

		System.out.println("Dao selectRow");
		
		// ���� �غ�	
		List<MemberVO> list = new ArrayList<MemberVO>(); // list�� arraylist�� �θ�
		MemberVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String selectSQL = "select * from incmember"; 
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(selectSQL);
			rset = pstmt.executeQuery();

			while(rset.next()) { // return�� ���� �� ������ �� ���� while ������ ����
				result = new MemberVO ( 
						rset.getString(1), 	rset.getString(2), 
						rset.getString(3), 	rset.getString(4), 
						rset.getInt(5), rset.getString(6)
						);
				list.add(result);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)  	conn.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	} 

	
}
