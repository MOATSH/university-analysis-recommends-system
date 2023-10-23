<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>UARS-Register</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="./D_bg.css">
    <script src="./js/jquery.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            $("#registerButton").click(function() {

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

                //验证两次输入密码是否一致
                var conformPasswordText = $("#conformPassword").val()
                if (conformPasswordText !== passwordText) {
                    $errMeg.text("两次输入密码不一致！")

                    return false
                }
            })

        })

    </script>
</head>
<body>
  <div class="container">
    <h2>Register</h2>
      <span class="errorMsg" style='color: #4caf50'>
          ${empty requestScope.msg ? "欢迎注册" : requestScope.msg}
      </span>

      <form method="post" action="./userServlet?action=register" accept-charset="UTF-8">

    <input type="text" id="username" placeholder="Username" name="username">
    <input type= "password" id="password" placeholder="Password" name="password" value="${requestScope.password}">
    <input type="password" id="conformPassword" placeholder="ConfirmPassword" name="conformPassword" value="${requestScope.password}">
    <select id="province" name="province">
        <option value="北京">北京</option>
        <option value="上海">上海</option>
        <option value="天津">天津</option>
        <option value="重庆">重庆</option>
        <option value="河北">河北</option>
        <option value="山西">山西</option>
        <option value="辽宁">辽宁</option>
        <option value="吉林">吉林</option>
        <option value="黑龙江">黑龙江</option>
        <option value="江苏">江苏</option>
        <option value="浙江">浙江</option>
        <option value="安徽">安徽</option>
        <option value="福建">福建</option>
        <option value="江西">江西</option>
        <option value="山东">山东</option>
        <option value="河南">河南</option>
        <option value="湖北">湖北</option>
        <option value="湖南">湖南</option>
        <option value="广东">广东</option>
        <option value="海南">海南</option>
        <option value="四川">四川</option>
        <option value="贵州">贵州</option>
        <option value="云南">云南</option>
        <option value="陕西">陕西</option>
        <option value="甘肃">甘肃</option>
        <option value="青海">青海</option>
        <option value="台湾">台湾</option>
        <option value="内蒙古">内蒙古</option>
        <option value="广西">广西</option>
        <option value="西藏">西藏</option>
        <option value="宁夏">宁夏</option>
        <option value="新疆">新疆</option>
        <option value="香港">香港</option>
        <option value="澳门">澳门</option>
    </select>
    <button type="submit" id="registerButton">Register</button>
      </form>
    <div class="login">
      Already have an account? <a href="./login.jsp">Log in</a>
    </div>
  </div>
</body>
</html>