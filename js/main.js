(function () {
  var toc = document.getElementById('markdown-toc');
  var parent = document.getElementById('static_markdown');
  var copyToc = toc.cloneNode(true);
  
  var lis = $(copyToc).find('ul>li>ul>li');
  for (var i = 0; i < lis.length; i++) {
	var childUl = lis[i].children[1];
	if (childUl) {
	  lis[i].removeChild(childUl);
	}
  }
  parent.appendChild(copyToc);
})();