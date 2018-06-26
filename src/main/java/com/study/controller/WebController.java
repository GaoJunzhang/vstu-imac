package com.study.controller;

import com.study.model.Download;
import com.study.model.FileInfo;
import com.study.model.User;
import com.study.result.JsonResult;
import com.study.result.ResultCode;
import com.study.service.DownloadService;
import com.study.service.FileInfoService;
import com.study.service.UserService;
import com.study.util.VTools;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/3/28.
 */
@RestController
@RequestMapping(value = "/api/web/imac")
public class WebController {
    @Resource
    private FileInfoService fileInfoService;

    @Resource
    private UserService userService;

    @Resource
    private DownloadService downloadService;

    @RequestMapping(value = "/getUserFiles", method = RequestMethod.POST)
    public List<FileInfo> getUserFiles(@Param("username") String username, HttpServletRequest request) {
        List list = new ArrayList();
        String uName = request.getAttribute("username")+"";
        if (StringUtils.isEmpty(username)){
            username = uName;
        }
        list.add("用户名不能为空");
        if (VTools.StringIsNullOrSpace(username)) {
            return list;
        }
//        User user = userService.selectByUsername(username);
        List<FileInfo> fileInfoList = fileInfoService.loadFileResources(username);
       /* if (fileInfoList.size()>0){
            for (int i=0;i<fileInfoList.size();i++){
                if ("1".equals(user.getCategory())){
                    if (fileInfoList.get(i).getFileaddress().contains(".exe")){
                        fileInfoList.remove(i);
                    }
                }
            }
        }*/
        return fileInfoList;
    }

   /* @RequestMapping(value = "/loginVerifyAndUpdateequipmentNo", method = RequestMethod.POST)
    public String loginVerifyAndUpdateequipmentNo(@Param("equipmentno") String equipmentno, @Param("uname") String uname, @Param("pwd") String pwd) {

        if (VTools.StringIsNullOrSpace(uname)) {
            return "账号不能为空";
        }
        if (VTools.StringIsNullOrSpace(pwd)) {
            return "密码不能为空";
        }
        if (VTools.StringIsNullOrSpace(equipmentno)) {
            return "设备号不能为空";
        }
        User user = userService.selectByUsername(uname);
        if (user == null) {
            return "账号不存在";
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(pwd);
        String pwdMd5 = passwordHelper.getPassword(user1);
        if (!pwdMd5.equals(user.getPassword())) {
            return "密码错误";
        }
        try {
            user.setEquipmentno(equipmentno);
            userService.updateEquipmentNoByUsername(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "faile";
        }
    }
*/
    @RequestMapping("/updateMac")
    public Object updateMac(@RequestBody User user) {
        JsonResult jsonResult;
        if (StringUtils.isEmpty(user.getUsername())) {
            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "账号为空", null);
            return jsonResult;
        }
        if (StringUtils.isEmpty(user.getMac())) {
            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "mac地址为空", null);
            return jsonResult;
        }
        User user1 = userService.selectByUsername(user.getUsername());
        if (user1 == null) {
            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "账号不存在", null);
            return jsonResult;
        } else {
            if (user1.getMac() != null&&user1.getMac().length()>1) {
                if (!user.getMac().equals(user1.getMac())) {
                    jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "该账号已在其他设备激活", null);
                    return jsonResult;
                } else {
                    jsonResult = new JsonResult(ResultCode.SUCCESS, "激活成功", true);
                    return jsonResult;
                }
            }
        }
        user1.setMac(user.getMac());
        userService.updateEquipmentNoByUsername(user1);
        jsonResult = new JsonResult(ResultCode.SUCCESS, "激活成功", true);
        return jsonResult;
    }

    @RequestMapping(value = "/putDownloadCount",method = RequestMethod.POST)
    public Object putDownloadCount(@Param("fileName") String  fileName,HttpServletRequest request) {
        FileInfo fileInfo = fileInfoService.selectByFilename(fileName.trim());
        String uid = request.getAttribute("userid")+"";
        String uName = request.getAttribute("username")+"";
        JsonResult jsonResult;
        if (fileInfo==null){
            jsonResult = new JsonResult(ResultCode.PARAMS_ERROR, "该视频不存在", null);
            return jsonResult;
        }
        Download download = new Download();
        download.setCreateTime(new Timestamp(System.currentTimeMillis()));
        download.setFileId(fileInfo.getId());
        download.setUid(Integer.parseInt(uid));
        download.setFileName(fileName);
        download.setUsername(uName);
        downloadService.save(download);
        jsonResult = new JsonResult(ResultCode.SUCCESS,"成功",true);
        return jsonResult;
    }

}
