/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpc.action.form.RPC00105Form;
import cn.com.prescription.leshan.rpc.biz.RPC00105DeleteLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00105InitLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00105UpdateLogic;

/**
 * 复诊信息编辑action。
 * 
 * @author fsb
 */
/*
 * 新規作成 DATE: 2012.11.07 NAME: fsb
 */
public class RPC00105Action extends AbstractAction {

	/**
	 * 串行版本UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 复诊信息编辑form
	 */
	private RPC00105Form rpc00105Form = new RPC00105Form();

	/**
	 * Action 的构造。
	 */
	public RPC00105Action() {
		super();
	}

	/**
	 * 复诊信息编辑 初期表示的处理。
	 * 
	 * @return 返却結果
	 * @throws Exception
	 *             処理実行時に系统例外发生的情况
	 */
	public String doInit() throws Exception {
		// this.setRpa00105Form(new RPC00105Form());
		// 用户情報設定eventを実行する
		doDispatchEvent(rpc00105Form, RPC00105InitLogic.class);
		return SUCCESS;
	}

	/**
	 * 复诊信息编辑 登録的处理。
	 * 
	 * @return 返却結果
	 * @throws Exception
	 *             処理実行時に系统例外发生的情况
	 */
	public String doUpdate() throws Exception {
		// 用户情報設定eventを実行する
		doDispatchEvent(rpc00105Form, RPC00105UpdateLogic.class);
		return SUCCESS;
	}

	/**
	 * 复诊信息编辑 删除的处理。
	 * 
	 * @return 返却結果
	 * @throws Exception
	 *             処理実行時に系统例外发生的情况
	 */
	public String doDelete() throws Exception {
		// 用户情報設定eventを実行する
		doDispatchEvent(rpc00105Form, RPC00105DeleteLogic.class);
		return SUCCESS;
	}

	/**
	 * 复诊信息编辑form的取得
	 * 
	 * @return 初诊信息form
	 */
	public RPC00105Form getRpc00105Form() {
		return rpc00105Form;
	}

	/**
	 * 复诊信息编辑form的设定
	 * 
	 * @param _rpc00105Form
	 *            初诊信息form
	 */
	public void setRpc00105Form(RPC00105Form _rpc00105Form) {
		this.rpc00105Form = _rpc00105Form;
	}

}
