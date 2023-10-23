<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <base href="/UARS/">
    <meta charset="UTF-8">
    <title>UARS-Home</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="D_bg.css">
    <script src="js/echarts.min.js"></script>
    <script src="js/china.js"></script>
    <script type="text/javascript">

        window.onload = function () {

            document.getElementById("selectedProv").innerText = "${requestScope.prov_name}"

        }

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

  <!-- 页面内容 -->
<div class="index">
    <!-- 中部展示地图 -->
    <div class="middle">
        <div id="optionMap" style="  width:100%;height:100%;"></div>
    </div>

    <!-- 省份选择显示 -->
    <!-- 根据鼠标点击的省份来显示 -->
    <div class="overlay">
        您选择的省份是：<div id="selectedProv"></div>
    </div>

    <!-- 左部展示学校列表，根据鼠标点击的省份获取 -->
    <div class="left">
        <div class="school-list">
            <h2 style="color: white;text-align: center">院校列表</h2>
        <ul>
        <c:forEach items="${requestScope.provUnivs}" var="proUniv">
            <li class="school-item"><a href="univService?action=displayUnivInfoByName&univName=${proUniv.univ_name}">${proUniv.univ_name}</a></li>
        </c:forEach>
        </ul>
    </div>
    </div>

    <!-- 右部展示排行榜和链接列表 -->
    <div class="right">
        <div class="school-container">

            <!-- 此排名模块参考统计意向表中的大学计数 -->
            <div class="ranking-section">
              <h2>前十学校排名</h2>
              <ul>
                <c:forEach items="${requestScope.topUniv}" var="tuniv">
                    <li class="school-item"><a href="univService?action=displayUnivInfoByName&univName=${tuniv.univ_name}">${tuniv.univ_name}</a></li>
                </c:forEach>
              </ul>
            </div>

            <!-- 此链接模块是静态的，后续再配置 -->
            <div class="link-section">
              <h2>跳转链接</h2>
              <ul>
                <li><a href="https://www.shanghairanking.cn/rankings/bcur/202310">
                    软科中国大学排行榜
                </a></li>
                <li><a href="https://www.qschina.cn/world-university-rankings">
                    QS世界大学排名
                </a></li>
                <li><a href="https://www.usnews.com/education/best-global-universities/rankings">
                    US.News世界大学排名
                </a></li>

              </ul>
            </div>

          </div>    
    </div>
</div>
<script src="js/optionMap.js"></script>
</body>
</html>