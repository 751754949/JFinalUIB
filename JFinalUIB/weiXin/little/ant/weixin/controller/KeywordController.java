package little.ant.weixin.controller;

import little.ant.pingtai.controller.BaseController;
import little.ant.weixin.model.Keyword;
import little.ant.weixin.service.KeywordService;
import little.ant.weixin.validator.KeywordValidator;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;

/**
 * 自动回复管理
 * @author 董华健
 */
public class KeywordController extends BaseController {

	private static Logger log = Logger.getLogger(KeywordController.class);
	
	private KeywordService keywordService = new KeywordService();
	
	public void index(){
		log.debug("微信自动回复管理：分页");
		keywordService.list(splitPage);
		render("/weiXin/keyword/list.html");
	}
	
	@Before(KeywordValidator.class)
	public void save() {
		ids = keywordService.save(getModel(Keyword.class));
		redirect("/jf/wx/keyword");
	}
	
	public void edit() {
		setAttr("group", Keyword.dao.findById(getPara()));
		render("/weiXin/keyword/update.html");
	}
	
	@Before(KeywordValidator.class)
	public void update() {
		keywordService.update(getModel(Keyword.class));
		redirect("/jf/wx/keyword");
	}
	
	public void delete() {
		keywordService.delete(getPara());
		redirect("/jf/wx/keyword");
	}
}
