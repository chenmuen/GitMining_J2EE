<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2016/5/12
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <script src="../assets/js/jquery.min.js"></script>
    <script src="../assets/js/echarts.js"></script>
    <script src="../assets/js/dataTool.js"></script>
</head>
<body>
<div id="scatter" style="width: 800px;height: 600px"></div>
<div id="chart" style="width: 800px;height: 600px"></div>
<div id="main" style="width: 600px;height:400px;"></div>
<div id="main2" style="width: 600px;height:400px;"></div>
<div id="main3" style="width: 600px;height:400px;"></div>
<div id="main4" style="width: 600px;height:400px;"></div>
<div id="main5" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var scatter = echarts.init(document.getElementById("scatter"));
    var N_POINT = 800;
    var dataAll = ${xxDataAll};
//    for (var i = 0; i <= N_POINT; i++) {
//        var x = 200*i / N_POINT;
//        var y = 3.056*x + 4.31;
//
//        var data= {
//            "value":[x,y],
//            "symbolSize":1
//        };
//
//        console.log(data);
//        dataAll.push(data);
//    }

    var markLineOpt = {
        animation: false,
        label: {
            normal: {
                formatter: '${xxFormatter}',
                textStyle: {
                    align: 'right'
                }
            }
        },
        lineStyle: {
            normal: {
                type: 'solid'
            }
        },
        tooltip: {
            formatter: '${xxFormatter}'
        },
        data: [[{
            coord: ${startCoord},
            symbol: 'none'
        }, {
            coord: ${endCoord},
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    console.log(${startCoord});
    console.log(${endCoord});
    var option = {
        title: {
            text: 'Anscombe\'s quartet',
            x: 'center',
            y: 0
        },
        // grid: [
        //     {x: '7%', y: '7%', width: '38%', height: '38%'},
        //     {x2: '7%', y: '7%', width: '38%', height: '38%'},
        //     {x: '7%', y2: '7%', width: '38%', height: '38%'},
        //     {x2: '7%', y2: '7%', width: '38%', height: '38%'}
        // ],
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: '${xxXName}',
                gridIndex: 0, min: 0, max: ${xMax}
            }
        ],
        yAxis: [
            {
                name: '${xxYName}',
                gridIndex: 0, min: 0, max: ${yMax}
            }
        ],
        series: [
            {
                name: 'I',
                type: 'scatter',
                xAxisIndex: [0],
                yAxisIndex: [0],
                data: dataAll,
                markLine: markLineOpt,
                //设置图形颜色
                itemStyle:{
                    normal:{
                        color : '#6532f3'
                    }
                }
            }
        ]
    };
    scatter.setOption(option);


    var chart = echarts.init(document.getElementById("chart"));
    var categories = [{name:'类目一'},{name:'类目二'}];
    option = {
        title: {
            text: '关系图',
            left: 'left'
        },
        tooltip: {
        },
        legend: {
            data:categories.map(function (a) {
                return a.name;
            })
        },
        series : [
            {
                name: 'Les Miserables',
                type: 'graph',
                layout: 'force',
                categories: categories,
                data: [{name:'a',value:700,category:0,symbolSize:20,
                label:{
                    normal:{
                        show:true
                    }
                }},
                    {name:'b',value:1040,category:1,symbolSize:30
                        ,
                        label: {
                            normal: {
                                show: true
                            }
                        }},
                    {name:'c',value:1020,category:0,symbolSize:40,
                        label:{
                            normal:{
                                show:true
                            }
                        }},
                    {name:'d',value:1000,category:1,symbolSize:50},
                    {name:'e',value:1020,category:0,symbolSize:40},
                    {name:'f',value:300,category:1,symbolSize:30}],
                links: [{
                    source: 'a',
                    target: 'b'
                },
                    {
                        source: 'a',
                        target: 'c'
                    },{
                        source: 'a',
                        target: 'd'
                    },{
                        source: 'a',
                        target: 'e'
                    },{
                        source: 'a',
                        target: 'f'
                    },{
                        source: 'b',
                        target: 'f'
                    }
                ],
                roam: true,
                label: {
                    normal: {
                    }
                },
                lineStyle: {
                    normal: {
                        curveness: 0.3
                    }
                }
            }
        ]
    };


    chart.setOption(option);




    var myChart = echarts.init(document.getElementById('main'));
    $.get('../assets/js/t4.gexf', function (xml) {
//    myChart.showLoading();
//    $.get('../assets/js/les-miserables.gexf', function (xml) {
//        myChart.hideLoading();
        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 9; i++) {
            categories[i] = {
                name: '类目' + i
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.value = node.symbolSize;
            node.symbolSize /= 1.5;
            node.label = {
                normal: {
                    show: node.symbolSize > 30
                }
            };
            node.category = node.attributes.modularity;
//            alert(node.attributes.modularity);
//            alert(node.attributes[0]);    //undefined??
//            node.category = 1;
        });
        option = {
            title: {
                text: 'Les Miserables',
                subtext: 'Default layout',
                top: 'bottom',
                left: 'right'
            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDuration: 1500,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            curveness: 0.3
                        }
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'xml');

    var myChart5 = echarts.init(document.getElementById('main5'));
    $.get('../assets/js/t3.gexf', function (xml) {
        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 9; i++) {
            categories[i] = {
                name: '类目' + i
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.value = node.symbolSize;
            node.symbolSize /= 3;
            node.label = {
                normal: {
                    show: node.symbolSize > 30
                }
            };
            node.category = node.attributes.modularity;
        });
        option = {
            title: {
                text: 'Les Miserables',
                subtext: 'Default layout',
                top: 'bottom',
                left: 'right'
            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDuration: 1500,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            curveness: 0.3
                        }
                    }
                }
            ]
        };
        console.log(categories.map(function (a) {
            return a.name;
        }));
        myChart5.setOption(option);
    }, 'xml');

    var myChart4 = echarts.init(document.getElementById('main4'));
    var option = {
        title: {
            text: 'Language Ranking Demo'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['C++','Java','C','Html','Python'],
            bottom: 2
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['2009','2010','2011','2012','2013','2014','2015']
        },
        yAxis: {
            type: 'value',
            inverse: true
        },
        series: [
            {
                name:'C++',
                type:'line',
                // stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210],
                label:{
                    normal:{
//                        show:true
                    }
                }
            },
            {
                name:'Java',
                type:'line',
                // stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'C',
                type:'line',
                // stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'Html',
                type:'line',
                // stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'Python',
                type:'line',
                // stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };
    myChart4.setOption(option);


    var myChart1 = echarts.init(document.getElementById('main2'));
    var dataAll = [
        [
            [10.0, 8.04],
            [8.0, 6.95],
            [13.0, 7.58],
            [9.0, 8.81],
            [11.0, 8.33],
            [14.0, 9.96],
            [6.0, 7.24],
            [4.0, 4.26],
            [12.0, 10.84],
            [7.0, 4.82],
            [5.0, 5.68]
        ],
        [
            [10.0, 9.14],
            [8.0, 8.14],
            [13.0, 8.74],
            [9.0, 8.77],
            [11.0, 9.26],
            [14.0, 8.10],
            [6.0, 6.13],
            [4.0, 3.10],
            [12.0, 9.13],
            [7.0, 7.26],
            [5.0, 4.74]
        ],
        [
            [10.0, 7.46],
            [8.0, 6.77],
            [13.0, 12.74],
            [9.0, 7.11],
            [11.0, 7.81],
            [14.0, 8.84],
            [6.0, 6.08],
            [4.0, 5.39],
            [12.0, 8.15],
            [7.0, 6.42],
            [5.0, 5.73]
        ],
        [
            [8.0, 6.58],
            [8.0, 5.76],
            [8.0, 7.71],
            [8.0, 8.84],
            [8.0, 8.47],
            [8.0, 7.04],
            [8.0, 5.25],
            [19.0, 12.50],
            [8.0, 5.56],
            [8.0, 7.91],
            [8.0, 6.89]
        ]
    ];

    var markLineOpt = {
        animation: false,
        label: {
            normal: {
                formatter: 'y = 0.5 * x + 3',
                textStyle: {
                    align: 'right'
                }
            }
        },
        lineStyle: {
            normal: {
                type: 'solid'
            }
        },
        tooltip: {
            formatter: 'y = 0.5 * x + 3'
        },
        data: [[{
            coord: [0, 3],
            symbol: 'none'
        }, {
            coord: [20, 13],
            symbol: 'none'
        }]]
    };

    var option = {
        title: {
            text: 'Anscombe\'s quartet',
            x: 'center',
            y: 0
        },
        grid: [
            {x: '7%', y: '7%', width: '38%', height: '38%'},
            {x2: '7%', y: '7%', width: '38%', height: '38%'},
            {x: '7%', y2: '7%', width: '38%', height: '38%'},
            {x2: '7%', y2: '7%', width: '38%', height: '38%'}
        ],
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {gridIndex: 0, min: 0, max: 20},
            {gridIndex: 1, min: 0, max: 20},
            {gridIndex: 2, min: 0, max: 20},
            {gridIndex: 3, min: 0, max: 20}
        ],
        yAxis: [
            {gridIndex: 0, min: 0, max: 15},
            {gridIndex: 1, min: 0, max: 15},
            {gridIndex: 2, min: 0, max: 15},
            {gridIndex: 3, min: 0, max: 15}
        ],
        series: [
            {
                name: 'I',
                type: 'scatter',
                xAxisIndex: [0],
                yAxisIndex: [0],
                data: dataAll[0],
                markLine: markLineOpt
            },
            {
                name: 'II',
                type: 'scatter',
                xAxisIndex: [1],
                yAxisIndex: [1],
                data: dataAll[1],
                markLine: markLineOpt
            },
            {
                name: 'III',
                type: 'scatter',
                xAxisIndex: [2],
                yAxisIndex: [2],
                data: dataAll[2],
                markLine: markLineOpt
            },
            {
                name: 'IV',
                type: 'scatter',
                xAxisIndex: [3],
                yAxisIndex: [3],
                data: dataAll[3],
                markLine: markLineOpt
            }
        ]
    };
    myChart1.setOption(option);


    var myChart2 = echarts.init(document.getElementById('main3'));
    var dataAll = [
        [
            [10.0, 8.04],
            [80.0, 60.95],
            [130.0, 70.58],
            [90.0, 80.81],
            [110.0, 80.33],
            [140.0, 90.96],
            [60.0, 70.24],
            [40.0, 40.26],
            [120.0, 100.84],
            [70.0, 40.82],
            [50.0, 50.68]
        ]
    ];

    var markLineOpt = {
        animation: false,
        label: {
            normal: {
                formatter: 'y = 0.5 * x + 3',
                textStyle: {
                    align: 'right'
                }
            }
        },
        lineStyle: {
            normal: {
                type: 'solid'
            }
        },
        tooltip: {
            formatter: 'y = 0.5 * x + 3'
        },
        data: [[{
            coord: [0, 3],
            symbol: 'none'
        }, {
            coord: [200, 130],
            symbol: 'none'
        }]]
    };

    option = {
        title: {
            text: 'Anscombe\'s quartet',
            x: 'center',
            y: 0
        },
        // grid: [
        //     {x: '7%', y: '7%', width: '38%', height: '38%'},
        //     {x2: '7%', y: '7%', width: '38%', height: '38%'},
        //     {x: '7%', y2: '7%', width: '38%', height: '38%'},
        //     {x2: '7%', y2: '7%', width: '38%', height: '38%'}
        // ],
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: 'forks',
                gridIndex: 0, min: 0, max: 200
            }
        ],
        yAxis: [
            {
                name: 'followers',
                gridIndex: 0, min: 0, max: 150
            }
        ],
        series: [
            {
                name: 'I',
                type: 'scatter',
                xAxisIndex: [0],
                yAxisIndex: [0],
                data: dataAll[0],
                markLine: markLineOpt,
                //设置图形颜色
                itemStyle:{
                    normal:{
                        color : '#6532f3'
                    }
                }
            }
        ]
    };
    myChart2.setOption(option);


</script>



</body>
</html>
