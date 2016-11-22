// unfollow mouseover effect
$(document).on({
    mouseenter: function () {
			$(this).addClass("btn-danger");
				$('.following').hide();
				$('.unfollow').show();
    },
    mouseleave: function () {
        //stuff to do on mouse leave
			$(this).removeClass("btn-danger");
				$('.unfollow').hide();
				$('.following').show();
    }	
}, ".btn-following"); //pass the element as an argument to .on

// follow/unfollow button handling
$('.btn-follow').click(function(){
	if ($(this).hasClass('btn-following')) {
		// successful unfollow
		$(this).removeClass('btn-following');
		$(this).removeClass('btn-danger');
		$(this).addClass('btn-primary');
		$('.following').hide();
		$('.unfollow').hide();
		$('.follow').show();
	
		// unfollow ajax here
	}
	else {	
		// successful follow
		$(this).addClass('btn-following');
		$(this).removeClass('btn-primary');
		$('.follow').hide();
		$('.following').show();
		
		// follow ajax here

	}
	
});