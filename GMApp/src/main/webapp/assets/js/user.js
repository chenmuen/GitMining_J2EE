/**
 * Created by Lenovo on 2016/5/13.
 */
var userScore = echarts.init(document.getElementById('userScore'));
userScore.showLoading();

var userLan = echarts.init(document.getElementById('userLan'));
userLan.showLoading();

var userCon = echarts.init(document.getElementById('userContr'));
userCon.showLoading();

var userCommit = echarts.init(document.getElementById('userCommit'));
userCommit.showLoading();

$(document).ready(function(){
    var para = window.location.search.split('=');
    data.login = para[1];

    jQuery.ajax({
        async: true,
         data: data,
        url: "user-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-radar",
        type: "post",
        success: function (json) {
            fill_score(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-lang",
        type: "post",
        success: function (json) {
            fill_language(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-rate",
        type: "post",
        success: function (json) {
            fill_contribute(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "user-commit",
        type: "post",
        success: function (json) {
            fill_commit(json);
        }
    });
    window.onresize = function () {
        userScore.resize();
        userLan.resize();
        userCon.resize();
        userCommit.resize();
    }

});
function fill_jsp(json) {
    //header
    var header =
    '<div class="avatar"><img src="' + json.avatar + '"></div>' +
    '<div class="sibling">' +
        '<h1><a>' + json.name + '</a></h1>' +
        '<p><i class="fa fa-map-marker fa-fw" aria-hidden="true"></i>' + (json.location == null ? "Unknown location" : json.location) + '</p>' +
        '<p><i class="fa fa-envelope fa-fw" aria-hidden="true"></i>' + (json.email == null ? "Unknown email" : json.email) + '</p>' +
        '<p><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Join on ' + json.joinTime + '</p>' +
        '<div class="tags">';
    var lans = json.languages;
    $.each(lans,function(key,value){
        header += '<a class="tag">' + value + '</a>\n';
    });
    header +=
        '</div>' +
    '</div>';
    $("#put_header").append(header);

    //stats
    var stats =
        '<div class="stat">' +
            '<i class="fa fa-star fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.stars + '</div><br>' +
            '<div class="stat-key">STARRED</div>' +
        '</div>' +
    '<div class="stat">' +
        '<i class="fa fa-github-alt fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.repos + '</div><br>' +
        '<div class="stat-key">REPOSITORIES</div>' +
    '</div>' +
    '<div class="stat">' +
        '<i class="fa fa-users fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.followers + '</div><br>' +
        '<div class="stat-key">FOLLOWERS</div>' +
    '</div>' +
    '<div class="stat">' +
        '<i class="fa fa-eye fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.following + '</div><br>' +
        '<div class="stat-key">FOLLOWING</div>' +
    '</div>' +
    '<div class="stat">' +
        '<i class="fa fa-code fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.gists + '</div><br>' +
        '<div class="stat-key">GISTS</div>' +
    '</div>';
    $("#put_stats").append(stats);
};

function fill_score(json){
    //score
    userScore.hideLoading();
    var option = {
        title: {},
        tooltip: {},
        toolbox: {
            feature: {
                saveAsImage: {show: true}
            }
        },
        legend: {data: []},
        radar: {
            indicator:json.scoreIndicator
        },
        series: [{
            name: '',
            type: 'radar',
            data : [{
                value : json.values,
                name : 'Value'
            }]
        }]
    };
    userScore.setOption(option);
}

function fill_language(json){
    ////language
    userLan.hideLoading();
    var option = {
        title : {
            text: '', subtext: '', x:'center'
        },
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
            data: json.lanName
        },
        series : [
            {
                name: 'Language',
                type: 'pie',
                radius : '60%',
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
    userLan.setOption(option);
}

function fill_contribute(json){
    //contribution
    userCon.hideLoading();
    var option = {
        title: {
            text: ''
        },
        tooltip: {},

        toolbox: {
            feature: {
                saveAsImage: {show: true}
            }
        },
        legend: {
            // data:['']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            data: json.repoName
        },
        yAxis: {},
        series: [{
            name: 'Contribution',
            type: 'bar',
            data: json.contrData
        }]
    };
    userCon.setOption(option);
}

function fill_commit(json){
    //commit
    userCommit.hideLoading();
    var option = {
        title: {
            text: ''
        },
        tooltip : {
            trigger: 'axis'
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
            //data:['邮件营销']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'Commits',
                type:'line',
                stack: 'Number',
                areaStyle: {normal: {}},
                data:json.commitData
            }
        ]
    }
    userCommit.setOption(option);
}


