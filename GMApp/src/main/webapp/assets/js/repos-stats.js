/**
 * Created by Lenovo on 2016/5/14.
 */

var repoLan = echarts.init(document.getElementById('repoLan'));
repoLan.showLoading();
var repoCreate = echarts.init(document.getElementById('repoCT'));
repoCreate.showLoading();
var repoFork = echarts.init(document.getElementById('repoFork'));
repoFork.showLoading();
var repoStar = echarts.init(document.getElementById('repoStar'));
repoStar.showLoading();
var repoContr = echarts.init(document.getElementById('repoContr'));
repoContr.showLoading();
var repoColla = echarts.init(document.getElementById('repoColla'));
repoColla.showLoading();
$(document).ready(function(){
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-stat-ajax",
        type: "post",
        success: function (json) {
            fill_lan(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-created",
        type: "post",
        success: function (json) {
            fill_created(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-star",
        type: "post",
        success: function (json) {
            fill_star(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-fork",
        type: "post",
        success: function (json) {
            fill_fork(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-contr",
        type: "post",
        success: function (json) {
            fill_contr(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "repos-colla",
        type: "post",
        success: function (json) {
            fill_colla(json);
        }
    });
    window.onresize = function () {
        repoLan.resize();
        repoCreate.resize();
        repoFork.resize();
        repoStar.resize();
        repoContr.resize();
        repoColla.resize();
    }

});

function fill_lan(json){
    //repo language
    repoLan.hideLoading();
    var option = {
        title : {text: '', subtext: '', x:'center'},
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                saveAsImage: {show: true}
            }
        },
        legend: {
            orient: 'horizontal',
            bottom: 'top',
            data: json.lanName
        },
        series : [
            {
                name: 'Language',
                type: 'pie',
                radius : '75%',
                center: ['50%', '45%'],
                data: json.lanData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    repoLan.setOption(option);
}
function fill_created(json){
    //repo create time
    repoCreate.hideLoading();
    var option = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data :  json.createTime
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'Number',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.createData
            }
        ]
    };
    repoCreate.setOption(option);
}
function fill_star(json){
    repoStar.hideLoading();
    var option = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            //data:['stars']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data :  json.starNum
        },
        yAxis: {
            type : 'value'
        },
        series: [

            {
                name: 'stars',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.starData
            }
        ]
    };
    repoStar.setOption(option);
}
function fill_fork(json){
    repoFork.hideLoading();
    var option = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            //data:['forks']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data :  json.forkNum
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'forks',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.forkData
            }
        ]
    };
    repoFork.setOption(option);
}
function fill_contr(json){
    repoContr.hideLoading();
    var option = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            //data:['contributors']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data :  json.contrNum
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'contributors',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.contrData
            }
        ]
    };
    repoContr.setOption(option);
}

function fill_colla(json){
    repoColla.hideLoading();
    var option = {
        title: {
            text: '',
            subtext: '',
            sublink: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            //data:['collaborators']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data :  json.collaNum
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'collaborators',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.collaData
            }
        ]
    };
    repoColla.setOption(option);
}


