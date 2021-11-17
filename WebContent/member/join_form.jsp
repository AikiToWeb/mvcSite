<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
if (session.getAttribute("memberInfo") != null) {
	out.println("<script>");
	out.println("alert('잘못된 경로로 들어오셨습니다.');");
	out.println("history.back();");
	out.println("</script>");
	out.close();	// jsp 아래쪽 부분 무시하고 끝내버림
}

Calendar today = Calendar.getInstance();
int year = today.get(Calendar.YEAR);		// 올해 연도
int month = today.get(Calendar.MONTH) + 1;	// 현재 월
int day = today.get(Calendar.DATE);			// 현재 일

String[] arrDomain = {"naver.com", "nate.com", "google.com", "naver.com", "yahoo.com"}; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>join_form</title>
</head>
<body>
<h2>회원가입 폼</h2>
<form name="frmJoin" action="member_in_proc.mem" method="post">
<input type="hidden" name="wtype" value="in" />
<div id="agreement" style="width:700px; height:100px; overflow:auto;">
약관내용<br />약관내용<br />약관내용<br />약관내용<br />약관내용<br />약관내용<br />
약관내용<br />약관내용<br />약관내용<br />약관내용<br />약관내용<br />약관내용<br />
</div>
<p style="width:700px; text-align:center;">
	<input type="radio" name="agree" value="y" id="agreeY" /><label for="agreeY">동의</label>
	<input type="radio" name="agree" value="n" id="agreeN" /><label for="agreeN">동의 안 함</label>
</p><br />
<table width="700" cellpadding="5" id="joinBrd">
<tr>
<th width="20%">아이디</th>
<td width="*">
	<input type="text" name="mi_id" />
</td>
</tr>
<tr><th>비밀번호</th><td><input type="password" name="mi_pw" /></td></tr>
<tr><th>비밀번호 확인</th><td><input type="password" name="mi_pw2" /></td></tr>
<tr><th>이름</th><td><input type="text" name="mi_name" /></td></tr>
<tr>
<th>성별</th>
<td>
	<input type="radio" name="mi_gender" value="m" id="genderM" /><label for="genderM">남자</label>
	<input type="radio" name="mi_gender" value="m" id="genderF" checked="checked" /><label for="genderF">여자</label>
</td>
</tr>
<tr>
<th>생년월일</th>
<td>
	<select name="by">
	<%for(int i = 1950; i<= year; i++) { %>
		<option value="<%=i %>" <% if (i == year) { %>selected="selected"<% } %>><%=i %></option>
	<%} %>
	</select>년
	<select name="bm">
	<%for(int i = 1; i<= 12; i++) { %>
		<option value="<%=i < 10 ? "0" + i : i %>" <% if (i == month) { %>selected="selected"<% } %>><%=i %></option>
	<%} %>
	</select>월
	<select name="bd">
	<%for(int i = 1; i<= 31; i++) { %>
		<option value="<%=i < 10 ? "0" + i : i %>" <% if (i == day) { %>selected="selected"<% } %>><%=i %></option>
	<%} %>
	</select>일
</td>
</tr>
<tr>
<th>전화번호</th>
<td>
	<select name="p1">
		<option value="010">010</option>
		<option value="010">011</option>
		<option value="010">016</option>
		<option value="010">019</option>
	</select> - 
	<input type="text" name="p2" size="4" maxlength="4" /> -
	<input type="text" name="p3" size="4" maxlength="4" />
</td>
</tr>
</table>

</form>

</body>
</html>