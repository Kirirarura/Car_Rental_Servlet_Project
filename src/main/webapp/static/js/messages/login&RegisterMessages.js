const status = document.getElementById('status').value;
const lang = document.getElementById('lang').value;

let emptyFieldException = "\u0411\u0443\u0434\u044c\u0020\u043b\u0430\u0441\u043a\u0430\u002c\u0020\u0437\u0430\u043f\u043e\u0432\u043d\u0456\u0442\u044c\u0020\u0443\u0441\u0456\u0020\u043f\u043e\u043b\u044f"
let passwordSizeOutOfBoundsException = "\u0420\u043e\u0437\u043c\u0456\u0440\u0020\u043f\u0430\u0440\u043e\u043b\u044f\u0020\u0432\u0438\u0445\u043e\u0434\u0438\u0442\u044c\u0020\u0437\u0430\u0020\u043c\u0435\u0436\u0456"
let passwordMatchException = "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0438\u0439\u0020\u043f\u043e\u0432\u0442\u043e\u0440\u043d\u0438\u0439\u0020\u043f\u0430\u0440\u043e\u043b\u044c"
let firstnameSizeOutOfBoundsException = "\u0420\u043e\u0437\u043c\u0456\u0440\u0020\u0456\u043c\u0435\u043d\u0456\u0020\u0432\u0438\u0445\u043e\u0434\u0438\u0442\u044c\u0020\u0437\u0430\u0020\u043c\u0435\u0436\u0456"
let lastnameSizeOutOfBoundsException = "\u0420\u043e\u0437\u043c\u0456\u0440\u0020\u043f\u0440\u0456\u0437\u0432\u0438\u0449\u0430\u0020\u0432\u0438\u0445\u043e\u0434\u0438\u0442\u044c\u0020\u0437\u0430\u0020\u043c\u0435\u0436\u0456"
let emailSizeOutOfBoundsException = "\u0420\u043e\u0437\u043c\u0456\u0440\u0020\u0435\u043b\u0435\u043a\u0442\u0440\u043e\u043d\u043d\u043e\u0457\u0020\u043f\u043e\u0448\u0442\u0438\u0020\u0432\u0438\u0445\u043e\u0434\u0438\u0442\u044c\u0020\u0437\u0430\u0020\u043c\u0435\u0436\u0456"
let emailMatchPatternException = "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0438\u0439\u0020\u0444\u043e\u0440\u043c\u0430\u0442\u0020\u0435\u043b\u0435\u043a\u0442\u0440\u043e\u043d\u043d\u043e\u0457\u0020\u043f\u043e\u0448\u0442\u0438"
let emailIsReserved = "\u0422\u0430\u043a\u0430\u0020\u0435\u043b\u0435\u043a\u0442\u0440\u043e\u043d\u043d\u0430\u0020\u043f\u043e\u0448\u0442\u0430\u0020\u0432\u0436\u0435\u0020\u0437\u0430\u0440\u0435\u0437\u0435\u0440\u0432\u043e\u0432\u0430\u043d\u0430"
let registrationException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u0440\u0435\u0454\u0441\u0442\u0440\u0430\u0446\u0456\u0457\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"

let userBlockedException = "\u0426\u0435\u0439\u0020\u043e\u0431\u043b\u0456\u043a\u043e\u0432\u0438\u0439\u0020\u0437\u0430\u043f\u0438\u0441\u0020\u0437\u0430\u0431\u043b\u043e\u043a\u043e\u0432\u0430\u043d\u043e"
let failedLoginException = "\u041a\u043e\u043c\u0431\u0456\u043d\u0430\u0446\u0456\u044f\u0020\u043b\u043e\u0433\u0456\u043d\u0443\u0020\u0430\u0431\u043e\u0020\u043f\u0430\u0440\u043e\u043b\u044f\u0020\u043d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0430"
let authenticationException = "\u041f\u0456\u0434\u0020\u0447\u0430\u0441\u0020\u043f\u0440\u043e\u0446\u0435\u0441\u0443\u0020\u0430\u0432\u0442\u0435\u043d\u0442\u0438\u0444\u0456\u043a\u0430\u0446\u0456\u0457\u0020\u0441\u0442\u0430\u043b\u0430\u0441\u044f\u0020\u043f\u043e\u043c\u0438\u043b\u043a\u0430"
let notVerifiedAccountException = "\u041f\u0456\u0434\u0442\u0432\u0435\u0440\u0434\u044c\u0442\u0435\u0020\u0441\u0432\u0456\u0439\u0020\u043e\u0431\u043b\u0456\u043a\u043e\u0432\u0438\u0439\u0020\u0437\u0430\u043f\u0438\u0441\u002c\u0020\u043c\u0438\u0020\u043d\u0430\u0434\u0456\u0441\u043b\u0430\u043b\u0438\u0020\u043f\u043e\u0441\u0438\u043b\u0430\u043d\u043d\u044f\u0020\u043d\u0430\u0020\u0432\u0430\u0448\u0443\u0020\u0435\u043b\u0435\u043a\u0442\u0440\u043e\u043d\u043d\u0443\u0020\u043f\u043e\u0448\u0442\u0443"

// ----- Register messages --------
if (lang === "ua") {
    if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: emptyFieldException,
            showConfirmButton: true
        })
    } else if (status === 'passwordSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: passwordSizeOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'passwordMatchException') {
        Swal.fire({
            icon: 'error',
            title: passwordMatchException,
            showConfirmButton: true
        })
    } else if (status === 'firstnameSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: firstnameSizeOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'lastnameSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: lastnameSizeOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'emailSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: emailSizeOutOfBoundsException,
            showConfirmButton: true
        })
    } else if (status === 'emailMatchPatternException') {
        Swal.fire({
            icon: 'error',
            title: emailMatchPatternException,
            showConfirmButton: true
        })
    } else if (status === 'emailIsReserved') {
        Swal.fire({
            icon: 'error',
            title: emailIsReserved,
            showConfirmButton: true
        })
    } else if (status === 'registrationException') {
        Swal.fire({
            icon: 'error',
            title: registrationException,
            showConfirmButton: true
        })
    }

// ----- Login messages --------

    else if (status === 'userBlockedException') {
        Swal.fire({
            icon: 'error',
            title: userBlockedException,
            showConfirmButton: true,
        })
    } else if (status === 'failedLoginException') {
        Swal.fire({
            icon: 'error',
            title: failedLoginException,
            showConfirmButton: true
        })
    } else if (status === 'authenticationException') {
        Swal.fire({
            icon: 'error',
            title: authenticationException,
            showConfirmButton: true
        })
    } else if (status === 'notVerifiedAccountException') {
        Swal.fire({
            icon: 'error',
            title: authenticationException,
            showConfirmButton: true
        })
    }
}
else if (lang === "en"){
    if (status === 'emptyFieldException') {
        Swal.fire({
            icon: 'error',
            title: 'Please fill in all fields',
            showConfirmButton: true
        })
    } else if (status === 'passwordSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Password size is out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'passwordMatchException') {
        Swal.fire({
            icon: 'error',
            title: 'Wrong repeat password',
            showConfirmButton: true
        })
    } else if (status === 'firstnameSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Firstname size is out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'lastnameSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Lastname size is out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'emailSizeOutOfBoundsException') {
        Swal.fire({
            icon: 'error',
            title: 'Email size is out of bounds',
            showConfirmButton: true
        })
    } else if (status === 'emailMatchPatternException') {
        Swal.fire({
            icon: 'error',
            title: 'Wrong email format',
            showConfirmButton: true
        })
    } else if (status === 'emailIsReserved') {
        Swal.fire({
            icon: 'error',
            title: 'Such Email already reserved',
            showConfirmButton: true
        })
    }
    else if (status === 'registrationException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during registration',
            showConfirmButton: true
        })
    }

// ----- Login messages --------

    else if (status === 'userBlockedException') {
        Swal.fire({
            icon: 'error',
            title: 'This account is blocked',
            showConfirmButton: true,
        })
    } else if (status === 'failedLoginException') {
        Swal.fire({
            icon: 'error',
            title: 'Email or Password combination are wrong',
            showConfirmButton: true
        })
    }
    else if (status === 'authenticationException') {
        Swal.fire({
            icon: 'error',
            title: 'Error occurred during authentication process',
            showConfirmButton: true
        })
    } else if (status === 'notVerifiedAccountException') {
        Swal.fire({
            icon: 'error',
            title: 'Please verify your account, we`ve sent a link to your email',
            showConfirmButton: true
        })
    }
}



