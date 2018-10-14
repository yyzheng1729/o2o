$(function(){
	$('#log-out').click(function() {
		//清楚session
		$.ajax({
			url : "/o2o/local/logout",
			type : "post",
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					var usertype = $("#log-out").attr("usertype");
					//清除成功后退出到登录界面
					window.location.href = '/o2o/local/login?usertype='+usertype;
					return false;
				}
			},
			error : function(data, error) {
				alert(error);
			}
		});
	});
})
