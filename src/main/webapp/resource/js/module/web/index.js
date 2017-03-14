define(['jquery'],function($){
	
	"use strict";
	
	var page = function(){}
	
	// 获取需要操作的dom节点
	var DOM = {
			left_nav_ul:$('.left_nav_ul'),
			main_workespace:$('#main_workspace'),
	}
	
	var main = function(){
		handle_event();
		$(function() {
			var current = -1;
			var num = $(".advert>ul>li").length;
			var $advert_li = $(".advert>ul>li");
			
			function advert() {
				current = (current + 1) % num;
				$advert_li.eq(current).fadeIn(1000).delay(5000).fadeOut(500,advert);
			}
			advert();
			
			$(".advert span").click(function () {
				$advert_li.eq(current).hide().stop(true,false);
				if($(this).attr("class") == "pre") {
					current = (current - 2 + num) % num;
				}
				advert();
			});
		})
	}
	
	// 监听事件的处理
	function handle_event(){
		DOM.left_nav_ul.find('li a').click(function(){
			var href = $(this).attr('href');
			DOM.main_workespace.attr('src',href);
			return false;
		});
	}
	
	// 对外访问的方法
	var method = {
			main:main,
			hh:function(){
				alert($('body').html());
			}
	}
	
	page.prototype = method;
	return page;
});
