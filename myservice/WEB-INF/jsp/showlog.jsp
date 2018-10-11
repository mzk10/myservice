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
            if (isdelete === true) {
                document.getElementById("deletesubmit").submit();
            }
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#selecter").change(function () {
                $("#showlogsubmit").submit();
            });
        });
    </script>
    <style type="text/css">
        body {
            padding: 0;
            margin: 0;
            background-color: #333333;
        }

        table{
            word-break:break-all;
        }

        /*普通*/
        table#content tr td.td_normaltime {
            background-color: beige;
            min-width: 150px;
            max-width: 160px;
            text-align: right;
        }

        table#content tr td.td_normalcontent {
            background-color: cadetblue;
        }

        table#content tr td p.p_normaltime {
            font-size: small;
            color: #666666;
        }

        table#content tr td p.p_normalcontent {
            font-size: small;
            color: #eeeeee;
            word-wrap: break-word;
        }

        /*错误*/
        table#content tr td.td_errortime {
            background-color: #333333;
            min-width: 150px;
            max-width: 160px;
            text-align: right;
        }

        table#content tr td.td_errorcontent {
            background-color: #333333;
        }

        table#content tr td p.p_errortime {
            font-size: small;
            /*color: #dc143c;*/
            color: #ff3333;
        }

        table#content tr td p.p_errorcontent {
            font-size: small;
            color: #ff3333;
        }
        #tip{
            color: #eeeeee;
        }
        form{
            margin: 0;
        }
    </style>
</head>
<body>
<form id="showlogsubmit" action="test?app=showlog" method="post">
    <table align="center">
        <tr>
            <td>
                <select id="selecter" name="devicesname">
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
        </tr>
    </table>
</form>
<form id="deletesubmit" action="test?app=showlog" method="post">
    <table id="content" align="center" width="100%" cellspacing="0" cellpadding="2">
        <c:if test="${not empty devicesname}">
            <tr>
                <td colspan="2" align="center" class="delete">
                    <input type="hidden" name="del_devices" value="${devicesname}"/>
                    <input onclick="isdelete();" type="button" value="删除该设备记录"/>
                </td>
            </tr>
            <c:forEach items="${logs}" var="log">
                <tr>
                    <c:if test="${log.type == 0}">
                        <td valign="top" class="td_normaltime">
                            <p class="p_normaltime">${log.time}:</p>
                        </td>
                        <td align="left" class="td_normalcontent">
                            <p class="p_normalcontent">${log.log}</p>
                        </td>
                    </c:if>
                    <c:if test="${log.type == 1}">
                        <td valign="top" class="td_errortime">
                            <p class="p_errortime">${log.time}:</p>
                        </td>
                        <td align="left" class="td_errorcontent">
                            <p class="p_errorcontent">${log.log}</p>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty devicesname}">
            <tr>
                <td align="center">
                    <big><big><big id="tip">选择设备</big></big></big>
                </td>
            </tr>
        </c:if>
    </table>
</form>
</body>
</html>
