package com.action.product;

import java.io.File;

import java.util.LinkedHashMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bean.FileBean;
import com.bean.QueryResult;
import com.bean.product.ProductInfo;
import com.bean.product.ProductStyle;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.product.ProductInfoService;
import com.service.product.ProductStyleService;


@Controller @Scope("prototype")
public class ProductStyleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource private ProductStyleService productStyleService;
	@Resource private ProductInfoService productInfoService;
	private ProductStyle productStyle;
	private Integer productstyleid;
	private Integer productid;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private Integer[] stylesids;
	
	public String addUI(){
		return "success";
	}
	public String editUI(){
		productStyle = productStyleService.find(ProductStyle.class, productstyleid);//���Թ��ܵ�ʵ��
		ActionContext.getContext().put("imagepath", productStyle.getImageFullPath());//������ʽͼƬ����·��
		return "success";
	}
	public String queryUI(){
		return "success";
	}
	
	public String list(){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("visible", "desc");
		orderby.put("id", "asc");
		QueryResult<ProductStyle> qr = productStyleService.getScrollData(ProductStyle.class, -1, -1,"o.product.id=?1",new Object[]{productid}, orderby);
		ActionContext.getContext().put("styles", qr.getResultlist());
		return "success";
	}
	
	public String add() throws Exception {
		
		System.out.println("��ȡ��productid"+productid);
		ProductInfo product = productInfoService.find(ProductInfo.class, productid);
//		ProductInfo product =new ProductInfo(productid);//�ڴ˲�����
		
		productStyle.setProduct(product);//�����Ʒ
		String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(imageFileName);
		productStyle.setImagename(imagename);//����ͼƬ����
		productStyleService.save(productStyle);//�����¼
		
		//��֤�ϴ��ļ��ĸ�ʽ  image/pjpeg  ���ļ��ϴ�����true��������ʽ�ϴ����� false
		if(!FileBean.validateImageFileType(imageContentType)){//��֤��ͨ���ŷ���input
			System.out.println(imageContentType);
			addFieldError("message", "�ļ���ʽ����ȷ�������ϴ�ͼƬ�ļ�");
			return "input";//��input�ʲ���Ҫelse
		}
		if(image!=null){
			FileBean.saveProductImageFile(image, product.getProducttype().getTypeid(), productid, imagename);
		}
		productStyleService.save(productStyle);
		return "toList";
		
	}
	
	public String edit() throws Exception{
		ProductStyle ps = productStyleService.find(ProductStyle.class, productstyleid);
		productStyle.setId(productstyleid);//�ܹؼ� Ҫȷ�����޸ĵ�����һ����ʽ
		productStyle.setProduct(ps.getProduct());//ʹ��ps���������ֳ���
		productStyle.setImagename(ps.getImagename());//������޸���ʹ��ԭ�ȵ�ͼƬ
		productid = ps.getProduct().getId();//Ϊת����listҳ���ṩ����
		if(!FileBean.validateImageFileType(imageContentType)){//��֤��ͨ���ŷ���input
			System.out.println(imageContentType);
			addFieldError("message", "�ļ���ʽ����ȷ�������ϴ�ͼƬ�ļ�");
			return "input";
		}
		if(image!=null){
			String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(imageFileName);
			FileBean.saveProductImageFile(image, ps.getProduct().getProducttype().getTypeid(), ps.getProduct().getId(), imagename);
			productStyle.setImagename(imagename);//�ϴ�ͼƬ��Ϊ�� ���޸�Ϊ�µ�ͼƬ
		}
		productStyleService.update(productStyle);
		return "toList";
		
	}
	
	public String visible(){
		productStyleService.setVisibleStatu(stylesids, true);
		ActionContext.getContext().put("message", "���ò�Ʒ��ʽΪ�ɼ�");
		System.out.println("ִ���˿ɼ�");
		return "toList";
	}
	
	public String disable(){
		productStyleService.setVisibleStatu(stylesids, false);
		ActionContext.getContext().put("message", "���ò�Ʒ��ʽΪ���ɼ�");
		return "toList";
	}
	

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public ProductStyle getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(ProductStyle productStyle) {
		this.productStyle = productStyle;
	}

	public File getImage() {
		return image;
	}
	
	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Integer[] getStylesids() {
		return stylesids;
	}

	public void setStylesids(Integer[] stylesids) {
		this.stylesids = stylesids;
	}

	public Integer getProductstyleid() {
		return productstyleid;
	}

	public void setProductstyleid(Integer productstyleid) {
		this.productstyleid = productstyleid;
	}

}
