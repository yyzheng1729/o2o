package com.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	//返回项目图片的根路径
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "C:/projectdev/image/";
		} else {
			basePath = "/home/o2o/image/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	//根据不同的业务需求，返回项目图片的子路径
	public static String getShopImagePath(int shopId){
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
}
