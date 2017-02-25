// ================================================
// HTMLロード時処理
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
$(document).ready(function(){

    $('#rpb00101Form').fileupload({
        maxNumberOfFiles:1,
        fileNameInputWidth:300,
//        acceptFileTypes: /(png)|(jpe?g)|(gif)$/i,
        dropZone:$('#rpb00101Form').find('.fileupload-buttonbar'),
        autoUpload : true, //这里为true，则选中文件后就会自动上传
        url:'rpb00101Action!uploadAjax.do'
    })
    .bind('fileuploadadded', function(e, data) {
        var regexp = /\.(png)|(jpg)|(jpeg)|(gif)$/i;
        if (!regexp.test(data.files[0].name)) {
            $('#rpb00101Form').find('.files .cancel').click();
            window.top.showAjaxMessage('上传的图片类型只能为：PNG、JPG、JPEG、GIF。');
            return false;
        }
    })
    .bind('fileuploaddone', function(e, data) {
        if (data.errorThrown == undefined) {
            $.ajax({
                url : "rpb00101Json!doImage.do",
                type : "post",
                dataType : "json",
//                data : params,
                success : function(result) {
//                    if (result.rpb00101Form.errorMessage != null) {
//                        window.top.showAjaxMessage(result.scha00101Form.errorMessage);
//                    }
                    var _recordCode = result.rpb00101Form.sessionImageDataSource[0].recordCode;
                    var _recordValue = result.rpb00101Form.sessionImageDataSource[0].recordValue;
                    if (_recordCode == 'error') {
                        window.top.showAjaxMessage(_recordValue);
                    } else {
                        $("#newQrCodeImage").attr('src', _recordValue);
                        $("#hiddenNewQrCodeImage").val(_recordValue)
                    }
                },
                error : function() {
                    window.top.showAjaxConnectFailed();
                    return;
                }
            });
            
        }
    });
//    .bind('fileuploaddestroy', function(e, data) {
//        $('.fileupload-buttonbar').show();
//    })
    
    // 読み込んだ画像データをsrcにセット
    if ($("#hiddenOldQrCodeImage").val() == "" || $("#hiddenOldQrCodeImage").val() == null) {
        $("#oldQrCodeImage").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#oldQrCodeImage").attr('src', $("#hiddenOldQrCodeImage").val());
    }
    // 読み込んだ画像データをsrcにセット
    if ($("#hiddenNewQrCodeImage").val() == "" || $("#hiddenNewQrCodeImage").val() == null) {
//        $("#newQrCodeImage").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#newQrCodeImage").attr('src', $("#hiddenNewQrCodeImage").val());
    }
});

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
function doUpdate(object) {
	window.top.showAjaxConfirm(object, 'Q00001,operation_update');
	return false;
}

//================================================
//削除処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doDelete(object) {
 window.top.showAjaxConfirm(object, 'Q00001,operation_delete');
 return false;
}

