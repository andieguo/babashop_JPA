package com.service.product.impl;

import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.product.Brand;
import com.service.base.DaoSupport;
import com.service.product.BrandService;
@Service
@Transactional
public class BrandServiceImpl extends DaoSupport implements BrandService {

	@Override
	public void save(Object object) {
		((Brand)object).setCode(UUID.randomUUID().toString());
		super.save(object);
	}

	
}
