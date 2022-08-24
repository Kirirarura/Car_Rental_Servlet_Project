const status = document.getElementById('status').value;

if (status === 'bookingAlreadyOnReviewException') {
    Swal.fire({
        icon: 'error',
        title: 'This request is already on review',
        showConfirmButton: true
    })
}