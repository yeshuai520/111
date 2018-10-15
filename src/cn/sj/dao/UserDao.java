package cn.sj.dao;

import cn.sj.domain.User;

public interface UserDao extends BaseDao<User> {
	//根据登陆名获得User对象
	User getByUserCode(String user_code);
}
