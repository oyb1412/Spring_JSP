<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register/style.css">

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!-- 사이드바 없이 중앙 정렬 -->
<div id="main-content" class="form-page">
  <div id="register-container">
    <h2>회원가입</h2>

	   

    <form action="${pageContext.request.contextPath}/register" method="post">
      <input type="hidden" name="_csrf" value="${_csrf.token}" />

      <div class="input-group">
        <label for="username">아이디</label>
        <input type="text" id="username" name="username" required />
      </div>

      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required />
      </div>

      <div class="input-group">
        <label for="writer">닉네임</label>
        <input type="text" id="writer" name="writer" required />
      </div>

      <button type="submit" class="register-button">회원가입</button>
    </form>

    <div class="login-link">
      <a href="${pageContext.request.contextPath}/login-page">이미 계정이 있으신가요?</a>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<c:if test="${not empty result}">
  <script>
    alert("${result}");
  </script>
</c:if>