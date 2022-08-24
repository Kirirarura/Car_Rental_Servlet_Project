const status = document.getElementById('status').value;

if (status === 'emptyFieldException') {
    Swal.fire({
        icon: 'error',
        title: 'Please fill in all fields',
        showConfirmButton: true
    })
} else if (status === 'PassportDataFormatException') {
    Swal.fire({
        icon: 'error',
        title: 'Wrong passport format',
        showConfirmButton: true
    })
} else if (status === 'DatesFormatException') {
    Swal.fire({
        icon: 'error',
        title: 'Wrong dates format',
        showConfirmButton: true
    })
} else if (status === 'DatesPeriodException') {
    Swal.fire({
        icon: 'error',
        title: 'Wrong dates period',
        showConfirmButton: true
    })
}
else if (status === 'bookingInfoLoadingException') {
    Swal.fire({
        icon: 'error',
        title: 'Failed to load booking info',
        showConfirmButton: true
    })
}
else if (status === 'registrationException') {
    Swal.fire({
        icon: 'error',
        title: 'Registration failed, please check if car is still available',
        showConfirmButton: true
    })
}



