/**
 * Created by Lenovo on 2016/5/18.
 */
var page;
var data = {};

$(document).ready(function(){

    var repo_key = decodeURIComponent(getCookie("repo-key"));

    if (repo_key != '') {
        data.keyword = repo_key;
        removeCookie("repo-key");
    }

    // Filter
    var $filters = $(".filters-wrapper");
    $(".btn-filter").click(function(event) {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
            $filters.slideUp();
        } else {7
            $(this).addClass("on");
            $filters.slideDown();
        }
    });

    // ajax
    jQuery.ajax({
        async: true,
         data: data,
        url: "repos-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });
    // top-5
    jQuery.ajax({
        async: true,
        // data: "var="+value,
        url: "repos-top5",
        type: "post",
        success: function (json) {
            fill_top5(json);
        }
    });
});

function fill_jsp(json) {
    $("#put_categories").empty();
    $("#put_languages").empty();
    $("#put_years").empty();
    $("#put_list").empty();
    $("#put_options").empty();
    $("#put_pages").empty();

    //category
    var categories = json.categories;
    $("#put_categories").append('<h2>Category: </h2>');
    $.each(categories,function(key,value){
        var put_categories = '<a class="filter ' + (json.currentCategory == value ? 'active' : '') + '">' + value + '</a>\n';
        $("#put_categories").append(put_categories);
    });
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
        '<a class="option'+(json.currentSort == 1 ? ' active' : '')+'">STARS ▼</a>\n'+
        '<a class="option'+(json.currentSort == 2 ? ' active' : '')+'">FORKS ▼</a>\n'+
        '<a class="option'+(json.currentSort == 3 ? ' active' : '')+'">CONTRIBUTORS ▼</a>';
    $("#put_options").append(put_options);
    //list
    var list = json.list;
    $.each(list, function (key, value) {
        var put_list =
            '<a class="item">'+
            '<div class="item-title">'+value.fullName+'</div>'+
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
            '<div class="item-title">' + value.fullName + '</div>' +
            '<div class="item-line">' +
            '<span><i class="fa fa-star fa-fw" aria-hidden="true"></i>' + value.stars + '</span>' +
            '<span><i class="fa fa-code-fork fa-fw" aria-hidden="true"></i>' + value.forks + '</span>' +
            '</div>' +
            '<div class="item-line">' +
            value.description +
            '</div>' +
            '</a>';
        $("#put_top5").append(top);
    });
}


$(document).on("click",".item",function(event){
    var keyword = $(this).find(".item-title").text();
    parent.location.href="/repo?repo="+keyword;
});


$(document).on("click" , ".option" ,function(event){
    var text = $(event.target).text();
    var sort = 0;
    if (text == 'ALL') sort = 0;
    if (text == 'STARS ▼') sort = 1;
    if (text == 'FORKS ▼') sort = 2;
    if (text == 'CONTRIBUTORS ▼') sort = 3;
    data.sort = sort;
    jQuery.ajax({
        url: "repos-ajax",
        type: "post",
        data: data,
        success: function(json){
            fill_jsp(json);
        }
    });
});


$(document).on("click", ".filter", function(event){
    var category = '';
    var language = '';
    var year = '';

    var text = $(event.target).parent().children("h2").text();
    if (text == 'Category: '){
        if ($(event.target).text() == data.category) category = '';
        else category = $(event.target).text();
        if (category != '' && category != 'All') data.category = category; else delete data.category;
    }
    else if (text == 'Language: '){
        if ($(event.target).text() == data.language) language = '';
        else{
            language = $(event.target).text();
            //if (language == 'C++') language = 'C%2B%2B';
        }
        if (language != '' && language != 'All') data.language = language; else delete data.language;
    }
    else if (text == 'Create Time: ') {
        if ($(event.target).text() == data.year) year = '';
        else year = $(event.target).text();
        if (year == 'All') year = 0;
        data.year = year == '' ? 0 : year;
    }
    jQuery.ajax({
        url: "repos-ajax",
        type: "post",
        data: data,
        success: function(json){
            fill_jsp(json);
        }
    });

});


$(document).on("click",".page",function(event){
    var text = $(event.target).text();
    var max = $(event.target).parent().children().last().prev().text();

    if (text == '<'){
        if (page > 1) page--;
    }
    else if (text == '>'){
        if (page < max) page++;
    }
    else page = text;

    data.page = page;
    jQuery.ajax({
        url: "repos-ajax",
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