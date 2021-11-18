package ctrl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;


@WebServlet("/dupId")
public class DupIdCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DupIdCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		response.setContentType("text/html; charset=utf-8");	// 사용자에게 응답 내용을 글자, html 캐릭터셋은 utf-8로 해서 보내겠다.
		PrintWriter out = response.getWriter();
		
		try {
			DupIdSvc dupIdSvc = new DupIdSvc();
			int result = dupIdSvc.chkDupId(uid);
			out.println(result);	// 결과값을 호출했던 자바스크립트 함수로 리턴시킴	
		} catch (Exception e) {
			e.printStackTrace();
			out.println(1);			// 사용할 수 없는 아이디
		}
		
	}

}
