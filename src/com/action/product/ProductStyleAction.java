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
		productStyle = productStyleService.find(ProductStyle.class, productstyleid);//回显功能的实现
		ActionContext.getContext().put("imagepath", productStyle.getImageFullPath());//返回样式图片完整路径
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
		
		System.out.println("获取的productid"+productid);
		ProductInfo product = productInfoService.find(ProductInfo.class, productid);
//		ProductInfo product =new ProductInfo(productid);//在此不适用
		
		productStyle.setProduct(product);//保存产品
		String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(imageFileName);
		productStyle.setImagename(imagename);//保存图片名称
		productStyleService.save(productStyle);//保存记录
		
		//验证上传文件的格式  image/pjpeg  空文件上传返回true、其他格式上传返回 false
		if(!FileBean.validateImageFileType(imageContentType)){//验证不通过才返回input
			System.out.println(imageContentType);
			addFieldError("message", "文件格式不正确，必须上传图片文件");
			return "input";//有input故不需要else
		}
		if(image!=null){
			FileBean.saveProductImageFile(image, product.getProducttype().getTypeid(), productid, imagename);
		}
		productStyleService.save(productStyle);
		return "toList";
		
	}
	
	public String edit() throws Exception{
		ProductStyle ps = productStyleService.find(ProductStyle.class, productstyleid);
		productStyle.setId(productstyleid);//很关键 要确定你修改的是哪一个样式
		productStyle.setProduct(ps.getProduct());//使用ps的作用体现出来
		productStyle.setImagename(ps.getImagename());//如果不修改则使用原先的图片
		productid = ps.getProduct().getId();//为转发到list页面提供参数
		if(!FileBean.validateImageFileType(imageContentType)){//验证不通过才返回input
			System.out.println(imageContentType);
			addFieldError("message", "文件格式不正确，必须上传图片文件");
			return "input";
		}
		if(image!=null){
			String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(imageFileName);
			FileBean.saveProductImageFile(image, ps.getProduct().getProducttype().getTypeid(), ps.getProduct().getId(), imagename);
			productStyle.setImagename(imagename);//上传图片不为空 则修改为新的图片
		}
		productStyleService.update(productStyle);
		return "toList";
		
	}
	
	public String visible(){
		productStyleService.setVisibleStatu(stylesids, true);
		ActionContext.getContext().put("message", "设置产品样式为可见");
		System.out.println("执行了可见");
		return "toList";
	}
	
	public String disable(){
		productStyleService.setVisibleStatu(stylesids, false);
		ActionContext.getContext().put("message", "设置产品样式为不可见");
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
