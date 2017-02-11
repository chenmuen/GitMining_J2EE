/**
 * Created by chendanni on 2016/6/10.
 */
$(document).on("click","#trends-search",function(){
    var key = $(this).parent().prev().val();
    search_trends(key);
});

$(document).on("keydown","#search-trends-bar",function(event){
    if (event.which == 13)
        var key = $("#search-trends-bar").val();
        search_trends(key);
});

function search_trends(key){
    console.log(key);

    if (key != null)
        window.location.search = "?langName=" + key;
}