/**
 * Created by Lenovo on 2016/5/18.
 */
var page;
var data = {};

$(document).ready(function () {
    // Filter
    var $filters = $(".filters-wrapper");
    $(".btn-filter").click(function(event) {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
            $filters.slideUp();
        } else {
            $(this).addClass("on");
            $filters.slideDown();
        }
    });
    // ajax
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "/repos-default",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
});

$(".item-title").click(function(){
    var keyword = $(this).text();
    parent.location.href="/repo?repo="+keyword;
});

$(document).on("click" , ".option" ,function(){
    var text = $(this).text();
    var sort = 0;
    // var url = window.location.search;
    if (text == 'ALL') sort = 0;
    if (text == 'STARS ▼') sort = 1;
    if (text == 'FORKS ▼') sort = 2;
    if (text == 'CONTRIBUTORS ▼') sort = 3;
    // if (url == '')  window.location.href = "?sort=" + sort;
    // else{
    //     var pos = url.indexOf("sort");
    //     if (pos != -1){
    //         url = url.substring(0,pos + 5) + sort + url.substring(pos + 6);
    //         window.location.href = url;
    //     }
    //     else window.location.href = url + "&sort=" + sort
    // }
    data.sort = sort;
    jQuery.ajax({
        url: "/repos-default",
        type: "post",
        data: data,
        success: function(json){
            fill_jsp(json);
        }
    });
});

$(".filter").click(function(){
    var category = '';
    var language = '';
    var year = '';
    var sort = '';
    var page = '';
    var current_url = window.location.search;
    var getFirst = false;
    current_url = current_url.substring(1);

    var paras = current_url.split('&');



    for (var i = 0;i < paras.length;i++){
        var pos = paras[i].indexOf('=');
        if (pos == -1) continue;

        var p_temp = paras[i].substring(0,pos);

        if (p_temp == 'category') category = paras[i].substring(pos + 1);
        else if (p_temp == 'language') language = paras[i].substring(pos + 1);
        else if (p_temp == 'year') year = paras[i].substring(pos + 1);
        else if (p_temp == 'sort') sort = paras[i].substring(pos + 1);
    }


    var text = $(this).parent().children("h2").text();
    if (text == 'Category: '){
        if ($(this).text() == category) category = '';
        else category = $(this).text();
    }
    else if (text == 'Language: '){
        if ($(this).text() == language) language = '';
        else{
            language = $(this).text();
            if (language == 'C++') language = 'C%2B%2B';
        }
    }
    else if (text == 'Create Time: ') {
        if ($(this).text() == year) year = '';
        else year = $(this).text();
    }

    var res = "?";
    if(page != ''){
        res += "page=" + page;
        getFirst = true;
    }
    if(sort != ''){
        if (getFirst) res += '&';
        res += "sort=" + sort;
        getFirst = true;
    }
    if(category != '' && category != 'All') {
        if (getFirst) res += '&';
        getFirst = true;
        res += "category=" + category;
    }
    if(language != '' && language != 'All'){
        if (getFirst) res += '&';
        res += "language=" + language;
        getFirst = true;
    }
    if(year != '' && year != 'All') {
        if (getFirst) res += '&';
        res += "year=" + year;
    }

    window.location.href = res;
});

$(".page").click(function(){
    var text = $(this).text();
    var max = $(this).parent().children().last().prev().text();
    var url = window.location.search;

    if (url == '') url += '?';

    if (text == '<'){
        if (page > 1) page--;
    }
    else if (text == '>'){
        if (page < max) page++;
    }
    else page = text;

    var pos = url.indexOf("page");
    if (pos == -1) {
        if (url != '?') url += '&';
        url += 'page=' + page;
    }else {
        var paras = url.split('&');
        url = '';
        for (var i = 0;i < paras.length;i++){
            pos = paras[i].indexOf('page');
            if (pos != -1) {
                paras[i] = paras[i].substring(0,pos + 5) + page;
            }
            if (i < paras.length-1) paras[i] += '&';
            url += paras[i];
        }
    }
    window.location.href = url;

});

function fill_jsp(json) {
    $("#put_list").empty();
    $("#put_options").empty();
    $("#put_pages").empty();
    //list
    var list = json.list;
    $.each(list, function (key, value) {
        var put_list =
            '<div class="item">'+
            '<a class="item-title">'+value.fullName+'</a>'+
            '<div class="item-line">'+
            '<span><i class="fa fa-star" aria-hidden="true"></i>'+value.stars+'</span>'+
            '<span><i class="fa fa-code-fork" aria-hidden="true"></i>'+value.forks+'</span>'+
            '<span><i class="fa fa-users" aria-hidden="true"></i>'+value.contributors+'</span>'+
            '</div>'+
            '<div class="item-line">'+
            value.description+
            '</div>'+
            '<div class="item-line item-line-s">'+
            'Updated on '+value.updateTime+
            '</div>'+
            '</div>';
        $("#put_list").append(put_list);
    });
    //option
    var put_options =
        '<a class="option'+(json.currentSort == 0 ? ' active' : '')+'">ALL</a>\n'+
        '<a class="option'+(json.currentSort == 1 ? ' active' : '')+'">STARS ▼</a>\n'+
        '<a class="option'+(json.currentSort == 2 ? ' active' : '')+'">FORKS ▼</a>\n'+
        '<a class="option'+(json.currentSort == 3 ? ' active' : '')+'">CONTRIBUTORS ▼</a>';
    $("#put_options").append(put_options);
    //page
    page = json.currentPage;
    var pages = json.pages;
    var put_pages_before = '<a class="page">&lt;</a>\n';
    $("#put_pages").append(put_pages_before);
    $.each(pages, function (key, value) {
        var put_pages = '<a class="page'+(value == page ? ' active' : '')+(value == "..." ? ' disable' : '')+'">'+value+'</a>\n';
        $("#put_pages").append(put_pages);
    });
    var put_pages_after = '<a class="page">&gt;</a>';
    $("#put_pages").append(put_pages_after);
}
