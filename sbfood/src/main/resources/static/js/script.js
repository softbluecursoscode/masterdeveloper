function setCmd(cmd) {
	document.getElementById("cmd").value = cmd;
	document.getElementById("form").submit();
}

function searchRest(categoriaId) {
	var t = document.getElementById("searchType");

	if (categoriaId == null) {
		t.value = "TEXTO";
	
	} else {
		t.value = "CATEGORIA";
		document.getElementById("categoriaId").value = categoriaId;
	}
	
	document.getElementById("form").submit();
}

function filterCardapio(categoria) {
	document.getElementById("categoria").value = categoria;
	document.getElementById("filterForm").submit();
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if (charCode >= 48 && charCode <= 57) {
		return true;
	}
	
	return false;
}