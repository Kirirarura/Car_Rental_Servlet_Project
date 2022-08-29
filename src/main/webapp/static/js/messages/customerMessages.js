const status = document.getElementById('status').value;
const lang = document.getElementById('lang').value;

let emptyFieldException = "\u0411\u0443\u0434\u044c\u0020\u043b\u0430\u0441\u043a\u0430\u002c\u0020\u0437\u0430\u043f\u043e\u0432\u043d\u0456\u0442\u044c\u0020\u0443\u0441\u0456\u0020\u043f\u043e\u043b\u044f"
let passportDataFormatException = "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0438\u0439\u0020\u0444\u043e\u0440\u043c\u0430\u0442\u0020\u043f\u0430\u0441\u043f\u043e\u0440\u0442\u0430"
let datesFormatException = "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0438\u0439\u0020\u0444\u043e\u0440\u043c\u0430\u0442\u0020\u0434\u0430\u0442"
let datesPeriodException = "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0438\u0439\u0020\u043f\u0435\u0440\u0456\u043e\u0434\u0020\u0434\u0430\u0442"
let registrationException = "\u041f\u043e\u043c\u0438\u043b\u043a\u0430\u0020\u0440\u0435\u0454\u0441\u0442\u0440\u0430\u0446\u0456\u0457"
let carAlreadyBookedException = "\u0412\u0438\u0431\u0430\u0447\u0442\u0435\u002c\u0020\u0446\u0435\u0439\u0020\u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044c\u0020\u0443\u0436\u0435\u0020\u0437\u0430\u0431\u0440\u043e\u043d\u044c\u043e\u0432\u0430\u043d\u043e"
let failedAdditionalPaymentException = "\u041d\u0435\u0020\u0432\u0434\u0430\u043b\u043e\u0441\u044f\u0020\u0441\u043f\u043b\u0430\u0442\u0438\u0442\u0438\u0020\u0434\u043e\u0434\u0430\u0442\u043a\u043e\u0432\u0443\u0020\u043f\u043b\u0430\u0442\u0443"
let updatingBookingStatusException = "\u041d\u0435\u0020\u0432\u0434\u0430\u043b\u043e\u0441\u044f\u0020\u0441\u043a\u0430\u0441\u0443\u0432\u0430\u0442\u0438\u0020\u0437\u0430\u043f\u0438\u0442"

if (lang === "ua") {
    if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: emptyFieldException,
            showConfirmButton: true
        })
    } else if (status === 'passportDataFormatException') {
        Swal.fire({
            icon: 'error',
            title: passportDataFormatException,
            showConfirmButton: true
        })
    } else if (status === 'datesFormatException') {
        Swal.fire({
            icon: 'error',
            title: datesFormatException,
            showConfirmButton: true
        })
    } else if (status === 'datesPeriodException') {
        Swal.fire({
            icon: 'error',
            title: datesPeriodException,
            showConfirmButton: true
        })
    } else if (status === 'registrationException') {
        Swal.fire({
            icon: 'error',
            title: registrationException,
            showConfirmButton: true
        })
    } else if (status === 'carAlreadyBookedException') {
        Swal.fire({
            icon: 'error',
            title: carAlreadyBookedException,
            showConfirmButton: true
        })
    } else if (status === 'failedAdditionalPaymentException') {
        Swal.fire({
            icon: 'error',
            title: failedAdditionalPaymentException,
            showConfirmButton: true
        })
    } else if (status === 'updatingBookingStatusException') {
        Swal.fire({
            icon: 'error',
            title: updatingBookingStatusException,
            showConfirmButton: true
        })
    }
} else if (lang === "en") {
    if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: 'Please fill in all fields',
            showConfirmButton: true
        })
    } else if (status === 'passportDataFormatException') {
        Swal.fire({
            icon: 'error',
            title: 'Wrong passport format',
            showConfirmButton: true
        })
    } else if (status === 'datesFormatException') {
        Swal.fire({
            icon: 'error',
            title: 'Wrong dates format',
            showConfirmButton: true
        })
    } else if (status === 'datesPeriodException') {
        Swal.fire({
            icon: 'error',
            title: 'Wrong dates period',
            showConfirmButton: true
        })
    } else if (status === 'registrationException') {
        Swal.fire({
            icon: 'error',
            title: 'Registration failed',
            showConfirmButton: true
        })
    } else if (status === 'carAlreadyBookedException') {
        Swal.fire({
            icon: 'error',
            title: 'Sorry, this car is already booked',
            showConfirmButton: true
        })
    } else if (status === 'failedAdditionalPaymentException') {
        Swal.fire({
            icon: 'error',
            title: 'Payment of extra fee failed',
            showConfirmButton: true
        })
    } else if (status === 'updatingBookingStatusException') {
        Swal.fire({
            icon: 'error',
            title: 'Failed to cancel request',
            showConfirmButton: true
        })
    }
}
