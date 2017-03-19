define(['jquery','../../common/frame','flow/form_folder'],function($,frame,folder){
	
	"user strict";
	
	var page = function(){}
	var frame = new frame();
	var folder = new folder();
	
	var DOM = {
			
	}
	
	var main = function(){
		handleEvent();
	}
	
	function handleEvent(){
		frame.toleft();
		frame.toright();
		frame.first_level_control();
		frame.second_level_control();
	}
	
	var method = {
		main: main,
		form_folder_add:folder.form_folder_add,
	}
	
	page.prototype = method;
	return page;
});