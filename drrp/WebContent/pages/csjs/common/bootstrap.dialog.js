// 弹出的最顶端窗口的编号
topDialogNumber = 1;
// 弹出一个页面
function openPage(options, onShow, onHidden) {

    
    var thisDlg = showDlg(options, onShow, onHidden);
    thisDlg.find('.modal-header').css("display", 'block');


}
// 弹出一个对话框
function showDlg(options, onShow, onHidden) {

//    $(window.top.document).find('div[id^=dlg]').css("display", 'none');
    //  $('dlg' + (popid-1)).modal('toggle');
//    var thisid = topDialogNumber;
    //  $('#myModal').modal({});
    var newDialog = $('#myModal').clone();
    newDialog.attr('id', 'dialog_' + topDialogNumber);
    $('#myModal').before(newDialog);
    var thisDlg = $('#dialog_' + topDialogNumber);
    if (options.style != undefined) {
        thisDlg.find('.modal-dialog').attr("style", options.style);
        // 设定弹出窗体body的高度
        var bodyHeight = thisDlg.find('.modal-dialog').height()-45;
        thisDlg.find('.modal-body').height(bodyHeight + 'px');
        thisDlg.find('.modal-body').css({'padding-bottom':'0'});
    //    thisDlg.find('.modal-body').attr("style", options.style);
    
    }

//    $(window.top.document).find('div[id^=dlg]').css({'z-index':'1050'});
//    thisDlg.css({'z-index':2000 + thisid});
    thisDlg.find('iframe').attr("src", options.url);
    thisDlg.on('show.bs.modal', onShow);
    thisDlg.on('shown.bs.modal', changeDialogZindex);
    
    var onHiddenCom = function () {
        thisDlg.attr("id", "hiddeDlg")
        var topDialogNumber = getTopDialogNumber();
        $('#dialog_' + topDialogNumber + '_backdrop').css({'display':'block'});
        $('#dialog_' + topDialogNumber).css({'overflow-y':'auto'});
        setTimeout(function () {
            thisDlg.remove();
        }, 1000);
        if(onHidden != null && onHidden != undefined){
            onHidden();
        }
    
    }
    thisDlg.on('hidden.bs.modal', onHiddenCom);
    thisDlg.modal({
        backdrop: 'static',
        keyboard: 'false'
    });

    // 设定顶端以外的对话框的灰色背景不可见
//    var thisDlg = $('#dialog_' + topDialogNumber);
//    $('#dialog_' + topDialogNumber + '_backdrop').css({'z-index':1000 + 2*topDialogNumber-2});
//    $('#dialog_' + topDialogNumber).css({'z-index':1000 + 2*topDialogNumber-1});
//    $('.modal-backdrop').each(function () {
//        if ($(this)[0].id != 'dialog_' + topDialogNumber + '_backdrop') {
//            $(this).css({'display':'none'});
//        }
//    });

    topDialogNumber = topDialogNumber + 1;

    return thisDlg;
}

// 关闭弹出窗口，执行回调函数。
function closeDlg(_callBack) {

    var topDialogNumber = getTopDialogNumber();
    var _thisDlg = $$('#dialog_' + topDialogNumber);
    var onHiddenCom = function () {
        _thisDlg.attr("id", "hiddeDlg")
        var topDialogNumber = getTopDialogNumber();
        $('#dialog_' + topDialogNumber).css({'overflow-y':'scroll','display':'block'});
        setTimeout(function () {
            thisDlg.remove();
        }, 3000);
        if(onHidden != null && onHidden != undefined){
            onHidden();
        }
    }
    _thisDlg.on('hidden.bs.modal', onHiddenCom);
    _thisDlg.modal('hide');
}
// 关闭弹出窗口
function closeDlg() {
    var topDialogNumber = getTopDialogNumber();
    $('#dialog_' + topDialogNumber).modal('hide');
}

// 取得最顶端窗口的编号
function getTopDialogNumber() {
    var returnDialogNumber = 0;
    $('[id*=dialog_]').each(function () {
        var _dialogNumber = parseInt($(this).attr("id").replace('dialog_', ''));
        if (_dialogNumber != undefined && _dialogNumber > returnDialogNumber) {
            returnDialogNumber = _dialogNumber;
        }
    });
    return returnDialogNumber;
}

// 设定对话框的z-index
function changeDialogZindex() {
    $(".modal-backdrop").each(function () {
        $(this).css("z-index", parseInt($(this).css("z-index")) - 11);
    });
    $(".modal").each(function () {
        if ($(this).attr("id") == "myModal") {
            return;
        }
        $(this).css("z-index", parseInt($(this).css("z-index")) - 11);
    });
}


// 关闭所有的弹出窗口
function closeAllDlg() {
    $('[id*=dialog_]').modal('hide');
}

