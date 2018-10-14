$(function() {
	//登录验证的controller url
	var loginUrl = '/o2o/local/logincheck';
	//从地址栏的URL里获取usertype
	//usertype=1则为customer,其余为shopadmin
	var usertype = getQueryString("usertype");
	//登录次数，累积登录三次失败之后自动弹出验证码要求输入
	var loginCount = 0;

	$('#submit').click(function() {
		//获取输入的账号
		var username = $('#username').val();
		//获取输入的密码
		var password = $('#psw').val();
		//获取验证码信息
		var verifyCodeActual = $('#j_captcha').val();
		//是否需要验证码验证，默认为false，即不需要
		var needVerify = false;
		//如果登录三次都失败
		if (loginCount >= 3) {
			//那么就需要验证码校验了
			if (!verifyCodeActual) {
				$.toast('请输入验证码！');
				return;
			} else {
				needVerify = true;
			}
		}
		//访问后台进行登录验证
		$.ajax({
			url : loginUrl,
			async : false,
			cache : false,
			type : "post",
			dataType : 'json',
			data : {
				username : username,
				password : password,
				verifyCodeActual : verifyCodeActual,
				needVerify : needVerify
			},
			success : function(data) {
				if (data.success) {
					$.toast('登录成功！');
					setTimeout(function(){
						if (usertype == 1) {
							// 若用户在前端展示系统页面则自动退回到前段展示系统首页
							window.location.href = '/o2o/frontend/index';
						} else {
							// 若用户上则店家管理系统页面则自动回退到店铺列表页中
							window.location.href = '/o2o/shopadmin/shoplist';
						}
					},1000);
				} else {
					$.toast('登录失败！');
					loginCount++;
					if (loginCount >= 3) {
						//登录失败三次，需要做验证码校验
						$('#verifyPart').show();
					}
				}
			}
		});
	});
});