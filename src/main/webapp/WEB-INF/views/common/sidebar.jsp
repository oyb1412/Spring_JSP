<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sidebar">
  <div id="login-box">
    <c:choose>
      <c:when test="${isAuthenticated}">
        <p style="text-align:center; font-size: 15px; margin-bottom: 10px;">${username}님</p>
        <a href="${pageContext.request.contextPath}/logout" class="sidebar-btn">로그아웃</a>
        <a href="${pageContext.request.contextPath}/my-page" class="sidebar-btn">마이페이지</a>
        <c:if test="${ADMIN}">
           <a href="${pageContext.request.contextPath}/admin-page" class="sidebar-btn">유저 관리</a>
        </c:if>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login-page" class="sidebar-btn">로그인</a>
        <a href="${pageContext.request.contextPath}/register-page" class="sidebar-btn">회원가입</a>
        <a href="${pageContext.request.contextPath}/find-password-page" class="sidebar-btn">비밀번호 찾기</a>
      </c:otherwise>
    </c:choose>
  </div>

  <div id="menu-box">
    <a href="${pageContext.request.contextPath}/">공지사항</a>
    <a href="${pageContext.request.contextPath}/board-list-page">자유게시판</a>
    <a href="${pageContext.request.contextPath}/resume-board-check-page" class="resume">📄이력서</a>
    <a href="${pageContext.request.contextPath}/projects-board-list-page" class="projects">💻프로젝트</a>
  </div>
</div>

<c:if test="${not empty result}">
  <script>
    alert("${result}");
  </script>
</c:if>

<c:if test="${param.error == 'true'}">
    <script>
    alert("아이디 혹은 비밀번호가 올바르지 않습니다");
  </script>
    </c:if>

    <c:if test="${param.error == 'ban'}">
    <script>
    alert("운영자에게 정지당한 계정입니다");
  </script>
    </c:if>
	<c:if test="${not empty error}">
   <script>
    alert(`${error}`);
  </script>
	</c:if>