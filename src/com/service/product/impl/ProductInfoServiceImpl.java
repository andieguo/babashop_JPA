package com.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.product.Brand;
import com.bean.product.ProductInfo;
import com.service.base.DaoSupport;
import com.service.product.ProductInfoService;
import com.service.product.ProductTypeService;

@Service
public class ProductInfoServiceImpl extends DaoSupport implements
		ProductInfoService {
	@Resource private ProductTypeService productTypeService;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductInfo> getViewHistory(Integer[] productids,int maxResult){
		StringBuffer jpql = new StringBuffer();
		for(int i=0;i<productids.length;i++){
			jpql.append('?').append(i).append(',');
		}
		jpql.deleteCharAt(jpql.length()-1);
		Query query = em.createQuery("select o from ProductInfo o where o.id in (" +
				jpql.toString()+")");
		for(int i=0;i<productids.length;i++){
			query.setParameter(i, productids[i]);
			
		}
		query.setFirstResult(0).setMaxResults(maxResult);
		return query.getResultList();
	}
	
	/**
	 * 获取销量最好的产品===当typeid是顶级类型的时候，将其子级别类型全部输出来
	 * @param typeid
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductInfo> getTopSell(Integer typeid,int maxResult){
		List<Integer> typeids = new ArrayList<Integer>();//存放所有子类型typeid
		typeids.add(typeid);//将顶级类型id放入其中
		getTypedis(typeids, new Integer[]{typeid});//调用方法获取所有的其旗下的子类型typeids
		StringBuffer n = new StringBuffer();
		for(int i=0;i<typeids.size();i++){
			n.append('?').append((i+2)).append(',');
		}
		n.deleteCharAt(n.length()-1);
		Query query = em.createQuery("select o from ProductInfo o where o.commend=?1 and o.producttype.typeid in(" +
				n.toString()+") order by sellcount desc");
		query.setParameter(1, true);
		for(int i=0;i<typeids.size();i++){
			query.setParameter(i+2, typeids.get(i));
		}
		query.setFirstResult(0).setMaxResults(maxResult);
		return query.getResultList();
	}
	
	/**
	 * 获取类别下所有子类id(注意：使用递归)
	 * @param outtypeids
	 * @param typeids
	 */
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	private void getTypedis(List<Integer> outtypeids,Integer[] typeids){
		
		List<Integer> subtypeids = productTypeService.getSubTypeid(typeids);
		if(subtypeids!=null&&subtypeids.size()>0){
			outtypeids.addAll(subtypeids);
			getTypedis(outtypeids, subtypeids.toArray(new Integer[]{}));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<Brand> getBrandsByProductTpeyid(Integer[] typeids){
		StringBuffer jpql = new StringBuffer();
		if(typeids!=null && typeids.length>0){
			for(int i=0;i<typeids.length;i++){
				jpql.append('?').append((i+1)).append(',');
			}
		}
		jpql.deleteCharAt(jpql.length()-1);
		Query query = em.createQuery("select o from Brand o where o.code in(select p.brand.code from ProductInfo p where p.producttype.typeid in (" +
				jpql.toString()+") group by p.brand.code)");
		for(int i=0;i<typeids.length;i++){
			query.setParameter(i+1, typeids[i]);
		}
		return query.getResultList();
	}

	@Override
	public void setVisibleStatu(Integer[] productids, boolean statu) {
		if(productids!=null && productids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0;i<productids.length;i++){
				jpql.append('?').append((i+2)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update ProductInfo o set o.visible = ?1 where o.id in(" +jpql.toString()+")");
			query.setParameter(1, statu);
			for(int i=0;i<productids.length;i++){
				query.setParameter(i+2, productids[i]);
			}
			query.executeUpdate();
		}
	}

	@Override
	public void setCommandStatu(Integer[] productids, boolean statu) {
		if(productids!=null && productids.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0;i<productids.length;i++){
				jpql.append('?').append((i+2)).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update ProductInfo o set o.commend = ?1 where o.id in(" +jpql.toString()+")");
			query.setParameter(1, statu);
			for(int i=0;i<productids.length;i++){
				query.setParameter(i+2, productids[i]);
			}
			query.executeUpdate();
		}
		
	}

}
