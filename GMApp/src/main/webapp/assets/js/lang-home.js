/**
 * Created by Lenovo on 2016/6/6.
 */

var page;
var data = {};

var ranking = echarts.init(document.getElementById("ranking-chart"));

$(document).ready(function(){
    // ajax
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang_home-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
    //fame
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang_home-ajax-fame",
        type: "post",
        success: function (json) {
            fill_fame(json);
        }
    });
    //top
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang_home-ajax-top",
        type: "post",
        success: function (json) {
            fill_top(json);
        }
    });
    //ranking
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang_home-ajax-trends",
        type: "post",
        success: function (json) {
            fill_charts(json);
        }
    });
    window.onresize = function () {
        ranking.resize();
    }
});

function fill_jsp(json){
    $("#put_weblist").empty();
    $("#put_applist").empty();
    $.each(json.listWeb,function(key,value){
        var webList =
        '<div class="card grid-1-3 grid-1-2-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">' +
            '<a class="card-lang-s card-blue">' +
            '<div class="card-lang-top">' +
            '<h3>' + value.name + '</h3>' +
            '<hr>' +
            '</div>' +
            '<div class="card-lang-bottom">' +
            '<div><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>' + value.repoCount + '<span>Repositories</span></div>' +
            '<div><i class="fa fa-users fa-fw" aria-hidden="true"></i>' + value.devCount + '<span>Developers</span></div>' +
            '</div>' +
            '</a>' +
        '</div>';
        $("#put_weblist").append(webList);
    });

    $.each(json.listApp,function(key,value){
        var appList =
            '<div class="card grid-1-3 grid-1-2-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">' +
            '<a class="card-lang-s card-blue">' +
            '<div class="card-lang-top">' +
            '<h3>' + value.name + '</h3>' +
            '<hr>' +
            '</div>' +
            '<div class="card-lang-bottom">' +
            '<div><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>' + value.repoCount + '<span>Repositories</span></div>' +
            '<div><i class="fa fa-users fa-fw" aria-hidden="true"></i>' + value.devCount + '<span>Developers</span></div>' +
            '</div>' +
            '</a>' +
            '</div>';
        $("#put_applist").append(appList);
    });
}

function fill_fame(json){
    $("#put_fame1").empty();
    $("#put_fame2").empty();
    $.each(json.listHallOfFame1,function(key,value){
       var put_fame1 =
        '<a  class="ranking-item">' +
            '<span class="rank"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i></span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right">' + value.year + '</span>'
        '</a>';
        $("#put_fame1").append(put_fame1);
    });

    $.each(json.listHallOfFame2,function(key,value){
        var put_fame2 =
        '<a  class="ranking-item">' +
            '<span class="rank"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i></span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right">' + value.year + '</span>' +
        '</a>';
        $("#put_fame2").append(put_fame2);
    });

}

function fill_top(json){
    $.each(json.listObjectiveOriented,function(key,value){
        var oriented =
        '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
        '</a>';
        $("#put_oriented").append(oriented);
    });

    $.each(json.listFunctional,function(key,value){
        var functional =
            '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
            '</a>';
        $("#put_functionals").append(functional);
    });
    $.each(json.listImperative,function(key,value){
        var imperative =
            '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
            '</a>';
        $("#put_imperatives").append(imperative);
    });
    $.each(json.listStructured,function(key,value){
        var structured =
            '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
            '</a>';
        $("#put_structured").append(structured);
    });

}

function fill_charts(json){

    var option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.langName,
            bottom: -4
        },
        grid: {
            top: '2%',
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
            data: json.langYears
        },
        yAxis: {
            type: 'value',
            inverse: true,
            min: 1,
            minInterval: 1
        },
        series: json.langData
//        series: [
//            {
//                name:'C++',
//                type:'line',
//                // stack: '总量',
//                data:[120, 132, 101, 134, 90, 230, 210],
//                label:{
//                    normal:{
////                        show:true
//                    }
//                }
//            },
//            {
//                name:'Java',
//                type:'line',
//                // stack: '总量',
//                data:[220, 182, 191, 234, 290, 330, 310]
//            },
//            {
//                name:'C',
//                type:'line',
//                // stack: '总量',
//                data:[150, 232, 201, 154, 190, 330, 410]
//            },
//            {
//                name:'Html',
//                type:'line',
//                // stack: '总量',
//                data:[320, 332, 301, 334, 390, 330, 320]
//            },
//            {
//                name:'Python',
//                type:'line',
//                // stack: '总量',
//                data:[820, 932, 901, 934, 1290, 1330, 1320]
//            }
//        ]
    };
    ranking.setOption(option);

}

$(document).on("click",".card-lang-s",function(){
    var langName = $(this).find(".card-lang-top").find("h3").html();
    window.location.href = "lang?lang=" + langName;
});

$(document).on("click",".ranking-item",function(){
    var langName = $(this).find(".rank").next().html();
    window.location.href = "lang?lang=" + langName;
});

$(document).on("click",".fa-link",function(){

    var title = $(this).parent().parent().get(0).innerText;
    var cardTitles = document.getElementsByClassName("card-title");
    var createWeb = cardTitles[0].innerText;
    var buildApp = cardTitles[1].innerText;
    var trends = cardTitles[2].innerText;
    var quote = cardTitles[3].innerText;

    console.log(title);
    console.log(buildApp);

    if (title == createWeb){
        setCookie("language-application","Website",100);
        window.location.href = "lang-list";
    }else if (title == buildApp){
        setCookie("language-application",encodeURIComponent("Mobile Application"),100);
        window.location.href = "lang-list";
    }else if (title == trends){
        window.location.href = "lang-rank";

    }else if (title == quote){

    }
});
function setCookie(name,value,time){
    var str = name + "=" + escape(value);
    if(time > 0){
        var date = new Date();
        var ms = time*3600*1000;
        date.setTime(date.getTime() + ms);
        str += "; expires=" + date.toGMTString();
    }
    document.cookie = str;
}