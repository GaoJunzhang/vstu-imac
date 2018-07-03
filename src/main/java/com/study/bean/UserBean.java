package com.study.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserBean {
    private Integer id;

    /**
     * 账号
     */
    private String username;

    private String password;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 设备号
     */
    private String equipmentno;

    /**
     * 级别（0-学生，1-教师,2-普通用户）
     */
    private String level;

    /**
     * 学校名称
     */
    private String organization;

    /**
     * 真实姓名
     */
    private String realname;

    private String mobile;

    private String mac;

    private String category;
    private String remark;

    private Integer parentId;

    private String fileName;
}
