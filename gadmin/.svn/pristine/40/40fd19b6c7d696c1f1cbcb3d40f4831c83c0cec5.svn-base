<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/bootstrap.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/fontawesome-all.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/iofrm-style.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/themes/login/css/iofrm-theme3.css'/>">
</head>
<body>
    <div class="form-body" class="container-fluid">
    	<!--
        <div class="website-logo">
            <a href="index.html">
                <div class="logo">
                    <img class="logo-size" src="images/logo-light.svg" alt="">
                </div>
            </a>
        </div>
        -->
        <div class="row">
            <div class="img-holder">
                <div class="bg"></div>
                <div class="info-holder">
                </div>
            </div>
            <div class="form-holder">
	              <c:if test="${param.error != null}">
	                  <div class="alert alert-danger alert-dismissible" role="alert" style="top: 0;margin-bottom: 0;width: 60%;position: fixed;z-index: 9999;">
	                      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                          <span aria-hidden="true">&times;</span>
	                      </button>
	                      <c:choose>
	                          <c:when test="${param.error == 'authenticationFailure'}">
	                              <fmt:message key="errors.password.mismatch" />
	                          </c:when>
	                          <c:when test="${param.error == 'maximumSessions'}">
	                              <fmt:message key="errors.maximum.sessions" />
	                          </c:when>
	                          <c:when test="${param.error == 'loginAttempts'}">
	                              <fmt:message key="errors.login.attempts">
	                                  <fmt:param value="${param.fail}" />
	                                  <fmt:param value="${param.limit}" />
	                              </fmt:message>
	                          </c:when>
	                          <c:when test="${param.error == 'locked'}">
	                              <fmt:message key="errors.user.locked" />
	                          </c:when>
	                          <c:when test="${param.error == 'disabled'}">
	                              <fmt:message key="errors.user.disabled" />
	                          </c:when>
	                          <c:when test="${param.error == 'profileFailure'}">
	                              <fmt:message key="errors.profile.inactive" />
	                          </c:when>
	                          <c:otherwise>
	                              <fmt:message key="errors.user.notfound" />
	                          </c:otherwise>
	                      </c:choose>
	                  </div>
	              </c:if>
            
                <div class="form-content">
                    <div class="form-items">
                    	<img src='<c:url value="/themes/admin/global_assets/images/backgrounds/logo.png"/>' width="400px" style="margin-bottom: 25%">
                    	<%--
                        <h3>Get more things done with Loggin platform.</h3>
                        <p>Access to the most powerfull tool in the entire design and web industry.</p>
                        --%>
                        <%-- <div class="page-links">
                            <a href="#" class="active"><fmt:message key="login.signin"/></a> <a href="register1.html">Register</a>
                        </div> --%>
                        <form method="post" id="loginForm" action="<c:url value='/login'/>">
                        	<span><fmt:message key="login.username"/></span>
                            <input class="form-control" type="text" name="username" placeholder="Username" required style="border-radius: 20px; width: 125%" autofocus="autofocus">
                            <span><fmt:message key="login.password"/></span>
                            <input class="form-control" type="password" name="password" placeholder="Password" required style="border-radius: 20px; width: 125%">

                            <div class="form-button">
                                <button id="submit" type="submit" class="ibtn" style="background-color: indianred; width: 125%; border-radius: 50px; height: 50px;">Login</button>
                            </div>
                        </form>
                        <div class="other-links">
                            <%-- <span>Or login with</span><a href="#">Facebook</a><a href="#">Google</a><a href="#">Linkedin</a> --%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="<c:url value='/themes/login/js/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/popper.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/themes/login/js/main.js'/>"></script>
</body>
</html>
