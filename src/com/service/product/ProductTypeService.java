package com.service.product;

import java.util.List;

import com.service.base.DAO;

public interface ProductTypeService extends DAO{
	/**
	 * ��ȡ�¼����id
	 * @param parentid
	 * @return
	 */
	public List<Integer> getSubTypeid(Integer[] parentids);

}