javascript: (function() {
	var objs = document.getElementsByTagName("a");
	for (var i = 0; i < objs.length; i++) {
		objs[i].onclick = function() {
			window.imagelistner.clickurl("a");
		}
	}
	var objs_img = document.getElementsByTagName("img");
	for (var i = 0; i < objs_img.length; i++) {
		objs_img[i].onclick = function() {
			window.imagelistner.clickurl("img");
		}
	}
})()