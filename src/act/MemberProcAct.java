package act;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import svc.*;
import vo.*;

public class MemberProcAct implements Action {
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      String wtype = request.getParameter("wtype");  // 가입(in), 수정(up), 탈퇴(del) 여부를 구분짓는 값
      MemberInfo memberInfo = new MemberInfo();;   	// 가입 및 수정할 회원의 데이터들을 저장할 인스턴스
      MemberAddr memberAddr = null;					// 주소정보로 회원 가입일 경우에만 필요한 인스턴스

      if (wtype.equals("in") || wtype.equals("up")) {
      // 현재 처리하는 작업이 회원 가입이나 정보 수정일 경우(사용자가 입력한 데이터들을 받아와서 memberInfo 인스턴스에 저장)
         memberInfo.setMi_phone(request.getParameter("p1") + "-" + 
               request.getParameter("p2").trim() + "-" + request.getParameter("p3").trim());
         memberInfo.setMi_email(request.getParameter("e1").trim() + "@" + request.getParameter("e3").trim());
         memberInfo.setMi_isad(request.getParameter("mi_isad"));
      }
      
      if (wtype.equals("in")) {
    	  	memberInfo.setMi_pw(request.getParameter("mi_pw").trim());
            memberInfo.setMi_id(request.getParameter("mi_id").trim().toLowerCase());
            memberInfo.setMi_name(request.getParameter("mi_name").trim());
            memberInfo.setMi_gender(request.getParameter("mi_gender"));
            memberInfo.setMi_birth(request.getParameter("by") + "-" + 
               request.getParameter("bm") + "-" + request.getParameter("bd"));
            
            memberAddr = new MemberAddr();
            memberAddr.setMa_name("기본 배송지");
            memberAddr.setMa_zip(request.getParameter("ma_zip"));
            memberAddr.setMa_addr1(request.getParameter("ma_addr1"));
            memberAddr.setMa_addr2(request.getParameter("ma_addr2"));
            
      } else if (wtype.equals("up") || wtype.equals("del")) {
            HttpSession session = request.getSession();
            MemberInfo mi = (MemberInfo)session.getAttribute("memberInfo");
            memberInfo.setMi_id(mi.getMi_id());
      }
      
      MemberProcSvc memberProcSvc = new MemberProcSvc();
      int result = memberProcSvc.memberProc(wtype, memberInfo, memberAddr);
      // 가입, 수정, 탈퇴를 처리할 메소드 호출(memberAddr은 수정과 탈퇴시 null로 가져감)
      
      String lnk = "../index.jsp";	// 이동할 위치
      if(result == 1) {	// 정상적으로 동작되었으면
    	  if (wtype.equals("in"))		lnk = "../login_form.jsp";
    	  else if (wtype.equals("up"))		lnk = "mypage.mem";
    	  else if (wtype.equals("del"))		lnk = "../logout.jsp";
      } else {
    	  response.setContentType("text/html; charset=utf-8");
    	  PrintWriter out = response.getWriter();
    	  out.println("<script>");
    	  out.println("alert('작업에 실패했습니다. 다시 시도해보십시오.')");
    	  out.println("history.back();");
    	  out.println("</script>");
    	  out.close();
      }
      
      // 작업 후 이동할 위치와 방법에 대해 지정하는 ActionForward 인스턴스 생성
      ActionForward forward = new ActionForward();	// 이동할 방법과 위치에 대해 지정. wtype에 따라 이동할 지점이 달라짐
      forward.setRedirect(true);
      forward.setPath(lnk);
      
      
      return forward;
   }
}