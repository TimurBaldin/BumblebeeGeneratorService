const URL_CASE1 = 'http://localhost:8080/creatortest/boundary?Len=';
const URL_CASE2 = 'http://localhost:8080/creatortest/specsymbol?SPECIAL_LEN=';
const URL_CASE3 = 'http://localhost:8080/creatortest/intboundary?BoundaryIntEnd=';
const URL_CASE4 = 'http://localhost:8080/creatortest/intrange?MaxIntVal=';
const CSV_REPORT = 'http://localhost:8080/creatortest/csvreport?docname=';
const EXCEL_REPORT = 'http://localhost:8080/creatortest/excelreport?docname=';

function savecheckboundarytest(obj) {
    if (obj.len.value != undefined && obj.len.value != null && obj.len.value != ''
        && obj.quantity.value != '' && obj.quantity.value != undefined) {
        var url1 = URL_CASE1 + obj.len.value + '&INCREASE_QUANTITY=' + obj.quantity.value + '&Low=' + obj.low.value + '&Cap=' + obj.cap.value + '&NullValue=' + obj.NullValue.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();


    } else {
        alert('Invalid input for Boundary test lines');
    }

}

function savecheckspecialtest(obj) {
    if (obj.len.value != undefined && obj.len.value != null && obj.len.value != ''
        && obj.quantity.value != '' && obj.quantity.value != undefined) {
        var url1 = URL_CASE2 + obj.len.value + '&INCREASE_QUANTITY=' + obj.quantity.value + '&ESC_SPECIAL=' + obj.specsymbol.value + '&SPECIAL=' + obj.SPECIAL.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();


    } else {
        alert('Invalid input for Boundary Special test');
    }
}

function savecheckboundaryint(obj) {
    if (obj.intend.value != undefined && obj.intend.value != null && obj.intend.value != ''
        && obj.intstart.value != '' && obj.intstart.value != undefined
        && obj.quantity.value != '' && obj.quantity.value != undefined
    ) {
        var url1 = URL_CASE3 + obj.intend.value + '&BoundaryIntStart=' + obj.intstart.value + '&QUANTITY=' + obj.quantity.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();


    } else {
        alert('Invalid input for Boundary Int test');
    }
}

function savecheckrangeint(obj) {
    if (obj.len.value != undefined && obj.len.value != null && obj.len.value != ''
        && obj.quantity.value != '' && obj.quantity.value != undefined) {
        var url1 = URL_CASE4 + obj.len.value + '&MinIntVal=' + obj.quantity.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();

        } else {
        alert('Invalid input for Range int');
    }
}

function reportscv(obj) {
    if (obj.reportname.value != undefined && obj.reportname.value != null && obj.reportname.value != ''
        && obj.delimiter.value != '' && obj.delimiter.value != undefined) {
        var url1 = CSV_REPORT + obj.reportname.value + '&delimiter=' + obj.delimiter.value;
        window.location.href = url1;
    } else {
        alert('Invalid input for Report CSV');
    }
}
function reportexcel(obj) {
    if (obj.reportname.value != undefined && obj.reportname.value != null && obj.reportname.value != ''
        && obj.sheet.value != '' && obj.sheet.value != undefined) {
        var url1 = EXCEL_REPORT + obj.reportname.value + '&sheetname=' + obj.sheet.value;
        window.location.href = url1;

    } else {
        alert('Invalid input for Report Excel');
    }
}