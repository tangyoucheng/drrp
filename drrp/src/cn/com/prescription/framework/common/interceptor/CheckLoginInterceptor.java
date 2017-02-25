package cn.com.prescription.framework.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.prescription.framework.common.session.UserSessionInfo;
import cn.com.prescription.framework.common.session.UserSessionUtils;
import cn.com.prescription.framework.event.StandardEvent;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.ServiceUtils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * セッション情報より兵庫住宅系统に登录しているかどうかを判断します。
 * 
 * @author NTTDATA-CHUGOKU
 */
public class CheckLoginInterceptor extends AbstractInterceptor {
    
	private String sessionInitLogic = "";
	
    public void setSessionInitLogic(String sessionInitLogic) {
		this.sessionInitLogic = sessionInitLogic;
	}

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * : セッションから登录情報を取得し、チェックする。
     */
    public String intercept(ActionInvocation invocation) throws Exception {
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
        
        if(!authorize(request)) {
            // event生成
            StandardEvent event_ = ServiceUtils.createEvent("cn.com.prescription.framework.event.common", "standard");
            event_.setObject(Class.forName(sessionInitLogic));
            ServiceUtils.dispatchEvent(event_);
        }
        
        String result = invocation.invoke();
        return result;
    }

    /**
     * 認証チェック.
     * 
     * @param _request リクエスト情報
     * @return true:認証成功
     */
    private boolean authorize(HttpServletRequest _request) {

        // セッションを取得
        HttpSession session = _request.getSession(false);

        // セッションが存在する場合
        if (session != null) {
        	synchronized(session) {
        		// 用户情報を取得
        	    UserSessionInfo userInfo = (UserSessionInfo) session.getAttribute(UserSessionUtils.SESSION_KEY);
        		
        		// 用户情報が存在する場合
        		if (userInfo != null && !CheckUtils.isEmpty(userInfo.getUserId())) {
        			// 認証OK
        			return true;
        		}
        	}
        }

        // 認証されていない
        return false;

    }
    
}
