var rpa00150InitFileContents;
//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {
    
    //处方类型初始化
    showPrescriptionType()

    //处方类型切换
    $('input[name="rpa00101Form.prescriptionType"]').click(function() {
        doChangePrescriptionType();
    });
    
    // 読み込んだ画像データをsrcにセット
    if ($("#fileContents").val() == "" || $("#fileContents").val() == null) {
        $("#kaoPicture").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#kaoPicture").attr('src', $("#fileContents").val());
    }

});


// ================================================
// 患者信息录入
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doSearchPatient() {
    window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:800px;height:570px"
        , url: getContextPath('/rpa002/rpa00204Action!doInit.do')}
        , undefined
        , null);

    return false;
}

//================================================
//登録処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function searchPatientCallback(_rerurnObject) {
    if (_rerurnObject != undefined) {
        $('#patientId').val(_rerurnObject.patientId);
        $('#patientName').val(_rerurnObject.patientName);
        if (_rerurnObject.sex == "1"
            || _rerurnObject.sex == "2") {
            $("input[name='rpa00101Form.sexId'][value='"+_rerurnObject.sex+"']").attr("checked",true);
        }
        $('#birthday_date').attr("value",_rerurnObject.birthday);
        $('#birthday_hidden').attr("value",_rerurnObject.birthday);
        getAge('birthday');
//        $('#age').val(_rerurnObject.age);
        
//        $('#phoneNumber').val(_rerurnObject.phoneNumber);
        $('#ceelNumber').val(_rerurnObject.ceelNumber);
        $('#idNumber').val(_rerurnObject.idNumber);
        $('#addr').val(_rerurnObject.addr);
        
        $('#prescriptionContent').val(_rerurnObject.contents);
        $('#fileContents').val(_rerurnObject.fileContents);
        $('#price').val(_rerurnObject.price);
        
        if (_rerurnObject.prescriptionType != "1"
            || _rerurnObject.prescriptionType != "2") {

            $("input[name='rpa00101Form.prescriptionType'][value='"+_rerurnObject.prescriptionType+"']").attr("checked",true);
            
            //处方类型初始化
            showPrescriptionType()
            
            if ($("#fileContents").val() == "" || $("#fileContents").val() == null) {
                $("#kaoPicture").attr('src', "../.." + nonPictureUrl);
            } else {
                $("#kaoPicture").attr('src', $("#fileContents").val());
            }
        }
    }
}

//================================================
//处方类型显示控制
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function showPrescriptionType() {
    var prescriptionType = $('input[name="rpa00101Form.prescriptionType"]:checked').val();
    if (prescriptionType == "0") {
        $('#prescriptionContentTr').hide();
        $('#clearPrescriptionContentButton').hide();
        $('#prescriptionFilePreviewTr').hide();
        $('#addPrescriptionImageButton').hide();
    }
    if (prescriptionType == "1") {
        $('#prescriptionContentTr').hide();
        $('#clearPrescriptionContentButton').hide();
        $('#prescriptionFilePreviewTr').show();
        $('#addPrescriptionImageButton').show();
    }
    if (prescriptionType == "2") {
        $('#prescriptionContentTr').show();
        $('#clearPrescriptionContentButton').show();
        $('#prescriptionFilePreviewTr').hide();
        $('#addPrescriptionImageButton').hide();
    }
}

//================================================
//登録処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doEntry(object) {
//    doCalcDrugPrice();
    window.top.showAjaxConfirm(object, 'Q00001,operation_entry');
    return false;
}

//================================================
//处方类型切换
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doChangePrescriptionType() {

    showPrescriptionType()
    
    return false;
}

//================================================
//登録処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doWritePrescription() {
    window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:850px;height:1180px"
        , url: getContextPath("/rpa001/rpa00150Action!doInit.do")}
        , undefined
        , null);

    return false;
}

//================================================
//处方手写录入回调函数
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doWritePrescriptionCallback(_rerurnObject) {
    if (_rerurnObject != undefined) {
    //    var i = new Image();
    //    i.src = _rerurnObject;
    //    $(i).appendTo($('#rpa00101ImagePreview'));
        $("#fileContents").val(_rerurnObject);
        $("#kaoPicture").attr('src', _rerurnObject);
        
        rpa00150InitFileContents = _rerurnObject;
    }
}


//================================================
//清空处方内容
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function clearPrescriptionContent() {
    $("#prescriptionContent").val('');
}

//================================================
//放大图片
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doEnlargeImage() {
    if (rpa00150InitFileContents != null && rpa00150InitFileContents != '') {
        window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:850px;height:1180px"
            , url: getContextPath("/rpa001/rpa00150Action!doInit.do?rpa00150Form.pageType=readonly")}
        , undefined
        , null);
    }
    return false;
}


//================================================
//药品录入
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doAddDrug() {
    window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:800px;height:570px"
      , url: getContextPath("/rpa001/rpa00304Action!doInit.do")}
      , undefined
      , null);

  return false;
}

//================================================
//药品录入回调函数
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doAddDrugCallback(_rerurnObject) {
    if (_rerurnObject != undefined) {
        if ($('#table_drguListInfo').length == 0) {
            var drugTableHtml = '<table id="table_drguListInfo" class="row_table" style="width: 600px;margin-left: 0px;">';
            drugTableHtml += '<col width="7%" />';
            drugTableHtml += '<col width="auto" />';
            drugTableHtml += '<col width="25%" />';
            drugTableHtml += '<col width="100px" />';
            drugTableHtml += '<col width="100px" />';
            drugTableHtml += '<col width="60px" />';
            drugTableHtml += '<thead><tr>';
            drugTableHtml += '<th style="font-size:1em;vertical-align:middle;">NO</th>';
            drugTableHtml += '<th style="font-size:1em;vertical-align:middle;">药品名称</th>';
            drugTableHtml += '<th style="font-size:1em;vertical-align:middle;">厂商名称</th>';
            drugTableHtml += '<th style="font-size:1em;vertical-align:middle;">价格(元)/规格</th>';
            drugTableHtml += '<th style="font-size:1em;vertical-align:middle;">数量<img class=icon_mustentry style="MARGIN-TOP: 2px" alt=必须 src="'+getContextPath("/pages/img/icon_mustentry.gif")+'"/></th>';
            drugTableHtml += '<th style="font-size:1em;" ></th>';
            drugTableHtml += '</tr></thead>';
            drugTableHtml += '<tbody></tbody>';
            drugTableHtml += '</table>';
            $("#addDrugInfoTr").append(drugTableHtml);
            
        }
        
        var drugTableDataHtml = '<tr>';
        drugTableDataHtml += '<td style="font-size:1em;text-align: center;vertical-align:middle;">NO</td>';
        // 药品名称
        drugTableDataHtml += '<td title="'+_rerurnObject.drugName+'" class="txt_overflow" style="font-size:1em;vertical-align:middle;">';
        drugTableDataHtml += _rerurnObject.drugName;
        drugTableDataHtml += '</td>';
        // 厂商名称
        drugTableDataHtml += '<td title="'+_rerurnObject.manufacturerName+'" class="txt_overflow" style="font-size:1em;vertical-align:middle;">';
        drugTableDataHtml += _rerurnObject.manufacturerName;
        drugTableDataHtml += '</td>';
        // 价格/规格
        drugTableDataHtml += '<td title="'+_rerurnObject.price + '/' + _rerurnObject.unit +'" class="txt_overflow" style="font-size:1em;vertical-align:middle;">';
        drugTableDataHtml += _rerurnObject.price + '/' + _rerurnObject.unit;
        drugTableDataHtml += '</td>';
        // 数量
        drugTableDataHtml += '<td style="font-size:1em;">';
        drugTableDataHtml += '<input name="rpa00101Form.subForm1['+$('#table_drguListInfo tbody tr').length+'].quantity" type="text" style="width:85px;" class="mustdata" maxLength="10"></input>';
        drugTableDataHtml += '</td>';
        // 删除和隐藏变量
        drugTableDataHtml += '<td style="font-size:1em;text-align: center;">';
        drugTableDataHtml += '<input name="rpa00101Form.subForm1['+$('#table_drguListInfo tbody tr').length+'].drugId" type="hidden" value="'+_rerurnObject.drugId+'" />';
        drugTableDataHtml += '<input name="rpa00101Form.subForm1['+$('#table_drguListInfo tbody tr').length+'].drugName" type="hidden" value="'+_rerurnObject.drugName+'" />';
        drugTableDataHtml += '<input name="rpa00101Form.subForm1['+$('#table_drguListInfo tbody tr').length+'].manufacturerName" type="hidden" value="'+_rerurnObject.manufacturerName+'" />';
        drugTableDataHtml += '<input name="rpa00101Form.subForm1['+$('#table_drguListInfo tbody tr').length+'].price" type="hidden" value="'+_rerurnObject.price+'" />';
        drugTableDataHtml += '<a class="aid_btn_r" href="javascript:void(0);" onclick="doRemoveDrug(this.parentNode.parentNode);"><span>删除</span></a>';
        drugTableDataHtml += '</td>';
        
        drugTableDataHtml += '</tr>';
        $('#table_drguListInfo tbody').append(drugTableDataHtml)

        // 刷新药品一览信息
        refreshDrygListInfo();
        // 自动计算按钮制御
        if ($('#table_drguListInfo tbody tr').length > 0) {
            $('#drugPriceAutoCalc').show();
        }
    }
}

//删除药品清单中的药品
function doRemoveDrug(_deletedObhect){
    window.top.showAjaxConfirm(null, 'Q00001,operation_delete', false,
            function(ok){
                if(ok ==false){
                    return;
                }
                $("#table_drguListInfo tbody")[0].deleteRow(_deletedObhect.rowIndex-1);
                // 刷新药品一览信息
                refreshDrygListInfo();
                // 自动计算按钮制御
                if ($('#table_drguListInfo tbody tr').length == 0) {
                    $('#table_drguListInfo').remove();
                    $('#drugPriceAutoCalc').hide();
                    $('#sumPrice').val('');
                }
    }
    );
}

// 刷新药品一览信息
function refreshDrygListInfo(){        
    $('#table_drguListInfo tbody tr').each(function () {
        var _rowIndex = $(this)[0].rowIndex;
        var _recordIndex = $(this)[0].rowIndex - 1;
        $(this).find("td:eq(0)").text($(this)[0].rowIndex);
        
        var regExp_index = new RegExp("(\.subForm1\\[)(\\d{1,})+(\\]\.)", "g");
        var strHtml = $(this).html().replace(regExp_index,
                function($0, $1, $2, $3) {
            return $1 + _recordIndex + $3;
        });
        $(this).html(strHtml);
    });
    
}

// 计算药品价格
function doCalcDrugPrice(){
    var drugSumPrice = 0;
    $('#table_drguListInfo tbody tr').each(function () {
        var _drugPrice = $(this).find("td:eq(3)").text().trim();
        var _drugPrices = _drugPrice.split("/")
        var _drugQuantity = $(this).find("td:eq(4) input")[0].value;
        if (!isNaN(Number(_drugPrices[0])) && !isNaN(Number(_drugQuantity))) {
            drugSumPrice = CommonUtilJs.accAdd(drugSumPrice, CommonUtilJs.accMul(_drugPrices[0],_drugQuantity));
        }
    });
    if(drugSumPrice != 0){
        $('#sumPrice').val(drugSumPrice);
    }
    
}

