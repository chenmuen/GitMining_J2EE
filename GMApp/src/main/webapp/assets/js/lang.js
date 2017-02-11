/**
 * Created by Lenovo on 2016/6/3.
 */
var langName ='';
var trends = echarts.init(document.getElementById('language-trends'));
var relations = echarts.init(document.getElementById('language-relations'));

$(document).ready(function(){
    var para = window.location.search.split('=');
    langName = para[1];
    data.langName = langName;

    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-ajax",
        type: "post",
        success: function (json) {
            fill_hw(json);
        }
    });

    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-ajax-trend",
        type: "post",
        success: function (json) {
            fill_charts(json);
        }
    });
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-ajax-relation",
        type: "post",
        success: function (json) {
                fill_relations(json);
        }
    });
    
    jQuery.ajax({
        async: true,
        data: data,
        url: "lang-ajax-related",
        type: "post",
        success: function (json) {
            fill_related(json);
        }
    });
    window.onresize = function () {
        trends.resize();
        relations.resize();
    }
    
    // fill_hw();
});

function fill_hw(json) {
    var helloWorld = json.helloWorld;
    if(helloWorld) {
        helloWorld = helloWorld.replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\n/g, "<br/>").replace(/\s/g, '&nbsp;');
        $(".card-terminal").html(helloWorld);
    }
}

function fill_info(json){

    var info =
    '<h1>' + json.language + '</h1>' +
        '<span>';
    $.each(json.applications,function(key,value){
        info += value + ' ';
    });
    info +=
        '</span>' +
    '<div class="lang-stats">' +
        '<div class="lang-stat">' +
        '<i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>\n' +
            json.repoCount +
        '\n<span>' + json.language + ' Repositories</span>' +
        '</div>' +
        '<div class="lang-stat">' +
        '<i class="fa fa-users fa-fw" aria-hidden="true"></i>\n' +
            + json.devCount +
        '\n<span>' + json.language + ' Developers</span>' +
        '</div>' +
    //    <%--<div class="lang-stat">--%>
    //    <%--<i class="fa fa-google fa-fw" aria-hidden="true"></i>--%>
    //    <%--16,110,000,000--%>
    //<%--<span>Google Items</span>--%>
    //<%--</div>--%>
    '</div>' +
    '<div class="share">' +
        '<a><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></a>' +
        '<a><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></a>' +
        '<a><i class="fa fa-weibo fa-fw" aria-hidden="true"></i></a>' +
        '</div>'
    $("#put_info").append(info);


    if ((json.wiki != null)&&(json.wiki != '')){
        var wiki =
        '<div class="card-wrapper" id="put_wiki">' +
            '<div class="card-title">' +
            'Wiki Says' +
            '<a><i class="fa fa-link" aria-hidden="true"></i></a>' +
            '</div>' +
            '<div class="card-content" >' +
            '<p>' + json.wiki + '</p>' +
            '</div>' +
        '</div>';
        $("#put_things").prepend(wiki);
    }

    if((json.helloWorld != null)&&(json.helloWorld != '')){
        console.log("in helloworld");
        console.log(json.helloWorld);
        var value = json.helloWorld;
        value = value.replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\n/g, "<br/>").replace(/\s/g, '&nbsp;');
        //console.log(value);
        var helloWorld =
        '<div class="card-wrapper">' +
            '<div class="card-title">' +
            'Hello World!' +
        '<a><i class="fa fa-play" aria-hidden="true"></i></a>' +
            '</div>' +
                '<div class="card-content">' +
                    '<div class="card-terminal">' +
                        value +
                    '</div>' +
                '</div>' +
            '</div>';
        $("#put_helloWorld_quote").append(helloWorld);
    }

    if ((json.quoteContent != null)&&(json.quoteContent != '')){
        var quote =
            '<div class="card-wrapper">' +
            '<div class="card-title">' +
            'Quote' +
            '<a><i class="fa fa-link" aria-hidden="true"></i></a>' +
            '</div>' +
            '<div class="card-content">' +
            '<div class="card-quote">' +
            '<p class="quote-content">"' + json.quoteContent + '"</p>';
        if (json.quoteFrom != null){
            quote +=
                '<p class="quote-from">' + json.quoteFrom + '</p>';
        }
        quote +=
            '</div>' +
                '</div>' +
                    '</div>';
        $("#put_helloWorld_quote").append(quote);
    }

    var top_title =
    'Top ' + json.language + ' Developers' +
    '<a><i class="fa fa-link" aria-hidden="true"></i></a>';
    $("#put_top_title").append(top_title);
}

function fill_related(json){

    //alert("in");

    var top_dev = '';
    $.each(json.listTopDev,function(key,value){
       top_dev +=
        '<a class="ranking-item" >' +
            '<span class="avatar"><img src="' + value.avatar + '"></span>' +
            '<span>' + value.rank + '. ' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-trophy fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
        '</a>';
    });
    $("#put_related_users").append(top_dev);

    if (json.listRepo.length != 0){
        var related_repos = '<div class="card-title">' +
            'Featured Recommendation' +
            '<a><i class="fa fa-link" aria-hidden="true"></i></a>' +
            '</div>' +
            '<div class="card-content">';
        $.each(json.listRepo,function(key,value){
            related_repos +=
                '<a class="item" >' +
                '<div class="item-title">' + value.fullName + '</div>' +
                    //'<span class="item-title">' + 'ssss' + '</span>' +
                '<div class="item-line item-number">' +
                '<span><i class="fa fa-star" aria-hidden="true"></i>' + value.stars + '</span>' +
                '<span><i class="fa fa-code-fork" aria-hidden="true"></i>' + value.forks + '</span>' +
                '</div>' +
                '<div class="item-line line-2">' +
                value.description +
                '</div>' +
                '</a>';
        });
        related_repos += '</div>';
        $("#put_related_repos").append(related_repos);
    }
}

function fill_charts(json){
    //TRENDS
    var max = 0;
    var interval = 1;
    for (var i = 0;i < json.langData.length;i++){
        if (json.langData[i] > max) {
            max = json.langData[i];
        }
    }
    max += 2;
    if (max < 10) interval = 1;
    else interval = Math.floor(max/10);

    var option = {
        title: {
            //text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:json.langName,
            bottom: 2
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
            data: json.langYear
        },
        yAxis: {
            type: 'value',
            inverse: true,
            min:1,
            //minInterval: 1,
            interval: interval,
            max: max
        },
        series: [
            {
                name:langName,
                type:'line',
                // stack: '总量',
                data:json.langData,
                label:{
                    normal:{
//                        show:true
                    }
                }
            }
        ]
    };
    trends.setOption(option);
}

function fill_relations(json){

    //console.log(json.categories);
    //console.log(json.relationNodes);
    //console.log(json.relationLinks);

    //RELATIONS
    var categories = json.categories;
    var option = {
        tooltip: {
        },
        legend: {
            data:categories.map(function (a) {
                return a.name;
            })
        },
        series : [
            {
                name: 'Language',
                type: 'graph',
                layout: 'force',
                force:{
                    repulsion: 900,
                    edgeLength:100
                },
                draggable: true,
                left: '10%',
                right: '10%',
                //bottom: '30%',
                //top: '13',
                categories: categories,
                data: json.relationNodes,
                links: json.relationLinks,
                roam: true,
                lineStyle: {
                    normal: {
                        curveness: 0.3
                    }
                }
            }
        ]
    };
    relations.setOption(option);
    //window.onresize = relations.resize;
}


$(document).on("click",".ranking-item",function(){
    var span = $(this).find(".avatar").next().text();
    var para = span.split('.');
    var login = para[1].substring(1);
    window.location.href = "/user?login=" + login;
});
$(document).on("click",".item",function(){
    //var fullName = $(event.target).children(":first");
    var fullName = $(this).find(".item-title").html();
    window.location.href = "/repo?repo=" + fullName;
});
$(document).on("click",".fa-link",function(){

    var title = $(this).parent().parent().get(0).innerText;
    var trends = document.getElementById("trend-title").innerText;
    var relations = document.getElementById("relation-title").innerText;

    console.log(title);
    console.log(trends);


    if (title == trends){
        window.location.href = 'lang-trends?langName=' + langName;
    }else if (title == relations){

    }
});