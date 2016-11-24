package Service;

import model.domain.vo.MemberVO;
import model.sql.OracleDao;
import model.sql.OracleDaoImpl;

public class UserServiceImpl {

	// ������ ���������̼� �������� ����ϴ� ������ ���� ���� ���. �������� �Ϲ� �ڹ� ����.
	
	// �α��� �޼ҵ� ����. 
	public MemberVO login(MemberVO member) {
		System.out.println("UserService login");
		
		// dao ���� - �����ͺ��̽��� �ִ� ������ ����� �α����ϱ� 
		OracleDao dao = new OracleDaoImpl();
		return dao.loginRow(member);
		
		
		
		//.............. jslim�� ������ �α���, �ƴϸ� fail.
		
		// ���� § �ڵ�
		/*
		MemberVO mem = member;
		String id = mem.getId();
		String pwd = mem.getPwd();
		
		if(id.equals("jslim") && pwd.equals("jslim")) {
			MemberVO newmem = new MemberVO("jslim", "jslim", "�Ӽ���", "16/10/17", 800, "����");
			return newmem;
		} else {
			return null;
		}
		*/
		
		// ������� § �ڵ�
		/*
		MemberVO result = null;
		if(member.getId().equals("jslim") && member.getPwd().equals("jslim")) {
			result = new MemberVO("jslim","jslim", "�Ӽ���", "16/10/17", 800, "����");
		}
		return result;
		*/
	}
	
	// if-else�� ���ؼ� id password�� jslim�� ���Դٸ� ��ü�� �ϳ� ����� �ѱ�.
	// jslim�� �ƴ� ��� null�� �ѱ�. jsp����.
	// �α��� ����/���� ����ϱ�.
	
	
}
