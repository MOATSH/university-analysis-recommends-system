<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="/UARS/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UARS-UserManagement</title>
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="D_bg.css">

    <script src="js/jquery.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            $("button.delete-btn").click(function() {

                var parent = $(this).parent().parent();
                var name = parent.find(".userInfo").find(".username")

                $(location).attr("href", "/UARS/userServlet?action=deleteUser&username=" + name.text())

            })

            $("button.modify-btn").click(function() {

                var parent = $(this).parent().parent();
                var name = parent.find(".userInfo").find(".username")
                var username = name.text();
                $("#username").val(username);
                const modifiedDialog = document.getElementById('modified-dialog');
                modifiedDialog.style.display = 'block'; // 显示密码对话框
            })

            <%
            if (request.getAttribute("delete") != null && request.getAttribute("delete").equals("success")) {
                out.println("alert(\"删除成功！\")");
            }
            else if (request.getAttribute("delete") != null && request.getAttribute("delete").equals("failure")){
                out.println("alert(\"删除失败！\")");
            }
            %>

            <%
            if (request.getAttribute("modify") != null && request.getAttribute("modify").equals("success")) {
                out.println("alert(\"修改成功！\")");
            }
            else if (request.getAttribute("modify") != null && request.getAttribute("modify").equals("failure")){
                out.println("alert(\"修改失败！\")");
            }
            %>

        })

    </script>
</head>
<body>

    <h1 style="text-align: center;">用户管理
        <a href="userServlet?action=managerHome">返回</a>
    </h1>

    <c:forEach items="${requestScope.users}" var="user">
        <div class="userList">
            <ul>
                <li>
                    <div class="userInfo">
                        <div class="username">${user.username}</div>
                        <div class="password">${user.password}</div>
                    </div>
                    <div class="btnGroup">
                        <button class="modify-btn">修改</button>
                        <button class="delete-btn" >删除</button>
                    </div>
                </li>
            </ul>
        </div>
    </c:forEach>



      <!--修改用户信息弹窗 -->
      <!-- 逻辑上同陈鹏的图书馆管理系统，不填就不改 -->
    <div id="modified-dialog" class="dialog" style="position: absolute; display: none;">
        <h3>修改信息</h3>
        <form id="modified-form" action="userServlet?action=managerUserInfo" method="post">
            <input type="hidden" id="username" name="username" />
            <label for="new-username">新用户名：</label>
            <input type="text" id="new-username" name="newUsername" required>
                <!-- for和id的值是关联的,请后端修改或新增name属性 -->
            <br>
            <label for="new-password">新密码：</label>
            <input type="text" id="new-password" name="newPassword" required>
            <br>
            <button type="submit">确认提交</button>
        </form>
    </div>

</body>
</html>