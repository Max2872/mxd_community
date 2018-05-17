 $(function(){
 	$('.home_device').css({
 		'height':$('.home_device').width()/2+'px'
 	})
 		var Myswiper = new Swiper('.swiper-container', {
	    		loop:true,
			autoplay: {
		    delay: 2000,
		    stopOnLastSlide: false,
		    disableOnInteraction: false,
		    },
	    });
	    var homeswiper = new Swiper('.home_swiper-container', {
		      slidesPerView: 3,
		      spaceBetween: 20,
		      loop:true,
	    });
	    var pddswiper = new Swiper('.p_dd_swiper-container', {
		      slidesPerView: 3.5,
		      spaceBetween: 10,
		      loop:true,
	    });
 })