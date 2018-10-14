package com.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.dto.ImageHolder;

import ch.qos.logback.core.util.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	// CommonsMultipartFile 为 Spring 自带的文件处理对象
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}

	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// 获取不重复的随机名
		String realFileName = getRandomFileName();
		// 获取文件的扩展名如 png,jpg 等
		String extension = getFileExtension(thumbnail.getImageName());
		// 如果目标路径不存在，则自动创建
		makeDirPath(targetAddr);
		// 获取文件存储的相对路径（带文件名）
		String relativeAddr = targetAddr + realFileName + extension;

		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		// 调用 Thumbnails 生成带有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		// 返回图片相对路径地址
		return relativeAddr;
	}
	
	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机的五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		// String + int ==> String
		return nowTimeStr + rannum;
	}

	/**
	 * 获取输入文件流的扩展名（例如：.jpg,.png等等）
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		// 获取输入文件流的原来的文件名
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 创建目标路径所涉及到的目录，即/home/work/o2oPicture/xxx.jpg 那么 home work o2oPicture
	 * 这是哪个文件夹都得自动创建
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			// 会将一路上所需要的但不存在的目录给创建出来
			dirPath.mkdirs();
		}
	}

	/**
	 * storePath 是文件的路径还是目录路径， 如果storePath是文件路径则删除文件，
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 * 
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
		}
		fileOrPath.delete();
	}

	// 测试处理图片 --> 添加水印
	public static void main(String[] args) throws IOException {
		String basePath1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		Thumbnails.of(new File("C:/Users/yy/Desktop/2015118050.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath1 + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile("C:/Users/yy/Desktop/2015118050new.jpg");
	}
}
