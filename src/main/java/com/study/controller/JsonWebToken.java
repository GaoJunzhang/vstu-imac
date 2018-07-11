package com.study.controller;

import com.study.bean.UserBean;
import com.study.config.Audience;
import com.study.model.TokenBean;
import com.study.model.User;
import com.study.result.JsonResult;
import com.study.result.ResultCode;
import com.study.service.FileInfoService;
import com.study.service.UserService;
import com.study.util.JwtHelper;
import com.study.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2018/3/20.
 */
@RestController
public class JsonWebToken {
    @Autowired
    private UserService userService;

    @Autowired
    private Audience audienceEntity;



    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping("oauth/token")
    public Object getAccessToken(@RequestBody UserBean user, HttpServletRequest request) {
        JsonResult jsonResult;
        if (StringUtils.isEmpty(user.getUsername())) {
            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "账号为空", null);
            return jsonResult;
        }
        User userBean = userService.selectByUsername(user.getUsername());
        if (userBean == null) {
            jsonResult = new JsonResult(ResultCode.NOT_LOGIN, "账号错误", null);
            return jsonResult;
        } else {
            User user1 = new User();
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user1);
            if (user1.getPassword().compareTo(userBean.getPassword()) != 0) {
                jsonResult = new JsonResult(ResultCode.NOT_LOGIN, "密码错误", null);
                return jsonResult;
            }
        }
//        FileInfo fileInfo = fileInfoService.selectByFilename(user.getFileName());
//        if (fileInfo==null){
//            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "资源为空", null);
//            return jsonResult;
//        }
//        String rip = getIpAddr(request);
//        LoginLog loginLog = new LoginLog();
//        loginLog.setIp(rip);
//        loginLog.setLoginTime(new Timestamp(System.currentTimeMillis()));
//        loginLog.setUid(userBean.getId());
//        loginLog.setUsername(userBean.getUsername());
//        loginLog.setFileName(user.getFileName());
//        loginLogService.save(loginLog);
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(user.getUsername(), String.valueOf(userBean.getId()),
                "", audienceEntity.getClientId(), audienceEntity.getName(),
                audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());
        //返回accessToken
        TokenBean tokenBean = new TokenBean();
        tokenBean.setAccess_token(accessToken);
        tokenBean.setExpires_in(audienceEntity.getExpiresSecond());
        tokenBean.setToken_type("bearer");
        jsonResult = new JsonResult(ResultCode.SUCCESS, "登录成功", tokenBean);
        return jsonResult;
    }


}
