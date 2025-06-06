<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/noticeList/style.css">

<div id="container">
  <div id="boardAdmin">
    <h2 id="boardAdminH2">공지사항</h2>
    <c:if test="${ADMIN == true || MANAGER == true}">
      <button type="button" onclick="location.href='${pageContext.request.contextPath}/notice-add-page'">작성</button>
    </c:if>
  </div>

  <table border="1" style="width: 100%; text-align: center; margin-top: 10px;">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>등록일</th>
        <th>조회</th>
        <th>댓글</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="notice" items="${noticeList}">
        <tr onclick="location.href='${pageContext.request.contextPath}/notice-check-page?idx=${notice.idx}'" style="cursor:pointer;">
          <td>${notice.idx}</td>
          <td>${notice.title}</td>
          <td>${notice.writer}</td>
          <td>${notice.indate}</td>
          <td>${notice.viewCount}</td>
          <td>${notice.commentCount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <div class="pagination">
    <c:if test="${currentNoticePage > 1}">
      <a href="?page=${currentNoticePage - 1}">이전</a>
    </c:if>
    <c:forEach begin="1" end="${totalNoticePage}" var="i">
      <a href="?page=${i}" style="${i == currentNoticePage ? 'font-weight: bold;' : ''}">${i}</a>
    </c:forEach>
    <c:if test="${currentNoticePage < totalNoticePage}">
      <a href="?page=${currentNoticePage + 1}">다음</a>
    </c:if>
  </div>
</div>

<%@ include file="../common/footer.jsp" %>
