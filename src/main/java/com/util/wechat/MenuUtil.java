package com.util.wechat;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.dto.Button;
import com.dto.Menu;
import com.dto.ViewButton;

import net.sf.json.JSONObject;

public class MenuUtil {
	private static final String APPID = "wxaf8bd3909de93819";
	private static final String APPSECRET = "6d6c90f49f829b20c46506b2be20bb3a";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 组装菜单
	 * 
	 * @return
	 */
	public static Menu initMenu() {
		String userUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=wxaf8bd3909de93819"
				+ "&redirect_uri=https://www.yyzheng.cn/o2o/wechatlogin/logincheck"
				+ "&role_type=1&response_type=1&response_type=code"
				+ "&scope=snsapi_userinfo&state=1#wechat_redirect";
		
		String businessUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=wxaf8bd3909de93819"
				+ "&redirect_uri=https://www.yyzheng.cn/o2o/wechatlogin/logincheck"
				+ "&role_type=1&response_type=1&response_type=code"
				+ "&scope=snsapi_userinfo&state=2#wechat_redirect";
		
		Menu menu = new Menu();

		ViewButton viewbutton1 = new ViewButton();
		viewbutton1.setName("用户系统");
		viewbutton1.setType("view");
//		viewbutton1.setUrl("http://www.imooc.com");
		viewbutton1.setUrl(userUrl);
		
		ViewButton viewbutton2 = new ViewButton();
		viewbutton2.setName("商家系统");
		viewbutton2.setType("view");
//		viewbutton2.setUrl("http://www.baidu.com");
		viewbutton2.setUrl(businessUrl);

		menu.setButton(new Button[] { viewbutton1, viewbutton2 });

		return menu;
	}

	public static int createMenu(String token, String menu) throws ClientProtocolException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	private static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
