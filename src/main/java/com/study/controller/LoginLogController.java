package com.study.controller;

import com.github.pagehelper.PageInfo;
import com.study.model.LoginLog;
import com.study.service.LoginLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loginLogs")
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @RequestMapping
    public Map<String, Object> getAll(LoginLog loginLog, String draw,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length,
                                      @RequestParam(required = false,defaultValue = "1990-01-01") String startDate,
                                      @RequestParam(required = false,defaultValue = "2099-01-01") String endDate) {
        Map<String, Object> map = new HashMap<>();
        PageInfo<LoginLog> pageInfo = loginLogService.selectByPage(loginLog, start, length,startDate,endDate);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
}
