/**
 * Created by raychen on 16/5/27.
 */

$(document).ready(function () {
    var value = 3;
    jQuery.ajax({
        async: true,
        data: "var="+value,
        url: "load-ajax",
        type: "post",
        success: function (json) {
            var dom = "<h1>"+json.msg+"</h1>";
            $("#load").append(dom);
        }
    });
});