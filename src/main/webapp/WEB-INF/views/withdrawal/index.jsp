<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/withdrawal/style.css">

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<!-- 사이드바 없이 중앙 정렬 -->
<div id="main-content" class="form-page">
  <div id="register-container">
    <h2>회원 탈퇴</h2>
	  
      <h3>정말 탈퇴하시겠습니까? </h3>
    
    <form action="${pageContext.request.contextPath}/withdrawal" method="post">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      <button type="submit" class="register-button">탈퇴</button>
    </form>

    <form action="${pageContext.request.contextPath}/" method="get">
      <button type="submit" class="register-button">취소</button>
    </form>

  </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
