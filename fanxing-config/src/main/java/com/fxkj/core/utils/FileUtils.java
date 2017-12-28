package com.fxkj.core.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fxkj.core.base.MsgUtil;
import com.fxkj.core.common.Commons;

public class FileUtils {
	

	/**
	 * 
	 * @param request
	 * @param map
	 * @param savePathFull
	 * @param newName
	 * @param uploadOriginally
	 *            是否上传原图
	 * @param uploadCompress
	 *            是否上传裁剪后的图片
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static MsgUtil uploadPhoto(HttpServletRequest request,
			Map<String, String> map, String savePathFull, String newName,
			Boolean uploadOriginally, Boolean uploadCompress, Integer x,
			Integer y, Integer w, Integer h, Double maxSize)
			throws IllegalStateException, IOException {
//		String savePath = request.getRealPath("") + File.separator + "upload"
//				+ File.separator + savePathFull;
		String savePath=savePathFull;
		MultipartHttpServletRequest multiRequest = getMultiRequest(request);
		if (multiRequest != null) {
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					if (StringUtil.isNotEmpty(fileName)) {
						MsgUtil msg = checkFileTypeAndSize(file, maxSize);
						if (msg != null)
							return msg;
						File f = new File(savePath);
						if (!f.exists()) {
							f.mkdirs();
						}
						if (uploadCompress) {
							uploadCompressPhoto(file, map, newName, savePath,
									x, y, w, h);
						}
						if (uploadOriginally) {
							String extName = fileName.substring(fileName
									.lastIndexOf("."));
							File localFile = new File(savePath + newName + "_o"
									+ extName);
							file.transferTo(localFile);
							if (map.containsKey(Commons.IMG_KEY_ORIGINAL))
								map.put(Commons.IMG_KEY_ORIGINAL,
										map.get(Commons.IMG_KEY_ORIGINAL) + ";"
												+ (newName + "_o" + extName));
							else
								map.put(Commons.IMG_KEY_ORIGINAL, newName
										+ "_o" + extName);
						}

					}
				}
			}
		}
		return null;
	}

	/**
	 * 创建一个通用解析器
	 * 
	 * @param request
	 * @return
	 */
	private static MultipartHttpServletRequest getMultiRequest(
			HttpServletRequest request) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			return multiRequest;
		}

		return null;
	}

	/**
	 * 校验文件格式
	 * 
	 * @param file
	 * @return
	 */
	private static MsgUtil checkFileTypeAndSize(MultipartFile file,
			Double maxSize) {
		String fileName = file.getOriginalFilename();
		long size = file.getSize();
		if (size > (1024 * 1024 * maxSize)) {
			return new MsgUtil(false, "只允许上传" + maxSize + "M之内的图片");
		}
		String extName = "";
		if (fileName.lastIndexOf(".") >= 0) {
			extName = fileName.substring(fileName.lastIndexOf("."));
		}
		if (!Commons.UPLOADIMGTYPE.contains(extName.toLowerCase())) {
			return new MsgUtil(false, "只允许上传"
					+ Commons.UPLOADIMGTYPE.replaceAll(";", "") + "格式的图片");
		}
		return null;
	}

	/**
	 * 上传剪切后的图片
	 * 
	 * @param file
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param newName
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	private static MsgUtil uploadCompressPhoto(MultipartFile file,
			Map<String, String> map, String newName, String savePath,
			Integer x, Integer y, Integer w, Integer h) throws IOException {
		String fileName = file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		Iterator<ImageReader> it = ImageIO
				.getImageReadersByFormatName(new String(extName.substring(1)
						.getBytes(), "utf-8"));
		ImageReader reader = it.next();
		ImageInputStream iis = ImageIO.createImageInputStream(file
				.getInputStream());
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, extName.substring(1), new File(savePath
				+ File.separator + newName + "_c" + extName));
		if (map.containsKey(Commons.IMG_KEY_COMPRESS))
			map.put(Commons.IMG_KEY_COMPRESS, map.get(Commons.IMG_KEY_COMPRESS)
					+ ";" + (newName + "_c" + extName));
		else
			map.put(Commons.IMG_KEY_COMPRESS, newName + "_c" + extName);

		return null;
	}

	/**
	 * 查找文件夹下的文件或子文件夹下的文件
	 * 
	 * @param file
	 *            文件或文件夹
	 * @param pat
	 *            格式
	 * @return 文件列表
	 */
	public static List<File> fileAllPattern(File file, Pattern pat) {

		if (file == null || !file.exists()) {
			return null;
		}

		if (file.isFile()) {
			Matcher fMatcher = pat.matcher(file.getName());
			if (fMatcher.matches()) {
				List<File> list = new ArrayList<File>();
				list.add(file);
				return list;
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				List<File> list = new ArrayList<File>();
				for (int i = 0; i < files.length; i++) {
					List<File> rlist = fileAllPattern(files[i], pat);
					if (rlist != null) {
						list.addAll(rlist);
					}
				}
				return list;
			}
		} else {
			return null;
		}

		return null;
	}

	public static String streamConvertToStr(InputStream inputStream) {
		try {
			byte[] bytes = new byte[1024 * 1024];
			int nRead = 1;
			int nTotalRead = 0;
			while (nRead > 0) {
				nRead = inputStream.read(bytes, nTotalRead, bytes.length
						- nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			return new String(bytes, 0, nTotalRead, "utf-8");
		} catch (IOException e) {
			return "";
		}

	}

}
