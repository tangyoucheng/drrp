//================================================
//  初期化時、バッチ実行結果取得。
//  【入力】 なし
//  【返却】 なし
//  【作成】 
//  【更新】
//  【概要】
//================================================
$(document).ready(
    function() {
        if ($("#batchKey").val() != null) {
            // パラメタ設定
            var params = {
                "batchResultForm.batchKey" : $("#batchKey").val()
            };
            $.ajax({
                url : "batchResultJson!doGetBatchResult.do",
                type : "post",
                dataType : "json",
                data : params,
                success : function(result) {
                    if (result.batchResultForm.errorMessage == null) {
                        regetStatusCallback(result);
                    } else {
                        window.top.showAjaxMessage(result.batchResultForm.errorMessage);
                    }
                },
                error : function() {
                    window.top.showAjaxConnectFailed();
                    return;
                }
            });
        }
});

// ================================================
// 状況再取得
// 【入力】なし
// 【返却】なし
// 【作成】zjw
// 【更新】
// 【概要】
// ================================================
function regetStatus() {
    // パラメタ設定
    var params = {
        "batchResultForm.batchKey" : $("#batchKey").val()
    };
    $.ajax({
        url : "batchResultJson!doBatchRegetStatus.do",
        type : "post",
        dataType : "json",
        data : params,
        success : function(result) {
            if (result.batchResultForm.errorMessage == null) {
                regetStatusCallback(result);
            } else {
                window.top.showAjaxMessage(result.batchResultForm.errorMessage);
            }
        },
        error : function() {
            window.top.showAjaxConnectFailed();
                return;
            }
        });
}

// ================================================
// ログ取得
// 【入力】なし
// 【返却】なし
// 【作成】zjw
// 【更新】
// 【概要】
// ================================================
function getLog() {
    // 空格を削除する
    var logFileName = del_LRSpace($("#logFileName").val());
    if (logFileName == "") {
        window.top.showAjaxMessageById('W40001');
        return;
    }
    // パラメタ設定
    var params = {
        "batchResultForm.logFileName" : logFileName
    };
    $.ajax({
        url : "batchResultJson!doGetBatchLog.do",
        type : "post",
        dataType : "json",
        data : params,
        success : function(result) {
            if (result.batchResultForm.errorMessage == null) {
                connectDownload();
                try {
                    window.top.releaseVar();
                } catch (e) {
                }
            } else {
                window.top
                        .showAjaxMessage(result.batchResultForm.errorMessage);
            }
        },
        error : function() {
            window.top.showAjaxConnectFailed();
            return;
        }
    });
}

// ================================================
// 実行状況の最新状態を画面に設定する
// 【入力】実行状況の最新状態
// 【返却】なし
// 【作成】zjw
// 【更新】
// 【概要】
// ================================================
function regetStatusCallback(result) {

    // 処理開始日時_元号
    $("#shoriStartDate_era").val(result.batchResultForm.shoriKaishiNichijiGengo);
    // 処理開始日時_年
    $("#shoriStartDate_year").val(result.batchResultForm.shoriKaishiNichijiNen);
    // 処理開始日時_月
    $("#shoriStartDate_month").val(result.batchResultForm.shoriKaishiNichijiTsuki);
    // 処理開始日時_日
    $("#shoriStartDate_day").val(result.batchResultForm.shoriKaishiNichijiDate);
    // 処理開始日時_時
    $("#shoriStartTime_hour").val(result.batchResultForm.shoriKaishiNichijiToki);
    // 処理開始日時_分
    $("#shoriStartTime_minute").val(result.batchResultForm.shoriKaishiNichijiFun);
    // 処理終了日時_元号
    $("#shoriEndDate_era").val(result.batchResultForm.shoriShuuryoNichijiGengo);
    // 処理終了日時_年
    $("#shoriEndDate_year").val(result.batchResultForm.shoriShuuryoNichijiNen);
    // 処理終了日時_月
    $("#shoriEndDate_month").val(result.batchResultForm.shoriShuuryoNichijiTsuki);
    // 処理終了日時_日
    $("#shoriEndDate_day").val(result.batchResultForm.shoriShuuryoNichijiDate);
    // 処理終了日時_時
    $("#shoriEndTime_hour").val(result.batchResultForm.shoriShuuryoNichijiToki);
    // 処理終了日時_分
    $("#shoriEndTime_minute").val(result.batchResultForm.shoriShuuryoNichijiFun);

    // 実行者
    $("#shoriExecuteSha").val(result.batchResultForm.shoriExecuteSha);
    // 処理実行状況
    $("#shoriJikkoJokyo").val(result.batchResultForm.shoriJikkoJokyo);
    // 実行状況名称
    $("#jikkoJokyoMeisho").val(result.batchResultForm.jikkoJokyoMeisho);
    // 実行結果メッセージ０１
    $("#message01").attr("innerHTML", result.batchResultForm.message01);
    // 実行結果メッセージ０２
    $("#message02").attr("innerHTML", result.batchResultForm.message02);
    // 実行結果メッセージ０３
    $("#message03").attr("innerHTML", result.batchResultForm.message03);
    // 実行結果メッセージ０４
    $("#message04").attr("innerHTML", result.batchResultForm.message04);
    // 実行結果メッセージ０５
    $("#message05").attr("innerHTML", result.batchResultForm.message05);
    // ログファイル名
    $("#logFileName").val(result.batchResultForm.logFileName);
    if ($("#shoriJikkoJokyo").val() == "3" || $("#shoriJikkoJokyo").val() == "4" || $("#shoriJikkoJokyo").val() == "5") {
        // 実行結果DIV
        $("#shoriResultDiv").show();
        // // 状況再取得ボタン
        $("#regetStatusBtn").hide();
    } else {
        // 実行結果DIV
        $("#shoriResultDiv").hide();
        // 状況再取得ボタン
        $("#regetStatusBtn").show();
    }
}