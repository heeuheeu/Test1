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

		// 컨트롤러. 컨트롤러가 수행하는 역할
		
		// 1. 파라미터로 가져온 값들(화면으로 넘어오는 데이터)을 객체로 만들자. 
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// 2. 객체에 값을 담음.
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		
		// 3. 서비스와의 의존 관계. serviceImpl에 객체 받기.
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
		
		// 4. 화면을 분기시킴
		// 요청 재지정시 sendRedirect 사용. 현재 페이지를 다시 리로드 할 때.
		if(result!=null) {
			response.sendRedirect("main.do");			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("loginFail.jsp");
			rd.forward(request, response);
		}
		
	} 

}
