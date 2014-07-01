package com.action.product;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bean.FileBean;
import com.bean.PageView;
import com.bean.QueryResult;
import com.bean.product.Brand;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.product.BrandService;

@Controller @Scope("prototype")
public class BrandAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	@Resource private BrandService brandService;
	private Brand brand ;
	private String code;
	private Integer page = 1;
	private File logo;
	private String logoFileName;
	private String logoContentType;
	private String query;//��ѯ��־
	private String name;//��ѯbrand����
	
	public String addUI(){
		return "success";
		
	}
	public String editUI(){
		brand = brandService.find(Brand.class, code);
		return  "success";
	}
	public String queryUI(){
		return "success";
	}
	public String list(){
		
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if("true".equals(query)){
			if(name!=null && !"".equals(name.trim())){
				jpql.append(" and o.name like ?"+(params.size()+1));
				params.add("%" +name+"%");	
			}
		}
		PageView<Brand> pageView = new PageView<Brand>(2,page);
		int firstindex = (pageView.getCurrentpage()-1)*pageView.getMaxresult();//��ȡ��¼�Ŀ�ʼ����
		QueryResult<Brand> qr = brandService.getScrollData(Brand.class, firstindex, pageView.getMaxresult(),jpql.toString(),params.toArray());
		pageView.setQueryResult(qr);		
		ActionContext.getContext().put("pageView", pageView);

		return "success";	
	}
	
	public String add() throws IOException{
		System.out.println("�ļ��ϴ�����"+logoContentType);//�ܹ���������ļ�����
		//��֤�ϴ��ļ��ĸ�ʽ  image/pjpeg
		if(!FileBean.validateImageFileType(logoContentType)){//��֤��ͨ���ŷ���input
			System.out.println(logoContentType);
			addFieldError("message", "�ļ���ʽ����ȷ�������ϴ�ͼƬ�ļ�");
			return "input";
		}else{
			if(logo!=null){
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
				String logopathdir="/images/brand/"+dateformat.format(new Date());
				String realpath = ServletActionContext.getServletContext().getRealPath(logopathdir);	
				String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(logoFileName);
				File savefile = new File(new File(realpath),imagename);
				if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
				
				FileUtils.copyFile(logo, savefile);
				brand.setLogopath(logopathdir+"/"+imagename);	
			}
			brandService.save(brand);
			return "toList";
		}
	}
	
	public String edit() throws IOException{
		Brand bd = brandService.find(Brand.class, code);
		brand.setCode(code);//�ܹؼ�ָ��Ҫ�޸ĵ�����һ��ʵ��
		brand.setLogopath(bd.getLogopath());
		if(!FileBean.validateImageFileType(logoContentType)){//��֤��ͨ���ŷ���input
			System.out.println(logoContentType);
			addFieldError("message", "�ļ���ʽ����ȷ�������ϴ�ͼƬ�ļ�");
			return "input";
		}
		if(logo!=null){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir="/images/brand/"+dateformat.format(new Date());
			String realpath = ServletActionContext.getServletContext().getRealPath(logopathdir);
			
			String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(logoFileName);
			File savefile = new File(new File(realpath),imagename);
			if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
			
			FileUtils.copyFile(logo, savefile);
			brand.setLogopath(logopathdir+"/"+imagename);	//�޸�֮��ĵ�ַ
		}
		brandService.update(brand);
		return "toList";
		
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}




}
