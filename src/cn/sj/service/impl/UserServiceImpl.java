package cn.sj.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sj.dao.UserDao;
import cn.sj.domain.User;
import cn.sj.service.UserService;

//对类中所有业务方法指定事务管理属性
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService {
	private UserDao ud;
	
	@Override
	//对某一个方法指定事务管理属性,可以覆盖类上的配置
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(User u) {
		ud.save(u);
	}
	@Override
	public User getLoginUser(User user) {
		//1 调用Dao根据登陆名查询User
		User existU = ud.getByUserCode(user.getUser_code());
			//user为空=>抛出异常
		if(existU==null){
			throw new RuntimeException("用户不存在!");
		}
		//2 校验密码是否一致
		if(!existU.getUser_password().equals(user.getUser_password())){
			//不一致=>抛出异常
			throw new RuntimeException("密码不正确!");
		}
		//3 返回User
		return existU;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}


}
