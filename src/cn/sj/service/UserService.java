package cn.sj.service;

import cn.sj.domain.User;

public interface UserService {
	//保存用户
	void save(User u);
	//登陆并返回登陆用户
	User getLoginUser(User user);

}
