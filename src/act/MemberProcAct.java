package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class MemberProcAct implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String wtype = request.getParameter("wtype");	// 가입(in), 수정(up), 탈퇴(del) 여부를 구분짓는 값
		MemberInfo memberInfo = null;	// 가입 및 수정할 회원 데이터를 저장하는 인스턴스
		
		if(wtype.equals("in") || wtype.equals("up")) {
		// 현재 처리하는 작업이 회원가입이나 정보 수정일 경우(사용자가 입력한 데이터들을 받아와서 memberInfo 인스턴스에 저장)
			memberInfo = new MemberInfo();
			if(wtype.equals("in")) {
				memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
				memberInfo.setMi_name(request.getParameter("mi_name").trim());
				memberInfo.setMi_gender(request.getParameter("mi_gender"));
				memberInfo.setMi_birth(request.getParameter("by") + "-" + request.getParameter("bm") + "-" + request.getParameter("bd"));
			} else {	// jsp 파일 아니라 세션이 없어서 만들어야 함
				HttpSession session = request.getSession();
				MemberInfo mi = (MemberInfo)session.getAttribute("memberInfo");
				memberInfo.setMi_id(mi.getMi_id());
			}
			// 수정 여부와 상관 없이 가져가야 할 정보들
			memberInfo.setMi_phone(request.getParameter("p1").trim() + request.getParameter("p2").trim() + request.getParameter("p3").trim());	// 전화번호
		}
		
		ActionForward forward = new ActionForward();
		
		return forward;
	}
}