//================================================
//  初期化時、データ的设定。
//  【入力】 なし
//  【返却】 なし
//  【作成】 
//  【更新】
//  【概要】
//================================================
$(document).ready(function() {

});

var TAG_FILE_DIS_PATH = "tag_file_disPath";
var TAG_FILE = "tag_file";
var TAG_FILE_SERVER_PAHT = "tag_file_serverPath";
var TAG_FILE_NAME = "tag_fileName";
var TAG_FILE_TYPE = "tag_fileContentType";

// ================================================
// file tag change
// 【入力】file tag
// 【返却】なし
// 【作成】t.c
// 【更新】
// 【概要】
// ================================================
function onchangeFileTag(fileObj) {
    var filePath = getPath(fileObj);
    var fileDivObj = $(fileObj).parent().parent();
    fileDivObj.find("#" + TAG_FILE_SERVER_PAHT).val("");
    fileDivObj.find("#" + TAG_FILE_DIS_PATH).val(filePath);
}
// ================================================
// file tag keydown
// 【入力】file tag
// 【返却】なし
// 【作成】t.c
// 【更新】
// 【概要】
// ================================================
function keydownFileTag(disObj) {

    var ek = window.event.keyCode;
    // ← || delete
    if (ek == 8 || ek == 46) {
        window.event.keyCode = 0;
        var fileDivObj = $(disObj).parent().parent();
        fileDivObj.find("#" + TAG_FILE_SERVER_PAHT).val("");
        fileDivObj.find("#" + TAG_FILE_DIS_PATH).val("");
        fileDivObj.find("#" + TAG_FILE_NAME).val("");
        fileDivObj.find("#" + TAG_FILE_TYPE).val("");
        var fileObj = fileDivObj.find("#" + TAG_FILE);
        var fileObjNew = fileObj.clone(true);
        fileObj.after(fileObjNew);
        fileObj.remove();
    }
    return false;
}

// ================================================
// ファイル位置の再設定
// 【入力】ボタン
// 【返却】なし
// 【作成】g.l
// 【更新】
// 【概要】
// ================================================
function resetFile(obj) {
    var offset = $(obj).offset();
    $(obj).next().offset({
        top : offset.top,
        left : offset.left
    });
    $(obj).next().focus();
    return false;
}
