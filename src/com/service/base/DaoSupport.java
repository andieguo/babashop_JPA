package com.service.base;

import java.util.LinkedHashMap;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.QueryResult;

@Transactional
public abstract class DaoSupport implements DAO {
	@PersistenceContext protected EntityManager em;
	
	@Override
	//打开一个事务
	public void save(Object object) {
		em.persist(object);

	}
	//关闭一个事务
	/* (non-Javadoc)
	 * @see com.service.base.DAO#update(java.lang.Object)
	 */
	
	@Override
	public void update(Object object) {
		em.merge(object);

	}

	/* (non-Javadoc)
	 * @see com.service.base.DAO#delete(java.lang.Class, java.lang.Object)
	 */

	@Override
	public  <T> void delete(Class<T> entityClass,Object entityid) {
		
		delete(entityClass, new Object[]{entityid});

	}

	/* (non-Javadoc)
	 * @see com.service.base.DAO#delete(java.lang.Class, java.lang.Object[])
	 */
	
	@Override
	public  <T> void delete(Class<T> entityClass ,Object[] entityids) {
		
		for(Object id : entityids){
			em.remove(em.getReference(entityClass, id));
		}

	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#find(java.lang.Class, java.lang.Object)
	 */
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> T find(Class<T> entityClass, Object entityid) {
		
		return em.find(entityClass, entityid);
	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class)
	 */

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass,-1,-1,null,null);
	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int)
	 */
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult) {
		// TODO Auto-generated method stub
		return getScrollData(entityClass,firstindex,maxresult,null,null,null);
	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[])
	 */

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams) {
		
		return getScrollData(entityClass,firstindex,maxresult,wherejpql,queryParams,null);
	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.util.LinkedHashMap)
	 */
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		
		return getScrollData(entityClass,firstindex,maxresult,null,null,orderby);
	}
	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex,int maxresult,
			String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby){
		
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(entityClass);
		
		Query query = em.createQuery("select o from " +entityname+" o "+(wherejpql==null?"":"where "+wherejpql)+buildOrderby(orderby));//o代表实体的属性
		setQueryParams(query,queryParams);//添加where参数
		if(firstindex!=-1&&maxresult!=-1){
			query.setFirstResult(firstindex).setMaxResults(maxresult);//添加查询参数
		}
		qr.setResultlist(query.getResultList());
		query = em.createQuery("select count(o) from " +entityname+" o "+(wherejpql==null?"":"where "+wherejpql));
		setQueryParams(query,queryParams);
		qr.setTotalrecord((Long)query.getSingleResult());
		return qr;
		
	}
	//where o.key=?1 andy key2=?2
	protected void setQueryParams(Query query,Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0;i<queryParams.length;i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
		
	}
	
	protected <T> String getEntityName(Class<T> entityClass){
		String entityname = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null&&!"".equals(entity.name())){
			entityname=entity.name();
		}
		return entityname;
	}
	//Order by o.key1 desc,o.key2 asc; 
	protected  String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null&& orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	
}
