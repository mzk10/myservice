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
			}else{
				alert(data["info"]);
			}
			
		}, "json");
	});
});

//function str2asc(strstr) {
//	return ("0" + strstr.charCodeAt(0).toString(16)).slice(-2);
//}
//function asc2str(ascasc) {
//	return String.fromCharCode(ascasc);
//}
//
//function UrlDecode(str) {
//	var ret = "";
//	for (var i = 0; i < str.length; i++) {
//		var chr = str.charAt(i);
//		if (chr == "+") {
//			ret += " ";
//		} else if (chr == "%") {
//			var asc = str.substring(i + 1, i + 3);
//			if (parseInt("0x" + asc) > 0x7f) {
//				ret += asc2str(parseInt("0x" + asc
//						+ str.substring(i + 4, i + 6)));
//				i += 5;
//			} else {
//				ret += asc2str(parseInt("0x" + asc));
//				i += 2;
//			}
//		} else {
//			ret += chr;
//		}
//	}
//	return ret;
//}
