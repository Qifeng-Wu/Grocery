package com.jeeplus.modules.vehicle.api;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.vehicle.entity.VFans;
import com.jeeplus.modules.vehicle.service.VFansService;
import com.jeeplus.modules.vehicle.utils.CommonUtils;
import com.jeeplus.modules.vehicle.utils.ConfigurationFileHelper;

import net.sf.json.JSONObject;

/**
 * 获取用户信息API
 * 
 * @author stephen
 * @version 2019-11-25
 */
@Controller
@RequestMapping(value = "v-api/fans")
public class VFansAPI extends BaseController {
	 private static String APPID = ConfigurationFileHelper.getAppid();
	 private static String APPSECRET = ConfigurationFileHelper.getAppsecret();
	 @Autowired
	 private VFansService vFansService;
	 
	/**
	 * 获取用户信息(openid,session_key)
	 * @return
	 * @throws WexinReqException 
	 */
	@RequestMapping(value = "/authorize")
	@ResponseBody
	public AjaxJson getFindList(VFans vFans,HttpServletRequest request) throws WexinReqException {			
		String code = request.getParameter("code").trim();
		String nickName = request.getParameter("nickName");
		String gender = request.getParameter("gender");
		String avatarUrl = request.getParameter("avatarUrl");
		AjaxJson ajax = new AjaxJson();	
		if (code != null && !code.isEmpty()) {// 小程序ID不为0
			if(APPID !=null && !APPID.isEmpty() && APPSECRET !=null && !APPSECRET.isEmpty()){								
				String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID
						+"&secret="+APPSECRET+"&js_code="+code+"&grant_type=authorization_code";
				
				JSONObject json_object = CommonUtils.httpsRequest(url, "GET", null);
				//得到微信返回的两个值(openid,session_key)
				String openId = json_object.getString("openid");				
				//String session_key = json_object.getString("session_key");//暂时用不到（解密时要用）			
				
				if(openId !=null && !openId.isEmpty() && !openId.equals("null")){
					//存储用户信息
					vFans.setOpenId(openId);
					vFans.setNickname(nickName);
					vFans.setSex(gender);
					vFans.setAvatar(avatarUrl);
					VFans fan = vFansService.get(openId);
					if(fan!=null && fan.getOpenId()!=null) {
						vFans.setUpdatetime(new Date());
						vFansService.customSave(vFans);//跟新
					}else {
						vFans.setAddtime(new Date());
						vFans.setUpdatetime(new Date());
						vFansService.save(vFans);//新增
					}
					
					if(openId ==null || openId.isEmpty()){
						ajax.setSuccess(false);
						ajax.setMsg("SAVE INFO ERROR !");
						ajax.setErrorCode("0");
						return ajax;
					}
				}else {
					ajax.setSuccess(false);
					ajax.setMsg("GET OPENID ERROR !");
					ajax.setErrorCode("0");
					return ajax;
				}
				LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
				body.put("openId", openId);		
				ajax.setBody(body);
				return ajax;
				
			}else{
				ajax.setSuccess(false);
				ajax.setMsg("GET MINA_APPSECRET ERROR !");
				ajax.setErrorCode("0");
				return ajax;
			}
		}
		ajax.setSuccess(false);
		ajax.setMsg("CODE IS NONE !");
		ajax.setErrorCode("0");
		return ajax;
	}		

	/**
	 * 获取粉丝详情信息
	 */
	@RequestMapping(value = "info")
	@ResponseBody
	public synchronized AjaxJson info(VFans vFans, RedirectAttributes redirectAttributes) {
		AjaxJson ajax = new AjaxJson();	
		vFans = vFansService.get(vFans);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("fans", vFans);			
		ajax.setBody(body);
		return ajax;
	}
}