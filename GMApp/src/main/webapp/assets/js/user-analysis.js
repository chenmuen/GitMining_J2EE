/**
 * Created by chendanni on 2016/6/18.
 */
var followerScore = echarts.init(document.getElementById("follower-score"));
followerScore.showLoading();
var followerScoreSample = echarts.init(document.getElementById("follower-score-sample"));
followerScoreSample.showLoading();
var repoStarScore = echarts.init(document.getElementById("repo-star-score"));
repoStarScore.showLoading();
var repoStarScoreSample = echarts.init(document.getElementById("repo-star-score-sample"));
repoStarScoreSample.showLoading();
var repoScore = echarts.init(document.getElementById("repo-score"));
repoScore.showLoading();
var repoScoreSample = echarts.init(document.getElementById("repo-score-sample"));
repoScoreSample.showLoading();

$(document).ready(function(){

    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-follower-score-ajax",
        type: "post",
        success: function (json) {
            fill_follower_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-follower-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_follower_sample_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-repo-star-score-ajax",
        type: "post",
        success: function (json) {
            fill_repo_star_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-repo-star-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_repo_star_sample_score(json);
        }
    });
    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-repo-score-ajax",
        type: "post",
        success: function (json) {
            fill_repo_score(json);
        }
    });// ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-repo-sample-score-ajax",
        type: "post",
        success: function (json) {
            fill_repo_sample_score(json);
        }
    });

});
//=================================================================================
//  followerScore
//=================================================================================
function fill_follower_score(json){
    followerScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.followerScoreDataAll;
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
                formatter: json.followerScoreFormatter,
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
            formatter: json.followerScoreFormatter
        },
        data: [[{
            coord: json.followerScorefollowertC,
            symbol: 'none'
        }, {
            //coord: followerScoreEndC,
            coord: [4000,12000],
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
                name: json.followerScoreXName,
                //gridIndex: 0, min: 0, max: followerScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.followerScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: followerScoreYMax
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
    followerScore.setOption(option);
}
function fill_follower_sample_score(json){
//sample
    followerScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.followerScoreDataAll;
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
                formatter: json.followerScoreFormatter,
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
            formatter: json.followerScoreFormatter
        },
        data: [[{
            coord: json.followerScorefollowertC,
            symbol: 'none'
        }, {
            //coord: json.followerScoreEndC,
            coord: [4000,12000],
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
                name: json.followerScoreXName,
                //gridIndex: 0, min: 0, max: followerScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.followerScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: followerScoreYMax
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
    followerScoreSample.setOption(option);
}
//=================================================================================
//  repo-starScore
//=================================================================================
function fill_repo_star_score(json){
    repoStarScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.repoStarScoreDataAll;
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
                formatter: json.repoStarScoreFormatter,
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
            formatter: json.repoStarScoreFormatter
        },
        data: [[{
            coord: json.repoStarScorerepoStartC,
            symbol: 'none'
        }, {
            //coord: repoStarScoreEndC,
            coord: [4000,12000],
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
                name: json.repoStarScoreXName,
                //gridIndex: 0, min: 0, max: repoStarScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.repoStarScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: repoStarScoreYMax
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
    repoStarScore.setOption(option);
}
function fill_repo_star_sample_score(json){
//sample
    repoStarScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.repoStarScoreDataAll;
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
                formatter: json.repoStarScoreFormatter,
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
            formatter: json.repoStarScoreFormatter
        },
        data: [[{
            coord: json.repoStarScorerepoStartC,
            symbol: 'none'
        }, {
            //coord: json.repoStarScoreEndC,
            coord: [4000,12000],
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
                name: json.repoStarScoreXName,
                //gridIndex: 0, min: 0, max: repoStarScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.repoStarScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: repoStarScoreYMax
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
    repoStarScoreSample.setOption(option);
}
//=================================================================================
//  repoScore
//=================================================================================
function fill_repo_score(json){
    repoScore.hideLoading();
    var N_POINT = 800;
    var dataAll = json.repoScoreDataAll;
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
                formatter: json.repoScoreFormatter,
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
            formatter: json.repoScoreFormatter
        },
        data: [[{
            coord: json.repoScorerepotC,
            symbol: 'none'
        }, {
            //coord: repoScoreEndC,
            coord: [4000,12000],
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
                name: json.repoScoreXName,
                //gridIndex: 0, min: 0, max: repoScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.repoScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: repoScoreYMax
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
    repoScore.setOption(option);
}
function fill_repo_sample_score(json){
//sample
    repoScoreSample.hideLoading();
    var N_POINT = 800;
    var dataAll = json.repoScoreDataAll;
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
                formatter: json.repoScoreFormatter,
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
            formatter: json.repoScoreFormatter
        },
        data: [[{
            coord: json.repoScorerepotC,
            symbol: 'none'
        }, {
            //coord: json.repoScoreEndC,
            coord: [4000,12000],
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
                name: json.repoScoreXName,
                //gridIndex: 0, min: 0, max: repoScoreXMax
                gridIndex: 0, min: 0, max: 4000
            }
        ],
        yAxis: [
            {
                name: json.repoScoreYName,
                gridIndex: 0, min: 0, max: 20000
                //gridIndex: 0, min: 0, max: repoScoreYMax
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
    repoScoreSample.setOption(option);
}


