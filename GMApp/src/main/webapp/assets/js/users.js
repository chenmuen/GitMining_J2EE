/**
 * Created by Lenovo on 2016/5/18.
 */

var page;
var data = {};

$(document).ready(function(){

    var user_key = decodeURIComponent(getCookie("user-key"));

    if (user_key != '') {
        data.keyword = user_key;
        removeCookie("user-key");
    }

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
         data: data,
        url: "users-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
    // top-5
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "users-top5",
        type: "post",
        success: function (json) {
            fill_top5(json);
        }
    });
});


function fill_jsp(json) {
    $("#put_languages").empty();
    $("#put_years").empty();
    $("#put_list").empty();
    $("#put_options").empty();
    $("#put_pages").empty();

    //language
    var languages = json.languages;
    $('#put_languages').append('<h2>Language: </h2>');
    $.each(languages,function(key,value){
        var put_languages = '<a class="filter ' + (json.currentLanguage == value ? 'active' : '') + '">' + value + '</a>\n';
        $("#put_languages").append(put_languages);
    });
    //year
    var years = json.years;
    $('#put_years').append('<h2>Create Time: </h2>');
    $.each(years,function(key,value){
        var put_years = '<a class="filter ' + (json.currentYear == value ? 'active' : '') + '">' + value + '</a>\n';
        $("#put_years").append(put_years);
    });

    //option
    var put_options =
        '<a class="option'+(json.currentSort == 0 ? ' active' : '')+'">ALL</a>\n'+
        '<a class="option'+(json.currentSort == 1 ? ' active' : '')+'">FOLLOWERS ▼</a>\n'+
        '<a class="option'+(json.currentSort == 2 ? ' active' : '')+'">REPOSITORIES ▼</a>\n';
    $("#put_options").append(put_options);
    //list
    var list = json.list;
    $.each(list, function (key, value) {
        var put_list =
            '<a class="item">'+
                '<div class="avatar"><img src="' + value.avatar + '"></div>' +
                '<div class="sibling">' +
                '<div class="item-title">' + value.name + '</div>' +
                '<div class="item-line">' +
                    '<span><i class="fa fa-users fa-fw" aria-hidden="true"></i>' + value.followers + '</span>' +
                    '<span><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>' + value.repos + '</span>' +
                '</div>' +
                '<div class="item-line">';

                        if (value.location != null){
                            put_list += '<span><i class="fa fa-map-marker fa-fw" aria-hidden="true"></i>' + value.location + '</span>';
                        }
                        if (value.email != null){
                            put_list += '<span><i class="fa fa-envelope fa-fw" aria-hidden="true"></i>' + value.email + '</span>';
                        }
                    put_list += '<span><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Join on ' + value.joinTime + '</span>' +
                '</div>' +
            '</div>' +
        '</a>';
        $("#put_list").append(put_list);
    });
    //featured recommendations
    //null

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


function fill_top5(json){

    //top-5
    var top5 = json.tops;
    $.each(top5, function(key,value){
        var top =
            '<a class="item">' +
            '<div class="avatar avatar-s"><img src="' + value.avatar + '"></div>' +
            '<div class="sibling">' +
            '<div class="item-title">' + value.name + '</div>' +
            '<div class="item-line">' +
            '<span><i class="fa fa-users fa-fw" aria-hidden="true"></i>' + value.followers + '</span>' +
            '<span><i class="fa fa-github-alt fa-fw" aria-hidden="true"></i>' + value.repos + '</span>' +
            '</div>' +
            '</div>' +
            '</a>';
        $("#put_top5").append(top);
    });
}

$(document).on("click",".item",function(event){
    var keyword = $(this).find(".item-title").text();
    parent.location.href="/user?login="+keyword;
});



$(document).on("click",".option",function(event){
    var text = $(event.target).text();
    var sort = 0;
    if (text == 'ALL') sort = 0;
    if (text == 'FOLLOWERS ▼') sort = 1;
    if (text == 'REPOSITORIES ▼') sort = 2;
    data.sort = sort;
    jQuery.ajax({
        async: true,
        data: data,
        url: "users-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
});


$(document).on("click",".filter",function(event){
    var language = '';
    var year = '';
    
    var text = $(event.target).parent().children("h2").text();

    if (text == 'Language: '){
        if ($(event.target).text() == data.language) language = '';
        else {
            language = $(event.target).text();
        }
        if (language != '' && language != 'All') data.language = language; else delete data.language;
    }
    else if (text == 'Create Time: ') {
        if ($(event.target).text() == data.year) year = '';
        else year = $(event.target).text();
        if (year == 'All') year = 0;
        data.year = year == '' ? 0 : year;
    }
    console.log(year);
    console.log(data);

    jQuery.ajax({
        url: "users-ajax",
        type: "post",
        data: data,
        success: function(json){
            fill_jsp(json);
        }
    });
});


function getCookie(name){
    //cookie中的数据都是以分号加空格区分开
    var arr = document.cookie.split("; ");
    for(var i=0; i<arr.length; i++){
        if(arr[i].split("=")[0] == name){
            return arr[i].split("=")[1];
        }
    }
    //未找到对应的cookie则返回空字符串
    return '';
}
//删除cookie
function removeCookie(name){
    document.cookie = name+"=;expires="+(new Date(0)).toGMTString();
}