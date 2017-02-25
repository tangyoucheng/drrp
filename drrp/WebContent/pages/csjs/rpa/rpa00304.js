
// ================================================
// 選択したTRのカラス設定
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setSelectedTrColor(trInfo) {
    $('#tablecloth_row1 td').removeClass("selected");
    $("#" + trInfo.id + ' td').addClass("selected");
}

//================================================
//選択したTRのカラス設定
//【入力】なし
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setSelectedData() {
    var selectedTd = $("#tablecloth_row1").find("td[class*=selected]");
    
    if (selectedTd.length == 0) {
        window.top.showAjaxMessage('请选择药品信息！');
        return false;
    } else {
        var dataIndex = selectedTd.parent()[0].id.replace("drugTr_", "");
        
        var drugIdExistFlag = false;
        $(window.top.right.document).find('#table_drguListInfo tbody input[name$=drugId]').each(function () {
            if($(this)[0].value == $("#hidDrugId_" + dataIndex).val()){
                drugIdExistFlag = true;
                return false;
            }
        });
        if (drugIdExistFlag) {
            window.top.showAjaxMessage('该药品已存在！');
            return false;
        }
        
        var _rpa00304RetuanObject = new Object();
        //药品ID
        _rpa00304RetuanObject.drugId = $("#hidDrugId_" + dataIndex).val();
        //药品名称
        _rpa00304RetuanObject.drugName = $("#hidDrugName_" + dataIndex).val();
        //厂商名称
        _rpa00304RetuanObject.manufacturerName = $("#hidManufacturerName_" + dataIndex).val();
        //价格
        _rpa00304RetuanObject.price = $("#hidPrice_" + dataIndex).val();
        //单位
        _rpa00304RetuanObject.unit = $("#hidUnit_" + dataIndex).val();
        //备注
        _rpa00304RetuanObject.notes = $("#hidNotes_" + dataIndex).val();
    	window.top.closeDlg(window.top.right.doAddDrugCallback(_rpa00304RetuanObject));
	}
}

