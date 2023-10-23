//配置省份和对应高校数量
//name：省份
//value：高校数量
var mydata = [
    {name: '北京',value: 92},{name: '天津',value: 56},
    {name: '上海',value: 68},{name: '重庆',value: 71},
    {name: '河北',value: 128},{name: '河南',value: 168},
    {name: '云南',value: 88},{name: '辽宁',value: 104},
    {name: '黑龙江',value: 78},{name: '湖南',value: 137},
    {name: '安徽',value: 111},{name: '山东',value: 156},
    {name: '新疆',value: 62},{name: '江苏',value: 168},
    {name: '浙江',value: 109},{name: '江西',value: 108},
    {name: '湖北',value: 134},{name: '广西',value: 87},
    {name: '甘肃',value: 50},{name: '山西',value: 83},
    {name: '内蒙古',value: 54},{name: '陕西',value: 97},
    {name: '吉林',value: 66},{name: '福建',value: 89},
    {name: '贵州',value: 77},{name: '广东',value: 162},
    {name: '青海',value: 12},{name: '西藏',value: 7},
    {name: '四川',value: 137},{name: '宁夏',value: 21},
    {name: '海南',value: 22},{name: '台湾',value: 101},
    {name: '香港',value: 18},{name: '澳门',value: 12}
];

//配置整个地图的属性
var option = {
    title: {
        text: '高校数量统计图',
        subtext: '数据来源教育部官网，截至2023/6/15',
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },

    
    //梯度可视化，数量落在哪个区间显示哪个颜色。
    visualMap: {
        show : false,
        x: 'left',
        y: 'bottom',
        splitList: [ 
            {start: 120, end:300},{start: 100, end: 120},
            {start: 80, end: 100},{start: 40, end: 80},
            {start: 20, end: 40},{start: 0, end: 20},
        ],
        color: ['#d299c2', '#dab1cb','#e2c8d4', '#ebdfdd','#f3f6e6', '#fef9d7']
    },

    // 鼠标悬停显示文本
    series: [{
        name: '高校数量',
        type: 'map',
        mapType: 'china', 
        roam: false,
        label: {
            normal: {
                show: false
            },
            emphasis: {
                show: true,
                textStyle: {
                    color: '#000000' // 设置选中后显示文字的颜色
                }
            }
        },
        data:mydata
        
    }]
};

var chart = echarts.init(document.getElementById('optionMap'));

chart.on("click", function(params) {
    var provinceName = params.name

    // document.getElementById("selectedProv").innerText = provinceName

    // $(location).attr("href", "univService?action=displayUnivByProvince?prov_name=" + provinceName)
    window.location.replace("univService?action=displayUnivByProvince&prov_name=" + provinceName)
})

chart.setOption(option);

