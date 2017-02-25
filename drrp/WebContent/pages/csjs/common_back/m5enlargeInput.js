/**
 * m5enlargeInput
 *
 * @version      1.0
 * @author       nori (norimania@gmail.com)
 * @copyright    5509 (http://5509.me/)
 * @license      The MIT License
 * @link         http://5509.me/log/m5enlargeInput
 *
 * Date: 2010-12-07 23:50
 */

(function($) {
	
	$.fn.m5enlargeInput = function(options) {
		var conf = $.extend({
				enlargeRatioWidth: 150,
				enlargeRatioHeight: 200,
				hoverEnlarge: true,
				hoverEnlargeRatioWidth: 110,
				hoverEnlargeRatioHeight: 120,
				hoverClass: 'enlargedHover',
				movingTop: false,
				movingLeft: false,
				enlargeClass: 'enlarged',
				zIndex: 100,
				easing: 'swing',
				duration: 300,
				callback: function() {}
			}, options);
			
		$(this).each(function() {
			var target = $(this),
				s = {
					width: target.width(),
					height: target.height()
				},
				atS = {
					width: s.width * ( conf.enlargeRatioWidth / 100 ),
					height: s.height * ( conf.enlargeRatioHeight / 100 )
				},
				hAtS = {
					width: s.width * ( conf.hoverEnlargeRatioWidth / 100 ),
					height: s.height * ( conf.hoverEnlargeRatioHeight / 100 )
				},
				ssM = {
					top: ( atS.height - s.height ) / 2,
					left: ( atS.width - s.width ) / 2
				},
				hSsM = {
					top: ( hAtS.height - s.height ) / 2,
					left: ( hAtS.width - s.width ) / 2
				}					
				
			target
				.wrap(
					$('<span></span>')
						.css({
							position: 'relative',
							display: $.browser.msie && $.browser.version < 8 ? 'inline' : 'inline-block',
							zoom: 1,
							width: target.attr('offsetWidth'),
							height: target.attr('offsetHeight')
						})
				)
				.css({
					position: 'absolute',
					top: 0,
					left: 0
				})
				.focus(function() {
					target
						.css('zIndex', conf.zIndex)
						.animate({
							marginTop: conf.movingTop ? '-' + ssM.top : 0,
							marginLeft: conf.movingLeft ? '-' + ssM.left : 0,
							width: atS.width,
							height: atS.height
						}, {
							duration: conf.duration,
							easing: conf.easing,
							queue: false,
							complete: function() {
								target.select();
								conf.callback.call(this);
							}
						})
						.addClass(conf.enlargeClass);
				})
				.blur(function() {
					target
						.animate({
							marginTop: 0,
							marginLeft: 0,
							width: s.width,
							height: s.height
						}, {
							duration: conf.duration,
							easing: conf.easing,
							queue: false,
							complete: function() {
								target.css('zIndex', 'auto');
							}
						})
						.removeClass(conf.enlargeClass)
						.removeClass(conf.hoverClass);
				});
				
			if ( conf.hoverEnlarge ) {
				target
					.hover(
						function() {
							if ( target.hasClass(conf.enlargeClass) ) return false;
							target
								.css('zIndex', conf.zIndex)
								.animate({
									marginTop: conf.movingTop ? '-' + hSsM.top : 0,
									marginLeft: conf.movingLeft ? '-' + hSsM.left : 0,
									width: hAtS.width,
									height: hAtS.height
								}, {
									duration: conf.duration,
									easing: conf.easing,
									queue: false
								})
								.addClass(conf.hoverClass);
						},
						function() {
							if ( target.hasClass(conf.enlargeClass) ) return false;
							target
								.animate({
									marginTop: 0,
									marginLeft: 0,
									width: s.width,
									height: s.height
								}, {
									duration: conf.duration,
									easing: conf.easing,
									queue: false,
									complete: function() {
										target.css('zIndex', 'auto');
									}
								})
								.removeClass(conf.hoverClass);
						}
					);
			}
		});
		
		return this;
	}

})(jQuery);