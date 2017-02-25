// ================================================
// 登録処理を行う
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doPrint(_url) {
    window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:350px;height:280px"
    , url: getContextPath(_url)}
    , undefined
    , null);
    return false;
}

