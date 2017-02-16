/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function MM_findObj(n, d) { //v4.01
    var p, i, x;
    if (!d)
        d = document;
    if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
        d = parent.frames[n.substring(p + 1)].document;
        n = n.substring(0, p);
    }
    if (!(x = d[n]) && d.all)
        x = d.all[n];
    for (i = 0; !x && i < d.forms.length; i++)
        x = d.forms[i][n];
    for (i = 0; !x && d.layers && i < d.layers.length; i++)
        x = MM_findObj(n, d.layers[i].document);
    if (!x && d.getElementById)
        x = d.getElementById(n);
    return x;
}

function MM_validateForm() { //v4.0
    var i, p, q, nm, test, num, min, max, errors = '', args = MM_validateForm.arguments;
    for (i = 0; i < (args.length - 2); i += 3) {
        test = args[i + 2];
        val = MM_findObj(args[i]);
        if (val) {
            nm = val.name;
            if ((val = val.value) != "") {
                if (test.indexOf('isEmail') != -1) {
                    p = val.indexOf('@');
                    if (p < 1 || p == (val.length - 1))
                        errors += '- ' + nm + ' must contain an e-mail address.\n';
                } else if (test != 'R') {
                    num = parseFloat(val);
                    if (isNaN(val))
                        errors += '- ' + nm + ' must contain a number.\n';
                    if (test.indexOf('inRange') != -1) {
                        p = test.indexOf(':');
                        min = test.substring(8, p);
                        max = test.substring(p + 1);
                        if (num < min || max < num)
                            errors += '- ' + nm + ' must contain a number between ' + min + ' and ' + max + '.\n';
                    }
                }
            } else if (test.charAt(0) == 'R')
                errors += '- ' + nm + ' is required.\n';
        }
    }
    if (errors)
        alert('The following error(s) occurred:\n' + errors);
    document.MM_returnValue = (errors == '');
}
function ieClicked() {
    if (document.all) {
        return false;
    }
}
function caps(element) {
    element.value = element.value.toUpperCase();
}
function firefoxClicked(e) {
    if (document.layers || (document.getElementById && !document.all)) {
        if (e.which === 2 || e.which === 3) {
            return false;
        }
    }
}
if (document.layers) {
    document.captureEvents(Event.MOUSEDOWN);
    document.onmousedown = firefoxClicked;
} else {
    document.onmouseup = firefoxClicked;
    document.oncontextmenu = ieClicked;
}
document.oncontextmenu = new Function("return false");
window.apex_search = {};
apex_search.init = function () {
    this.rows = document.getElementById('data').getElementsByTagName('TR');
    this.rows_length = apex_search.rows.length;
    this.rows_text = [];
    for (var i = 0; i < apex_search.rows_length; i++) {
        this.rows_text[i] = (apex_search.rows[i].innerText) ? apex_search.rows[i].innerText.toUpperCase() : apex_search.rows[i].textContent.toUpperCase();
    }
    this.time = false;
};

apex_search.lsearch = function () {
    this.term = document.getElementById('S').value.toUpperCase();
    for (var i = 0, row; row = this.rows[i], row_text = this.rows_text[i]; i++) {
        row.style.display = ((row_text.indexOf(this.term) !== -1) || this.term === '') ? '' : 'none';
    }
    this.time = false;
};

apex_search.search = function (e) {
    var keycode;
    if (window.event) {
        keycode = window.event.keyCode;
    }
    else if (e) {
        keycode = e.which;
    }
    else {
        return false;
    }
    if (keycode === 13) {
        apex_search.lsearch();
    }
    else {
        return false;
    }
};
function startTime() {
    var today = new Date();
    var d = today.getDate();
    var mo = today.getMonth() + 1;
    var y = today.getFullYear();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    mo = checkTime(mo);
    d = checkTime(d);
    h = checkTime(h);
    document.getElementById('txt').innerHTML = d + "-" + mo + "-" + y + "  " + h + ":" + m + ":" + s;
    var t = setTimeout(function () {
        startTime();
    }, 500);
}
$(document).ready(function () {
    $(function () {
        $("#account_no").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "crb",
                    type: "GET",
                    data: {
                        term: request.term
                    },
                    dataType: "json",
                    success: function (data) {
                        response(data);
                    }
                });
            }
        });
    });
});

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    ;  // add zero in front of numbers < 10
    return i;
}
;