package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.model.LoginLog;
import com.study.service.LoginLogService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {
    @Override
    public PageInfo<LoginLog> selectByPage(LoginLog loginLog, int start, int length, String startDate, String endDate) {
        int page = start / length + 1;
        Example example = new Example(LoginLog.class);
        example.setOrderByClause("login_time DESC");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(loginLog.getUsername())) {
            criteria.andLike("username", "%" + loginLog.getUsername() + "%");
        }
        if (StringUtil.isNotEmpty(loginLog.getFileName())) {
            criteria.andLike("fileName", "%" + loginLog.getFileName() + "%");
        }
        if (StringUtil.isNotEmpty(loginLog.getIp())) {
            criteria.andLike("ip", "%" + loginLog.getIp() + "%");
        }
        if (startDate!=null){
            criteria.andGreaterThan("loginTime",startDate);
        }
        if (endDate!=null){
            criteria.andLessThan("loginTime",endDate);
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<LoginLog> loginLogList = selectByExample(example);
        return new PageInfo<>(loginLogList);
    }
}
