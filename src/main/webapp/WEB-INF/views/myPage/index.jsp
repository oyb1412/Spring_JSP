<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myPage/style.css">

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!-- 사이드바 없이 중앙 정렬 -->
<div id="main-content" class="form-page">
  <div id="register-container">
    <h2>회원정보 수정</h2>
	  
    <form action="${pageContext.request.contextPath}/myPage-Modify" method="post">
      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      <div class="input-group">
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" value="${user.username}" readonly />
      </div>

      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password"/>
      </div>

      <div class="input-group">
        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" value="${user.writer}"/>
      </div>

      <button type="submit" class="register-button">수정</button>
    </form>

    
    <form action="${pageContext.request.contextPath}/" method="get">
      <button type="submit" class="register-button">취소</button>
    </form>
    
  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
