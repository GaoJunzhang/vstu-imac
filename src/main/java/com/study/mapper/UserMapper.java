package com.study.mapper;

import com.study.model.User;
import com.study.model.UserFile;
import com.study.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    public int addUserFile(UserFile userFile);

    public void batchDeleteUser(@Param("list") List<User> list);

    public int delUserByparent(@Param("id") int id, @Param("enable") int enable);

    public int updateEnable(@Param("id") int id, @Param("enable") int enable);
}