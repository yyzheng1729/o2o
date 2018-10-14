package com.wechat;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.util.wechat.MenuUtil;

import net.sf.json.JSONObject;

public class MenuCreateTest {
	@Test
	public void test() throws ClientProtocolException, IOException{
		String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();
		int result = MenuUtil.createMenu("13_mYKonJae87ArWn16vy-qplHuhAgFa3eilGNSBQwihgkM3BA8HN1i1zP4J8zg6yyng3VLpNtEws-qD4561Pl_BzPglefEuMwJkKa3YQqCgp1noWQuJQ3EZ45j_dX9iKiCnjkwOPqCXwt4UaPlNVNbAEATBT", menu);
		System.out.println(result);
	}
}
