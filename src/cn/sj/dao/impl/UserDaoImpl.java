package cn.sj.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.sj.dao.UserDao;
import cn.sj.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getByUserCode(String user_code) {
		//Criteria
		
		//创建离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		//添加参数
		dc.add(Restrictions.eq("user_code",user_code ));
		//查询
		List<User> list = (List<User>) super.getHt().findByCriteria(dc);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}}
