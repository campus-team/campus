// JavaScript Document

// JavaScript Document

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
			var $a = $(".list ul li a");
			var len = $a.length;
			for(var i = 0; i < len; i ++) {
				if($($a[i]).height() > 30) {
					$($a[i]).after("<span class='more'>...</span>");
				}
			}
		});
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