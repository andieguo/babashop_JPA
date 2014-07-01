package com.service.base;

import java.util.LinkedHashMap;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.QueryResult;

public interface DAO {

	//打开一个事务
	public void save(Object object);

	//关闭一个事务
	/* (non-Javadoc)
	 * @see com.service.base.DAO#update(java.lang.Object)
	 */

	public void update(Object object);

	public <T> void delete(Class<T> entityClass, Object entityid);

	public <T> void delete(Class<T> entityClass, Object[] entityids);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#find(java.lang.Class, java.lang.Object)
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityid);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class)
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int)
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[])
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.util.LinkedHashMap)
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	/* (non-Javadoc)
	 * @see com.service.base.DAO#getScrollData(java.lang.Class, int, int, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@SuppressWarnings("unchecked")
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby);

}