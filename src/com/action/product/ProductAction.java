package com.action.product;

import java.io.File;

import java.util.ArrayList;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bean.FileBean;
import com.bean.PageView;
import com.bean.QueryResult;
import com.bean.product.Brand;
import com.bean.product.ProductInfo;
import com.bean.product.ProductStyle;
import com.bean.product.Board;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.product.BrandService;
import com.service.product.ProductInfoService;
import com.service.product.ProductTypeService;

@Controller @Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<ProductInfo>{

	private static final long serialVersionUID = 1L;
	@Resource private ProductInfoService productInfoService;
	@Resource private BrandService brandService;
	@Resource private ProductTypeService productTypeService;

	//采用模型驱动
	private ProductInfo model =new ProductInfo();//用于封装会员属性模型
	private Integer productid;
	private Integer page = 1 ;
	private String query;//查询标记
	private Integer[] productids;
	private Float startbaseprice;
	private Float endbaseprice;
	private Float startsellprice;
	private Float endsellprice;
	private String message;
	private Integer typeid;//产品类型id
	private String brandid;//品牌id
	private String stylename;//样式名称
	private File image;
	private String imageFileName;
	private String imageContentType;
	
	public String addUI(){
		ActionContext.getContext().put("brands", brandService.getScrollData(Brand.class).getResultlist());
		return "success";
	}
	public String editUI(){
		ProductInfo tempProduct = productInfoService.find(ProductInfo.class, productid);
		ActionContext.getContext().getValueStack().pop();
		ActionContext.getContext().getValueStack().push(tempProduct);
		brandid = tempProduct.getBrand().getCode();//很关键 回显
		typeid = tempProduct.getProducttype().getTypeid();//很关键 回显
	
		ActionContext.getContext().put("brands", brandService.getScrollData(Brand.class).getResultlist());
		return "success";
	}
	public String queryUI(){
		ActionContext.getContext().put("brands", brandService.getScrollData(Brand.class).getResultlist());
		return "success";
	}
	public String selectUI(){
		String jpql = "o.parent is null";//初始时，显示所有的顶级目录；故parent is null
		Object[] params = new Object[0];
		if(typeid!=null&& typeid>0){
			jpql = "o.parent.id=?1";//将其所要单击的id给parant.id 即访问该id下的所有下级目录;
			params = new Object[]{typeid};
			//创建导航
			Board type = productTypeService.find(Board.class,typeid);
			List<Board> types = new ArrayList<Board>();
			types.add(type);//将当前type添加进types
			while(type.getParent()!=null){
				types.add(type.getParent());
				type=type.getParent();
			}
			Board[] values = new Board[types.size()];
			for(int i = types.size()-1;i>=0;i--){
				values[types.size()-i-1]= types.get(i);
			}
			ActionContext.getContext().put("menutypes", values);
			
		}
		ActionContext.getContext().put("types",productTypeService.getScrollData(Board.class, -1, -1, jpql, params).getResultlist());
		return "selsectType";
	}
	
	public String list(){
		StringBuffer jpql = new StringBuffer("1=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		if("true".equals(query)){
			System.out.println(model.getCommend());
			if(model.getCommend()==true){
				jpql.append(" and o.commend = ?"+(params.size()+1));
				params.add(model.getCommend());	
			}
			if(model.getCommend()==false){
				jpql.append(" and o.commend = ?"+(params.size()+1));
				params.add(model.getCommend());	
			}
			if(model.getVisible()== true){
				jpql.append(" and o.visible = ?"+(params.size()+1));
				params.add(model.getVisible());	
			}
			if(model.getVisible()== false){
				jpql.append(" and o.visible = ?"+(params.size()+1));
				params.add(model.getVisible());	
			}
			if(model.getName()!=null && !"".equals(model.getName().trim())){
				jpql.append(" and o.name like ?"+(params.size()+1));
				params.add("%" +model.getName()+"%");	
			}
			if(typeid!=null && typeid>0){
				jpql.append(" and o.producttype.typeid = ?"+(params.size()+1));
				params.add(typeid);
			}
			if(startbaseprice!=null && startbaseprice>0){
				jpql.append(" and o.baseprice >=?" +(params.size()+1));
				params.add(startbaseprice);
			}
			if(endbaseprice!=null && endbaseprice>0){
				jpql.append(" and o.baseprice <=?" +(params.size()+1));
				params.add(endbaseprice);
			}
			if(startsellprice!=null && startsellprice>0){
				jpql.append(" and o.sellprice >=?" +(params.size()+1));
				params.add(startsellprice);
			}
			if(endsellprice!=null && endsellprice>0){
				jpql.append(" and o.sellprice <=?" +(params.size()+1));
				params.add(endsellprice);
			}
			if(model.getCode()!=null && !"".equals(model.getCode().trim())){
				jpql.append(" and o.code =?" +(params.size()+1));
				params.add(model.getCode());
			}
			if(brandid!=null && !"".equals(brandid.trim())){//产品所属品牌
				jpql.append(" and o.brand.code =?" +(params.size()+1));
				params.add(brandid);
			}	
		}
		PageView<ProductInfo> pageView = new PageView<ProductInfo>(6,page);
		int firstindex = (pageView.getCurrentpage()-1)*pageView.getMaxresult();//获取记录的开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		QueryResult<ProductInfo> qr = productInfoService.getScrollData(ProductInfo.class, firstindex, pageView.getMaxresult(),jpql.toString(), params.toArray(),orderby);
		pageView.setQueryResult(qr);
		ActionContext.getContext().put("pageView", pageView);
		return "success";
	}
	public String add() throws Exception{
		model.setCreatedate(new Date());
		//添加时性别是自动匹配的  属性要与实体属性一致
		model.setBrand(brandService.find(Brand.class, brandid));
		model.setProducttype(productTypeService.find(Board.class, typeid));
		
		String imagename = UUID.randomUUID().toString()+"."+FileBean.getExt(imageFileName);
		model.addProductStyle(new ProductStyle(stylename,imagename,true));
		productInfoService.save(model);

		if(!FileBean.validateImageFileType(imageContentType)){
			System.out.println(imageContentType);
			addFieldError("message", "文件格式不正确，必须上传图片文件");
			return "input";
		}
		if(image!=null){
			FileBean.saveProductImageFile(image, model.getProducttype().getTypeid(), model.getId(), imagename);
		}				
		return "toList";
	}
	public String edit(){
		model.setId(productid);
		model.setBrand(new Brand(brandid));
		model.setProducttype(new Board(typeid));
		productInfoService.update(model);
		return "toList";
	}
	public String visible(){
		productInfoService.setVisibleStatu(productids,true);
		message = "上架成功";
		return "toList";
	}
	public String disable(){
		productInfoService.setVisibleStatu(productids,false);
		message = "下架成功";
		return "toList";
	}
	public String commend(){
		productInfoService.setCommandStatu(productids, true);
		message = "设置为推荐";
		return "toList";
	}
	public String uncommend(){
		productInfoService.setCommandStatu(productids, false);
		message = "设置为不推荐";
		return "toList";
	}
	public ProductInfo getModel() {
		return model;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Float getStartbaseprice() {
		return startbaseprice;
	}
	public void setStartbaseprice(Float startbaseprice) {
		this.startbaseprice = startbaseprice;
	}
	public Float getEndbaseprice() {
		return endbaseprice;
	}
	public void setEndbaseprice(Float endbaseprice) {
		this.endbaseprice = endbaseprice;
	}
	public Float getStartsellprice() {
		return startsellprice;
	}
	public void setStartsellprice(Float startsellprice) {
		this.startsellprice = startsellprice;
	}
	public Float getEndsellprice() {
		return endsellprice;
	}
	public void setEndsellprice(Float endsellprice) {
		this.endsellprice = endsellprice;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

	public Integer[] getProductids() {
		return productids;
	}

	public void setProductids(Integer[] productids) {
		this.productids = productids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getStylename() {
		return stylename;
	}
	public void setStylename(String stylename) {
		this.stylename = stylename;
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
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public void setModel(ProductInfo model) {
		this.model = model;
	}

}
