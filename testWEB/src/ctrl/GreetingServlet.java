package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GreetingServlet
 */
@WebServlet({ "/greeting.do" })
public class GreetingServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("GreetingServlet doGet"); // 콘솔에 출력
		
		// 1. send redirect 방식
		//response.sendRedirect("greeting.jsp");

		// 2. forward 방식 
		// 현장에서는 .jsp 대신 do로 화면 분기를 지향.
		// 우리가 사용하려는 mvc 모델에서 모든 요청은 servlet이, 응답은 jsp이 수행한다!
		RequestDispatcher rd = request.getRequestDispatcher("greeting.jsp");
		rd.forward(request,  response);
		
		/*
		// 만약 jsp 없이 html을 출력하고 싶다면
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); // html 형식의 데이터로 출력
		out.println("<div align = 'center'>"); 
		out.println("<font color='blue'>섭섭해~~</font>"); 
		out.println("</div'>"); 
		out.println("</body></html>"); 
		*/
		
		// 이제 이 프로젝트를 서버에 deploy - server에 add!
		
		
	}

}
