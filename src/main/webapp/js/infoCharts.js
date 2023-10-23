/**
 * @file: infoCharts.js - 高校详情页面图表
 * @author: TobyChen
 * @
 */

/**
 * 数据结构定义（已在html声明）
 */

// /* from 高校信息表 */
// var univ_info = {
//     男女比例: 0.9298,
//     硕博士点: 52,
//     重点学科数量: 46,
//     重点实验室数量: 3,
// };

/* from 高校毕业生质量表 */
// var grad_quality = [
//     { 年份:2019, 深造率:30.38, 出国率:6.86, 就业率:62.25},
//     { 年份:2020, 深造率:31.99, 出国率:6.83, 就业率:60.09},
//     { 年份:2021, 深造率:33.35 ,出国率:6.06, 就业率:59.19},
// ];


/**
 * 高校重点资源柱状图
 * 元素 ID：collegeKeyChart
 * @param :univ_info 高校信息表
 */
function collegeKeyChart(univ_info) {
    var collegeKeyHistoChart = echarts.init(document.getElementById('collegeKeyChart'));
      var option = {
        title: {
            text: '重点资源',
            padding: 15,
        },
        grid:{
            x:75,
            y:50,
            x2:30,
            y2:30,
            borderWidth:1
	    },
        dataset: {
            source: [
                /**/
            ['value', 'name'],
            [univ_info.重点实验室数量, '重点实验室'],
            [univ_info.重点学科数量, '重点学科'],
            [univ_info.硕博士点, '硕博士点'],
            ]
        },
        tooltip: {},
        xAxis: {},
        yAxis: { type: 'category' },
        series: [{
            type: 'bar',
            encode: {
                x: 'value',
                y: 'name',
            },
            label: {
                show:true,
                fontFamily: 'Bahnschrift',
                fontSize: 20,
                color:'#ffffff',
                position: 'right',
                align: 'right',
                offset:[-7,0],
            },
            itemStyle: {
                barBorderRadius: [0, 5, 5, 0], //柱体圆角   
                color: new echarts.graphic.LinearGradient(
                    0, 0, 1, 0, [{//渐变方向
                            offset: 0, //offset:0~1
                            color: '#00C6FB'
                        },
                        {
                            offset: 1, //offset:0~1
                            color: '#888df8'
                        }
                    ]
                ),
            }
        }]
    };
    collegeKeyHistoChart.setOption(option);
    // window.addEventListener('resize', function() {
    // collegeKeyHistoChart.resize();
    // });  // 自动变更长度
}


/**
 * 男女比例圆饼图
 * 元素 ID：sexRatioChart
 * @param: univ_info  高校信息表 
 */
function sexRatioChart(univ_info) {
    const manIconPath = 'path://M20 15c-.32-3.4-2.87-6-5.9-6H5.93c-3 0-5.58 2.58-5.9 6a2.56 2.56 0 0 0 0 .39v11.88a2 2 0 0 0 2 2.09 2 2 0 0 0 1.86-2.09V15.73h.76v31.48A2.62 2.62 0 0 0 7 50h.11a2.64 2.64 0 0 0 2.47-2.78V29.08h.66v18.13A2.63 2.63 0 0 0 12.75 50h.12a2.74 2.74 0 0 0 2.55-2.78V15.73h.76v11.54a2 2 0 0 0 1.95 2.09A2 2 0 0 0 20 27.27V15.42a2.56 2.56 0 0 0 0-.42ZM15.42 47.21ZM10 8.26a4.1 4.1 0 0 0 0-8.19 4.1 4.1 0 0 0 0 8.19Z';
    const girlIconPath = 'path://m19.91 24.52-3.06-11.7c-.42-1.33-1.9-3.66-4.38-3.77H7.55a5.09 5.09 0 0 0-4.39 3.77L.1 24.51c-.66 2.56 2.15 3.55 2.84 1.16l2.74-10.79h.77L1.74 33.83h4.4v14.24a1.68 1.68 0 1 0 3.33 0V33.83h1v14.24c0 2.57 3.23 2.57 3.23 0V33.83h4.52l-4.77-18.95h.87l2.74 10.79c.68 2.45 3.47 1.4 2.85-1.15ZM10 8.23A4.1 4.1 0 1 0 10 0a4.1 4.1 0 0 0 0 8.19Z';
    var sexRatioPieChart = echarts.init(document.getElementById('sexRatioChart'));
    var option = {
        title: {
            text: '男女比例',
            padding: 15,
        },
        tooltip: {
            trigger: 'item',
            formatter:function (params) {
                //return params.marker + params.name + "&emsp;<b>" + params.value+"%</b>";
                return params.marker + "&nbsp;" + params.name;
            }
        },
        legend: {
            top: 'middle',
            left: 'left',
            orient:'vertical',
            data:[
                {name: '男', icon:manIconPath},
                {name: '女', icon:girlIconPath}
            ],
            textStyle: {
                fontWeight:'bold',
                color: '#A9A9A9',
                fontSize:20
            },
            itemHeight:50, // icon Size
        },
        series:[
        {
            type: 'pie',
            data: [
                { value: univ_info.男女比例, name: '男', itemStyle: {color:'#29bbfd'}, label: {textBorderColor:'#29bbfd'} },
                { value: 1, name: '女', itemStyle: {color:'#fd6d79'}, label: {textBorderColor:'#fd6d79'} }
            ].sort(function (a, b) {
                return a.value - b.value;
            }),
            radius:['50%','80%'],  // 半径[inner，outter]
            label: {
                position: 'inside',  // 数据标签 
                formatter: '{d}%',   // {persentage} %
                fontWeight:'bold',   // text字形
                fontFamily: 'Bahnschrift',
                fontSize: 20,
                color:'#ffffff',
                textBorderType: 'solid',  // text描边
                textBorderWidth:4
            },
            labelLine: { show: false },
            avoidLabelOverlap: false,
        }
        ]
    };
    // 执行
    sexRatioPieChart.setOption(option);
    // 跳过点击后取消选取
    sexRatioPieChart.on('legendselectchanged', function (params) {
        sexRatioPieChart.setOption({
            legend:{selected:{[params.name]: true}}
        })
        console.log('收到点击', params.name);
        // do something
    });
}


/**
 * 毕业生去向图
 * 元素 ID：graduateChart
 * @param: grad_quality 毕业质量表
 * @param: index 选择的年份索引（第0,1,2年）
 */
function graduateChart(grad_quality, index) {
    var graduatePolarBarChart = echarts.init(document.getElementById('graduateChart'));
    var option = {
        // 实际数据自动映射颜色
        visualMap: {
            show: false,
            min: 0,
            max: 100,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        polar: {
            radius: [20, '80%'],
        },
        // 角度坐标（刻度）
        angleAxis: {
            max: 100,
            startAngle:75,
            minInterval:10,
            axisLabel:{
                formatter: "{value}%"
            },
        },
        // 半径坐标（条目）
        radiusAxis: {
            type: 'category',
            data: ['出国', '深造', '就业'],
            axisLabel:{ show:false }
        },
        tooltip: {
            trigger: 'item',
            formatter:function (params) {
                return params.marker + params.name + "率&emsp;<b>" + params.value+"%</b>";
            }
        },
        series: [
            {
                //背景柱
                type: "bar",
                data: [72,72,72],
                coordinateSystem: 'polar',
                barGap:'-100%',
                silent:true,
                label:{
                    show:true,
                    position:'end',
                    formatter:'{b}',
                    fontWeight:'normal',
                    color:'#394b85',
                    textBorderColor:'#f0fff0',
                    textBorderWidth:2,
                    offset:[-12,-5],
                    rotate:0,
                },
                itemStyle: {
                    color: 'rgb(240,240,240)',
                    borderRadius: 4.5,
                },
            },
            {
                //实际数据
                type: 'bar',
                data: [grad_quality[index].出国率, grad_quality[index].深造率, grad_quality[index].就业率],
                coordinateSystem: 'polar',
                label: {
                    show: true,
                    position: 'start',
                    formatter: '{c}%',
                    fontWeight:'bold',   // text字形
                    fontFamily: 'Bahnschrift',
                    fontSize: 20,
                    color:'inherit',
                    rotate:0,
                    textBorderType: 'solid',  // text描边
                    textBorderWidth:4
                },
                itemStyle:{
                    opacity:0.9,
                    borderRadius: 4.5,
                },
            }
        ]
    };
    graduatePolarBarChart.setOption(option);
}
