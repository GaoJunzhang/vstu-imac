package com.study.service;

import com.github.pagehelper.PageInfo;
import com.study.model.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    PageInfo<LoginLog> selectByPage(LoginLog loginLog, int start, int length, String startDate, String endDate);
}
