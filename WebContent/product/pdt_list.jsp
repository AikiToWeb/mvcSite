<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ include file="../_inc/incHead.jsp" %>
<%
request.setCharacterEncoding("utf-8");

ArrayList<ProductInfo> pdtList = (ArrayList<ProductInfo>)request.getAttribute("pdtList");
ArrayList<PdtCataBig> cataBigList = (ArrayList<PdtCataBig>)request.getAttribute("cataBigList");
ArrayList<PdtCataSmall> cataSmallList = (ArrayList<PdtCataSmall>)request.getAttribute("cataSmallList");
ArrayList<PdtBrandInfo> brandInfoList = (ArrayList<PdtBrandInfo>)request.getAttribute("brandInfoList");
PdtPageInfo pdtPageInfo = (PdtPageInfo)request.getAttribute("pdtPageInfo");

String args = "", schargs = "";
// 검색관련 쿼리스트링 제작
if (pdtPageInfo.getKeyword() != null && !pdtPageInfo.getKeyword().equals(""))   schargs += "&keyword=" +pdtPageInfo.getKeyword();

if (pdtPageInfo.getBcata() != null && !pdtPageInfo.getBcata().equals(""))      schargs += "&bcata=" + pdtPageInfo.getBcata();

if (pdtPageInfo.getScata() != null && !pdtPageInfo.getScata().equals(""))      schargs += "&scata=" + pdtPageInfo.getScata();

if (pdtPageInfo.getBrand() != null && !pdtPageInfo.getBrand().equals(""))      schargs += "&brand=" + pdtPageInfo.getBrand();

if (pdtPageInfo.getSprice() != null && !pdtPageInfo.getSprice().equals(""))      schargs += "&sprice=" + pdtPageInfo.getSprice();

if (pdtPageInfo.getEprice() != null && !pdtPageInfo.getEprice().equals(""))      schargs += "&eprice=" + pdtPageInfo.getEprice();

args = "?cpage=" + pdtPageInfo.getCpage() + schargs;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상품 목록</h2>
<table width="800" cellpadding="5">
<%
if (pdtList.size() > 0 ) {
// 상품 검색결과가 있으면
   for (int i = 0 ; i < pdtList.size() ; i++) {
      ProductInfo pi = pdtList.get(i);
      String lnk = null;
      if (pi.getPi_stock() != 0) {   // 상품의 재고가 남아 있는 경우
         lnk = "<a href=\"pdt_view.pdt" + args + "&piid=" + pi.getPi_id() + 
            "&sort=" + pdtPageInfo.getSort() + "&psize=" + pdtPageInfo.getPsize() + "\">";
      } else {   // 상품의 재고가 없는 품절일 경우
         lnk = "<a href=\"javasript:alert('품절된 상품입니다.');\">";
      }
      
      if (pdtPageInfo.getPsize() == 8) {
      // 한 페이지에 12개의 상품 목록을 보여줄 경우 (한 줄에 4개씩 보여주기)
         if (i % 4 == 0)      out.println("<tr align='center'>");
%>
<td width="25%">
   <%=lnk %><img src="product/pdt_img/<%=pi.getPi_img1() %>" width="180" height="200" /><br />
   <%=pi.getPi_name() %></a><br /><%=pi.getPi_price() %> 원
</td>
<%
         if (i % 4 == 3)      out.println("</tr>");
      } else {
      // 한 페이지에 10개의 상품 목록을 보여줄 경우 (한 줄에 1개씩 보여주기)
%>
<tr>
<td width="150" align="center"><%=lnk %><img src="product/pdt_img/<%=pi.getPi_img1() %>" width="110" height="130" /></a></td>
<td width="*"><%=lnk + pi.getPi_name() %></a><br /><%=pi.getPb_name() %></td>
<td width="100"><strong><%=pi.getPi_price() %></strong> 원</td>
</tr>   
<%      
      }
   }
} else {
   out.println("<tr><th>검색된 상품이 없습니다.</th></tr>");
}
%>
</table>
<%
if (pdtList.size() > 0 ) {
// 상품 검색 결과가 있으면 페이지 번호를 출력
   out.println("<p style='width:800px;' align='center'>");   
   args = "?sort=" + pdtPageInfo.getSort() + "&psize=" + pdtPageInfo.getPsize() + schargs;   
   if (pdtPageInfo.getCpage() == 1) {
      out.println("[&lt;&lt;]&nbsp;&nbsp;[&lt;]&nbsp;&nbsp;");
   } else {
      out.print("<a href='pdt_list.pdt" + args + "&cpage=1'>[&lt;&lt;]</a>&nbsp;&nbsp;");
      out.println("<a href='pdt_list.pdt" + args + "&cpage=" + (pdtPageInfo.getCpage() - 1) + "'>[&lt;]</a>&nbsp;&nbsp;");
   }   // 첫  페이지와 이전 페이지 링크
   
   for (int i = 1, j = pdtPageInfo.getSpage() ; i <= pdtPageInfo.getBsize() && j <= pdtPageInfo.getEpage() ; i++, j++) {
      if (pdtPageInfo.getCpage() == j) {
         out.print("&nbsp;<strong>" + j + "</strong>&nbsp;");
      } else {
         out.print("&nbsp;<a href='pdt_list.pdt" + args + "&cpage=" + j + "'>" + j + "</a>&nbsp;");
      }
   }
   
   if (pdtPageInfo.getCpage() == pdtPageInfo.getPcnt()) {
      out.println("[&lt;&lt;][&gt;]&nbsp;&nbsp;[&gt;&gt;]");
   } else {
      out.print("&nbsp;&nbsp;<a href='pdt_list.pdt" + args + "&cpage=" + (pdtPageInfo.getCpage() + 1) + "'>[&gt;]</a>");
      out.println("&nbsp;&nbsp;<a href='pdt_list.pdt" + args + "&cpage=" + (pdtPageInfo.getPcnt()) + "'>[&gt;&gt;]</a>");
   }
   out.println("</p>");
}
%>
</body>
</html>