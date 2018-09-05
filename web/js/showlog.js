$(document).ready(function () {
    var loglist;
    $.post("test?app=getloglist", {}, function (data) {
        var code = data["code"];
        if (code == 200) {
            loglist = data["data"];
            var opt = "<option value=\"-1\">选择日志</option>";
            for (var i = 0; i < loglist.length; i++) {
                var f = loglist[i];
                opt += "<option value=\"" + i + "\">" + f + "</option>"
            }
            $("#logchose").html(opt);
        } else {
            var info = data["info"];
            alert(info);
        }
    }, "json");

    $("#logchose").change(function () {
        var val = $("#logchose option:selected").val();
        if (val >= 0) {
            var file = loglist[val];
            $.get("test", {
                "app": "getlogfile",
                "filename": file
            }, function (data) {
                var code = data["code"];
                if (code == 200) {
                    var log = data["data"];
                    $("#logcontent").html(log);
                } else {
                    alert(data["info"]);
                }

            }, "json");
        }
    });
});
