package cn.sj.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//不拦截login方法
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		//1 获得登陆标识
		Object object = ActionContext.getContext().getSession().get("user");
		//2 判断标识是否存在
		if(object!=null){
			//存在=>放行
			return invocation.invoke();
		}else{
			//不存在=>重定向到登陆页面
			return "toLogin";
		}
		
	}

}
