/**
 * Created by Lenovo on 2016/5/18.
 */
var page;
var data = {};

$(document).on("click","#normal-search",function(event){
    search();
});

$(document).on("keydown","#searchBar",function(event){
    if (event.which == 13)
        search();
});

function search(){
    var keyWord = $("#searchBar").val();

    data.keyword = keyWord;
    setCookie("search-key",encodeURIComponent(keyWord),100);
    if (type == 'User') {
        setCookie("user-key",encodeURIComponent(keyWord),100);
        window.location.href = "users";
    }else if (type == 'Repo') {
        setCookie("repo-key",encodeURIComponent(keyWord),100);
        window.location.href = "repos";
    }else if (type == 'Language'){
        setCookie("language-key",encodeURIComponent(keyWord),100);
        window.location.href = "lang-list";
    }
}
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