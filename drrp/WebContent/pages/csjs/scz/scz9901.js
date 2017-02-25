// デバッグ
isDebug = true;

//================================================
//  閉じる処理
//  【入力】なし
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
//window.onbeforeunload = function(){
//	window.event.returnValue="要关闭当前窗口吗？";
//};


var timeOutValue = maxTime;

//================================================
// ロック画面的调用
//【入力】なし
//【返却】なし
//【作成】tdm
//【更新】
//【概要】
//================================================
function timeListener(){
	
    timeOutValue = timeOutValue - 1;

    try {
		if (isDebug) {
//			document.title = "あと " + timeOutValue + " 秒 でロック";
//			document.title = "公営住宅管理";
		}
	} catch(e) {
	}
	
    if (timeOutValue <= 0) {
        timeOutValue = maxTime;
        PubjyutakuLogin.show(function(result){
        	if (result.data.resultFlg) {
        		setTimeout(timeListener, 1000);
        	} else {
        		window.location.replace(getContextPath("/scz/scz990101Action!doLoginOut.do"));
        	}
        });
    }
    else {
        setTimeout(timeListener, 1000);
    }
}

//================================================
//ロックタイマーリセット
//【入力】なし
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function resetTimeInterval() {
	timeOutValue = maxTime;
}

//================================================
//タイマーeventセット
//【入力】なし
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function addTimerEvent(_target) {
	_target.blur(function(){
		resetTimeInterval();
	}).click(function(){
		resetTimeInterval();
	}).dblclick(function(){
		resetTimeInterval();
	}).focus(function(){
		resetTimeInterval();
	}).keydown(function(){
		resetTimeInterval();
	}).keypress(function(){
		resetTimeInterval();
	}).mousedown(function(){
		resetTimeInterval();
	}).mousemove(function(){
		resetTimeInterval();
	}).mouseover(function(){
		resetTimeInterval();
	}).scroll(function(){
		resetTimeInterval();
	});
}

//================================================
// 画面の初期化処理
//【入力】なし
//【返却】なし
//【作成】tdm
//【更新】
//【概要】
//================================================
$(document).ready(function(){
    addTimerEvent($(document));
//    timeListener();
    if ($('#scz9901AdUrl').val().length > 0) {
        window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:800px;height:570px"
            , url: $('#scz9901AdUrl').val()}
        , undefined
        , null);
    }
});

function reinitIframe(){
	
    try{
    	
//    	document.getElementById("left").height = $(document).height()-document.getElementById("head").height-35;
//    	document.getElementById("right").height = $(document).height()-document.getElementById("head").height-35;
    	document.getElementById("left").height = document.documentElement.clientHeight-$('iframe[id=head]').height()-35;
    	document.getElementById("right").height = document.documentElement.clientHeight-$('iframe[id=head]').height()-35;
    	
        if (left.document.body.scrollHeight > right.document.body.scrollHeight) {
            var bHeight = document.all["left"].contentWindow.document.body.scrollHeight;
            //var dHeight = document.all["left"].contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(450, bHeight);
            document.getElementById("right").scrollHeight= height;
            document.getElementById("left").scrollHeight = height;
          } else {
            var bHeight = document.all["right"].contentWindow.document.body.scrollHeight;
            //var dHeight = document.all["right"].contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(450, bHeight);
            document.getElementById("right").scrollHeight= height;
            document.getElementById("left").scrollHeight = height;
          }
        
    }catch (ex){}
    try {
    	right.isFinishAdjustFrameSize = true;
    } catch(ex) {
    }
}
//window.setInterval("reinitIframe()", 200);


$( window ).resize(function(){
	reinitIframe();
});


//================================================
// 画面のトップに戻る
// 【入力】なし
// 【返却】なし
// 【作成】tdm
// 【更新】
// 【概要】
//================================================
function goTop() {
	window.scrollTo(0, 0);
}
