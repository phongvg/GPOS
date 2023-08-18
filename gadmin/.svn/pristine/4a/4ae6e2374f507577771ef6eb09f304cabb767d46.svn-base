<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="systemParameter.form.title"/></title>
    <meta name="menu" content="parameterMenu"/>
</head>

<div class="content">
 	<form:form  modelAttribute="systemParameter" method="post" id="systemParameterForm" action="${ctx}/system/systemParameter/save" role="form">
		<div class="card">
			<div class="card-header header-elements-inline">
				<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="systemParameter.form.title"/></span>
			</div>

			<div class="card-body">
				<p class="mb-4"><fmt:message key="systemParameter.form.desc"/></p>
                <form:hidden path="id" />
                <form:hidden path="version" />
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="param.legend"/></legend>
					<div class="row">
						<div class="form-group col-lg-12">
							<label class="col-form-label"><fmt:message key="systemParameter.paramName"/>:</label>
							<form:input path="paramName" class="form-control" type="text" maxlength="20000" readonly="true"/>
						</div>
						<div class="form-group col-lg-12">
							<label class="col-form-label"><fmt:message key="systemParameter.paramValue"/>:</label>
							<form:input path="paramValue" class="form-control" type="text" maxlength="20000" placeholder="Nhập giá trị cho tham số..."/>
						</div>
						<div class="form-group col-lg-12">
							<label class="col-form-label"><fmt:message key="systemParameter.description"/>:</label>
							<textarea rows="5" cols="5" class="form-control" name="description" placeholder="Nhập mô tả cho tham số..." maxlength="255">${systemParameter.description}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="d-flex justify-content-end align-items-center">
					<a href="${ctx}/system/systemParameter/list" type="button" class="btn btn-light" id="reset"><fmt:message key="button.back"/> <i class="icon-reload-alt ml-2"></i></a>
					<security:authorize access="hasAnyRole('ROLE_SYS_PARAM_EDIT,ROLE_ADMIN')">
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
					</security:authorize>
				</div>
			</div>
		</div>
	</form:form>
</div>




