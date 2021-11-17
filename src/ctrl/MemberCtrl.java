package ctrl;

import java.io.*;
import javax.servlet.*; // RequestDispatcher ��ü ����� ���� import
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; // request, response, session ��ü�� ����ϱ� ���� import
import act.*;
import vo.*;

@WebServlet("*.mem")
public class MemberCtrl extends HttpServlet {
	// ȸ�� ���� �۾�(����, ��������, Ż��)�� ���� ���� ���� ���� ������ ����ϴ� ��Ʈ�ѷ�
	private static final long serialVersionUID = 1L;

	public MemberCtrl() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get�� post �� ���� ����� ��û�� ó���ϴ� �޼ҵ�
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
			// ó�� �� ���� �� �̵��� ��ο� ����� �޾ƿ�
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
