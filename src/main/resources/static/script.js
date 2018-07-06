const URL_SETCOLUMN='http://localhost:8080/creatortest/home?column=';
$(document).ready(function  about() {
    $("#about:hidden").show();
})
function howuse() {
    $("#howuse:hidden").show();
};
function home() {

    window.location.href="../static/index.html";
}
function createtestdata() {
$("#create:hidden").show();
};
function createcolumn(obj) {
    if (obj.rix.value!=undefined && obj.rix.value!=null && obj.rix.value!='') {
        var url1 = URL_SETCOLUMN + obj.rix.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();
        }else {
        alert('Column name not be null');
    }


};
