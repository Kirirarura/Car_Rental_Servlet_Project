const status = document.getElementById('status').value;
if (status === 'successfulRegistration') {
    Swal.fire({
        icon: 'success',
        title: 'Your account has been registered',
        showConfirmButton: false,
        timer: 2000
    })
} else if (status === 'emptyFieldException') {
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

