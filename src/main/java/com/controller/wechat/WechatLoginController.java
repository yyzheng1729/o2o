package com.controller.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.UserAccessToken;
import com.dto.WechatAuthExecution;
import com.dto.WechatUser;
import com.enums.WechatAuthStateEnum;
import com.pojo.PersonInfo;
import com.pojo.WechatAuth;
import com.service.PersonInfoService;
import com.service.WechatAuthService;
import com.util.wechat.WechatUtil;

/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaf8bd3909de93819&
 * redirect_uri=https://www.yyzheng.cn/o2o/wechatlogin/logincheck&role_type=1&
 * response_type=1&response_type=code&scope=snsapi_userinfo&state=1#
 * wechat_redirect 则在这里将会获取到code，之后再通过code获取到access_token进而获取到用户信息
 * 
 * @author yy
 *
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private WechatAuthService wechatAuthService;

	private static final String FRONTEND = "1";
	private static final String SHOPEND = "2";

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		// 获取微信公众号传输过来的code，通过code可获取access_token,进而获取用户信息
		String code = request.getParameter("code");
		// 这个State可以用来传我们自定义的信息，方便程序调用，这里也可以不用
		String roleType = request.getParameter("state");

		WechatUser user = null;
		String openId = null;

		WechatAuth auth = null;
		
		if (null != code) {
			UserAccessToken token;
			try {
				// 通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				// 通过token获取accessToken
				String accessToken = token.getAccessToken();
				// 通过token获取openId
				openId = token.getOpenId();
				// 通过access_token和openId获取用户昵称等信息
				user = WechatUtil.getUserInfo(accessToken, openId);
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (auth != null) {
			PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
			auth = new WechatAuth();
			auth.setOpenId(openId);
			if (FRONTEND.equals(roleType)) {
				personInfo.setUserType(1);
			} else {
				personInfo.setUserType(2);
			}
			auth.setPersonInfo(personInfo);
			WechatAuthExecution we = wechatAuthService.register(auth);
			if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			}else{
				personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user", personInfo);
			}
		}
		
		//若用户点击的是前端展示系统按钮则进入前端展示系统
		if (FRONTEND.equals(roleType)) {
			//为 1 时，跳转到前端系统
//			return "frontend/index";
			return "forward:/frontend/index";
		} else {
			//为 2 时，跳转到商家系统
//			return "shop/shoplist";
			return "forward:/shopadmin/shoplist";
		}
		
	}
}
