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
		
		System.out.println("GreetingServlet doGet"); // �ֿܼ� ���
		
		// 1. send redirect ���
		//response.sendRedirect("greeting.jsp");

		// 2. forward ��� 
		// ���忡���� .jsp ��� do�� ȭ�� �б⸦ ����.
		// �츮�� ����Ϸ��� mvc �𵨿��� ��� ��û�� servlet��, ������ jsp�� �����Ѵ�!
		RequestDispatcher rd = request.getRequestDispatcher("greeting.jsp");
		rd.forward(request,  response);
		
		/*
		// ���� jsp ���� html�� ����ϰ� �ʹٸ�
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); // html ������ �����ͷ� ���
		out.println("<div align = 'center'>"); 
		out.println("<font color='blue'>������~~</font>"); 
		out.println("</div'>"); 
		out.println("</body></html>"); 
		*/
		
		// ���� �� ������Ʈ�� ������ deploy - server�� add!
		
		
	}

}
