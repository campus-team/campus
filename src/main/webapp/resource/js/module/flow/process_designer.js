/**
 * 流程设计
 * @author 刘汉升
 */
define(['jquery_old','raphael','jquery_ui','snaker_dialog','snaker_designer','snaker_model','snaker_editors'],function($){
	
	"use strict";
/*	require(['raphael']);
	require(['jquery_ui']);
	
//	require(['snaker_designer']);
	require(['snaker_dialog']);
	require(['snaker_model']);
	require(['snaker_editors']);
*/	
	var page = function(){}
	
	var DOM = {
			
	}
	
	var main = function(){
		
	}
	
	var method = {
			main:main,
			saveModel:function(data){
				alert(data);
			}
	}
	
	page.prototype = method;
	return page;
});