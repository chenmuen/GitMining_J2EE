/**
 * Created by chendanni on 2016/6/17.
 */
var starScore = echarts.init(document.getElementById("star-score"));
starScore.showLoading();
var starScoreSample = echarts.init(document.getElementById("star-score-sample"));
starScoreSample.showLoading();
var forkScore = echarts.init(document.getElementById("fork-score"));
forkScore.showLoading();
var forkScoreSample = echarts.init(document.getElementById("fork-score-sample"));
forkScoreSample.showLoading();
var contributorScore = echarts.init(document.getElementById("contributor-score"));
contributorScore.showLoading();
var contributorScoreSample = echarts.init(document.getElementById("contributor-score-sample"));
contributorScoreSample.showLoading();
var issueScore = echarts.init(document.getElementById("issue-score"));
issueScore.showLoading();
var issueScoreSample = echarts.init(document.getElementById("issue-score-sample"));
issueScoreSample.showLoading();
var sizeScore = echarts.init(document.getElementById("size-score"));
sizeScore.showLoading();
var sizeScoreSample = echarts.init(document.getElementById("size-score-sample"));
sizeScoreSample.showLoading();

$(document).ready(function(){

    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-star-score-ajax",
        type: "post",
        success: function (json) {
            fill_star_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-star-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_star_sample_score(json);
        }
    });
     ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-fork-score-ajax",
        type: "post",
        success: function (json) {
            fill_fork_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-fork-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_fork_sample_score(json);
        }
    });
     ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-contributor-score-ajax",
        type: "post",
        success: function (json) {
            fill_contributor_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-contributor-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_contributor_sample_score(json);
        }
    });
     ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-issue-score-ajax",
        type: "post",
        success: function (json) {
            fill_issue_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-issue-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_issue_sample_score(json);
        }
    });
     ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-size-score-ajax",
        type: "post",
        success: function (json) {
            fill_size_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        //async: true,
        data: data,
        url: "repo-size-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_size_sample_score(json);
        }
    });
});
//=================================================================================
//  starScore
//=================================================================================
function fill_star_score(json){
    starScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.starScoreDataAll;
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
                formatter: json.starScoreFormatter,
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
            formatter: json.starScoreFormatter
        },
        data: [[{
            coord: json.starScoreStartC,
            symbol: 'none'
        }, {
            //coord: starScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.starScoreXName,
                //gridIndex: 0, min: 0, max: starScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.starScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: starScoreYMax
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
    starScore.setOption(option);

}
function fill_star_sample_score(json){
//sample
    starScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.starScoreDataAll;
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
                formatter: json.starScoreFormatter,
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
            formatter: json.starScoreFormatter
        },
        data: [[{
            coord: json.starScoreStartC,
            symbol: 'none'
        }, {
            //coord: json.starScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.starScoreXName,
                //gridIndex: 0, min: 0, max: starScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.starScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: starScoreYMax
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
    starScoreSample.setOption(option);
}
//=================================================================================
//  forkScore
//=================================================================================
function fill_fork_score(json){
    forkScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.forkScoreDataAll;
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
                formatter: json.forkScoreFormatter,
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
            formatter: json.forkScoreFormatter
        },
        data: [[{
            coord: json.forkScoreforktC,
            symbol: 'none'
        }, {
            //coord: forkScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.forkScoreXName,
                //gridIndex: 0, min: 0, max: forkScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.forkScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: forkScoreYMax
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
    forkScore.setOption(option);
}
function fill_fork_sample_score(json){
//sample
    forkScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.forkScoreDataAll;
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
                formatter: json.forkScoreFormatter,
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
            formatter: json.forkScoreFormatter
        },
        data: [[{
            coord: json.forkScoreforktC,
            symbol: 'none'
        }, {
            //coord: json.forkScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.forkScoreXName,
                //gridIndex: 0, min: 0, max: forkScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.forkScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: forkScoreYMax
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
    forkScoreSample.setOption(option);
}
//=================================================================================
//  contributorScore
//=================================================================================
function fill_contributor_score(json){
    contributorScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.contributorScoreDataAll;
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
                formatter: json.contributorScoreFormatter,
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
            formatter: json.contributorScoreFormatter
        },
        data: [[{
            coord: json.contributorScorecontributortC,
            symbol: 'none'
        }, {
            //coord: contributorScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.contributorScoreXName,
                //gridIndex: 0, min: 0, max: contributorScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.contributorScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: contributorScoreYMax
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
    contributorScore.setOption(option);
}
function fill_contributor_sample_score(json){
//sample
    contributorScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.contributorScoreDataAll;
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
                formatter: json.contributorScoreFormatter,
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
            formatter: json.contributorScoreFormatter
        },
        data: [[{
            coord: json.contributorScorecontributortC,
            symbol: 'none'
        }, {
            //coord: json.contributorScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.contributorScoreXName,
                //gridIndex: 0, min: 0, max: contributorScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.contributorScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: contributorScoreYMax
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
    contributorScoreSample.setOption(option);
}
//=================================================================================
//  issueScore
//=================================================================================
function fill_issue_score(json){
//sample
    contributorissueScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.contributorissueScoreDataAll;
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
                formatter: json.contributorissueScoreFormatter,
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
            formatter: json.contributorissueScoreFormatter
        },
        data: [[{
            coord: json.contributorissueScorecontributorissuetC,
            symbol: 'none'
        }, {
            //coord: json.contributorissueScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.contributorissueScoreXName,
                //gridIndex: 0, min: 0, max: contributorissueScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.contributorissueScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: contributorissueScoreYMax
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
    contributorissueScoreSample.setOption(option);
}
function fill_issue_sample_score(json){
//sample
    contributorissueScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.contributorissueScoreDataAll;
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
                formatter: json.contributorissueScoreFormatter,
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
            formatter: json.contributorissueScoreFormatter
        },
        data: [[{
            coord: json.contributorissueScorecontributorissuetC,
            symbol: 'none'
        }, {
            //coord: json.contributorissueScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.contributorissueScoreXName,
                //gridIndex: 0, min: 0, max: contributorissueScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.contributorissueScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: contributorissueScoreYMax
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
    contributorissueScoreSample.setOption(option);
}
//=================================================================================
//  sizeScore
//=================================================================================
function fill_size_score(json){
    sizeScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.sizeScoreDataAll;
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
                formatter: json.sizeScoreFormatter,
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
            formatter: json.sizeScoreFormatter
        },
        data: [[{
            coord: json.sizeScoresizetC,
            symbol: 'none'
        }, {
            //coord: sizeScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.sizeScoreXName,
                //gridIndex: 0, min: 0, max: sizeScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.sizeScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: sizeScoreYMax
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
    sizeScore.setOption(option);

}
function fill_size_sample_score(json){
//sample
    sizeScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.sizeScoreDataAll;
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
                formatter: json.sizeScoreFormatter,
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
            formatter: json.sizeScoreFormatter
        },
        data: [[{
            coord: json.sizeScoresizetC,
            symbol: 'none'
        }, {
            //coord: json.sizeScoreEndC,
            coord: [4000,100],
//            coord: [75, 150],
            symbol: 'none'
        }]]

    };

    var option = {
        //title: {
        //    text: 'Anscombe\'s quartet',
        //    x: 'center',
        //    y: 0
        //},
        tooltip: {
            formatter: 'Group {a}: ({c})'
        },
        xAxis: [
            {
                name: json.sizeScoreXName,
                //gridIndex: 0, min: 0, max: sizeScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.sizeScoreYName,
                gridIndex: 0, min: 0, max: 100
                //gridIndex: 0, min: 0, max: sizeScoreYMax
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
    sizeScoreSample.setOption(option);
}
