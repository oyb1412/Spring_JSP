<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/style.css">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

<div id="main-content">
  <div id="login-container">
    <h2>로그인</h2>
    
    
    <form action="${pageContext.request.contextPath}/login" method="post">
      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      
      <div class="input-group">
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" required />
      </div>

      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required />
      </div>

      <button type="submit" id="login-button">로그인</button>
    </form>

    <div id="register-link">
      <a href="${pageContext.request.contextPath}/register-page">회원가입하시겠습니까?</a>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
