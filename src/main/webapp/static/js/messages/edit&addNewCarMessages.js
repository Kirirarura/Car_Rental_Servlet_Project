const status = document.getElementById('status').value;
const lang = document.getElementById('lang').value;

let failedEditCarException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0440\u0435\u0434\u0430\u0433\u0443\u0432\u0430\u043d\u043d\u044f\u0020\u0434\u0435\u0442\u0430\u043b\u0435\u0439\u0020\u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let wrongPriceInputException = "\u0411\u0443\u0434\u044c\u0020\u043b\u0430\u0441\u043a\u0430\u002c\u0020\u0432\u0432\u0435\u0434\u0456\u0442\u044c\u0020\u0447\u0438\u0441\u043b\u043e\u0020\u0432\u0020\u043f\u043e\u043b\u0435\u0020\u0446\u0456\u043d\u0438"
let priceSizeOutOfBounds = "\u0426\u0456\u043d\u0430\u0020\u043f\u043e\u0437\u0430\u0020\u043c\u0435\u0436\u0430\u043c\u0438"
let descriptionSizeOutOfBoundsException = "\u041e\u043f\u0438\u0441\u0020\u043f\u043e\u0437\u0430\u0020\u043c\u0435\u0436\u0430\u043c\u0438"
let failedAddCarException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0434\u043e\u0434\u0430\u0432\u0430\u043d\u043d\u044f\u0020\u043d\u043e\u0432\u043e\u0433\u043e\u0020\u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0456\u043b\u044f\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let modelOutOfBoundsException = "\u041d\u0430\u0437\u0432\u0430\u0020\u043c\u043e\u0434\u0435\u043b\u0456\u0020\u043f\u043e\u0437\u0430\u0020\u043c\u0435\u0436\u0430\u043c\u0438"
let brandNotFoundException = "\u0411\u0440\u0435\u043d\u0434\u0020\u043d\u0435\u0020\u0437\u043d\u0430\u0439\u0434\u0435\u043d\u043e"
let qualityNotFoundException = "\u042f\u043a\u0456\u0441\u0442\u044c\u0020\u043d\u0435\u0020\u0437\u043d\u0430\u0439\u0434\u0435\u043d\u043e"
let emptyFieldException = "\u0411\u0443\u0434\u044c\u0020\u043b\u0430\u0441\u043a\u0430\u002c\u0020\u0437\u0430\u043f\u043e\u0432\u043d\u0456\u0442\u044c\u0020\u0443\u0441\u0456\u0020\u043f\u043e\u043b\u044f"

if (lang === "ua"){
    //-- Edit messages --//
    if (status === 'failedEditCarException') {
        Swal.fire({
            icon: 'error',
            title: failedEditCarException,
            showConfirmButton: true
        })
    } else if (status === 'wrongPriceInputException') {
        Swal.fire({
            icon: 'error',
            title: wrongPriceInputException,
            showConfirmButton: true
        })
    } else if (status === 'priceSizeOutOfBounds') {
        Swal.fire({
            icon: 'error',
            title: priceSizeOutOfBounds,
            showConfirmButton: true
        })
    } else if (status === 'descriptionSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: descriptionSizeOutOfBoundsException,
            showConfirmButton: true
        })
    }

//-- Add messages --//
    else if (status === 'failedAddCarException') {
        Swal.fire({
            icon: 'error',
            title: failedAddCarException,
            showConfirmButton: true
        })
    } else if (status === 'modelOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: modelOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'brandNotFoundException') {
        Swal.fire({
            icon: 'error',
            title: brandNotFoundException,
            showConfirmButton: true
        })
    } else if (status === 'qualityNotFoundException') {
        Swal.fire({
            icon: 'error',
            title: qualityNotFoundException,
            showConfirmButton: true
        })
    }

//-- Common messages --//
    else if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: emptyFieldException,
            showConfirmButton: true
        })
    }
} else if (lang === "en"){
    //-- Edit messages --//
    if (status === 'failedEditCarException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during editing car details',
            showConfirmButton: true
        })
    } else if (status === 'wrongPriceInputException') {
        Swal.fire({
            icon: 'error',
            title: 'Please enter number in the price field',
            showConfirmButton: true
        })
    } else if (status === 'priceSizeOutOfBounds') {
        Swal.fire({
            icon: 'error',
            title: 'Price out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'descriptionSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Description out of bounds',
            showConfirmButton: true
        })
    }

//-- Add messages --//
    else if (status === 'failedAddCarException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during adding of a new car',
            showConfirmButton: true
        })
    } else if (status === 'ModelOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Model name out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'brandNotFoundException') {
        Swal.fire({
            icon: 'error',
            title: 'Brand not found',
            showConfirmButton: true
        })
    } else if (status === 'qualityNotFoundException') {
        Swal.fire({
            icon: 'error',
            title: 'Quality not found',
            showConfirmButton: true
        })
    }

//-- Common messages --//
    else if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: 'Please fill in all fields',
            showConfirmButton: true
        })
    }
}



