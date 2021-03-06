define(['jquery'],function($){
	
	var page = function(){}
	
	var DOM = {
		form_folder_add_btn: $('.form_folder_add_btn'),
		form_folder_name: $('.form_folder_name'),
		form_folder_sequence: $('.form_folder_sequence'),
		form_folder_parent_id: $('.form_folder_parent_id'),
	}
	
	var main = function(){
		handleEvent();
	}
	
	function handleEvent(){
		/*DOM.form_folder_add_btn.on('click',function(){
			var name = DOM.form_folder_name.val();
			var sequence = DOM.form_folder_sequence.val();
			var post_data = {'name':name,'sequence':sequence};
			var url = 'form_folder/form_folder_save.htm';
			ajax_form_folder(url, post_data);
		});*/
		
	}
	
	function ajax_form_folder(url, post_data, callback){
		$.ajax({
			url: _ctx + url,
			type:'post',
			dataType:'json',
			data: post_data,
			success:function(data){console.log('success:'+data);
				if(callback && callback instanceof Function){
					// 若回调函数不为空  则执行回调函数
					callback(data);
					return;
				}
				if(data.result){
					console.log('操作成功');
				}else{
					console.log('操作失败');
				}
			}
		});
	}
	
	function form_folder_add(){
		var name = DOM.form_folder_name.val();
		var sequence = DOM.form_folder_sequence.val();
		var parent_id = DOM.form_folder_parent_id.val();
		var post_data = {'name':name,'sequence':sequence,'parent_id':parent_id};
		var url = '/form_folder/form_folder_save.htm';
		ajax_form_folder(url, post_data);
	}
	
	var method = {
			main: main,
			form_folder_add:form_folder_add,
	}
	
	page.prototype = method;
	return page;
});