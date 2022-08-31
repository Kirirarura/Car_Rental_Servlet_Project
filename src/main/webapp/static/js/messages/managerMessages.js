const status = document.getElementById('status').value;
const lang = document.getElementById('lang').value;

let bookingAlreadyOnReviewException = "\u0426\u0435\u0439\u0020\u0437\u0430\u043f\u0438\u0442\u0020\u0443\u0436\u0435\u0020\u0440\u043e\u0437\u0433\u043b\u044f\u0434\u0430\u0454\u0442\u044c\u0441\u044f\u0020\u0430\u0431\u043e\u0020\u0431\u0443\u0432\u0020\u0441\u043a\u0430\u0441\u043e\u0432\u0430\u043d\u0438\u0439\u0020\u043a\u043e\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0435\u043c"
let emptyFieldException = "\u0411\u0443\u0434\u044c\u0020\u043b\u0430\u0441\u043a\u0430\u002c\u0020\u0437\u0430\u043f\u043e\u0432\u043d\u0456\u0442\u044c\u0020\u0443\u0441\u0456\u0020\u043f\u043e\u043b\u044f"
let descriptionSizeOutOfBoundsException = "\u041e\u043f\u0438\u0441\u0020\u0432\u0456\u0434\u0445\u0438\u043b\u0435\u043d\u043d\u044f\u0020\u0432\u0438\u0445\u043e\u0434\u0438\u0442\u044c\u0020\u0437\u0430\u0020\u043c\u0435\u0436\u0456"
let failedAcceptRequestException = "\u041d\u0435\u0020\u0432\u0434\u0430\u043b\u043e\u0441\u044f\u0020\u043f\u0440\u0438\u0439\u043d\u044f\u0442\u0438\u0020\u0437\u0430\u043f\u0438\u0442"
let failedDeclineRequestException = "\u041d\u0435\u0020\u0432\u0434\u0430\u043b\u043e\u0441\u044f\u0020\u0432\u0456\u0434\u0445\u0438\u043b\u0438\u0442\u0438\u0020\u0437\u0430\u043f\u0438\u0442"
let failedTakeOnReviewException = "\u041d\u0435\u0020\u0432\u0434\u0430\u043b\u043e\u0441\u044f\u0020\u0432\u0437\u044f\u0442\u0438\u0020\u043d\u0430\u0020\u0440\u043e\u0437\u0433\u043b\u044f\u0434"

let carInfoLoadingException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0434\u0430\u043d\u0438\u0445\u0020\u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let findAllCarsException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u043a\u0430\u0442\u0430\u043b\u043e\u0433\u0443\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let findAllUsersException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0441\u043f\u0438\u0441\u043a\u0443\u0020\u043a\u043e\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0456\u0432\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let bookingInfoLoadingException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0437\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0435\u043d\u043d\u044f\u0020\u0441\u043f\u0438\u0441\u043a\u0443\u0020\u0431\u0440\u043e\u043d\u044e\u0432\u0430\u043d\u043d\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"

if (lang === "ua") {
    if (status === 'bookingAlreadyOnReviewException') {
        Swal.fire({
            icon: 'error',
            title: bookingAlreadyOnReviewException,
            showConfirmButton: true
        })
    } else if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: emptyFieldException,
            showConfirmButton: true
        })
    } else if (status === 'descriptionSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: descriptionSizeOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'failedAcceptRequestException') {
        Swal.fire({
            icon: 'error',
            title: failedAcceptRequestException,
            showConfirmButton: true
        })
    } else if (status === 'failedDeclineRequestException') {
        Swal.fire({
            icon: 'error',
            title: failedDeclineRequestException,
            showConfirmButton: true
        })
    } else if (status === 'failedTakeOnReviewException') {
        Swal.fire({
            icon: 'error',
            title: failedTakeOnReviewException,
            showConfirmButton: true
        })
    }
//-- Loading messages --//
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
} else if (lang === "en") {
    if (status === 'bookingAlreadyOnReviewException') {
        Swal.fire({
            icon: 'error',
            title: 'This request is already on review or has been canceled by user',
            showConfirmButton: true
        })
    } else if (status === "bookingAlreadyOnReviewException") {
        Swal.fire({
            icon: 'error',
            title: 'This request is already on review',
            showConfirmButton: true
        })
    } else if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: 'Please fill in all fields',
            showConfirmButton: true
        })
    } else if (status === 'descriptionSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Decline description is out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'failedAcceptRequestException') {
        Swal.fire({
            icon: 'error',
            title: 'Failed to accept request',
            showConfirmButton: true
        })
    } else if (status === 'failedDeclineRequestException') {
        Swal.fire({
            icon: 'error',
            title: 'Failed to decline request',
            showConfirmButton: true
        })
    } else if (status === 'failedTakeOnReviewException') {
        Swal.fire({
            icon: 'error',
            title: 'Failed to take on review',
            showConfirmButton: true
        })
    }

//-- Loading message --//
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
}



