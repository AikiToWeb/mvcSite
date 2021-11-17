package ctrl;

import java.io.*;
import javax.servlet.*; // RequestDispatcher 객체 사용을 위한 import
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; // request, response, session 객체를 사용하기 위해 import
import act.*;
import vo.*;

@WebServlet("*.mem")
public class MemberCtrl extends HttpServlet {
	// 회원 관련 작업(가입, 정보수정, 탈퇴)과 정보 수정 폼에 대해 연결을 담당하는 컨트롤러
	private static final long serialVersionUID = 1L;

	public MemberCtrl() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get과 post 두 가지 모두의 요청을 처리하는 메소드
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath(); // mvcBoard
		String command = requestUri.substring(contextPath.length()); //

		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/member/member_proc.mem":
			action = MemberProcAct();
			break;
		case "/member/mypage.mem":
			break;
		}

		try {
			forward = action.execute(request, response);
			// 처리 및 실행 후 이동할 경로와 방법을 받아옴
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
