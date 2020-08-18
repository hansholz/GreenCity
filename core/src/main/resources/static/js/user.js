$(document).ready(function() {
    $('.table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(user, status) {
           $('.myForm #id').val(user.id);
           $('.myForm #name').val(user.name);
           $('.myForm #email').val(user.email);
           $('.myForm #userCredo').val(user.userCredo);
           $('.myForm #userStatus').val(user.userStatus);
        });

        $('.myForm #exampleModal').modal();
    });
});

