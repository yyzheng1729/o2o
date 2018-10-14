package com.controller.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.LocalAuthExecution;
import com.enums.LocalAuthStateEnum;
import com.pojo.LocalAuth;
import com.pojo.PersonInfo;
import com.service.LocalAuthService;
import com.util.CodeUtil;
import com.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {
	@Autowired
	private LocalAuthService localAuthService;

	@RequestMapping(value = "/bindlocalauth", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 获取输入的账号
		String username = HttpServletRequestUtil.getString(request, "username");
		// 获取输入的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 从session中获取当前用户信息（用户一旦通过微信登录之后，便能获取到用户的信息）
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		// 非空判断，要求账号密码以及当前的用户session非空
		if (username != null && password != null && user != null && user.getUserId() != null) {
			// 创建LocalAuth对象并赋值
			LocalAuth localAuth = new LocalAuth();
			localAuth.setUsername(username);
			localAuth.setPassword(password);
			localAuth.setPersonInfo(user);
			// 绑定账号
			LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
			if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", le.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	@RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 获取账号
		String username = HttpServletRequestUtil.getString(request, "username");
		// 获取原密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 获取新密码
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		// 从session中获取当前用户信息（用户一旦通过微信登录之后，便能获取到用户的信息
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		// 非空判断，要求账号新旧密码以及当前的用户session非空，且新旧密码不相同
		if (username != null && password != null && newPassword != null && user != null && user.getUserId() != null
				&& !password.equals(newPassword)) {
			try {
				// 查看原先账号，看看与输入的账号是否一致，不一致则认为是非法操作
				LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
				if (localAuth == null || !localAuth.getUsername().equals(username)) {
					// 不一致则直接退出
					modelMap.put("success", false);
					modelMap.put("errMsg", "输入的账号非本次登录的账号");
					return modelMap;
				}
				// 修改平台账号的用户密码
				LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), username, password,
						newPassword);
				if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", le.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}
		return modelMap;
	}

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> loginCheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取是否需要进行验证码校验的标识符
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if (needVerify && !CodeUtil.checkVerifyCode(request)) { 
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 获取账号
		String username = HttpServletRequestUtil.getString(request, "username");
		// 获取原密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 非空校验
		if (username != null && password != null) {
			// 传入账号和密码且获取平台账号信息
			LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, password);
			if (localAuth != null) {
				// 若能取到账号信息则登录成功
				modelMap.put("success", true);
				// 同时在session里设置用户信息
				request.getSession().setAttribute("user", localAuth.getPersonInfo());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	/**
	 * 当用户点击登出按钮的时候注销session
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logOut(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 将用户Session置为空
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}

}
