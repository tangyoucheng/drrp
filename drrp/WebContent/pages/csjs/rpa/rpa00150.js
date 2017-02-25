//var lastUpdateImage;
//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {
    
    if ($('#rpa00150PageType').val() == 'readonly') {
       $('#enlargePicture').attr('src', window.top.right.rpa00150InitFileContents);
    } else {
        $signature = $('#signature');
        $signaturetools = $('#signaturetools');
//    $extraarea = $('#displayarea')
        // 字的颜色 缺省值 color: "#000",
        // 签名下划线的颜色 缺省值 "decor-color": "#eee",
        // 画布的背景颜色 缺省值 "background-color": "#fff",
        $signature.jSignature({"decor-color": "lightgrey",width:'100%', height:'1000','UndoButton': UndoButtonRenderer});
        
        
        // reset button
        
        $('<input id="reset_subbtn" type="button" class="reset_subbtn_off" style="margin-right:10px;margin-top:5px;margin-bottom:5px;float:right;" disabled>').bind('click', function(e){
            window.top.showAjaxConfirm(null, 'Q00001,operation_reset', null, resetSignature);
        }).appendTo($signaturetools);
        
        
//     $('<input type="button" value="提取数据" style="margin-left:10px;margin-top:5px;" disabled>').bind('click', function(e){
//         $("#signature").jSignature('reset')
//         var datapair = $signature.jSignature("getData","base30");
//         var datastr="data:" + datapair.join(",");
//         $extraarea.val(datastr);
        
//         var data = $signature.jSignature('getData', 'image');
//         $extraarea.html('');
//         var i = new Image()
//         i.src = 'data:' + data[0] + ',' + data[1];
//         lastUpdateImage = 'data:' + data[0] + ',' + data[1];
//         $(i).appendTo($extraarea);
//         $('#previewArea').show();
//         $('#fix_button').show();
//     }).appendTo($signaturetools);
        
        $signature.on('change', function(e){
            if ($(e.target).jSignature('getData','native').length) {
                $signaturetools.find('#reset_subbtn').prop('disabled', false);
                $signaturetools.find('#reset_subbtn').addClass('reset_subbtn').removeClass('reset_subbtn_off');
                $('#fix_button').show();
            } else {
                $signaturetools.find('#reset_subbtn').prop('disabled', true);
                $signaturetools.find('#reset_subbtn').addClass('reset_subbtn_off').removeClass('reset_subbtn');
                $('#fix_button').hide();
            }
            
        });
        
        // 初始化画布
        var _initFileContents = window.top.right.rpa00150InitFileContents;
        if (_initFileContents != undefined 
                && _initFileContents != null
                && _initFileContents != ''  ) {
            $signature.jSignature('setData', _initFileContents);
            $signaturetools.find('input').prop('disabled', false)
        }
    }
    
});


var UndoButtonRenderer = function(){
    // this === jSignatureInstance 
    var undoButtonSytle = 'position:absolute;display:none;margin:0 !important;top:auto;padding: 0.3em !important;'
    , $undoButton = $('<input type="button" value="撤销最后一笔" style="'+undoButtonSytle+'" />')
        .appendTo(this.$controlbarUpper)

    // this centers the button against the canvas.
    var buttonWidth = $undoButton.width()
//    $undoButton.css(
//        'left'
//        , Math.round(( this.canvas.width - buttonWidth ) / 2)
//    )
    // IE 7 grows the button. Correcting for that.
    if ( buttonWidth !== $undoButton.width() ) {
        $undoButton.width(buttonWidth)
    }

    return $undoButton
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
   var data = $signature.jSignature('getData', 'image');
   var lastUpdateImage = 'data:' + data[0] + ',' + data[1];
   window.top.closeDlg(window.top.right.doWritePrescriptionCallback(lastUpdateImage));
}

//================================================
//選択したTRのカラス設定
//【入力】なし
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function resetSignature() {
    $signature.jSignature('reset')
    $('#fix_button').hide();
}

