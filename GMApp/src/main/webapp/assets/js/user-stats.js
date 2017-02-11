/**
 * Created by Lenovo on 2016/5/13.
 */
var userCom = echarts.init(document.getElementById('userCom'));
userCom.showLoading();
var userType = echarts.init(document.getElementById('userType'));
userType.showLoading();
var userRepo = echarts.init(document.getElementById('userRepo'));
userRepo.showLoading();
var userGist = echarts.init(document.getElementById('userGist'));
userGist.showLoading();
var userFollower = echarts.init(document.getElementById('userFollower'));
userFollower.showLoading();
var userFollowing = echarts.init(document.getElementById('userFollowing'));
userFollowing.showLoading();


$(document).ready(function(){
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-com",
        type: "post",
        success: function (json) {
            fill_com(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-type",
        type: "post",
        success: function (json) {
            fill_type(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-repo",
        type: "post",
        success: function (json) {
            fill_repo(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-gist",
        type: "post",
        success: function (json) {
            fill_gist(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-follower",
        type: "post",
        success: function (json) {
            fill_follower(json);
        }
    });
    jQuery.ajax({
        async: true,
        //data: data,
        url: "users-following",
        type: "post",
        success: function (json) {
            fill_following(json);
        }
    });
    window.onresize = function () {
        userCom.resize();
        userType.resize();
        userRepo.resize();
        userGist.resize();
        userFollower.resize();
        userFollowing.resize();
    }
});

function fill_com(json){
    userCom.hideLoading();
    var option = {
        title : {text: '', subtext: '', x:'center'},
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {d}%"
        },
        toolbox: {
            feature: {
                saveAsImage: {show: true}
            }
        },
        legend: {
            orient: 'horizontal',
            bottom: 'top',
            data: json.comName
        },
        series : [
            {
                name: 'Company',
                type: 'pie',
                radius : '75%',
                center: ['50%', '45%'],
                data:json.comData,
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
    userCom.setOption(option);
}
function fill_type(json){
    //userType
    userType.hideLoading();
    var option = {
        title : {text: '', subtext: '', x:'center'},
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {d}%"
        },
        toolbox: {
            feature: {
                saveAsImage: {show: true}
            }
        },
        legend: {
            orient: 'horizontal',
            bottom: 'top',
            data: json.typeName
        },
        series : [
            {
                name: 'Company',
                type: 'pie',
                radius : '75%',
                center: ['50%', '45%'],
                data:json.typeData,
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
    userType.setOption(option);
}
function fill_repo(json){
    //userRepo
    userRepo.hideLoading();
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
            //data:['repositories']
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
            data :  json.repoNum
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'repositories',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.repoData
            }
        ]
    };
    userRepo.setOption(option);
}
function fill_gist(json){
    userGist.hideLoading();
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
            //data:['gists']
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
            data :  json.gistNum
        },
        yAxis: {
            type : 'value'
        },
        series: [

            {
                name: 'gists',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.gistData
            }
        ]
    };
    userGist.setOption(option);
}
function fill_follower(json){
    userFollower.hideLoading();
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
            //data:['followers']
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
            data :  json.followerNum
        },
        yAxis: {
            type : 'value'
        },
        series: [

            {
                name: 'followers',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data:json.followerData
            }
        ]
    };
    userFollower.setOption(option);
}
function fill_following(json){
    userFollowing.hideLoading();
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
            //data:['followings']
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
            data :  json.followingNum
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: 'followings',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: json.followingData
            }
        ]
    };
    userFollowing.setOption(option);
}