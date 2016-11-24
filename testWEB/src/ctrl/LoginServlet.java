package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.UserServiceImpl;
import model.domain.vo.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");

		// ��Ʈ�ѷ�. ��Ʈ�ѷ��� �����ϴ� ����
		
		// 1. �Ķ���ͷ� ������ ����(ȭ������ �Ѿ���� ������)�� ��ü�� ������. 
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 2. ��ü�� ���� ����.
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		
		// 3. ���񽺿��� ���� ����. serviceImpl�� ��ü �ޱ�.
		UserServiceImpl service = new UserServiceImpl();
		MemberVO result = service.login(member);
		
		/*
		if(result!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("loginSuccess.jsp");
			rd.forward(request, response);
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("loginFail.jsp");
			rd.forward(request, response);
		}
		*/
		
		// 4. ȭ���� �б��Ŵ
		// ��û �������� sendRedirect ���. ���� �������� �ٽ� ���ε� �� ��.
		if(result!=null) {
			response.sendRedirect("main.do");			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("loginFail.jsp");
			rd.forward(request, response);
		}
		
	} 

}
