/**
 * Created by chendanni on 2016/6/1.
 */

var page;
var data = {};

$(document).ready(function(){

    //console.log('in ' + decodeURIComponent(getCookie("language-application")));
    var language_application = decodeURIComponent(getCookie("language-application"));
    var language_key = decodeURIComponent(getCookie("language-key"));
    var search_key = decodeURIComponent(getCookie("search-key"));
    console.log(language_application);
    if (language_application != '') {
        data.application = language_application;
        removeCookie("language-application");
    }
    if (language_key != '') {
        data.keyword = language_key;
        removeCookie("language-key");
    }
    if (search_key != '') {
        $("#searchBar").val(search_key);
        removeCookie("search-key");
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
        url: "lang-list-ajax",
        type: "post",
        success: function (json) {
            fill_jsp(json);
        }
    });

});

function fill_jsp(json){
    $("#put_categories").empty();
    $("#put_applications").empty();
    $("#put_options").empty();
    $("#put_list").empty();
    $("#put_pages").empty();

    var categories = json.categories;
    $("#put_categories").append('<h2>Category: </h2>');
    $.each(categories,function(key,value){
        var put_category =
        '<a class="filter ' + (value == json.currentCategory ? 'active' : '') + '">' + value + '</a>\n';
        $("#put_categories").append(put_category);
    });


    var applications = '';
    $("#put_applications").append('<h2>Application: </h2>');
    $.each(json.applications,function(key,value){
        applications +=
            '<a class="filter ' + (value == json.currentApplication ? 'active' : '') + '">' + value + '</a>\n';
    });
    $("#put_applications").append(applications);

    var options =
        '<a class="option'+(json.currentSort == 0 ? ' active' : '') + '">ALL</a>\n' +
        '<a class="option'+(json.currentSort == 1 ? ' active' : '') + '">REPOSITORIES ▼</a>\n' +
        '<a class="option'+(json.currentSort == 2 ? ' active' : '') + '">DEVELOPERS ▼</a>\n';
    $("#put_options").append(options);
    var list = '';
    $.each(json.list,function(key,value){
       list +=
        '<div class="card grid-1-5 grid-1-4-lg grid-1-3-md grid-1-2-sm-min grid-1-1-xs">' +
            '<a class="card-lang card-blue">' +
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
    });
    $("#put_list").append(list);

    page = json.currentPage;
    var pages = '<a class="page">&lt;</a>\n';
    $.each(json.pages,function(key,value){
        pages +=
            '<a class="page'+(value == page ? ' active' : '')+(value == "..." ? ' disable' : '')+'">'+value+'</a>\n';
    });
    pages += '<a class="page">&gt;</a>';
    $("#put_pages").append(pages);
}

$(document).on("click",".card-lang",function(event){
    var key = $(this).find("h3").text();
    window.location.href = "/lang?lang="+key;
});

$(document).on("click",".filter",function(event){
    var category = '';
    var application = '';

    var text = $(event.target).parent().children("h2").text();
    if (text == 'Category: '){
        if ($(event.target).text() == data.category) category = '';
        else category = $(event.target).text();
        if (category != '' && category != 'All') data.category = category; else delete data.category;
    }
    else if (text == 'Application: '){
        if ($(event.target).text() == data.application) application = '';
        else{
            application = $(event.target).text();
            //if (Application == 'C++') Application = 'C%2B%2B';
        }
        if (application != '' && application != 'All') data.application = application; else delete data.application;
    }
    jQuery.ajax({
        url: "lang-list-ajax",
        type: "post",
        data: data,
        success: function (json) {
            fill_jsp(json);
        }
    });
});

$(document).on("click",".option",function(event){
    var text = $(event.target).text();
    var sort = 0;
    if (text == 'ALL') sort = 0;
    if (text == 'REPOSITORIES ▼') sort = 1;
    if (text == 'DEVELOPERS ▼') sort = 2;
    data.sort = sort;
    jQuery.ajax({
        url: "lang-list-ajax",
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

    //console.log(page);

    data.page = page;
    jQuery.ajax({
        url: "lang-list-ajax",
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