/**
 * 跟子框架 iframe 相关的共同js
 */

define(['jquery'],function($){
	
	"use strict";
	
	var frame = function(){}
	
	var DOM = {
		panel_left: $('.panel_left'),
		panel_main: $('.panel_main'),
		icon_first_level: $('.icon_first_level'),
		icon_second_level: $('.icon_second_level'),
	}
	
	var method = {
		// 左面板向左移动
		toleft:function(){
			DOM.panel_left.on('click', '.icon_toleft' ,function(){
				DOM.panel_left.animate({
					'left':'-175px',
				});
				$(this).removeClass('icon-zuozhankai icon_toleft').addClass('icon-youzhankai icon_toright');
				DOM.panel_main.animate({
					'left':'25px',
				});
			});
		},
		// 左面板向右移动
		toright:function(){
			DOM.panel_left.on('click', '.icon_toright' ,function(){
				DOM.panel_left.animate({
					'left':'0px',
				});
				$(this).removeClass('icon-youzhankai icon_toright').addClass('icon-zuozhankai icon_toleft');
				DOM.panel_main.animate({
					'left':'200px',
				});
			});
		},
		// 左面板 一级目录的显示与隐藏
		first_level_control:function(){
			DOM.icon_first_level.on('click',function(){
				var $icon_first_level = $(this);
				var $ul_first_level = $(this).parent().siblings('ul.first_level');
				if($ul_first_level.is(':hidden')){
					$ul_first_level.slideDown();
					$icon_first_level.removeClass('icon-jiahao').addClass('icon-jianhao');
				}else{
					$ul_first_level.slideUp();
					$icon_first_level.removeClass('icon-jianhao').addClass('icon-jiahao');
				}
			});
		},
		// 左面板 二级目录的显示与隐藏
		second_level_control:function(){
			DOM.icon_second_level.on('click',function(){
				var $icon_second_level = $(this);
				var $ul_second_level = $(this).parent().siblings('ul.second_level');
				if($ul_second_level.is(':hidden')){
					$ul_second_level.slideDown();
					$icon_second_level.removeClass('icon-jiahao').addClass('icon-jianhao');
				}else{
					$ul_second_level.slideUp();
					$icon_second_level.removeClass('icon-jianhao').addClass('icon-jiahao');
				}
			});
		}
	}
	
	frame.prototype = method;
	
	return frame;
})