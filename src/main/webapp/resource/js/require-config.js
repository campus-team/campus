/**
 * 公共的js,使用别名简化路径配置
 * @author 刘汉升 
 */

require.config({
	baseUrl: _ctx + '/resource/js/module',
	paths: {
      'jquery': '../../plugin/jquery/jquery-3.1.1',
      'jquery_old': '../../plugin/jquery-ui/jquery-ui-1.8.4.custom/js/jquery.min',
      'jquery_ui': '../../plugin/jquery-ui/jquery-ui-1.8.4.custom/js/jquery-ui.min',
      'raphael': '../../plugin/raphael/raphael-min',
      'snaker_designer': '../../plugin/snaker/snaker.designer',
      'snaker_dialog': '../../plugin/snaker/dialog',
      'snaker_editors': '../../plugin/snaker/snaker.editors',
      'snaker_form': '../../plugin/snaker/snaker.form',
      'snaker_model': '../../plugin/snaker/snaker.model',
      'snaker_util': '../../plugin/snaker/snaker.util'
    },
    shim:{
    	'jquery_old':{
    		exports:'jQuery'
    	},
    	'jquery_ui':{
    		deps:['jquery_old'],
    		exports:'jquery_ui'
    	},
    	'raphael': {
    		
    	},
    	'snaker_designer':{
    		deps:['jquery_old'],
    		exports:'snaker'
		},
		'snaker_dialog': {
			deps:['jquery_old','snaker_designer'],
		},
		'snaker_editors': {
			deps:['jquery_old','snaker_designer'],
		},
		'snaker_form': {
			deps:['jquery_old','snaker_designer'],
		},
		'snaker_model': {
			deps:['jquery_old','snaker_designer'],
		},
		'snaker_util': {
			deps:['jquery_old','snaker_designer'],
		}
    }
});

