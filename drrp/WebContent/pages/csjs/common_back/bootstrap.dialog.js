
popid = 1;
function showDlg(options, onShow, onHidden) {

//                    $('dlg' + (popid-1)).modal('toggle');
    var thisid = popid;
//                    $('#myModal').modal({});
    var dlg = $('#myModal').clone();
    dlg.attr('id', 'dlg' + thisid);
    $('#myModal').before(dlg);
    var thisDlg = $('#dlg' + thisid);
    popid = popid + 1;
    if (options.style != undefined) {
        thisDlg.find('.modal-body').attr("style", options.style);
        thisDlg.find('.modal-dialog').attr("style", options.style);

    }
    thisDlg.find('iframe').attr("src", options.url);
    thisDlg.on('show.bs.modal', onShow);
    var onHiddenCom = function () {
        thisDlg.attr("id", "hiddeDlg")
        setTimeout(function () {
            thisDlg.remove();
        }, 3000);
        onHidden();
    }
    thisDlg.on('hidden.bs.modal', onHiddenCom);
//                    $('#dlg' + thisid).
    thisDlg.modal({
        backdrop: 'static',
        keyboard: 'false'});
}

function closeDlg() {
    var thisid = 0;

    $('[id*=dlg]').each(function () {
        var tmpid = parseInt($(this).attr("id").replace('dlg', ''));
        if (tmpid > thisid) {
            thisid = tmpid;
        }
    });
    $('#dlg' + thisid).modal('hide');
}

function closeAllDlg() {
    $('[id*=dlg]').modal('hide');
}