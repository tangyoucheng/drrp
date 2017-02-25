/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionContext;

import cn.com.prescription.framework.biz.StandardBiz;
import cn.com.prescription.framework.biz.StandardLogic;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.event.StandardEventResult;
import cn.com.prescription.framework.exception.ApplicationException;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.ServiceUtils;
import cn.com.prescription.leshan.common.action.form.MenuBootForm;
import cn.com.prescription.leshan.common.data.RpmMenuDao;
import cn.com.prescription.leshan.common.data.condition.RpmMenuCondition;
import cn.com.prescription.leshan.common.data.model.RpmMenuModel;

/**
 * 菜单起動時処理.
 * 
 * @author bpchikazawa
 */
/*
 * 新規作成 DATE: 2010.11.01 NAME: bpchikazawa
 */
public class MenuBootLogic extends StandardBiz implements StandardLogic {

	/** 菜单DAO */
	private RpmMenuDao menuDao = null;

	/**
	 * event処理を行う。
	 * 
	 * @param _event
	 *            event
	 * @throws ApplicationException
	 *             event処理里应用程序例外发生的情况
	 * @throws SystemException
	 *             event処理里系统例外发生的情况
	 * @return _eventResult event処理結果
	 */
	public StandardEventResult service(StandardEvent _event) throws ApplicationException, SystemException {

		// form取得
		MenuBootForm form_ = (MenuBootForm) _event.getForm();
		// 用户セッション情報を取得
		UserSessionInfo userInfo_ = UserSessionUtils.getUserSessionInfo();

		// セッションクリア
		clearSession();

		// 菜单情報設定
		userInfo_ = setMenuInfo(userInfo_, form_.getMenuId());

		// URL生成
		form_.setUrl(ServletActionContext.getRequest().getContextPath().concat("/").concat(form_.getUrl()));

		// 用户情報再設定
		UserSessionUtils.setUserSessionInfo(userInfo_);

		// 処理結果返却
		return this.getEventResult(form_);

	}

	/**
	 * セッションクリア
	 */
	private void clearSession() {

		// クリア値
		String remove_ = null;

		// セッションクリア
		for (String key_ : ActionContext.getContext().getSession().keySet()) {

			// カラーパターンだけは除外・・・・・
			if (key_.equals("colorPattern") || key_.contains(".UserSessionInfo")) {
				continue;
			}

			try {
				// 文字列変換
				remove_ = JSONUtil.serialize(ServiceUtils.getSession(key_), false);
			} catch (JSONException e) {
				// 予期せぬ错误
				remove_ = e.getMessage();
			}

			// セッションクリアログ
			LogUtils.debug("clear session attribute (key=".concat(key_).concat("): ").concat(remove_));
			// セッションクリア
			ServiceUtils.removeSession(key_);

		}

	}

	/**
	 * 菜单情報設定
	 * 
	 * @param _userInfo
	 *            用户情報
	 * @param _menuId
	 *            菜单ID
	 * @return 用户情報
	 */
	private UserSessionInfo setMenuInfo(UserSessionInfo _userInfo, String _menuId) {

		// 菜单情報をクリア
		_userInfo.setMenuInfo(null);
		_userInfo.getMenuTree().clear();

		// パラメータ：菜单ID指定なし
		if (CheckUtils.isEmpty(_menuId)) {
			return _userInfo;
		}

		// クリックされた菜单
		_userInfo.setMenuInfo(new UserSessionInfo.MenuInfo(_menuId));

		// 菜单オブジェクト生成
		List<UserSessionInfo.MenuInfo> menuTree_ = new ArrayList<UserSessionInfo.MenuInfo>();
		// モデル生成
		RpmMenuModel model_ = new RpmMenuModel();

		// 最初の条件設定
		model_.setParentMenuId(_userInfo.getMenuInfo().getMenuId());
		// 菜单読込（子から親に向かって検索）
		do {
			// 条件保存
			String parent_ = model_.getParentMenuId();
			// モデル生成
			model_ = new RpmMenuModel();
			// Condition生成
			RpmMenuCondition _condition = new RpmMenuCondition();
			// キー設定
			_condition.setMenuId(parent_);
			// 菜单情報読込
			model_ = menuDao.select(_condition);
			// 菜单情報を保存
			menuTree_.add(new UserSessionInfo.MenuInfo(model_.getMenuId()));
		}
		// 最上位レベルで終了
		while (!CheckUtils.isEmpty(model_.getParentMenuId()));

		// 菜单ツリーを並べ替え（親 > 子）
		Collections.reverse(menuTree_);

		// 菜单情報を保存
		_userInfo.setMenuTree(menuTree_);

		// 用户情報を返却
		return _userInfo;

	}

	/**
	 * 菜单DAOを設定する。
	 * 
	 * @param menuDao
	 *            菜单DAO
	 */
	public void setMenuDao(RpmMenuDao menuDao) {
		this.menuDao = menuDao;
	}

}
