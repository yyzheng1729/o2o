package com.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.LocalAuthDao;
import com.dto.LocalAuthExecution;
import com.enums.LocalAuthStateEnum;
import com.pojo.LocalAuth;
import com.service.LocalAuthService;
import com.util.MD5;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public LocalAuth getLocalAuthByUserNameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(int userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws RuntimeException {
		// 控制判断，传入的localAuth账号密码，用户信息特别上userId不能为空，否则直接返回错误
		System.out.println("localAuth:" + localAuth);
		System.out.println("localAuth.getPassword():" + localAuth.getPassword());
		System.out.println("localAuth.getUsername():" + localAuth.getUsername());
		System.out.println("localAuth.getPersonInfo().getUserId():" + localAuth.getPersonInfo().getUserId());
		if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
				|| localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		// 查询此用户是否已绑定过平台账号
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		System.out.println("tempAuth:" + tempAuth);
		if (tempAuth != null) {
			// 如果绑定过则直接退出，以保证平台账号的唯一性
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			// 如果之前没有绑定过平台账号，则创建一个平台账号与该用户绑定
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			// 对密码进行MD5加密
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			// 判断创建是否成功
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号绑定失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertLocalAuth error: " + e.getMessage());
		}
	}

	@Override
	public LocalAuthExecution modifyLocalAuth(int userId, String username, String password, String newPassword) {
		// 非空判断，判断传入的用户Id，账号，新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
		if (userId > 0 && username != null && password != null && newPassword != null
				&& !password.equals(newPassword)) {
			try {
				// 更新密码，并对新密码进行MD5加密
				int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				// 判断更新是否成功
				if (effectedNum <= 0) {
					throw new RuntimeException("更新密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new RuntimeException("更新密码失败:" + e.toString());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
