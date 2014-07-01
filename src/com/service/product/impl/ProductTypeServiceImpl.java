package com.service.product.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.service.base.DaoSupport;
import com.service.product.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceImpl extends DaoSupport implements ProductTypeService {
	
	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		if(entityids!=null&&entityids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0;i<entityids.length;i++){
				jpql.append("?").append(i+2).append(",");
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update ProductType o set o.visible=?1 where o.typeid in(" +
			jpql.toString()+")").setParameter(1, false);
			for(int i=0;i<entityids.length;i++){
				query.setParameter(i+2, entityids[i]);
			}
			query.executeUpdate();
		}
			//update ProductType o set o.visible=?1 where o.typeid in(?2,?3,?4);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<Integer> getSubTypeid(Integer[] parentids){
		StringBuffer jpql = new StringBuffer();
		if(parentids!=null && parentids.length>0){
			for(int i=0;i<parentids.length;i++){
				jpql.append('?').append((i+1)).append(',');
			}
		}
		jpql.deleteCharAt(jpql.length()-1);
		Query query = em.createQuery("select o.typeid from ProductType o where o.parent.typeid in(" +jpql.toString()+")");
		for(int i=0;i<parentids.length;i++){
			query.setParameter(i+1, parentids[i]);
		}
		return query.getResultList();
		
	}
	
}
