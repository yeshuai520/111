package cn.sj.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.dsna.util.images.ValidateCode;
import cn.sj.domain.User;
import cn.sj.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;
	
	
	
	
	public String image() throws Exception {
		//1 生成验证码
		ValidateCode vc = new ValidateCode(80,30,4,3);
		//2 将正确答案放入session中
		ActionContext.getContext().getSession().put("code", vc.getCode());
		//3 将验证码图片流输出
		vc.write(ServletActionContext.getResponse().getOutputStream());
		return null;//return "none";
	}





	private String code;
	public String login() throws Exception {
		//1 比对验证码是否一致
		String sessionCode = (String) ActionContext.getContext().getSession().get("code");
			//不一致,转发到登陆页面提示
		if( sessionCode==null ||  !sessionCode.equalsIgnoreCase(code) ){
			throw new RuntimeException("验证码错误!");
		}
		//2 调用Service执行登陆逻辑
		User existU = userService.getLoginUser(user);
		//3 将返回的User放入session域
		ActionContext.getContext().getSession().put("user", existU);
		//4 重定向到项目首页
		return "toHome";
	}






	public void setUserService(UserService userService) {
		this.userService = userService;
	}






	@Override
	public User getModel() {
		return user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
