define('jquery',function($){
	
	var page = function(){}
	
	var DOM = {
<<<<<<< HEAD
		form_folder_add_btn: $('.form_folder_add_btn'),
		form_folder_name: $('form_folder_name'),
		form_folder_sequence: $('form_folder_sequence')
	}
	
	var main = function(){
		
	}
	
	function handleEvent(){
		DOM.form_folder_add_btn.bind('click',function(){
			var name = DOM.form_folder_name.val();
			var sequence = DOM.form_folder_sequence.val();
			var post_data = {'name':name,'sequence':sequence};
		});
		
	}
	
	function ajax_form_folder(url, post_data, callback){
		$.ajax({
			url: _ctx + url,
			type:'post',
			dataType:'json',
			data: post_data,
			success:function(data){
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
=======
		btn_form_folder_add: $('.btn_form_folder_add')
	}
	
	var main = function(){
		
	}
	
	function handleEvent(){
		DOM.btn_form_folder_add.bind('click',function(){
			
		});
		
	}
	
	//function
>>>>>>> branch 'master' of https://github.com/campus-team/campus.git
	
	var method = {
			main: main,
			add_folder
	}
});