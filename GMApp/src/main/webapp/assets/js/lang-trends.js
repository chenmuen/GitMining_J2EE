/**
 * Created by chendanni on 2016/6/1.
 */
var page;
var data = {};
var langName = '';
var ranking = echarts.init(document.getElementById('ranking-chart'));
ranking.showLoading();
var activeRepos = echarts.init(document.getElementById('active-repos-chart'));
activeRepos.showLoading();
var totalStars = echarts.init(document.getElementById('total-stars-chart'));
totalStars.showLoading();
var newForks = echarts.init(document.getElementById('new-forks-per-repo-chart'));
newForks.showLoading();
var newWatchers = echarts.init(document.getElementById('new-watchers-per-repo-chart'));
newWatchers.showLoading();
var openIssues = echarts.init(document.getElementById('open-issue-per-chart'));
openIssues.showLoading();

$(document).ready(function(){
    var para = window.location.search.split('=');
    langName = para[1];
    data.langName = langName;


    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-trends-repo",
        type: "post",
        success: function (json) {
            fill_repo(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-trends-star",
        type: "post",
        success: function (json) {
            fill_star(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-trends-fork",
        type: "post",
        success: function (json) {
            fill_fork(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-trends-watcher",
        type: "post",
        success: function (json) {
            fill_watcher(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-trends-issue",
        type: "post",
        success: function (json) {
            fill_issue(json);
        }
    });

    //trends
    ranking.hideLoading();
    var max = 0;
    for (var i = 0;i < langData.length;i++){
        if (langData[i] > max) {
            max = langData[i];
        }
    }
    max += 2;
    var option = {
        //title: {
        //    //text: ''
        //},
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: langYear
        },
        yAxis: {
            type: 'value',
            inverse: true,
            min: 1,
            max: max,
            interval: 1
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:langData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    ranking.setOption(option);

    window.onresize = function () {
        ranking.resize();
        activeRepos.resize();
        totalStars.resize();
        newForks.resize();
        newWatchers.resize();
        openIssues.resize();
    }
});

function fill_repo(json){
    //active repos
    activeRepos.hideLoading();
    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.repos.langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: json.repos.actRepoYears
        },
        yAxis: {
            type: 'value',
            //inverse: true
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.repos.actRepoData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    activeRepos.setOption(option);
}
function fill_star(json){
    totalStars.hideLoading();
    //total stars
    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.stars.langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: json.stars.totalStarsYears
        },
        yAxis: {
            type: 'value',
            //inverse: true
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.stars.totalStarsData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    totalStars.setOption(option);
}
function fill_fork(json){
    //new forks per repo
    newForks.hideLoading();
    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.forks.langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: json.forks.newForkPRYears
        },
        yAxis: {
            type: 'value',
            //inverse: true
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.forks.newForkPRData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    newForks.setOption(option);
}
function fill_watcher(json){
    //new watchers per repo
    newWatchers.hideLoading();
    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.watches.langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: json.watches.newWatchersPRYears
        },
        yAxis: {
            type: 'value',
            //inverse: true
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.watches.newWatchersPRData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    newWatchers.setOption(option);
}
function fill_issue(json){
    //open issues per repo
    openIssues.hideLoading();
    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.issues.langName,
            bottom: 2
        },
        grid: {
            top: '3%',
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
            data: json.issues.newOpenIssuePRYears
        },
        yAxis: {
            type: 'value',
            //inverse: true
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.issues.newOpenIssuePRData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    openIssues.setOption(option);
}


