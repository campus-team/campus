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
			
			$(".style span").click(function() {
				$(this).siblings().removeClass("select");
				$(this).addClass("select");
				$("div:gt(2)").hide();
				var x = $(this).index(".style span") + 3;
				$("div:eq("+x+")").show();
			})
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