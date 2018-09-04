$(document).ready(function() {
	var errlist;
	$.post("test?app=geterrlist", {}, function(data) {
		var code = data["code"];
		if (code == 200) {
			errlist = data["data"];
			var opt = "<option value=\"-1\">选择崩溃日志</option>";
			for (var i = 0; i < errlist.length; i++) {
				var f = errlist[i];
				opt += "<option value=\"" + i + "\">" + f + "</option>"
			}
			$("#errchose").html(opt);
		} else {
			var info = data["info"];
			alert(info);
		}
	}, "json");

	$("#errchose").change(function() {
		var val = $("#errchose option:selected").val();
		if (val >= 0) {
			var file = errlist[val];
			$.get("test", {
				"app" : "geterrfile",
				"filename" : file
			}, function(data) {
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
