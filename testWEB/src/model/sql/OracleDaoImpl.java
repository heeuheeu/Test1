package model.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.domain.vo.MemberVO;

public class OracleDaoImpl implements OracleDao { 	
	// dao 실행. 현장에서는 보통 interface를 implements하고 있는 클래스

	// 나중에 static 상수들은 특정 파일로 캡슐화
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; // 오라클의 드라이버 패키지 경로
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	public static final String USER = "hr";
	public static final String PASSWD = "hr";
	
	public OracleDaoImpl() {
		try{
			Class.forName(DRIVER); // 드라이버 로딩. 불러오는 메소드
			System.out.println("Loading ok");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public int insertRow(MemberVO member) {
		
		System.out.println("Dao insertRow");
		
		// 변수 준비
		int flag = 0; // 반환형이 int 형이므로 resultset 이 아닌 int형 flag 선언
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
			
			// excuteUpdate로 받기
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
		
		// 변수 준비
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
		
		// 변수 준비
		int flag = 0; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		String deleteSQL = "delete from incmember where id = ? ";		
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, member.getId());
			
			// excuteUpdate로 받기
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
		
		// 변수 준비
		MemberVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String loginSQL = "select * from incmember " // 쿼리문 띄어쓰기 주의!!!!!!!!
				+ "where id = ? and pwd = ?";
		
		try{
			// 여기에 수행 구문 작성
			
			// 2. Connection
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			// 3. Statement : SQL을 담을 수 있는 그릇 준비
			pstmt = conn.prepareStatement(loginSQL);
			
			// sql문의 물음표 (동적 쿼리)에 id값, pwd 값 넣기
			pstmt.setString(1, member.getId()); // id 값 넣기
			pstmt.setString(2, member.getPwd()); // pwd 값 넣기
			
			// 4. Execute - select 
			// DML일 때는 executeUpdate, select 일 때는 executeQuery
			rset = pstmt.executeQuery();
			
			// 5. Resultset Handling
			// rset - 커서를 내리는 메소드
			if(rset.next()) { // 컬럼을 하나씩 꺼냄. 이것을 객체에 담는다.
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
		
		// 변수 준비	
		List<MemberVO> list = new ArrayList<MemberVO>(); // list가 arraylist의 부모
		MemberVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String selectSQL = "select * from incmember"; 
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(selectSQL);
			rset = pstmt.executeQuery();

			while(rset.next()) { // return할 행이 몇 개인지 모를 때는 while 문으로 실행
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
