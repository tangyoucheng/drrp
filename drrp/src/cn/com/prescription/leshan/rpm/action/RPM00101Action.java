/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.intra_mart.framework.system.exception.SystemException;
import org.seasar.extension.datasource.DataSourceFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.LogUtils;
import cn.com.prescription.framework.util.StringUtils;
import cn.com.prescription.leshan.rpm.action.form.RPM00101Form;
import cn.com.prescription.leshan.rpm.biz.RPM00101InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00101LoginLogic;

/**
 * 登录action。
 * 
 * @author tyc
 */
/*
 * 新規作成 DATE: 2016.03.20 NAME: tyc
 */
public class RPM00101Action extends AbstractAction {

	/**
	 * 串行版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录的form
	 */
	private RPM00101Form rpm00101Form = new RPM00101Form();

	/**
	 * 构造RPM00101Action。
	 */
	public RPM00101Action() {
		super();
	}

	/**
	 * 初期化处理。
	 * 
	 * @return 返回结果
	 * @throws Exception
	 *             处理时发生的例外
	 */
	public String doInit() throws Exception {

		doDispatchEvent(rpm00101Form, RPM00101InitLogic.class);
		saveFormObject(rpm00101Form);
		return INPUT;
	}

	/**
	 * 登录处理。
	 * 
	 * @return 返回结果
	 * @throws Exception
	 *             处理时发生的例外
	 */
	public String doLogin() throws Exception {

		if (CheckUtils.isEqual(ERROR, checkSystemStatus())) {
			return ERROR;
		}

		// コンテナからファクトリを取得
		DataSourceFactory dsFactory = (DataSourceFactory) SingletonS2ContainerFactory.getContainer()
				.getComponent(DataSourceFactory.class);
		// データソース名を設定
		dsFactory.setSelectableDataSourceName(rpm00101Form.getStoreCode());

		// 登录处理
		doDispatchEvent(rpm00101Form, RPM00101LoginLogic.class);

		// 业务处理发生错误的场合
		if (!CheckUtils.isEmpty(rpm00101Form.getErrMsg())) {
			this.addActionMessage(rpm00101Form.getErrMsg());
			return ERROR;
		}
		UserSessionInfo userInfo = rpm00101Form.getUserInfo();
		userInfo.setLoginUrl(this.request.getRequestURI());
		userInfo.setUserIp(this.request.getRemoteAddr());
		this.setUserInfo(this.request.getHeader("User-Agent"), userInfo);
		try {
			UserSessionUtils.setUserSessionInfo(userInfo);
		} catch (IllegalStateException e) {
			return INPUT;
		}

		// 编辑信息
		StringBuffer strMessage_ = new StringBuffer();
		// 画面ID
		// strMessage_.append(LeshanConstantsIF.GAMEN_ID_RPM00101);
		// 画面名
		String gamenName = StringUtils.defaultString(MessageUtils.getSimpleMessage("gamen_name_RPM00101"));
		strMessage_.append(",");
		strMessage_.append(gamenName);
		// 方法名
		String methodName = MessageUtils.getSimpleMessage("accessLog.event.doLogin");
		if (!CheckUtils.isEmpty(methodName)) {
			strMessage_.append(",");
			strMessage_.append(methodName);
			// 标记
			for (int i = 1; i <= 20; i++) {
				strMessage_.append("*");
			}
			// 换行
			strMessage_.append(System.getProperty("line.separator"));
			// 当前操作的页面信息
			try {
				strMessage_.append(JSONUtil.serialize(rpm00101Form, false));
			} catch (JSONException e) {
				throw new SystemException(e);
			}
			// 换行
			strMessage_.append(System.getProperty("line.separator"));
			// 输出操作日志信息
			LogUtils.debugOperate(strMessage_.toString(), false);
		}

		return rpm00101Form.getGamenId();
	}

	/**
	 * 设定客户端的浏览器和操作系统信息
	 * 
	 * @param userAgentInfo
	 *            客户端信息
	 * @param userInfo
	 *            用户会话信息
	 */
	public void setUserInfo(String userAgentInfo, UserSessionInfo userInfo) {
		String info = userAgentInfo.toUpperCase();
		String[] strInfo = info.substring(info.indexOf("(") + 1, info.indexOf(")") - 1).split(";");
		userInfo.setUserOS(strInfo[0].trim());
		if ((info.indexOf("MSIE")) > -1) {
			userInfo.setUserBrowser(strInfo[1].trim());
		} else {
			String[] str = info.split(" ");
			if (info.indexOf("NAVIGATOR") < 0 && info.indexOf("FIREFOX") > -1) {
				userInfo.setUserBrowser(str[str.length - 4].trim());
			} else if ((info.indexOf("OPERA")) > -1) {
				userInfo.setUserBrowser(str[0].trim());
			} else if (info.indexOf("CHROME") < 0 && info.indexOf("SAFARI") > -1) {
				userInfo.setUserBrowser(str[str.length - 1].trim());
			} else if (info.indexOf("CHROME") > -1) {
				userInfo.setUserBrowser(str[str.length - 2].trim());
			} else if (info.indexOf("NAVIGATOR") > -1) {
				userInfo.setUserBrowser(str[str.length - 1].trim());
			} else {
				userInfo.setUserBrowser("Unknown Browser");
			}
		}
	}

	/**
	 * 检查系统状态。
	 * 
	 * @return 返回结果
	 * @throws Exception
	 *             处理时发生的例外
	 */
	private String checkSystemStatus() throws Exception {

		if (CheckUtils.isEqual(rpm00101Form.getFlg(), "t")) {
			return SUCCESS;
		}
		String userIp = "";
		UserSessionInfo _userInfo = null;
		// 取得会话
		HttpSession session = this.request.getSession();
		// 回话存在的场合
		if (session != null) {
			_userInfo = (UserSessionInfo) session.getAttribute(UserSessionUtils.SESSION_KEY);
		}
		if (_userInfo != null) {
			userIp = _userInfo.getUserIp();
		}
		// if (CheckUtils.isEqual(userIp, this.request.getRemoteAddr())) {
		// this.addActionMessage(MessageUtils.getMessage("E20099"));
		// return ERROR;
		//
		// }
		return SUCCESS;
	}

	public RPM00101Form getRpm00101Form() {
		return rpm00101Form;
	}

	public void setRpm00101Form(RPM00101Form rpm00101Form) {
		this.rpm00101Form = rpm00101Form;
	}
}
