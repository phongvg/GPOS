<%--
  Created by IntelliJ IDEA.
  User: tiendat
  Date: 04/07/2023
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="audit.log.title"/></title>
    <meta name="menu" content="auditLogMenu"/>

    <!-- Theme JS files -->
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/ui/moment/moment.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/daterangepicker.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/anytime.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/pickadate/picker.date.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/audit_log_list.js'/>"></script>
</head>

<div class="content">
    <form:form id="auditForm" action="${ctx}/audit-log/list" method="post" modelAttribute="criteria" enctype="multipart/form-data">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="input-group col-md-6 offset-md-2">
                        <div class="input-group">
							<span class="input-group-prepend">
								<span class="input-group-text"><i class="icon-calendar22"></i></span>
							</span>
                            <input type="text" class="form-control daterange-basic" name="keyword" value="${criteria.keyword }" id="modifiedDate">
                        </div>
                    </div>
                    <div class="col-md-3"><button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button></div>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header header-elements-inline">
                <span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="audit.log.title"/></span>
            </div>
            <div class="card-body"><fmt:message key="audit.log.title.description"/></div>
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr class="table-success text-left">
                        <th style="width: 5%">#</th>
                        <th style="width: 20%"><fmt:message key="audit.action" /></th>
                        <th style="width: 20%"><fmt:message key="audit.modifedBy" /></th>
                        <th style="width: 20%"><fmt:message key="so.modifedDate"/></th>
                        <th style="width: 5%" class="text-center"><i class="icon-so-open2"></i></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty page.content}">
                        <tr>
                            <td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
                        </tr>
                    </c:if>
                    <c:forEach items="${page.content}" var="audit" varStatus="cnt">
                        <tr class="text-left">
                            <td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
                            <td><c:out value="${audit.action}"></c:out></td>
                            <td><c:out value="${audit.modifiedBy }"></c:out></td>
                            <td><c:out value="${audit.displayModifiedDate}"/></td>
                            <td class="text-left">
                                <div class="list-icons">
                                    <a href="javascript(0);" data-target="#show_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal" data-container="body"><fmt:message key="button.detail"/></a>
                                    <div class="modal fade" id="show_${cnt.index}" role="dialog">
                                        <div class="modal-dialog modal-md">
                                            <div class="modal-content">
                                                <div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
                                                    <h4 class="modal-title"><fmt:message key="button.detail"/></h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>${audit.content}</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.back"/></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Pagination -->
            <jsp:include page="/themes/admin/common/pagination.jsp">
                <jsp:param value="${page.number}" name="pageNumber"/>
                <jsp:param value="${page.totalPages}" name="maxPages"/>
                <jsp:param value="${page.size}" name="size"/>
                <jsp:param value="${page.totalElements}" name="totalElements"/>
            </jsp:include>
            <!-- /Pagination -->
        </div>
    </form:form>
</div>
<script>
    function searchFunction(){
        $('#page').val(0);
    }
</script>
