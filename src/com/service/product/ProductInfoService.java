package com.service.product;

import java.util.List;

import com.bean.product.Brand;
import com.bean.product.ProductInfo;
import com.service.base.DAO;


public interface ProductInfoService extends DAO{
	
	public List<ProductInfo> getTopSell(Integer typeid,int maxResult);
	/**
	 * ��ȡ���������Ʒ��
	 * @param typeids
	 * @return
	 */
	public List<Brand> getBrandsByProductTpeyid(Integer[] typeids);
	/**
	 * �����Ƿ��ϼ�
	 * @param productids ��Ʒid����
	 * @param statu trueΪ�ϼܣ�falseΪ�¼�
	 */
	public void setVisibleStatu(Integer[] productids ,boolean statu);
	/**
	 * ���ò�Ʒ�Ƿ��Ƽ�
	 * @param productids ��Ʒid����
	 * @param statu trueΪ�Ƽ���falseΪ���Ƽ�
	 */
	public void setCommandStatu(Integer[] productids ,boolean statu);

	public List<ProductInfo> getViewHistory(Integer[] productids,int maxResult);
}
