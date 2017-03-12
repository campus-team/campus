// JavaScript Document
define(['jquery'],function($){
		
	"use strict";
	
	var page = function(){}
	
	// 获取需要操作的dom节点
	var DOM = {
	}
	
	var main = function(){
		handle_event();
		$(function () {
			$("body").css("height",$(document).height());
			
			var current = -1;
			var $advert_image = $(".advert_image");
			var length = $advert_image.children().length;
			for (var i = 0; i < length; i ++) {
				$advert_image.next().append("<li></li>");
			}
			
			$(".bottom").css("left",205-15*length+"px");
		
			function advert() {
				current = (current+1)%length;
				$(".bottom li").removeClass("selected").eq(current).delay(500).addClass("selected");
				$advert_image.children().hide().eq(current).fadeIn(1000).delay(5000).fadeOut(500,advert);
			};
			
			advert();
			
			$(".bottom li").click(function () {
				$advert_image.children().stop(true,false);
				current = $(this).index() - 1;
				advert();
			});
		});
	}
	
	// 监听事件的处理
	function handle_event(){
		
	}
	
	// 对外访问的方法
	var method = {
			main:main,
	}
	
	page.prototype = method;
	return page;
});