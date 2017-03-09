define(['jquery'],function($){
	
	"use strict";
	
	var page = function(){}
	
	var DOM = {
			left_nav_ul:$('.left-nav-ul'),
			main_workespace:$('#main_workspace'),
	}
	
	var main = function(){
		handle_event();
	}
	
	function handle_event(){
		DOM.left_nav_ul.find('li a').click(function(){
			var href = $(this).attr('href');
			DOM.main_workespace.attr('src',href);
			return false;
		});
	}
	
	var method = {
			main:main,
			hh:function(){
				alert($('body').html());
			}
	}
	
	page.prototype = method;
	return page;
});
