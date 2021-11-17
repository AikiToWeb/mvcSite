package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class MemberProcAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String wtype = request.getParameter("wtype");	// ����(in), ����(up), Ż��(del) ���θ� �������� ��
		MemberInfo memberInfo = null;	// ���� �� ������ ȸ�� �����͸� �����ϴ� �ν��Ͻ�
		
		if(wtype.equals("in") || wtype.equals("up")) {
		// ���� ó���ϴ� �۾��� ȸ�������̳� ���� ������ ���(����ڰ� �Է��� �����͵��� �޾ƿͼ� memberInfo �ν��Ͻ��� ����)
			memberInfo = new MemberInfo();
			if(wtype.equals("in")) {
				memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
				memberInfo.setMi_name(request.getParameter("mi_name").trim());
				memberInfo.setMi_gender(request.getParameter("mi_gender"));
				memberInfo.setMi_birth(request.getParameter("by") + "-" + request.getParameter("bm") + "-" + request.getParameter("bd"));
			} else {	// jsp ���� �ƴ϶� ������ ��� ������ ��
				HttpSession session = request.getSession();
				MemberInfo mi = (MemberInfo)session.getAttribute("memberInfo");
				memberInfo.setMi_id(mi.getMi_id());
			}
			// ���� ���ο� ��� ���� �������� �� ������
			memberInfo.setMi_phone(request.getParameter("p1").trim() + request.getParameter("p2").trim() + request.getParameter("p3").trim());	// ��ȭ��ȣ
		}
		
		ActionForward forward = new ActionForward();
		
		return forward;
	}
}