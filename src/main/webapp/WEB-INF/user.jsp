<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="/UARS/">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UARS-UserInformation</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="user.css">
    <link rel="stylesheet" href="D_bg.css">
    <script src="js/jquery.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            $("#check-province-button").click(function() {

                $(location).attr("href", "/UARS/userServlet?action=updateInfo&username=${requestScope.user.username}&"
                    + "province=" + $("#province-select").val())
            })

            $("#updatePasswordButton").click(function() {

                var newPasswordText = $("#new-password").val();
                var confirmPasswordText = $("#confirm-password").val();

                if (confirmPasswordText !== newPasswordText) {
                    alert("两次输入密码不一致！");
                    return false
                }

            })

            $("button.delete-btn").click(function() {

                var parent = $(this).parent().parent();
                var name = parent.find(".info").find(".name")

                $(location).attr("href", "/UARS/userServlet?action=deleteFavourUniv&username=${requestScope.user.username}&univ_name=" + name.text())

            })

            $("button.details-btn").click(function() {
                var parent = $(this).parent().parent();
                var name = parent.find(".info").find(".name")

                $(location).attr("href", "univService?action=displayUnivInfoByName&univName=" + name.text())
            })

            <%
            if (request.getAttribute("update") != null && request.getAttribute("update").equals("success")) {
                out.println("alert(\"修改成功！\")");
            }
            else if (request.getAttribute("update") != null && request.getAttribute("update").equals("failure")){
                out.println("alert(\"修改失败！\")");
            }
            %>

        })

    </script>
</head>
<body>

<!-- 信息容器 -->
<div class="centerContainer">
    
    <!-- 个人信息容器 -->
    <div class="personalInfo">
        <!-- 上部显示个人信息 -->
        <h2>个人信息</h2>
        <ul class="info-list">
          <li>
            <strong>用户名：</strong>
            <span>${requestScope.user.username}</span>
          </li>
          <li>
            <strong>省份：</strong>
        <!-- 省份选择框 -->
            <select id="province-select">
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

            <!-- 下面设置省份的默认显示 -->
            <script>// 获取省份下拉框元素
                const provinceSelect = document.getElementById('province-select');
                const keyword = "${requestScope.user.province}";//此处替换成用户注册时的省份选择
                for (let i = 0; i < provinceSelect.options.length; i++) {
                    if (provinceSelect.options[i].text.indexOf(keyword) !== -1) {
                        provinceSelect.selectedIndex = i;
                        break;
                    }
                }
            </script>
            </li>
        </ul>

        <!-- 下面有两个按钮 一个进入修改页面的弹窗提示  另一个点击会确认你是否修改了省份-->
        <button id="change-password-button" class="edit-button" onclick="showPasswordDialog()">修改密码</button>
        <button id="check-province-button" class="edit-button">修改省份</button>
    </div>


    <!-- 修改密码模块 -->
        <div id="password-dialog" class="dialog" style="position: absolute; display: none;">
            <h3>修改密码</h3>
            <form id="password-form" action="userServlet?action=updateInfo&username=${requestScope.user.username}" method="post">
                <label for="new-password">新密码：</label>
                <input type="password" id="new-password" name="password" required>
                <!-- for和id的值是关联的,请后端修改或新增name属性 -->
                <br>
                <label for="confirm-password">确认密码：</label>
                <input type="password" id="confirm-password" required>
                <br>
                <button type="submit" id="updatePasswordButton">确认提交</button>
            </form>
        </div>
        <script>
            function showPasswordDialog() {
                const passwordDialog = document.getElementById('password-dialog');
                passwordDialog.style.display = 'block'; // 显示密码对话框
            }
        </script>


    <!-- 意向管理容器 -->
    <div class="favorList">
        <ul>
            <!-- 下面是一个具体意向院校实例 -->
            
            <li>


                    <!-- 展示文字信息 校名,所在省份,学校类型(985/211)-->
                    <c:forEach items="${requestScope.favorUnivs}" var="favourUniv">
                    <div class="university-item">
                        <div class="logo">
                            <img src="img/校徽/${favourUniv.univ_name}.jpeg" alt="校徽">
                        </div>

                        <div class="info">
                            <div class="name" id="name">${favourUniv.univ_name}</div>
                            <div class="details">
                                <div class="school-province">${favourUniv.prov_name}</div>
                                <div>  </div>
                                <div class="school-type">${favourUniv.univ_rank}</div>
                            </div>
                            <!-- 更多附属信息... -->
                        </div>
                        <div class="actions">
                            <button class="details-btn">详情</button>
                            <button class="delete-btn">删除</button>
                        </div>
                    </div>
                    </c:forEach>


            </li>

        </ul>
    </div>

</div>


<!-- 导航栏 -->
    <ul class="navbar">
        <li><a href="univService?action=home">首页</a></li>
        <li><a href="univService?action=univInfo">院校详情</a></li>
        <li><a href="univService?action=analyseHome">对比分析</a></li>
        <li><a href="recommend?action=recommendHome">院校推荐</a></li>
        <li class="user"><a href="userServlet?action=userInfo">个人信息</a></li>
    </ul>

</body>
</html>
