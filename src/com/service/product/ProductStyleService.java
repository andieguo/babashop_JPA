package com.service.product;

import com.service.base.DAO;

public interface ProductStyleService extends DAO {

	public void setVisibleStatu(Integer[] stylesids, boolean statu);
}
