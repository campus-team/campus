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
			var width = $(window).width();
			var height = $(document).height();
			$("#left").height(height);
			$("iframe").css("width",width-180).css("height",height-60);
			
			$(".nav li").click(function() {
				var index = $(this).index();
				$(this).addClass("select").siblings().removeClass("select");
				$("#left>nav>ul").hide();
				$("#left>nav>ul:eq("+index+")").show();
			});
			
			$("#left nav>ul>li>ul").before("<img src='image/right.png' class='arrow' />");
			
		    $("#left nav>ul>li>ul").parent().click(function() {
				if($(this).children("ul").is(":visible")) {
					$(this).children("ul").slideUp(500).parent().css("padding-bottom","6px").children("img.arrow").attr("src","image/right.png");
				} else {
					$(this).children("ul").slideDown(500).parent().css("padding-bottom", "0").children("img.arrow").attr("src","image/down.png");
				}
			});
			
			$("#left li ul li").click(function(event) {
				event.stopPropagation();
			})
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
