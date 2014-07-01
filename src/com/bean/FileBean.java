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
	 * ��ȡ�ļ���׺ jpg��
	 * @param formfile �ϴ����ļ�
	 * @return
	 */
	public static String getExt(String fileName){
		return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	}
	
	/**
	 * ��֤�ϴ��ļ������Ƿ�����ͼƬ��ʽ
	 * @return
	 */
	public static boolean  validateImageFileType(String fileContentType){

		//������ϴ��ļ�������    image/pjpeg
		if(fileContentType!=null){
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/jpg","image/gif","image/jpeg","image/pjpeg");			
			return arrowType.contains(fileContentType.toLowerCase());//��ʽ���ڷ���true;
		}else{
			return true;//�����ϴ�logoͼƬ
		}
	}
	

	/**
	 * ��֤�ϴ��ļ�����
	 * @return
	 */

	public static boolean  validateFileType(String fileName , String fileContentType){
		String ext = FileBean.getExt(fileName);
	
		//������ϴ��ļ�������
		List<String> arrowType = new ArrayList<String>();
		for(Object key :properties.keySet()){
			String value = (String) properties.get(key);//�õ�����value��ֵ
			String[] values = value.split(",");
			for(String v:values ){
				arrowType.add(v.trim());
			}
		}
		return arrowType.contains(fileContentType.toLowerCase())&&properties.keySet().contains(ext);//��ʽ���ڷ���true;

	}
	
	/**
	 * �����Ʒ��ʽͼƬ
	 * @param file �ϴ�����ʽͼƬ�ļ�
	 * @param productTypeId ��Ʒ����id
	 * @param productId ��Ʒid
	 * @param fileName ����UUID����ͼƬ�ļ���
	 * @throws Exception
	 */
	public static void saveProductImageFile(File file ,Integer productTypeId,Integer productId,String fileName) throws Exception{
		String ext = FileBean.getExt(fileName);	
		String pathdir = "/images/product/"+ productTypeId+ "/"+ productId+ "/prototype";//�����ļ������Ŀ¼
		//�õ�ͼƬ����Ŀ¼����ʵ·��
		String realpathdir = ServletActionContext.getServletContext().getRealPath(pathdir);
		System.out.println(realpathdir);
		File savedir = new File(realpathdir);
		if(!savedir.exists()) savedir.mkdirs();//���Ŀ¼�����ھʹ���
		FileUtils.copyFile(file, new File(savedir,fileName));
		
		String pathdir140 = "/images/product/"+ productTypeId+ "/"+ productId+ "/140x";//140��ȵ�ͼƬ����Ŀ¼
		String realpathdir140 = ServletActionContext.getServletContext().getRealPath(pathdir140);
		File savedir140 = new File(realpathdir140);
		if(!savedir140.exists()) savedir140.mkdirs();//���Ŀ¼�����ھʹ���
		
		File file140 = new File(realpathdir140, fileName);
		ImageSizer.resize(file, file140, 140, ext);//fileΪԭͼ��file140Ϊѹ�����ͼ��
	}
}
