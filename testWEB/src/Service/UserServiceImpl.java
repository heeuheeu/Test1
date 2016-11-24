package Service;

import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

public class UserServiceImpl {

	// 서블릿은 프리젠테이션 영역에서 담당하는 서블릿만 서블릿 파일 사용. 나머지는 일반 자바 파일.
	
	// 로그인 메소드 생성. 
	public MemberVO login(MemberVO member) {
		System.out.println("UserService login");
		
		// dao 연동 - 데이터베이스에 있는 데이터 사용해 로그인하기 
		OracleDao dao = new OracleDaoImpl();
		return dao.loginRow(member);
		
		
		
		//.............. jslim이 있으면 로그인, 아니면 fail.
		
		// 내가 짠 코드
		/*
		MemberVO mem = member;
		String id = mem.getId();
		String pwd = mem.getPwd();
		
		if(id.equals("jslim") && pwd.equals("jslim")) {
			MemberVO newmem = new MemberVO("jslim", "jslim", "임섭순", "16/10/17", 800, "교육");
			return newmem;
		} else {
			return null;
		}
		*/
		
		// 강사님이 짠 코드
		/*
		MemberVO result = null;
		if(member.getId().equals("jslim") && member.getPwd().equals("jslim")) {
			result = new MemberVO("jslim","jslim", "임섭순", "16/10/17", 800, "교육");
		}
		return result;
		*/
	}
	
	// if-else를 통해서 id password가 jslim이 들어왔다면 객체를 하나 만들어 넘김.
	// jslim이 아닐 경우 null을 넘김. jsp에게.
	// 로그인 성공/실패 출력하기.
	
	
}
