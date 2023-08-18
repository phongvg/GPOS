function areaFunction(id){
	var areaPrinterName = "#areaPrinterName" + id;
	var areaPrintPosCode = "#areaPrintPosCode" + id;
	var areaCross = "#areaCross" + id;
	var areaVMark = "#areaVMark" + id;
	var areaEdit = "#areaEdit" + id;
	var printerName = "#printerName" + id;
	var printPosCode = "#printPosCode" + id;
	
	
	$(areaPrinterName).attr("hidden",false);
	$(areaPrintPosCode).attr("hidden",false);
	$(areaCross).attr("hidden",false);
	$(areaVMark).attr("hidden",false);
	$(areaEdit).attr("hidden",true);
	$(printerName).attr("hidden",true);
	$(printPosCode).attr("hidden",true);	
} 

function cancelAreaFunction(id){
	var areaPrinterName = "#areaPrinterName" + id;
	var areaPrintPosCode = "#areaPrintPosCode" + id;
	var areaCross = "#areaCross" + id;
	var areaVMark = "#areaVMark" + id;
	var areaEdit = "#areaEdit" + id;
	var printerName = "#printerName" + id;
	var printPosCode = "#printPosCode" + id;

	$(areaPrinterName).attr("hidden",true);
	$(areaPrintPosCode).attr("hidden",true);
	$(areaCross).attr("hidden",true);
	$(areaVMark).attr("hidden",true);
	$(areaEdit).attr("hidden",false);
	$(printerName).attr("hidden",false);
	$(printPosCode).attr("hidden",false);
}

function saveAreaFunction(id, restaurantId){
	var areaPrinterName = "#areaPrinterName" + id;
	var areaPrintPosCode = "#areaPrintPosCode" + id;
	var printerName = "#printerName" + id;
	var printPosCode = "#printPosCode" + id;
	var areaPrinterNameVal = $(areaPrinterName).val();
	var areaPrintPosCodeVal = $(areaPrintPosCode).val();
	var printerNameVal = $(printerName).val();
	var printPosCodeVal = $(printPosCode).val();
	var data = {"id":id,
				"restaurantId":restaurantId,
				"areaPrinterName":areaPrinterNameVal, 
				"areaPrintPosCode":areaPrintPosCodeVal
				};

	var contextPath = getContext();
	$.ajax({
		url : contextPath + '/restaurant/kds/area/save',
		type : 'POST',
		dataType : 'json',
		data : data,
		success : function(result){
			$(printerName).text(result['printerName']);
			$(printPosCode).text(result['printPosCode']);
			cancelAreaFunction(id);
			console.log(result);
		},
		error: function(xhr, resp, text) {
	        console.log(xhr, resp, text);
	    }
	});
}

function editKitchen(id){
	var kcPrinterName = "#kcPrinterName" + id;
	var kitchenPrinterName = "#kitchenPrinterName" + id;
	var kcArea = "#kcArea" + id;
	var kitchenArea = "#kitchenArea" + id;
	var kitchenCross = "#kitchenCross" + id;
	var kitchenVMark = "#kitchenVMark" + id;
	var kitchenEdit = "#kitchenEdit" + id;
	var kitchenDelete = "#kitchenDelete" + id;

	$(kcPrinterName).attr("hidden",true);
	$(kitchenPrinterName).attr("hidden",false);
	$(kcArea).attr("hidden",true);
	$(kitchenArea).attr("hidden",false);
	$(kitchenCross).attr("hidden",false);
	$(kitchenVMark).attr("hidden",false);
	$(kitchenEdit).attr("hidden",true);
	$(kitchenDelete).attr("hidden",true);
}

function cancelKitchen(id){
	var kcPrinterName = "#kcPrinterName" + id;
	var kitchenPrinterName = "#kitchenPrinterName" + id;
	var kcArea = "#kcArea" + id;
	var kitchenArea = "#kitchenArea" + id;
	var kitchenCross = "#kitchenCross" + id;
	var kitchenVMark = "#kitchenVMark" + id;
	var kitchenEdit = "#kitchenEdit" + id;
	var kitchenDelete = "#kitchenDelete" + id;

	$(kcPrinterName).attr("hidden",false);
	$(kitchenPrinterName).attr("hidden",true);
	$(kcArea).attr("hidden",false);
	$(kitchenArea).attr("hidden",true);
	$(kitchenCross).attr("hidden",true);
	$(kitchenVMark).attr("hidden",true);
	$(kitchenEdit).attr("hidden",false);
	$(kitchenDelete).attr("hidden",false);
}

function saveKitchenFunction(id, restaurantId){
	var kcPrinterName = "#kcPrinterName" + id;
	var kitchenPrinterName = "#kitchenPrinterName" + id;
	// var kcArea = "#kcArea" + id;
	// var kitchenArea = "#kitchenArea" + id;
	var kitchenPrinterNameVal = $(kitchenPrinterName).val();
	// var kitchenAreaVal = $(kitchenArea).val();
	var data = {"id":id,
				"restaurantId":restaurantId,
				"kitchenPrinterName":kitchenPrinterNameVal
				};

	var contextPath = getContext();
	$.ajax({
		url : contextPath + '/restaurant/kds/kitchen/save',
		type : 'POST',
		dataType : 'json',
		data : data,
		success : function(result){
			$(kcPrinterName).text(result['printerName']);
			// $(kcArea).text(result['printPosCode']);
			cancelKitchen(id);
			console.log(result);
		},
		error: function(xhr, resp, text) {
	        console.log(xhr, resp, text);
	    }
	});
}

function editPrintGroup(id){
	var printGroupKCode = "#printGroupKCode" + id;
	var printGroupKitchenCode = "#printGroupKitchenCode" + id;
	var printGroupCross = "#printGroupCross" + id;
	var printGroupVMark = "#printGroupVMark" + id;
	var printGroupEdit = "#printGroupEdit" + id;
	var printGroupDelete = "#printGroupDelete" + id;

	$(printGroupKCode).attr("hidden",true);
	$(printGroupKitchenCode).attr("hidden",false);
	$(printGroupCross).attr("hidden",false);
	$(printGroupVMark).attr("hidden",false);
	$(printGroupEdit).attr("hidden",true);
	$(printGroupDelete).attr("hidden",true);
}

function cancelPrintGroup(id){
	var printGroupKCode = "#printGroupKCode" + id;
	var printGroupKitchenCode = "#printGroupKitchenCode" + id;
	var printGroupCross = "#printGroupCross" + id;
	var printGroupVMark = "#printGroupVMark" + id;
	var printGroupEdit = "#printGroupEdit" + id;
	var printGroupDelete = "#printGroupDelete" + id;

	$(printGroupKCode).attr("hidden",false);
	$(printGroupKitchenCode).attr("hidden",true);
	$(printGroupCross).attr("hidden",true);
	$(printGroupVMark).attr("hidden",true);
	$(printGroupEdit).attr("hidden",false);
	$(printGroupDelete).attr("hidden",false);
}

function alertCancel(link) {
	var userselection = confirm("Bạn có chắc chắn muốn xóa");
	if(!userselection) {
		return false;
	} else {
		window.location.href = link;
	}
 }

 function editPrintGroupFood(id){
	var pGFood = "#pGFood" + id;
	var printGroupFood = "#printGroupFood" + id;
	var PGFCross = "#PGFCross" + id;
	var PGFVMark = "#PGFVMark" + id;
	var PGFEdit = "#PGFEdit" + id;
	var PGFDelete = "#PGFDelete" + id;

	$(pGFood).attr("hidden",true);
	$(printGroupFood).attr("hidden",false);
	$(PGFCross).attr("hidden",false);
	$(PGFVMark).attr("hidden",false);
	$(PGFEdit).attr("hidden",true);
	$(PGFDelete).attr("hidden",true);
}

function cancelPrintGroupFood(id){
	var pGFood = "#pGFood" + id;
	var printGroupFood = "#printGroupFood" + id;
	var PGFCross = "#PGFCross" + id;
	var PGFVMark = "#PGFVMark" + id;
	var PGFEdit = "#PGFEdit" + id;
	var PGFDelete = "#PGFDelete" + id;

	var pGFoodVal = $(pGFood).val();
	$(printGroupFood).chilren

	$(pGFood).attr("hidden",false);
	$(printGroupFood).attr("hidden",true);
	$(PGFCross).attr("hidden",true);
	$(PGFVMark).attr("hidden",true);
	$(PGFEdit).attr("hidden",false);
	$(PGFDelete).attr("hidden",false);
}

function savePrintGroupFunction(id){
	var printGroupKCode = "#printGroupKCode" + id;
	var printGroupKitchenCode = "#printGroupKitchenCode" + id;
	var printGroupKitchenCodeVal = $(printGroupKitchenCode).val();
	var data = {"id":id,
				"kitchenId":printGroupKitchenCodeVal
				};

	var contextPath = getContext();
	$.ajax({
		url : contextPath + '/restaurant/kds/printGroup/save',
		type : 'POST',
		dataType : 'json',
		data : data,
		success : function(result){
			$(printGroupKCode).text(result['kitchen']['code']);
			cancelPrintGroup(id);
			console.log(result);
		},
		error: function(xhr, resp, text) {
	        console.log(xhr, resp, text);
	    }
	});
}

function savePrintGroupFoodFunction(id){
	var pGFood = "#pGFood" + id;
	var printGroupFood = "#printGroupFood" + id;
	var printGroupFoodVal = $(printGroupFood).val();
	var data = {"id":id,
				"printGroupId":printGroupFoodVal
				};

	var contextPath = getContext();
	$.ajax({
		url : contextPath + '/restaurant/kds/printGroupFood/save',
		type : 'POST',
		dataType : 'json',
		data : data,
		success : function(result){
			$(pGFood).text(result['printGroup']['name']);
			cancelPrintGroupFood(id);
			console.log(result);
		},
		error: function(xhr, resp, text) {
	        console.log(xhr, resp, text);
	    }
	});
}

$document.ready(function(){
	if ($('#tab-key') != '') {
		$('#nav nav-tabs>li>a.active').removeClass("active");
		if ($('#tab-key').val() === 'kitchen'){
			$('#tabKitchen').className += " active show";
		}
	}
});