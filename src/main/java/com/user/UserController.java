package com.user;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.common.model.User;

/**
 * 
 * Controller
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	
	static UserService service = new UserService();
	
	public void index() {
		setAttr("userPage", service.paginate(getParaToInt(0, 1), 10));
		render("user.html");
	}

	public void list() {
		int page = Integer.valueOf(getPara("page","1"));
		int limit = Integer.valueOf(getPara("limit","10"));
		renderJson(service.layUiPaginate(getParaToInt(page, 1), limit));
	}

	public void add() {
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(UserValidator.class)
	public void save() {
		getModel(User.class).save();
		redirect("/user");
	}
	
	public void edit() {
		setAttr("user", service.findById(getParaToInt()));
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(UserValidator.class)
	public void update() {
		getModel(User.class).update();
		redirect("/user");
	}
	
	public void delete() {
		service.deleteById(getParaToInt());
		redirect("/user");
	}
}

