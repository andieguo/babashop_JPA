package com.action.product;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bean.PageView;
import com.bean.QueryResult;
import com.bean.product.Board;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.product.ProductTypeService;

@SuppressWarnings("serial")
@Controller @Scope("prototype")
public class ProductTypeAction extends ActionSupport {
	@Resource private ProductTypeService productTypeService; //按名次找不到,会按类型找
	private Board productType;
	private Integer parentid;
	private Integer typeid ;
	private Integer page = 1;
	private String query;//查询标志
	private String name;//查询类型名称
	
	public String addUI(){
		return "success";//		转发到添加产品类别的页面
	}
	public String editUI(){
		productType = productTypeService.find(Board.class, typeid);//回显功能的实现
		if( productType.getParent()!=null){
			parentid = productType.getParent().getTypeid();	
		}
		return "success";//		转发到添加产品类别的页面
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
		}else{//相当关键
			if(parentid!= null&& parentid > 0 ){
				jpql.append(" and o.parent.typeid=?"+(params.size()+1));
				params.add( parentid );
			}else{
				jpql.append(" and o.parent is null");
			}
		}
		
		
		PageView<Board> pageView = new PageView<Board>(2,page);
		int firstindex = (pageView.getCurrentpage()-1)*pageView.getMaxresult();//获取记录的开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("typeid", "asc");
		QueryResult<Board> qr = productTypeService.getScrollData(Board.class, firstindex, pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		ActionContext.getContext().put("pageView", pageView);
		return SUCCESS;
		
	}
	
	public String add(){
		if(parentid != null &&  parentid > 0){
//			System.out.println(productType.getName());
			productType.setParent(new Board(parentid));
		}
		productTypeService.save(productType);

		return "toList";//		添加成功后转发到list页面
	}
	
	public String edit(){
//		ProductType temp  = productTypeService.find(ProductType.class, typeid);
		productType.setTypeid(typeid);//设置编辑哪一个产品类型
		productType.setParent(new Board(parentid));//设置所属父类类型
		productTypeService.update(productType);
		return "toList";
	}
	

	public Board getProductType() {
		return productType;
	}
	public void setProductType(Board productType) {
		this.productType = productType;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
