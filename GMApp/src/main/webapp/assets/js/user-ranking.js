/**
 * Created by chendanni on 2016/6/10.
 */
var page;
var data = {};

function fill_json(json){
    $("#put_title").empty();
    $("#put_list").empty();
    $("#put_list2").empty();

    $("#put_title").append('Ranking of ' + json.currentFilter + ' Repositories');

    var filters = document.getElementById("put_filters").children;
    for (var i = 0;i < filters.length;i++){
        if (filters[i].className == "filter active"){
            filters[i].className = "filter";
        }
        if (filters[i].innerText == json.currentFilter){
            filters[i].className = "filter active";
        }
    }

    $.each(json.list,function(key,value){
        var list =
        '<a class="ranking-item" >' +
            '<span class="avatar"><img src="' + value.avatar + '"></span>' +
            '<span>' + value.rank + '. ' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-star fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
        '</a>';
        $("#put_list").append(list);
    });

    $.each(json.list2,function(key,value){
        var list2 =
        '<a class="ranking-item" >' +
            '<span class="avatar"><img src="' + value.avatar + '"></span>' +
            '<span>' + value.rank + '. ' + value.name + '</span>' +
            '<span class="pull-right"><i class="fa fa-star fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
        '</a>';
        $("#put_list2").append(list2);
    });
}


$(document).on("click",".filter",function(){
    var langName = $(this).text();

    if (($(this).className == "filter active")||(langName == 'All'))
        delete data.langName;
    else
        data.langName = langName;

    console.log(data.langName);

    jQuery.ajax({
        async: true,
        data: data,
        url: "user-rank-ajax",
        type: "post",
        success: function (json) {
            fill_json(json);
        }
    });
});

$(document).on("click",".ranking-item",function(){
    var owner = $(this).find(".avatar").next().text();
    var para = owner.split('.');
    owner = para[1].substring(1);
    //console.log(owner);
    parent.location.href = "user?login=" + owner;
});
