<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %><!-- 로그인 정보를 담은 MemberInfo인스턴스를 사용하기 위해 import  -->
<%
MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
if (memberInfo != null) {	// 로그인이 되어있다면 들어올 이유가 없으니까 튕겨내야 함
	out.println("<script>");
	out.println("alert('잘못된 경로로 들어오셨습니다.');");
	out.println("history.back();");
	out.println("</script>");
	out.close();	// jsp 아래쪽 부분 무시하고 끝내버림
}

request.setCharacterEncoding("utf-8");
String url = request.getParameter("url");
if (url == null)	url = "";
// 로그인 후 이동할 페이지 주소로, 이동할 페이지 주소가 없는 경우 빈 문자열로 지정	=> 여기에서 쓸 값이 아니기 때문에 로그인 처리할 곳으로 값을 보내야 하는데 url = ""안 하면 NULL 찍힘 	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그인 폼</h2>
<form name="frmLogin" action="login" method="post">	<!-- LoginCtrl로 가서 작업하쇼 -->
<input type="hidden" name="url" value=<%=url %> />
<label for="uid">아이디 : </label><input type="text" name="uid" value="test1"><br />		<!-- 테스트할 때마다 입력하기 귀찮으니까 value로 미리 입력해둠-->
<label for="pwd">비밀번호 : </label><input type="password" name="pwd" value="1111"><br />	<!-- 테스트할 때마다 입력하기 귀찮으니까 value로 미리 입력해둠-->
<input type="submit" value="로그인" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" value="다시 입력" />
</form>
</body>
</html>