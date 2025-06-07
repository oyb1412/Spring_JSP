<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardAdd/style.css">
<form action="${pageContext.request.contextPath}/board-add" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="boardType" value="FREE" />

    <div id="container">
        <div id="boardAdmin">
            <h2 id="boardAdminH2">글 작성</h2>

            <label for="memID">회원아이디</label>
            <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${username}" readonly>

            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목" maxlength="10">

            <label for="content">내용</label>
            <textarea id="content" name="content" placeholder="내용을 입력하세요"
                    rows="5" cols="40" maxlength="1000"></textarea>

            <label for="writer">작성자</label>
            <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${writer}" readonly>

            <input type="hidden" id="indate" name="indate" value="${today}">

            <button id="buttonSubmit" type="submit">확인</button>
        </div>
    </div>
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.css" rel="stylesheet">
<script>
  $(document).ready(function () {
    $('#content').summernote({
      height: 300,
      toolbar: [
         ['style', ['bold', 'underline', 'clear']],              
        ['font', ['fontsize', 'color']],                        
        ['para', ['ul', 'ol', 'paragraph']],                    
        ['insert', ['link']],                                   
        ['misc', ['undo', 'redo']]                             
      ]
    });
  });
</script>
