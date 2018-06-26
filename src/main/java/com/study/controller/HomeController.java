package com.study.controller;

import com.study.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangqj on 2017/4/21.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Model model) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            subject.getSession().setTimeout(1800000);
            return "redirect:fileInfo";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    @RequestMapping(value = {"/usersPage", ""})
    public String usersPage() {
        return "user/users";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage() {
        return "role/roles";
    }

    @RequestMapping("/resourcesPage")
    public String resourcesPage() {
        return "resources/resources";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }

    @RequestMapping("/fileInfo")
    public String filesourcePage() {
        return "fileInfo/fileInfo";
    }

    @RequestMapping(value = "/addfileView")
    public String addfileView() {
        return "upload/uploadfile";
    }

    @RequestMapping(value = "/editfileView")
    public String editfileView() {
        return "fileInfo/fileInfoEdit";
    }

    @RequestMapping(value = "/viewUserFile")
    public String addUserFile() {
        return "user/fileInfo";
    }

    @RequestMapping(value = "/userfileinfo")
    public String userfileinfo() {
        return "user/userfileinfo";
    }

    @RequestMapping(value = "/fileSources")
    public String fileSources() {
        return "fileSources/fileSources";
    }

    @RequestMapping(value = "/studentPage")
    public String studentPage(Model model, @RequestParam(value = "mainId",required = true) int mainId,@RequestParam(value = "mainName") String mainName) {

        model.addAttribute("mainId",mainId);
        model.addAttribute("mainName",mainName+"的学生列表");
        return "student/student";
    }

    @RequestMapping(value = {"/workPage",""})
    public String workPage() {
        return "workPage/workPage";
    }

}
