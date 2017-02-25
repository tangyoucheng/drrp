//================================================
//HTMLロード時処理
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
$(document).ready(function() {

    if ($(this).find("input[datepicker]").length > 0) {

        // --------------------------------------------------
        // 時刻
        // --------------------------------------------------
        // initTimepicker('');

        // --------------------------------------------------
        // 日历
        // --------------------------------------------------
        initDatepicker('');

    }
});

// ================================================
// 時刻初期化
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function initTimepicker(_id) {
    var datepicker = "input[timepicker]";
    if (_id != null && _id.length > 0) {
        datepicker = _id;
    }
    $(window.top.right.document).find(datepicker).timepicker({
        showOn : "both",
        buttonText : "時刻",
        buttonImage : getContextPath("/pages/img/aidbtn_calender_bg.gif"),
        buttonImageOnly : true,
        timeFormat : 'hh:mm',
        beforeShow : function() {
            beforePickTime(this);
        },
        onSelect : function() {
            onPickTime(this);
        }
    });

};

// ================================================
// 日历初期化
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function initDatepicker(_id) {

//    var options = this.getOptions();
//    if (options.length == 2) {

        var datepicker = "input[datepicker]";
        if (_id != null && _id.length > 0) {
            datepicker = _id;
        }
        $(document).find(datepicker).datepicker({
        	dateFormat: 'yy年mm月dd日',
            changeMonth: true,
            changeYear: true,
            showOn : "both",
            buttonText : "日历",
            buttonImage : getContextPath("/pages/img/aidbtn_calender_bg.gif"),
            buttonImageOnly : true,
            yearRange : '-90:+10',// 系统日付の年-150年～系统日付の年＋1年
//            minDate : '+1',
            beforeShow : function() {
                beforePickDate(this);
            },
            onSelect : function() {
                onPickDate(this);
            }
        });
//    }

};



// ================================================
// 日历的调用前、時刻的设定
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function beforePickTime(hidden) {
    $p = $(hidden).parent();
    $hour = $p.find("#" + $p.attr("group") + "_hour");
    $minute = $p.find("#" + $p.attr("group") + "_minute");
    $hiddenTime = $p.find("#" + $p.attr("group") + "_hidden");

    $hiddenTime
            .attr("value", $hour.attr("value") + ":" + $minute.attr("value"));

};
// ================================================
// hidden tag of timePicker -> select tag of hh時mm刻
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function onPickTime(hidden) {
    var val = hidden.value.split(":");
    var h = val[0];
    var m = val[1];

    $p = $(hidden).parent();
    $hour = $p.find("#" + $p.attr("group") + "_hour");
    $minute = $p.find("#" + $p.attr("group") + "_minute");
    $hiddenTime = $p.find("#" + $p.attr("group") + "_hidden");

    $hour.attr("value", h);
    $minute.attr("value", m);

    $hiddenTime.attr("value", hidden.value);

};


// ================================================
// 日历的调用前、日付的设定
// 【入力】選択した日付
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function beforePickDate(hidden) {
    $p = $(hidden).parent();
    $dateObject = $p.find("#" + $p.attr("group") + "_date");
    $hiddenObject = $p.find("#" + $p.attr("group") + "_hidden");

    $hiddenObject.attr("value", $dateObject.attr("value"));

};

// ================================================
// hidden tag of datePicker -> select tag of GGGyyMMdd
// 【入力】選択した日付
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function onPickDate(hidden) {

    $p = $(hidden).parent();
    $dateObject = $p.find("#" + $p.attr("group") + "_date");

    $dateObject.attr("value", hidden.value);

    $hiddenObject.attr("value", hidden.value);
//    $(hidden).blur();

    // if($(hidden).parent().parent().find("#" + $p.attr("group") +
    // "_age").attr("value") != undefined){
    // 年齢の計算
    // var jsBirth=new Date( y, m, d ); // 誕生日
    // var arraySysdate = this.getSystemDate().split("/");
    // var jsSysdate=new Date( arraySysdate[0],arraySysdate[1],arraySysdate[2]
    // ); // 計算日
    // jsSysdate=new Date( 86400000 + jsSysdate.getTime() ); // y=計算日の翌日
    // var
    // strBirth=jsBirth.getFullYear()+jsBirth.getMonth()/100+jsBirth.getDate()/10000;
    // var
    // strSysdate=jsSysdate.getFullYear()+jsSysdate.getMonth()/100+jsSysdate.getDate()/10000;
    // var jsAge=Math.floor(strSysdate-strBirth);

    // 年齢の設定
    // $(hidden).parent().parent().find("#" + $p.attr("group") +
    // "_age").attr("value",jsAge);
    // }

};

// ================================================
// 年齢の計算
// 【入力】選択した日付
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function getAge(_calendarId) {
    if ($("#" + _calendarId + "_age").attr("value") != undefined) {
        $birthdayOBject = $("#" + _calendarId + "_date");
        var birthdayOBjectValue = $birthdayOBject.attr("value").replace('年','/').replace('月','/').replace('日','');
        // 年齢の計算
        var jsBirth = new Date(Date.parse(birthdayOBjectValue)); // 誕生日
        if (jsBirth != undefined && jsBirth != null && !isNaN(jsBirth)) {
            var arraySysdate = this.getSystemDate().split("/");
            var jsSysdate = new Date(arraySysdate[0], arraySysdate[1],
                    arraySysdate[2]); // 計算日
            // jsSysdate=new Date( 86400000 + jsSysdate.getTime() ); // y=計算日の翌日
            jsSysdate = new Date(jsSysdate.getTime());
            var strBirth = jsBirth.getFullYear() + jsBirth.getMonth() / 100
                    + jsBirth.getDate() / 10000;
            var strSysdate = jsSysdate.getFullYear() + jsSysdate.getMonth()
                    / 100 + jsSysdate.getDate() / 10000;
            var jsAge = Math.floor(strSysdate - strBirth);

            if (jsAge < 0) {
                // 年齢の設定
                $("#" + _calendarId + "_age").attr("value", '');
            } else {
                // 年齢の設定
                $("#" + _calendarId + "_age").attr("value", jsAge);
            }
        } else {
            // 年齢の設定
            $("#" + _calendarId + "_age").attr("value", '');
        }
    }

};

// ================================================
// 取得系统日付
// 【入力】なし
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function getSystemDate() {
    var systemDate = "";
    // パラメタ設定
    $
            .ajax({
                async:false,
                url : getContextPath('/commonJsonData/calendarJson!doGetSystemDate.do'),
                success : function(result) {
                    systemDate = result.form.systemDate;
                    return;
                },
                error : function() {
                    window.top.showAjaxConnectFailed();
                    return;
                }
            });
    return systemDate;
}


// ================================================
// leftPad
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
function zeroize(value, length) {

    if (!length)
        length = 2;

    value = String(value);

    for ( var i = 0, zeros = ''; i < (length - value.length); i++) {

        zeros += '0';

    }

    return zeros + value;

};
