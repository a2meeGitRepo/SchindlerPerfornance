

$(function(){
	var a = document.getElementById("av").value;
	  var $ppc = $('.progress-pie-chart'),
	    //percent = parseInt($ppc.data('percent')),
		percent = parseInt(a),
	    deg = 360*percent/100;
	  if (percent > 50) {
	    $ppc.addClass('gt-50');
	  }
	  $('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
	  $('.ppc-percents span').html(percent+'%');
	});



$(function(){
	var a = document.getElementById("av2").value;
	  var $ppc = $('.progress-pie-chart2'),
	    //percent = parseInt($ppc.data('percent')),
		percent = parseInt(a),
	    deg = percent;
	  if (percent > 50) {
	    $ppc.addClass('gt-50');
	  }
	  $('.ppc-progress-fill2').css('transform','rotate('+ deg +'deg)');
	  $('.ppc-percents2 span').html(percent+'%');
	});




$(function(){
	var a = document.getElementById("av3").value;
	  var $ppc = $('.progress-pie-chart3'),
	    //percent = parseInt($ppc.data('percent')),
		percent = parseInt(a),
	    deg = 360*percent/100;
	  if (percent > 50) {
	    $ppc.addClass('gt-50');
	  }
	  $('.ppc-progress-fill3').css('transform','rotate('+ deg +'deg)');
	  $('.ppc-percents3 span').html(percent+'%');
	});




$(function(){
	var a = document.getElementById("av4").value;
	  var $ppc = $('.progress-pie-chart4'),
	    //percent = parseInt($ppc.data('percent')),
		percent = parseInt(a),
	    deg = 360*percent/100;
	  if (percent > 50) {
	    $ppc.addClass('gt-50');
	  }
	  $('.ppc-progress-fill4').css('transform','rotate('+ deg +'deg)');
	  $('.ppc-percents4 span').html(percent+'%');
	});
