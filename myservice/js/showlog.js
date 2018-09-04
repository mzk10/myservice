$(document).ready(function() {

	var flist;

	$.post("test?app=getfilelist", {}, function(data) {
		var code = data["code"];
		if (code == 200) {
			flist = data["data"];
			var opt = "<option value=\"-1\">请选择</option>";
			for (var i = 0; i < flist.length; i++) {
				var f = flist[i];
				opt += "<option value=\"" + i + "\">" + f + "</option>"
			}
			$("#ipchose").html(opt);
		} else {
			var info = data["info"];
			alert(info);
			window.location = "checktoken.html";
		}
	}, "json");

	$("#ipchose").change(function() {
		var val = $("#ipchose option:selected").val();
		var file = flist[val];
		$.get("test", {
			"app" : "getlogfile",
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
	});
});
