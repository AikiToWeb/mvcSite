package ctrl;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import act.Action;
import vo.ActionForward;

@WebServlet("*.mem")
public class MemberCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberCtrl() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// get과 post 두 가지 모두의 요청을 처리하는 메소드
        	request.setCharacterEncoding("utf-8");
        	String requestUri = request.getRequestURI();	
        	String contextPath = request.getContextPath();	// mvcBoard
        	String command = requestUri.substring(contextPath.length());	//
        	
        	ActionForward forward = null;
        	Action action = null;
    }
        	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
