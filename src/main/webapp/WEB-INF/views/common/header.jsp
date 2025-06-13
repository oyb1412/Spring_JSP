<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>오윤범의 홈페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/header.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/footer.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/sidebar.css">

</head>
<body>

  <div id="header">
    <div class="title" onclick="location.href='${pageContext.request.contextPath}/'">오윤범의 홈페이지</div>
  </div>

  <hr class="divider">

  <c:if test="${not empty _csrf}">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </c:if>

