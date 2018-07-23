const URL_SETCOLUMN = 'http://localhost:8080/creatortest/setcolumn?column=';
const URL_SAVEMODEL = 'http://localhost:8080/creatortest/savemodel';
function savecolumn() {
    var request = new XMLHttpRequest();
    var url2 = 'http://localhost:8080/creatortest/savecolumn'
    request.open("GET", url2, true);
    request.send();
}
function createcolumn(obj) {
    if (obj.name.value != undefined && obj.name.value != null && obj.name.value != '') {
        var url1 = URL_SETCOLUMN + obj.name.value;
        var request = new XMLHttpRequest();
        request.open("GET", url1, true);
        request.send();

    } else {
        alert('Column name not be null');
    }


}

function savemodel() {
    var request = new XMLHttpRequest();
    request.open("GET", URL_SAVEMODEL, true);
    request.send();
}