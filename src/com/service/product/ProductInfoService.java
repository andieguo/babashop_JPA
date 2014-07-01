package com.service.product;

import java.util.List;

import com.bean.product.Brand;
import com.bean.product.ProductInfo;
import com.service.base.DAO;


public interface ProductInfoService extends DAO{
	
	public List<ProductInfo> getTopSell(Integer typeid,int maxResult);
	/**
	 * 获取类别下所有品牌
	 * @param typeids
	 * @return
	 */
	public List<Brand> getBrandsByProductTpeyid(Integer[] typeids);
	/**
	 * 设置是否上架
	 * @param productids 产品id数组
	 * @param statu true为上架，false为下架
	 */
	public void setVisibleStatu(Integer[] productids ,boolean statu);
	/**
	 * 设置产品是否推荐
	 * @param productids 产品id数组
	 * @param statu true为推荐，false为不推荐
	 */
	public void setCommandStatu(Integer[] productids ,boolean statu);

	public List<ProductInfo> getViewHistory(Integer[] productids,int maxResult);
}
