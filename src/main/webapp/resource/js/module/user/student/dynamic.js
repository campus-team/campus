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
			
			if($(".comments .num").html() == 0) {
				$(".comments .num").html("评论");
			}
			
			if($(".good .num").html() == 0) {
				$(".good .num").html("赞");
			}
			
			$(".show").on("mouseover", ".comments img", function() {
				$(this).attr("src", _ctx+"/resource/img/module/web/comments2.png");
			}).on("mouseout", ".comments img", function() {
				$(this).attr("src", _ctx+"/resource/img/module/web/comments1.png");
			});
			
			$(".show").on("click", ".message .good", function() {
				if($(this).attr("data-falg") == 1) {
					return ;
				}
				$(this).children("img").attr("src", _ctx+"/resource/img/module/web/good2.png");
				var num = $(this).children(".num").html();
				if(isNaN(num)) {
					num = 0;
				}
				num = Number(num);
				$(this).children(".num").html(num + 1).css("color","#e0620d");
				$(this).attr("data-falg", "1");
			});
			
			$(".show").on("click",".message .comments",function() {
				var $other = $(this).nextAll(".other_comments");
				if($other.is(":visible")) {
					$other.slideUp(500);
					$other.next().slideUp(500);
				} else {
					$other.slideDown(500);
					$other.next().slideDown(500);
				}
			});
			
			$(".new .emotion").hover(function() {
				$(this).children("img").attr("src", _ctx+"/resource/img/module/web/face2.png");
			}, function () {
				$(this).children("img").attr("src", _ctx+"/resource/img/module/web/face1.png");
			});
			
			$(".new .picture").hover(function() {
				$(this).children("img").attr("src", _ctx+"/resource/img/module/web/picture2.png");
			}, function () {
				$(this).children("img").attr("src", _ctx+"/resource/img/module/web/picture1.png");
			});
			
			
			$('.emotion').qqFace({
				id : 'facebox', //表情盒子的ID
				assign:'text', //给那个控件赋值
				path:_ctx+'/resource/img/module/web/face/'	//表情存放的路径
			});
			
			$(".new .submit").click(function() {
				var str = $("#text").val();
				if (str == "") {
					return ;
				}
				str = replace_em(str);
				$(".show").prepend("<div class='message'><span class='photo'><img src='"+_ctx+"/resource/img/module/web/photo.png' /></span><span class='name'>小明</span><span class='time'>3月15日 13:46</span><div class='content'><p>" +str + "</p></div><span class='comments'><img src='"+_ctx+"/resource/img/module/web/comments1.png' /><span class='num'>评论</span></span><span class='good'><img src='"+_ctx+"/resource/img/module/web/good1.png' /><span class='num' data-falg='0'>赞</span></span><div class='other_comments'><ul></ul></div><div class='my_comment'><input type='text' class='input' placeholder='评论' /><input type='submit' class='submit' value='发表' /></div></div>");
				$("#text").val("");
			});
			
			$(".show").on("click", ".message .my_comment .submit", function () {
				var $input = $(this).prev();
				if($input.val() == "") {
					return;
				}
				$(this).parent().prev().children("ul").append("<li><div><span class='other_photo'><img src='"+_ctx+"/resource/img/module/web/other_photo.png' /></span><span class='other_name'>小强</span><span class='other_time'>3月15日 13:50</span><p class='other_comment'>" + $input.val() + "</p></div></li>");
				var $num = $(this).parent().siblings(".comments").children(".num");
				var num = $num.html();
				if(isNaN(num)) {
					num = 0;
				}
				$num.html(Number(num)+1);
				$input.val("");
			});
			
			$(".new .picture img, .new .picture span").click(function() {
				$(this).siblings("input").trigger("click");
			});
			
			//查看结果
			function replace_em(str) {
				str = str.replace(/\</g,'&lt;');
				str = str.replace(/\>/g,'&gt;');
				str = str.replace(/\n/g,'<br/>');
				str = str.replace(/\[em_([0-9]*)\]/g,"<img src='"+_ctx+"/resource/img/module/web/face/$1.gif'/>");
				return str;
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