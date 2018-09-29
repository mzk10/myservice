<%--
  Created by IntelliJ IDEA.
  User: mzk10
  Date: 2018/9/28
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>showlog</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function isdelete() {
            var isdelete = confirm("确认要删除吗？");
            if (isdelete === true){
                document.getElementById("deletesubmit").submit();
            }
        }
    </script>
    <style type="text/css">
        table#content{
            border-top: 1px solid #dddddd;
            border-left: 1px solid #dddddd;
        }
        table#content tr td.delete{
            border-bottom: 1px solid #dddddd;
        }
        table#content tr td.time{
            border-bottom: 1px solid #dddddd;
        }
        table#content tr td.content{
            border-bottom: 1px solid #dddddd;
            border-right: 1px solid #dddddd;
        }
        table#content tr td p.normal{
            font-size: small; color: #666666;
        }
        table#content tr td .error{
            font-size: small; color: #ff0000;
        }
    </style>
</head>
<body>
<form action="test?app=showlog" method="post">
    <table align="center">
        <tr>
            <td>
                <select name="devicesname">
                    <option value="">请选择</option>
                    <c:forEach items="${devices}" var="dev">
                        <c:if test="${dev.selected == 1}">
                            <option value="${dev.devices}" selected="selected">${dev.devices}</option>
                        </c:if>
                        <c:if test="${dev.selected == 0}">
                            <option value="${dev.devices}">${dev.devices}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="submit" value="查看">
            </td>
        </tr>
    </table>
</form>
<form id="deletesubmit" action="test?app=showlog" method="post">
    <table id="content" align="center" width="100%" cellspacing="0" cellpadding="1">
        <c:if test="${not empty devicesname}">
            <tr>
                <td colspan="2" align="center" class="delete">
                    <input type="hidden" name="del_devices" value="${devicesname}"/>
                    <input onclick="isdelete();" type="button" value="删除"/>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${logs}" var="log">
            <tr>
                <td valign="top" class="time">
                    <c:if test="${log.type == 0}">
                        <p class="normal">${log.time}:</p>
                    </c:if>
                    <c:if test="${log.type == 1}">
                        <p class="error">${log.time}:</p>
                    </c:if>
                </td>
                <td align="left" class="content">
                    <c:if test="${log.type == 0}">
                        <p class="normal">${log.log}</p>
                    </c:if>
                    <c:if test="${log.type == 1}">
                        <p class="error">${log.log}</p>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
