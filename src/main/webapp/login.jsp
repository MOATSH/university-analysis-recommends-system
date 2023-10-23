<!-- 起始登录页 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>UARS-Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="./D_bg.css">
    <script src="./js/jquery.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            $("#loginButton").click(function () {
                /*
                验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                验证密码：必须由字母，数字下划线组成，并且长度为 5 到 20 位
                 */
                var $errMeg = $("span.errorMsg");
                //用户名
                var usernameText = $("#username").val()
                var usernamePatt = /^\w{5,12}$/
                if (!usernamePatt.test(usernameText)){
                    $errMeg.text("请重新输入用户名！")

                    return false
                }

                //密码
                var passwordText = $("#password").val()
                var passwordPatt = /^\w{5,20}$/
                if (!passwordPatt.test(passwordText)){
                    $errMeg.text("请重新输入密码！")

                    return false
                }


                $(location).attr("href", "/UARS/userServlet?action=login&username=" + $("#username").val() + "&password=" + $("#password").val())
            })

        })

    </script>
</head>

<body>
  <div class="container">
    <h2>Login</h2>
      <span class="errorMsg" style='color: #4caf50'>
          ${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
      </span>
    <input type="text" id="username" placeholder="Username" name="username" value="${requestScope.username}">
    <input type="password" id="password" placeholder="Password" name="password" value="${requestScope.password}">
    <button id="loginButton">Login</button>
    <div class="register">
      New user? <a href="register.jsp">Register</a>
    </div>
  </div>
</body>

</html>