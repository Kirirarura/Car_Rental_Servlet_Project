var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();
if (dd < 10) {
    dd = '0' + dd;
}
if (mm < 10) {
    mm = '0' + mm;
}
today = yyyy + '-' + mm + '-' + dd;

$(function () {
    $("#startDateField").datepicker({
        numberOfMonths: 1,
        dateFormat: 'yy-mm-dd',
        minDate: today,
        onSelect: function (selectdate) {
            let dt = new Date(selectdate);
            dt.setDate(dt.getDate() + 1)
            $("#endDateField").datepicker("option", "minDate", dt);
        }
    })
    $("#endDateField").datepicker({
        numberOfMonths: 1,
        dateFormat: 'yy-mm-dd',
        onSelect: function (selectdate) {
            let dt = new Date(selectdate);
            dt.setDate(dt.getDate() - 1)
            $("#startDateField").datepicker("option", "maxDate", dt);
        }
    })
})
