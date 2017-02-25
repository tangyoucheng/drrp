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
    if ($(this).find("input[datepicker_gym]").length > 0) {
        // --------------------------------------------------
        // 日历(GGGGYY.MM)
        // --------------------------------------------------
        initDatepickerGYM('');
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

    var options = this.getOptions();
    if (options.length == 2) {

        var datepicker = "input[datepicker]";
        if (_id != null && _id.length > 0) {
            datepicker = _id;
        }
        $(document).find(datepicker).datepicker({
            showOn : "both",
            buttonText : "日历",
            buttonImage : getContextPath("/pages/img/aidbtn_calender_bg.gif"),
            buttonImageOnly : true,
            yearRange : options[0],// 系统日付の年-150年～系统日付の年＋1年
            minDate : options[1],
            beforeShow : function() {
                beforePickDate(this);
            },
            onSelect : function() {
                onPickDate(this);
            }
        });
    }

};

// ================================================
// 日历初期化
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function initDatepickerGYM(_id) {

    var options = this.getOptions();
    if (options.length == 2) {

        var datepicker = "input[datepicker_gym]";
        if (_id != null && _id.length > 0) {
            datepicker = _id;
        }
        $(window.top.right.document).find(datepicker).datepicker({
            showOn : "both",
            buttonText : "日历",
            buttonImage : getContextPath("/pages/img/aidbtn_calender_bg.gif"),
            buttonImageOnly : true,
            yearRange : options[0],// 系统日付の年-150年～系统日付の年＋1年
            minDate : options[1],
            beforeShow : function() {
                beforePickDateGYM(this);
            },
            onSelect : function() {
                onPickDateGYM(this);
            }
        });
    }
};

// ================================================
// 日历のオプション取得処理
// 【入力】選択した時刻
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function getOptions() {
    var arrayObj = new Array();
    $.ajax({
        url : getContextPath('/commonJsonData/calendarJson!doGetOptions.do'),
        success : function(result) {
            arrayObj.push(result.form.yearRange);
            arrayObj.push(result.form.minDate);
            return;
        },
        error : function() {
            window.top.showAjaxConnectFailed();
            return;
        }
    });
    return arrayObj;
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
function beforePickDateGYM(hidden) {
    $p = $(hidden).parent();
    $era = $p.find("#" + $p.attr("group") + "_era");
    $y = $p.find("#" + $p.attr("group") + "_year");
    $m = $p.find("#" + $p.attr("group") + "_month");
    $h = $p.find("#" + $p.attr("group") + "_hidden");
    var nen = wareki_to_seireki($era.attr("value"), $y.attr("value"));
    var strDay = "01";
    if ($h.attr("value") != undefined && $h.attr("value") != ''
            && $h.attr("value").length == 3) {
        strDay = $h.attr("value").split("/")[2];
    }
    $h.attr("value", nen + "/" + $m.attr("value") + "/" + strDay);
};

// ================================================
// hidden tag of datePicker -> select tag of GGGyyMMdd
// 【入力】選択した日付
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function onPickDateGYM(hidden) {
    var val = hidden.value.split("/");
    var y = val[0];
    var m = val[1];
    var d = val[2];

    $p = $(hidden).parent();
    $y = $p.find("#" + $p.attr("group") + "_year");
    $m = $p.find("#" + $p.attr("group") + "_month");
    $h = $p.find("#" + $p.attr("group") + "_hidden");
    var era = document.getElementById($p.attr("group") + "_era");

    var eraValue = seireki_to_wareki_select(parseInt(y + zeroize(m)
            + zeroize(d)));
    for ( var i = 0; i < era.options.length; i++) {
        var o = era.options[i];
        if (o.value == eraValue) {
            o.selected = true;
            break;
        }
    }
    $y.attr("value",
            seireki_to_wareki(parseInt(y + zeroize(m) + zeroize(d)), y));
    $m.attr("value", m);

    $h.attr("value", hidden.value);
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
    $era = $p.find("#" + $p.attr("group") + "_era");
    $y = $p.find("#" + $p.attr("group") + "_year");
    $m = $p.find("#" + $p.attr("group") + "_month");
    $d = $p.find("#" + $p.attr("group") + "_day");
    $h = $p.find("#" + $p.attr("group") + "_hidden");

    var nen = wareki_to_seireki($era.attr("value"), $y.attr("value"));
    $h.attr("value", nen + "/" + $m.attr("value") + "/" + $d.attr("value"));

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
    var val = hidden.value.split("/");
    var y = val[0];
    var m = val[1];
    var d = val[2];
    $p = $(hidden).parent();
    $y = $p.find("#" + $p.attr("group") + "_year");
    $m = $p.find("#" + $p.attr("group") + "_month");
    $d = $p.find("#" + $p.attr("group") + "_day");
    $h = $p.find("#" + $p.attr("group") + "_hidden");
    var era = document.getElementById($p.attr("group") + "_era");

    var eraValue = seireki_to_wareki_select(parseInt(y + zeroize(m)
            + zeroize(d)));
    for ( var i = 0; i < era.options.length; i++) {
        var o = era.options[i];
        if (o.value == eraValue) {
            o.selected = true;
            break;
        }
    }
    $y.attr("value",
            seireki_to_wareki(parseInt(y + zeroize(m) + zeroize(d)), y));
    $m.attr("value", m);
    $d.attr("value", d);

    $h.attr("value", hidden.value);
    $(hidden).blur();

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
        $era = $("#" + _calendarId + "_era");
        $y = $("#" + _calendarId + "_year");
        $m = $("#" + _calendarId + "_month");
        $d = $("#" + _calendarId + "_day");

        var year = wareki_to_seireki($era.attr("value"), $y.attr("value"));
        var month = $m.attr("value");
        var day = $d.attr("value");
        // 年齢の計算
        var jsBirth = new Date(year, month, day); // 誕生日
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
// 西暦から和暦に変更する
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function seireki_to_wareki(date, y) {
    if (date >= 19890108)
        return y - 1988; // 平成
    if (date >= 19261225)
        return y - 1925; // 昭和
    if (date >= 19120730)
        return y - 1911; // 大正
    if (date >= 18681023)
        return y - 1867; // 明治
}

// ================================================
// 西暦から和暦に変更する
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function seireki_to_wareki_select(date) {
    if (date >= 19890108)
        return "4"; // 平成
    if (date >= 19261225)
        return "3"; // 昭和
    if (date >= 19120730)
        return "2"; // 大正
    if (date >= 18681023)
        return "1"; // 明治
}

// ================================================
// 和暦から西暦に変更する
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function wareki_to_seireki(era, y) {
    if (era == "")
        return;
    // 西暦を調べる
    var base;
    switch (era) {
        case "1":
            base = 1867;
            break;
        case "2":
            base = 1911;
            break;
        case "3":
            base = 1925;
            break;
        case "4":
            base = 1988;
            break;
    }
    return base + parseInt(y);
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
