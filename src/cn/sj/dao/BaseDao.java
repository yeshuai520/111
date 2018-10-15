package cn.sj.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


public interface BaseDao<T> {
	
	//增
		void save(T t);
		//woailuo
	//删
		void delete(Serializable id);
	//改
		void update(T t);
	//查
		T	getById(Serializable id);
	//根据页面条件查询总记录数
	Integer getTotalCount(DetachedCriteria dc);
	//根据条件查询分页列表List数据
	List<T> findPageList(DetachedCriteria dc, Integer startIndex, Integer pageSize);
	
}
