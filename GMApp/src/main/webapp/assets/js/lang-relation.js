/**
 * Created by Lenovo on 2016/6/6.
 */
var chart = echarts.init(document.getElementById("relation-chart"));
var showLabel = false;
chart.showLoading();
$(document).ready(function(){

    // ajax
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-relation-ajax",
        type: "post",
        success: function (json) {
            fill_lang_relation(json);
        }
    });

    window.onresize = function () {
        showLabel = true;
        chart.resize();
    }


});
function fill_lang_relation(json){
    chart.hideLoading();
    option = {
        //title: {
        //    text: '',
        //    left: 'left'
        //},
        tooltip: {
        },
        legend: {
            data:json.data.categories.map(function (a) {
                return a.name;
            }),
            right: 0,
            width: 200
        },
        series : [
            {
                name: 'Relations',
                type: 'graph',
                layout: 'force',
                 left: 100,
                // top: 20,
                // layout: 'circular',
                categories: json.data.categories,
                data: json.data.relationNodes,
                links: json.data.relationLinks,
                roam: true,
                 //draggable: true,
                force:{
                    initLayout:'circular',
                    repulsion: 300,
                    gravity: 0.3,
                     edgeLength:60,
                    layoutAnimation: false
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        curveness: 0.1
                    }
                },
                label:{
                    normal:{
                        show:true,
                        position: 'right',
                         formatter: '{b}'
                    }
                }

            }
        ]
    };
    chart.setOption(option);
}

