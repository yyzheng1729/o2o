$(function() {
	//从跳转链接中获取当前商品的ID
	var productId = getQueryString('productId');
	//定义商品详情获取的URL
	var productUrl = '/o2o/frontend/listproductdetailpageinfo?productId='
			+ productId;

	$
			.getJSON(
					productUrl,
					function(data) {
						console.log(data);
						if (data.success) {
							var product = data.product;
							$('#product-img').attr('src', product.imgAddr);
							$('#product-time').text(
									new Date(product.lastEditTime)
											.Format("yyyy-MM-dd"));
							$('#product-name').text(product.productName);
							$('#product-desc').text(product.productDesc);
							var imgListHtml = '';
							product.productImgList.map(function(item, index) {
								imgListHtml += '<div class="card-header color-white no-border no-padding"> <img class="card-cover" src="'
										+ item.imgAddress + '"/></div>';
							});
							// 生成购买商品的二维码供商家扫描
							if(data.needQRCode){
								//若客户已登录，则生成购买商品的二维码供商家扫描
								imgListHtml += '<div> <img src="/o2o/frontend/generateqrcode4product?productId='
									+ product.productId + '" width="100%" /></div>';
							}
							$('#imgList').html(imgListHtml);
						}
					});
	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});
	
	$.init();
});
