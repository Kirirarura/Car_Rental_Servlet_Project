const status = document.getElementById('status').value;

//-- Edit messages --//

if (status === 'failedEditCarException') {
    Swal.fire({
        icon: 'error',
        title: 'Error occurred during editing car details',
        showConfirmButton: true
    })
}
else if (status === 'wrongPriceInputException') {
    Swal.fire({
        icon: 'error',
        title: 'Please enter number in the price field',
        showConfirmButton: true
    })
}
else if (status === 'priceSizeOutOfBounds') {
    Swal.fire({
        icon: 'error',
        title: 'Price out of bounds',
        showConfirmButton: true
    })
}
else if (status === 'descriptionSizeOutOfBoundsException') {
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
}

else if (status === 'ModelOutOfBoundsException') {
    Swal.fire({
        icon: 'error',
        title: 'Model name out of bounds',
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


