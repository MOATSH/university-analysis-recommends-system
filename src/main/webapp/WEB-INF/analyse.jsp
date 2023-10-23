<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <base href="/UARS/">
  <title>UARS-Analyse</title>
  <script src="js/echarts.min.js"></script>
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="analyse.css">
  <link rel="stylesheet" href="D_bg.css">
  <script src="js/jquery.js"></script>

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

        <!-- 选择要对比的院校 -->
        <div class="choose">
            <form method="post" action="univService?action=analyse">
              <div class="input-container">
                <input type="text" class="mrEdit" id="input1" name="univName1" value="${requestScope.univName1}">
                <ul id="results1"></ul>
              </div>

              <span style="font-size: 20px;">
                VS
              </span>

              <div class="input-container">
                <input type="text" class="mrEdit" id="input2" name="univName2" value="${requestScope.univName2}">
                <ul id="results2"></ul>
              </div>

              <!-- 对比分析按钮 ，按下之后才显示下面的对比情况-->
              <button type="submit" class="analyse">
                对比分析
              </button>
            </form>
        </div>

          <div class="univInfoDiv">
            <a href="univService?action=displayUnivInfoByName&univName=${requestScope.univInfo1.univ_name}" class="univInfo">
              <div class="univInfo">
                <img src="img/校徽/${requestScope.univInfo1.univ_name}.jpeg" alt="" class="logo">
                <div class="name">${requestScope.univInfo1.univ_name}</div>
                <div class="province">${requestScope.univInfo1.prov_name}</div>
              </div>
            </a>
            <a href="univService?action=displayUnivInfoByName&univName=${requestScope.univInfo2.univ_name}" class="univInfo">
              <div class="univInfo">
                <img src="img/校徽/${requestScope.univInfo2.univ_name}.jpeg" alt="" class="logo">
                <span class="name">${requestScope.univInfo2.univ_name}</span>
                <span class="province">${requestScope.univInfo2.prov_name}</span>
              </div>
            </a>
          </div>

          <div id="chart" ></div>
          <div id="chart2"></div>
          <div id="chart3"></div>
        </div>

        <script type="text/javascript">
            //选择对比院校
            //下方数组应该想办法获得数据库中记录的所有大学名称
            var optionsArray = ['清华大学', '浙江大学', '武汉大学', '复旦大学', '四川大学', '南京大学', '北京大学', '中山大学', '中国科学技术大学', '上海交通大学'];

            $(document).ready(function () {

                //下面两个做的是自动匹配，例如，输入“四川”，会下拉出现包含四川的所有学校。
                $('#input1').on('input', function () {
                    var input = $(this);
                    var results = $('#results1');

                    input.on('input', function () {
                        var text = input.val();

                        if (text === '') {
                            results.html('');
                            return;
                        }

                        var matchedBrands = [];

                        for (var j = 0; j < optionsArray.length; j++) {
                            var element = optionsArray[j];

                            if (element.toLowerCase().indexOf(text.toLowerCase()) === 0) {
                                if (matchedBrands.indexOf(element) === -1) {
                                    matchedBrands.push(element);
                                }
                            }
                        }

                        results.html('');

                        $.each(matchedBrands, function (k, brand) {
                            var li = $('<li></li>').text(brand);
                            results.append(li);

                            li.on('click', function () {
                                input.val(brand);
                                results.html('');
                            });
                        });
                    });
                    console.log("kkkkkk");
                });

                $('#input2').on('input', function () {
                    var input = $(this);
                    var results = $('#results2');

                    input.on('input', function () {
                        var text = input.val();

                        if (text === '') {
                            results.html('');
                            return;
                        }

                        var matchedBrands = [];

                        for (var j = 0; j < optionsArray.length; j++) {
                            var element = optionsArray[j];

                            if (element.toLowerCase().indexOf(text.toLowerCase()) === 0) {
                                if (matchedBrands.indexOf(element) === -1) {
                                    matchedBrands.push(element);
                                }
                            }
                        }

                        results.html('');

                        $.each(matchedBrands, function (k, brand) {
                            var li = $('<li></li>').text(brand);
                            results.append(li);

                            li.on('click', function () {
                                input.val(brand);
                                results.html('');
                            });
                        });
                    });
                });


                // 创建图表实例
                var chart = echarts.init(document.getElementById('chart'));
                var chart2 = echarts.init(document.getElementById('chart2'));
                var chart3 = echarts.init(document.getElementById('chart3'));

                // 模拟数据
                // 需要先获取到input里面的两个学校
                // 再从数据库拉取两个学校的具体信息，然后替换下方 data的对应项目
                var data = [
                    { school: "${requestScope.univInfo1.univ_name}",
                        毕业人数: ${requestScope.univGrad1.grad_amount},
                        就业率: ${requestScope.univGrad1.employ_rate},
                        深造率: ${requestScope.univGrad1.prog_rate},
                        男女比例: ${requestScope.univInfo1.sex_ratio},
                        国家重点学科数量: ${requestScope.univInfo1.key_disp_amount},
                        国家重点实验室数量: ${requestScope.univInfo1.key_lab_amount},
                        出国率: ${requestScope.univGrad1.abroad_rate} ,
                        平均薪资: ${requestScope.univGrad1.avarage_salary}
                    },
                    { school: "${requestScope.univInfo2.univ_name}",
                        毕业人数: ${requestScope.univGrad2.grad_amount},
                        就业率: ${requestScope.univGrad2.employ_rate},
                        深造率: ${requestScope.univGrad2.prog_rate},
                        男女比例: ${requestScope.univInfo2.sex_ratio},
                        国家重点学科数量: ${requestScope.univInfo2.key_disp_amount},
                        国家重点实验室数量: ${requestScope.univInfo2.key_lab_amount},
                        出国率: ${requestScope.univGrad2.abroad_rate} ,
                        平均薪资: ${requestScope.univGrad2.avarage_salary}
                    }
                ];

                // 以下三个图表的配置，数据来源只依赖上方的data
                option1 = {
                    title: {
                        text: '学校数据对比'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {},
                    grid: {
                        left: '10%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: ['就业率','深造率','出国率','男女比例']
                    },
                    series: [
                        {
                            name: data[0].school,
                            type: 'bar',
                            data: [data[0].就业率, data[0].深造率, data[0].出国率, data[0].男女比例],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: 'rgb(226,161,62)'
                            }
                        },
                        {
                            name: data[1].school,
                            type: 'bar',
                            data: [data[1].就业率, data[1].深造率, data[1].出国率, data[1].男女比例],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: '#cf3131'
                            }
                        }
                    ]
                };

                option2 = {
                    title: {
                        text: '薪资数据对比（月薪）'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {},
                    grid: {
                        left: '10%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: ['平均薪资']
                    },
                    series: [
                        {
                            name: data[0].school,
                            type: 'bar',
                            data: [data[0].平均薪资],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: 'rgb(226,161,62)'
                            }
                        },
                        {
                            name: data[1].school,
                            type: 'bar',
                            data: [data[1].平均薪资],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: '#cf3131'
                            }
                        }
                    ]
                };

                option3 = {
                    title: {
                        text: '学术力量对比'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: ['国家重点学科数量','国家重点实验室数量']
                    },
                    series: [
                        {
                            name: data[0].school,
                            type: 'bar',
                            data: [data[0].国家重点学科数量,data[0].国家重点实验室数量],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: 'rgb(226,161,62)'
                            }
                        },
                        {
                            name: data[1].school,
                            type: 'bar',
                            data: [data[1].国家重点学科数量,data[1].国家重点实验室数量],
                            label: {
                                show: true,
                                position: 'right',
                            },
                            itemStyle: {
                                color: '#cf3131'
                            }
                        }
                    ]
                };

                // 渲染图表
                chart.setOption(option1);
                chart2.setOption(option2);
                chart3.setOption(option3);

            })

        </script>
    </body>
</html>