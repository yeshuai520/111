package cn.sj.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.sj.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private HibernateTemplate ht;
	
	private Class clazz;
	public BaseDaoImpl() {
		//获得运行期泛型类型
		
		//运行期BaseDaoImpl的可获得泛型参数的class
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
	
		clazz = (Class) pt.getActualTypeArguments()[0];
	}
	
	
	@Override
	public void save(T t) {
		ht.saveOrUpdate(t);
	}


	@Override
	public void delete(Serializable id) {
		//先根据id查询对象
		T t = this.getById(id);
		//调用方法删除
		ht.delete(t);
	}

	@Override
	public void update(T t) {
		ht.update(t);
	}

	@Override
	public T getById(Serializable id) {
		
		return (T) ht.load(clazz, id);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		//为dc添加count查询
		dc.setProjection(Projections.rowCount());//设置使用count 聚合函数
		
		//调用模板进行查询,并获得结果
		List<Long> list = (List<Long>) ht.findByCriteria(dc);
		
		//清空聚合函数
		dc.setProjection(null);
		
		return list.get(0).intValue();
	}

	@Override
	public List<T> findPageList(DetachedCriteria dc, Integer startIndex, Integer pageSize) {
		return (List<T>) ht.findByCriteria(dc,startIndex,pageSize);
	}
	
	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}


	public HibernateTemplate getHt() {
		return ht;
	}
	

}
