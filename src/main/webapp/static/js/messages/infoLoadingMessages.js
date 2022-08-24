const status = document.getElementById('status').value;

if (status === 'carInfoLoadingException') {
    Swal.fire({
        icon: 'error',
        title: 'Error occurred during loading car details',
        showConfirmButton: true
    })
}

else if (status === 'findAllCarsException') {
    Swal.fire({
        icon: 'error',
        title: 'Error occurred during loading catalog',
        showConfirmButton: true
    })
}

else if (status === 'findAllUsersException') {
    Swal.fire({
        icon: 'error',
        title: 'Error occurred during loading UserList',
        showConfirmButton: true
    })
}