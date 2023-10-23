<%@ page import="com.scu.uars.bean.MinAndMaxScore" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="/UARS/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UARS-Recommend</title>
    <link rel="stylesheet" href="recommonds.css">
    <link rel="stylesheet" href="D_bg.css">
    <script src="js/jquery.js"></script>
    <script type="text/javascript">

      $(document).ready(function() {

        $("#recommendButton").click(function() {

          var score = $("#score");

          if (score.val() ==='') {

            alert("请输入预估分数")

            return false;
          }


        })

      })

    </script>
</head>
<body>
      <!-- 导航栏 -->
  <ul class="navbar">
    <li><a href="univService?action=home">首页</a></li>
    <li><a href="univService?action=univInfo">院校详情</a></li>
    <li><a href="univService?action=analyseHome">对比分析</a></li>
    <li><a href="recommend?action=recommendHome">院校推荐</a></li>
    <li class="user"><a href="userServlet?action=userInfo">个人信息</a></li>
  </ul>

  <!-- 这是上方标题和选择器的容器 -->
  <div class="container">
    <div class="title">
      推荐院校
    </div>
    <!-- 这是筛选条件表单 -->
    <form method="post" action="recommend?action=recommendUniv">
      <div class="form">
        省份筛选：
        <select name="univ_province">
          <!-- 第一项意味着没有省份要求 -->
          <option value="${empty requestScope.univ_province ? "无" : requestScope.univ_province}">${empty requestScope.univ_province ? "无" : requestScope.univ_province}</option>

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
        <input type="text" placeholder="意向专业" name="major" value="${requestScope.major}"/>
        <!-- 此高考分数需要给到score参数 -->
        <input type="text" placeholder="请输入高考分数" name="score" id="score" value="${requestScope.score}" />
        <button type="submit" id="recommendButton">推荐</button>
      </div>
    </form>

        <ul class="schoolList">
          <c:forEach items="${requestScope.univInfos}" var="univInfo">
            <li class="schoolCard">
              <a href="univService?action=displayUnivInfoByName&univName=${univInfo.univ_name}" class="schoolLink">
                <div class="schoolLogo">
                  <img src="img/校徽/${univInfo.univ_name}.jpeg" alt="">
                </div>
                <div class="schoolName">${univInfo.univ_name}</div>
                <div class="schoolInfo">${univInfo.prov_name}</div>
                <div class="schoolInfo">${univInfo.univ_rank}</div>
                <div class="probability">
                  <span>
                    录取概率为
                    <span class="probability-value">

                  </span>
                    %
                  </span>

                </div>
              </a>
            </li>
          </c:forEach>
            <!-- 添加更多的名片项... -->
          </ul>

        </div>
          <!-- 录取概率计算模型 -->
          <script>
            //score，输入的高考分数
            //lowestScore 三年最低分的最低
            //highestScore 三年最低分的最高
            //threshold，设定为0.2
            function calculateAdmissionProbability(score, lowestScore, highestScore) {
              // 生成随机值
              var random = Math.random();

              // 将分数范围缩小50%
              var scoreRange = (highestScore - lowestScore) * 0.5;

              // 计算调整后的最低分和最高分
              lowestScore = score - scoreRange;
              highestScore = score + scoreRange;

              // 检查输入是否在合理范围内
              if (score <= lowestScore) {
                // random值低于10%
                return (random * 0.1 * 100).toFixed(2);
              } else if (score >= highestScore) {
                // random值高于80%
                return ((0.8 + random * 0.2) * 100).toFixed(2);
              } else {
                // random值在20%到70%之间
                return ((0.2 + random * 0.5) * 100).toFixed(2);
              }
            }

            const spanElements = document.getElementsByClassName("probability-value");
            var score = ${requestScope.score};
            var lowestScore = 637;
            var highestScore = 650;
            var threshold = 0.2;
            var admissionProbability = "80%"

            <%
            if (request.getAttribute("min_max_scores") != null) {
              List<MinAndMaxScore> minMaxScores = (List<MinAndMaxScore>) request.getAttribute("min_max_scores");
            int i = 0;
            for (MinAndMaxScore minAndMaxScore : minMaxScores) {
              out.println("lowestScore = " + minAndMaxScore.getMinScore());
              out.println("highestScore = " + minAndMaxScore.getMaxScore());
              out.println("admissionProbability = calculateAdmissionProbability(score, lowestScore, highestScore, threshold);");
              out.println("spanElements[" + i + "].innerText = admissionProbability;");
              i++;
            }
            }
            %>
            //结束
          </script>

      <script>
        const ul = document.querySelector(".schoolList");
        const lis = Array.from(ul.getElementsByClassName("schoolCard"));

        lis.sort((a, b) => {
          const valueA = parseFloat(a.querySelector(".probability-value").textContent);
          const valueB = parseFloat(b.querySelector(".probability-value").textContent);
          return valueB - valueA;
        });

        lis.forEach(li => ul.appendChild(li));
      </script>

</body>
</html>


  
