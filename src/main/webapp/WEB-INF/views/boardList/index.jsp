<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boardList/style.css">

<div id="container">
  <div id="boardAdmin">
    <h2 id="boardAdminH2">자유게시판</h2>
    <c:if test="${isAuthenticated}">
      <button type="button" onclick="location.href='${pageContext.request.contextPath}/board-add-page'">작성</button>
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
      <c:forEach var="board" items="${boardList}">
        <tr onclick="location.href='${pageContext.request.contextPath}/board-check-page?idx=${board.idx}'" style="cursor:pointer;">
          <td>${board.idx}</td>
          <td>${board.title}</td>
          <td>${board.writer}</td>
          <td>${board.indate}</td>
          <td>${board.viewCount}</td>
          <td>${board.commentCount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

<div id="searchBox">
  <form action="${pageContext.request.contextPath}/board-list-page" method="get" style="display: flex; justify-content: center; align-items: center; margin-top: 20px; gap: 10px;">
    <select name="searchType">
      <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
      <option value="content" ${param.searchType == 'content' ? 'selected' : ''}>내용</option>
      <option value="writer" ${param.searchType == 'writer' ? 'selected' : ''}>글쓴이</option>
      <option value="title_content" ${param.searchType == 'title_content' ? 'selected' : ''}>제목+내용</option>
    </select>
    <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요" />
    <button type="submit">🔍</button>
  </form>
</div>


  <div class="pagination">

  <c:if test="${currentBoardPage > 1}">
    <a href="${pageContext.request.contextPath}/board-list-page?page=${currentBoardPage - 1}&searchType=${param.searchType}&keyword=${param.keyword}">이전</a>
  </c:if>

  <c:forEach begin="1" end="${totalBoardPage}" var="i">
    <a href="${pageContext.request.contextPath}/board-list-page?page=${i}&searchType=${param.searchType}&keyword=${param.keyword}"
       style="${i == currentBoardPage ? 'font-weight: bold;' : ''}">${i}</a>
  </c:forEach>

  <c:if test="${currentBoardPage < totalBoardPage}">
    <a href="${pageContext.request.contextPath}/board-list-page?page=${currentBoardPage + 1}&searchType=${param.searchType}&keyword=${param.keyword}">다음</a>
  </c:if>

</div>

<%@ include file="../common/footer.jsp" %>
