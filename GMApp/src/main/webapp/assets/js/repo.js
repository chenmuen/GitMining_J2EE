/**
 * Created by Chendanni on 2016/5/13.
 */

var repoScore = echarts.init(document.getElementById('repoScore'));
repoScore.showLoading();

var repoLan = echarts.init(document.getElementById('repoLan'));
repoLan.showLoading();
var repoAD;
var repoPC;

$(document).ready(function(){
    var para = window.location.search.split('=');
    data.repo = para[1];

    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-ajax",
        type: "post",
        success: function (json) {
            fill_info(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-related",
        type: "post",
        success: function (json) {
            fill_related(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-radar",
        type: "post",
        success: function (json) {
            fill_score(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-lang",
        type: "post",
        success: function (json) {
            fill_language(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-ad",
        type: "post",
        success: function (json) {
            if ((json != null)&&(json != '')&&(json.length != 0))
                fill_addition_deletion(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-punch",
        type: "post",
        success: function (json) {
            if ((json != null)&&(json != ''))
                fill_punch_card(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "repo-commit",
        type: "post",
        success: function (json) {
            if ((json != null)&&(json != ''))
                fill_different_mem_commit(json);
        }
    });
    window.onresize = function () {
        repoScore.resize();
        repoLan.resize();
        repoAD.resize();
        repoPC.resize();
    }
});


function fill_info(json){
    //header
    var header =
        '<h1><a class="repo-owner">' + json.owner + '</a> / ' + json.name + '</h1>' +
        '<p>' + json.description + '</p>' +
        '<p>Updated on ' + json.updateTime + '</p>' +
        '<div class="input-url">' +
        '<input type="text" value="' + json.cloneUrl + '" id="repo-url">\n' +
        '<a><i class="fa fa-clipboard fa-fw" aria-hidden="true" id="copy-url" data-clipboard-target="#repo-url"></i></a>\n' +
        '<a><i class="fa fa-github fa-fw" aria-hidden="true"></i></a>\n' +
        '</div>';
    $("#put_header").append(header);
    //stats
    var stats =
        '<div class="stat">' +
        '<i class="fa fa-star fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.stars + '</div><br>' +
        '<div class="stat-key">STARS</div>' +
        '</div>' +
        '<div class="stat">' +
        '<i class="fa fa-code-fork fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.forks + '</div><br>' +
        '<div class="stat-key">FORKS</div>' +
        '</div>' +
        '<div class="stat">' +
        '<i class="fa fa-users fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.contributors + '</div><br>' +
        '<div class="stat-key">CONTRIBUTORS</div>' +
        '</div>' +
        '<div class="stat">' +
        '<i class="fa fa-eye fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.subscribers + '</div><br>' +
        '<div class="stat-key">SUBSCRIBERS</div>' +
        '</div>' +
        '<div class="stat">' +
        '<i class="fa fa-wrench fa-fw fa-pull-left fa-2x" aria-hidden="true"></i><div class="stat-value">' + json.collaborators + '</div><br>' +
        '<div class="stat-key">COLLABORATORS</div>' +
        '</div>';
    $("#put_stats").append(stats);
}

function fill_related(json){
    if ((json.relatedsByTags != null)&&(json.relatedsByTags != '')&&(json.relatedsByTags.length > 0)){
        console.log("in" + json.relatedsByTags.length);
        //related by tags
        var related_tags =
        '<div class="card grid-1-2 grid-1-1-sm">'+
            '<div class="card-wrapper">' +
            '<div class="card-title">' +
            'Related Repositories by Tags' +
            '</div>' +
            '<div class="card-content">';
        $.each(json.relatedsByTags,function(key,value){
            related_tags +=
                '<a class="item">' +
                '<div class="item-title">' + value.fullName + '</div>' +
                '<div class="item-line">' +
                '<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
                '<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>' + value.forks + '</span>' +
                '</div>' +
                '</a>';
        });
        related_tags +=
            '</div>' +
            '</div>' +
            '</div>';
        $("#com_relate").append(related_tags);

    }

    if ((json.relatedsByOwner != null)&&(json.relatedsByOwner != '')&&(json.relatedsByOwner.length > 0)){
        //related by owner
        var related_owners =
        '<div class="card grid-1-2 grid-1-1-sm">' +
            '<div class="card-wrapper" id="put_related_owners">' +
            '<div class="card-title">' +
            'Related Repositories by Owner' +
            '</div>' +
            '<div class="card-content">';
        $.each(json.relatedsByOwner,function(key,value){
            related_owners +=
                '<a class="item">' +
                '<div class="item-title">' + value.fullName + '</div>' +
                '<div class="item-line">' +
                '<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
                '<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>' + value.forks + '</span>' +
                '</div>'+
                '</a>';
        });
        related_owners +=
            '</div>' +
            '</div>' +
            '</div>';
        $("#com_relate").append(related_owners);
    }
}

function fill_score(json){
    //score
    //var repoScore = echarts.init(document.getElementById('repoScore'));
    repoScore.hideLoading();
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
        }],
        backgroundColor:'transparent'
    };
    repoScore.setOption(option);
}

function fill_language(json){
    //language
    repoLan.hideLoading();
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
    repoLan.setOption(option);
}

function fill_addition_deletion(json){
    console.log(json.adDate.length);
    if (json.adDate.length != 0){
        var ad =
            '<div class="card grid-1-1">' +
            '<div class="card-wrapper">' +
            '<div class="card-title">' + "Repository's Addition and Deletion" + '</div>' +
            '<div class="card-content">' +
            '<div style="height: 350px" id="repoAD">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        $("#ad_punch").prepend(ad);
        //Addition and Deletion
        repoAD = echarts.init(document.getElementById('repoAD'));
        //repoAD.showLoading();
        //repoAD.hideLoading();
        var option = {
            title: {text: ''},
            tooltip : {trigger: 'axis'},
            legend: {
                data:['Addition','Deletion']
            },
            toolbox: {
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                    saveAsImage: {}
                }
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
                    data : json.adDate
                }
            ],
            yAxis : [{
                type : 'value'
            }],
            series : [
                {
                    name:'Addition',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data : json.aData
                },
                {
                    name:'Deletion',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: json.dData
                }
            ]
        };
        repoAD.setOption(option);
    }

}

function fill_punch_card(json){
    console.log(json);
    if (json.punchData.length != 0){
        console.log(json.punchData.length);
        var punch =
            '<div class="card grid-1-1">' +
            '<div class="card-wrapper">' +
            '<div class="card-title">Repository' + "'" + 's Punch Card</div>' +
            '<div class="card-content">' +
            '<div style="height: 350px" id="repoPC">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        $("#punch_com").prepend(punch);
        //punch card
        repoPC = echarts.init(document.getElementById('repoPC'));
        //repoPC.showLoading();
        //repoPC.hideLoading();
        var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
            '7a', '8a', '9a','10a','11a',
            '12p', '1p', '2p', '3p', '4p', '5p',
            '6p', '7p', '8p', '9p', '10p', '11p'];
        var days = ['Saturday', 'Friday', 'Thursday',
            'Wednesday', 'Tuesday', 'Monday', 'Sunday'];
        var data = json.punchData;
        data = data.map(function (item) {
            return [item[1], item[0], item[2]];
        });
        var option = {
            title: {
//		text: 'Punch Card of Github',
//		link: 'https://github.com/pissang/echarts-next/graphs/punch-card'
            },
            legend: {
                //data: ['Punch Card'],
                //left: 'right'
            },
            tooltip: {
                position: 'top',
                formatter: function (params) {
                    return params.value[2] + ' commits in ' + hours[params.value[0]] + ' of ' + days[params.value[1]];
                }
            },
            toolbox: {
                right: '1%',
                feature: {
                    saveAsImage: {show: true}
                }
            },
            grid: {
                top:'3%',
                left: '2%',
                bottom: '2%',
                right: '4%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                data: hours,
                boundaryGap: false,
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: '#ddd',
                        type: 'dashed'
                    }
                },
                axisLine: {
                    show: false
                }
            },
            yAxis: {
                type: 'category',
                data: days,
                axisLine: {
                    show: false
                }
            },
            series: [{
                name: 'Punch Card',
                type: 'scatter',
                symbolSize: function (val) {
                    return parseInt(Math.sqrt(12*val[2]));
                },
                data: data,
                animationDelay: function (idx) {
                    return idx * 5;
                }
            }]
        };
        repoPC.setOption(option);
    }

}

function fill_different_mem_commit(json){
    if (json.memName.length > 0){
        var diC =
            '<div class="card grid-1-1">' +
            '<div class="card-wrapper" id="put_dif_com">' +
            '<div class="card-title">Different Members' + "'" + ' Commit</div>' +
            '</div>' +
            '</div>';
        $("#com_relate").prepend(diC);

        //different members' commits
        var dif_com = '<div class="card-content grids">';
        $.each(json.memName,function(key,value){
            dif_com += '<div style="height: 300px" id="' + value + '" class="grid-1-3 grid-1-1-sm grid-1-2-md"></div>';
        });
        dif_com += '</div>';
        $("#put_dif_com").append(dif_com);
        //different members' commit
        for(var i = 0;i < json.memName.length;i++){

            var repoMemC = echarts.init(document.getElementById(json.memName[i]));
            option = {
                title: {
                    text: json.memName[i]
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {},
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '8%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : json.memCDate[i]
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
                        stack: '总量',
                        areaStyle: {normal: {}},
                        data:json.memData[i]
                    }
                ]
            };
            repoMemC.setOption(option);
        }
    }
}

$(document).on("click",".item",function(event){
    var keyword = $(this).find(".item-title").text();
    parent.location.href="/repo?repo="+keyword;
});

$(document).on("click",".repo-owner",function(event){
    var keyword = $(this).text();
    parent.location.href="/user?user="+keyword;
});

new Clipboard('#copy-url');

$(document).on("click",".fa-github",function(){
    var url = $(this).parent().prev().prev().val();
    window.open(url);
});
