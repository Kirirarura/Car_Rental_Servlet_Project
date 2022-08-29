const status = document.getElementById('status').value;
const lang = document.getElementById('lang').value;

let carInfoLoadingException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0434\u0430\u043d\u0438\u0445\u0020\u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let findAllCarsException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u043a\u0430\u0442\u0430\u043b\u043e\u0433\u0443\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let findAllUsersException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0441\u043f\u0438\u0441\u043a\u0443\u0020\u043a\u043e\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let bookingInfoLoadingException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0441\u043f\u0438\u0441\u043a\u0443\u0020\u0431\u0440\u043e\u043d\u044e\u0432\u0430\u043d\u043d\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"


if (lang === "en") {
    if (status === 'carInfoLoadingException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during loading car details',
            showConfirmButton: true
        })
    } else if (status === 'findAllCarsException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during loading catalog',
            showConfirmButton: true
        })
    } else if (status === 'findAllUsersException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during loading User List',
            showConfirmButton: true
        })
    } else if (status === 'bookingInfoLoadingException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during loading Booking List',
            showConfirmButton: true
        })
    }
} else if (lang === ua) {
    if (status === 'carInfoLoadingException') {
        Swal.fire({
            icon: 'error',
            title: carInfoLoadingException,
            showConfirmButton: true
        })
    } else if (status === 'findAllCarsException') {
        Swal.fire({
            icon: 'error',
            title: findAllCarsException,
            showConfirmButton: true
        })
    } else if (status === 'findAllUsersException') {
        Swal.fire({
            icon: 'error',
            title: findAllUsersException,
            showConfirmButton: true
        })
    } else if (status === 'bookingInfoLoadingException') {
        Swal.fire({
            icon: 'error',
            title: bookingInfoLoadingException,
            showConfirmButton: true
        })
    }
}
