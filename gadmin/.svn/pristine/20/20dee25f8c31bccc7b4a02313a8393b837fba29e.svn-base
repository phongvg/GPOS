<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="change.password"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/fontawesome-all.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/iofrm-style.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/iofrm-theme3.css'/>">
</head>
<body>
    <div class="form-body" class="container-fluid">
        <div class="row">
            <div class="img-holder">
                <div class="bg"></div>
                <div class="info-holder">
                </div>
            </div>
            <div class="form-holder">
	      		<c:if test="${not empty error}">
	                  <div class="alert alert-danger alert-dismissible" role="alert" style="top: 0;margin-bottom: 0;width: 60%;position: fixed;z-index: 9999;">
	                      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                          <span aria-hidden="true">&times;</span>
	                      </button>
	                      <c:choose>
	                          <c:when test="${not empty error}">
	                              <fmt:message key="user.password.false" />
	                          </c:when>
	                      </c:choose>
	                  </div>
	              </c:if>
            
                <div class="form-content">
                	<c:url var="urlSubmit" value="/save-change-pass"></c:url>
                    <div class="form-items">
                    	<img src='<c:url value="https://ggg.com.vn/wp-content/themes/ggg-webportal/logo.png"/>' width="400px" style="margin-bottom: 25%">
                        <form method="post" id="loginForm" action="${urlSubmit}">
                        	<c:if test="${not empty check}">
	                        	<span><fmt:message key="login.password.old"/></span>
	                            <input class="form-control" type="password" name="passwordOld" placeholder="Nhập mật khẩu cũ" style="border-radius: 20px; width: 125%" autofocus="autofocus">
                        	</c:if>
                        	<span><fmt:message key="login.password"/></span>
                            <input id="password" class="form-control" type="password" name="password" placeholder="Nhập mật khẩu mới" required style="border-radius: 20px; width: 125%" autofocus="autofocus">
                            <span><fmt:message key="userForm.confirmPassword"/></span>
                            <input id= "confirmPassword" class="form-control" type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu mới" required style="border-radius: 20px; width: 125%">

                            <div class="form-button">
                                <button id="submit" type="submit" class="ibtn" style="background-color: indianred; width: 125%; border-radius: 50px; height: 50px;"><span><fmt:message key="change.password"/></span></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="<c:url value='/themes/login/js/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/popper.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/main.js'/>"></script>
	<script type="text/javascript">
		var password = document.getElementById("password");
		var confirm_password = document.getElementById("confirmPassword");
	
		function validatePassword(){
		  if(password.value != confirm_password.value) {
		    confirm_password.setCustomValidity("\u004d\u1ead\u0074 \u006b\u0068\u1ea9\u0075 \u006b\u0068\u00f4\u006e\u0067 \u0074\u0072\u00f9\u006e\u0067 "
		    									+"\u0076\u1edb\u0069 \u0078\u00e1\u0063 \u006e\u0068\u1ead\u006e \u006d\u1ead\u0074 \u006b\u0068\u1ea9\u0075\u0021");
			  } else {
			    confirm_password.setCustomValidity('');
			  }
			}
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>
</html>
