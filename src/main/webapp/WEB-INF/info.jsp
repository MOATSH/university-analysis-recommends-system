<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<!-- 高校详细信息页面 -->
<head>
    <base href="/UARS/">
    <meta charset="UTF-8">
    <title>UARS-UniversityInformation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="info.css">
    <script src="js/echarts.min.js"></script>
    <script src="js/infoCharts.js"></script>
    <script src="js/jquery.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            $("#select-year").val("${requestScope.year}")
            $("#select-year").change(() => {

                var value = $("#select-year").val();

                $(location).attr("href", "univService?action=displayUnivInfoByName&univName=" + "${requestScope.univInfoByName.univ_name}" + "&year=" + value)

            })

            $("#addFavour").click(function () {

                $(location).attr("href", "univService?action=addFavour&univName=" + "${requestScope.univInfoByName.univ_name}")

            })

        })

    </script>

    <script>
        /**
         * 数据结构定义（页面会传递这些var给js的图表函数）
         */
        /* from 高校信息表 */
        var univ_info = {
            男女比例: ${requestScope.univInfoByName.sex_ratio},
            硕博士点: ${requestScope.univInfoByName.MDP_amount},
            重点学科数量: ${empty requestScope.univInfoByName.key_disp_amount ? "0" : requestScope.univInfoByName.key_disp_amount},
            重点实验室数量: ${requestScope.univInfoByName.key_lab_amount},
        };

        /* from 高校毕业生质量表 */
        var grad_quality = [
            { 年份:2020, 深造率:${requestScope.gradQualityByName.prog_rate}, 出国率:${requestScope.gradQualityByName.abroad_rate}, 就业率:${requestScope.gradQualityByName.employ_rate}},
            { 年份:${requestScope.gradQualityByName.year}, 深造率:${requestScope.gradQualityByName.prog_rate}, 出国率:${requestScope.gradQualityByName.abroad_rate}, 就业率:${requestScope.gradQualityByName.employ_rate}},
            { 年份:2022, 深造率:${requestScope.gradQualityByName.prog_rate}, 出国率:${requestScope.gradQualityByName.abroad_rate}, 就业率:${requestScope.gradQualityByName.employ_rate}},
        ];
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

    <!--button float to add to favor list-->
    <div class="addToFavor">
        <button id="addFavour">添加该校为意向院校</button>
    </div>

    <div class="info">
        <!-- 左面板 -->
        <div class="infoLeft">
            <h2>院校列表</h2>
            <p>
                <!-- 📌Data: 插入院校列表 -->
                <c:forEach items="${requestScope.allUnivName}" var="univName">
                    <a href="univService?action=displayUnivInfoByName&univName=${univName}">${univName}</a><br />
                </c:forEach>
            </p>
        </div>

        <!-- 主面板 -->
        <div class="infoMiddle">
            <!-- 头部信息 -->
            <div class="infoHeader">
                <!-- 📌Data: 校徽、高校名称、省份 | 985 or 211 -->
                <div><img class="collegeIcon" src="img/校徽/${requestScope.univInfoByName.univ_name}.jpeg"></div>
                <div class="collegeNameText">
                    <b><h1>${requestScope.univInfoByName.univ_name}</h1></b>
                    <p>${requestScope.univInfoByName.prov_name} | ${requestScope.univInfoByName.univ_rank}</p>
                </div>
                <!-- 📊 Chart -->
                <div class="infoCharts">
                    <div id="collegeKeyChart" style="width: 420px; height:180px; min-width: 10%;"></div>
                    <script>var histo = new collegeKeyChart(univ_info);</script>
                    <div id="sexRatioChart" style="width: 280px; height:180px;"></div>
                    <script>var pie = new sexRatioChart(univ_info);</script>
                </div>
            </div>
            <!-- 基本信息 -->
            <div class="infoBody">
                <!-- 院校简介 -->
                <div class="infoTitle">
                    <a>院校简介</a>
                </div>
                <div class="infoBasicText">
                    <!-- 📌Data: 高校简介 -->
                    <p>${requestScope.univInfoByName.univ_info}</p>
                </div>

                <!-- 专业招生信息 & 分数线 -->
                <div class="infoTitle">
                    <a>专业信息</a>
                        <!-- 📌Select:省份（选择）  -->
                        <select name="province" id="province-select" >
                            <option value="四川省">四川省</option>
                            <option value="北京市">北京市</option>
                            <option value="天津市">天津市</option>
                            <option value="上海市">上海市</option>
                            <option value="重庆市">重庆市</option>
                        </select>
                        <!-- 📌Select:年份（选择）  -->
                        <select name="year" id="select-year">
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <option value="2021">2021</option>
                        </select>
                </div>
                <div class="infoScoreTable">
                    <!-- 📌Data:专业分数线表格 -->
                    <table>
                        <thaed><tr> <th>专业名称</th><th>招生数量</th><th>最低分数线</th><th>最低位次</th> </tr></thaed>
                        <tbody>
                        <c:forEach items="${requestScope.univEnrollmentsByNameAndYear}" var="univEnrollment">

                            <tr> <td>${univEnrollment.major_name}</td><td>${univEnrollment.enroll_amount}</td><td>${univEnrollment.lowest_score}</td><td>${univEnrollment.lowest_rank}</td> </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

                <!-- 毕业生质量 -->
                <div><a></a></div>
                <div class="infoTitle">
                    <a>毕业生质量</a>
                    <!-- 📌Select:年份（选择2）  -->
                    <select name="year2" id="year2">
                        <option value="0">2021</option>
                        <option value="1">2020</option>
                        <option value="2">2019</option>
                    </select>
                </div>

                <div class="infoCharts">
                    <!-- 📊Chart -->
                    <p>毕业去向</p>
                    <div id="graduateChart" style="width: 350px; height:300px;"></div>
                    <script>
                        // 这里要另外给图表一个当前年份的索引
                        var selectYear2 = document.getElementById("year2");
                        var index = selectYear2.selectedIndex;
                        var gra = new graduateChart(grad_quality, index);
                        // 当 year2 选择变动
                        selectYear2.onchange = function (){
                            var index = selectYear2.selectedIndex;
                            var gra = new graduateChart(grad_quality, index);
                        }

                    </script>
                    <div class="infoGraduate">
                        <!-- 📌Data:毕业人数、平均薪资 -->
                        <p><b>毕业数据</b></p><br>
                        <p>毕业人数</p><a>${requestScope.gradQualityByName.grad_amount * 3}</a><br>
                        <p>平均薪资</p><a>${requestScope.gradQualityByName.avarage_salary}</a><br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>