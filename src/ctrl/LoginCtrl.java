package ctrl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginCtrl() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url");
		if (url.equals("")) url = "index.jsp";		// url이 비어있다면 첫 화면(index.jsp)로 이동	
		// NULL 검사 안 한 이유 : 앞에 login_form.jsp에서 url이 hidden 객체로 있어서 Null이 들어올 수 없음
		
		LoginSvc loginSvc = new LoginSvc();
		MemberInfo memberInfo = loginSvc.getLoginMember(uid, pwd);
		
		HttpSession session = request.getSession();
		// JSP가 아니므로 세션 객체를 사용하려면 직접 HttpSession 인스턴스를 생성하여야 함
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (memberInfo != null) {	// 로그인 성공시
			session.setAttribute("memberInfo", memberInfo);
			response.sendRedirect(url);
		}else {
			out.println("<script>");
			out.println("alert('로그인에 실패했습니다.');");
			out.println("history.back();");			
			out.println("</script>");
		}
	}
}
