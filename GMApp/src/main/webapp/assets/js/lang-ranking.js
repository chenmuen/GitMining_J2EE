/**
 * Created by Lenovo on 2016/6/6.
 */

var page;
var data = {};

var ranking = echarts.init(document.getElementById("ranking-chart"));
ranking.showLoading();

$(document).ready(function(){
    // ajax
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang-rank-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "lang-rank-pic",
        type: "post",
        success: function (json) {
            //console.log(json);
            //console.log(json.data);
            //console.log(json.data.langName)
            fill_charts(json);
        }
    });
    window.onresize = function () {
        ranking.resize();
    }
});

function fill_jsp(json){
    $.each(json.listRanking1,function(key,value){
        var rank1 =
        '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
        '</a>';
        $("#put_rank1").append(rank1);
    });

    $.each(json.listRanking2,function(key,value){
        var rank2 =
            '<a  class="ranking-item">' +
            '<span class="rank">' + value.rank + '</span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.rating + '</span>' +
            '</a>';
        $("#put_rank2").append(rank2);
    });

    $.each(json.listHallOfFame,function(key,value){
        var listHall =
        '<a  class="ranking-item">' +
            '<span class="rank"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i></span>' +
            '<span>' + value.name + '</span>' +
            '<span class="pull-right">' + value.year + '</span>' +
        '</a>';
        $("#put_listHall").append(listHall);
    });
}

function fill_charts(json){
    //console.log(json);
    //console.log(json.data);
    //console.log(json.data.langName);
    ranking.hideLoading();
    var option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.langName,
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


$(document).on("click",".ranking-item",function(){
    var langName = $(this).find(".rank").next().html();
    window.location.href = "/lang?lang=" + langName;
});