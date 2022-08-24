$('.filter-checkbox-order').on('change', function () {
    $('.filter-checkbox-order').not(this).prop('checked', false);
});

$('.filter-checkbox-sort').on('change', function () {
    $('.filter-checkbox-sort').not(this).prop('checked', false);
});

