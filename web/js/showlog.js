$(document).ready(function () {
    var btn_delete = $("#btn_delete");

    btn_delete.hide();
    var log_list;
    var selected_file;

    var function_initfilelist = function (data) {
        btn_delete.hide();
        var code = data["code"];
        if (code === 200) {
            log_list = data["data"];
            var opt = "<option value=\"-1\">选择日志</option>";
            for (var i = 0; i < log_list.length; i++) {
                var f = log_list[i];
                opt += "<option value=\"" + i + "\">" + f + "</option>"
            }
            $("#logchose").html(opt);
        } else {
            var info = data["info"];
            alert(info);
        }
        $("#logcontent").html("");
    };

    $.post(
        "test?app=getloglist",
        {},
        function_initfilelist,
        "json"
    );

    $("#logchose").change(function () {
        var val = $("#logchose option:selected").val();
        if (val >= 0) {
            selected_file = log_list[val];
            $.get(
                "test",
                {
                    "app": "getlogfile",
                    "filename": selected_file
                },
                function (data) {
                    var code = data["code"];
                    if (code === 200) {
                        btn_delete.show();
                        var log = data["data"];
                        $("#logcontent").html(log);
                    } else {
                        btn_delete.hide();
                        alert(data["info"]);
                    }
                },
                "json"
            );
        } else {
            btn_delete.hide();
        }
    });

    btn_delete.click(function () {
        $.post(
            "test",
            {
                "app": "deletefile",
                "filename": selected_file
            },
            function_initfilelist,
            "json"
        );
    });

});
