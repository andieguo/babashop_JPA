package com.service.product;

import java.util.List;

import com.service.base.DAO;

public interface ProductTypeService extends DAO{
	/**
	 * 获取下级类别id
	 * @param parentid
	 * @return
	 */
	public List<Integer> getSubTypeid(Integer[] parentids);

}