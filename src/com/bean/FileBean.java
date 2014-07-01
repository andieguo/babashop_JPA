package com.bean;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.utils.ImageSizer;

public class FileBean {

	private static Properties properties = new Properties();
	static{
		try {
			properties.load(FileBean.class.getClassLoader().getResourceAsStream("arrowuploadfiletype.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文件后缀 jpg等
	 * @param formfile 上传的文件
	 * @return
	 */
	public static String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	}
	
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @return
	 */
	public static boolean  validateImageFileType(String fileContentType){

		//容许的上传文件的类型    image/pjpeg
		if(fileContentType!=null){
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/jpg","image/gif","image/jpeg","image/pjpeg");			
			return arrowType.contains(fileContentType.toLowerCase());//格式存在返回true;
		}else{
			return true;//容许不上传logo图片
		}
	}
	

	/**
	 * 验证上传文件类型
	 * @return
	 */

	public static boolean  validateFileType(String fileName , String fileContentType){
		String ext = FileBean.getExt(fileName);
	
		//容许的上传文件的类型
		List<String> arrowType = new ArrayList<String>();
		for(Object key :properties.keySet()){
			String value = (String) properties.get(key);//得到的是value的值
			String[] values = value.split(",");
			for(String v:values ){
				arrowType.add(v.trim());
			}
		}
		return arrowType.contains(fileContentType.toLowerCase())&&properties.keySet().contains(ext);//格式存在返回true;

	}
	
	/**
	 * 保存产品样式图片
	 * @param file 上传的样式图片文件
	 * @param productTypeId 产品类型id
	 * @param productId 产品id
	 * @param fileName 生成UUID码后的图片文件名
	 * @throws Exception
	 */
	public static void saveProductImageFile(File file ,Integer productTypeId,Integer productId,String fileName) throws Exception{
		String ext = FileBean.getExt(fileName);	
		String pathdir = "/images/product/"+ productTypeId+ "/"+ productId+ "/prototype";//构建文件保存的目录
		//得到图片保存目录的真实路径
		String realpathdir = ServletActionContext.getServletContext().getRealPath(pathdir);
		System.out.println(realpathdir);
		File savedir = new File(realpathdir);
		if(!savedir.exists()) savedir.mkdirs();//如果目录不存在就创建
		FileUtils.copyFile(file, new File(savedir,fileName));
		
		String pathdir140 = "/images/product/"+ productTypeId+ "/"+ productId+ "/140x";//140宽度的图片保存目录
		String realpathdir140 = ServletActionContext.getServletContext().getRealPath(pathdir140);
		File savedir140 = new File(realpathdir140);
		if(!savedir140.exists()) savedir140.mkdirs();//如果目录不存在就创建
		
		File file140 = new File(realpathdir140, fileName);
		ImageSizer.resize(file, file140, 140, ext);//file为原图像，file140为压缩后的图像
	}
}
