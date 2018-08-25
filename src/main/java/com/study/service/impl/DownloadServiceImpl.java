package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.mapper.FileInfoMapper;
import com.study.model.Download;
import com.study.model.FileInfo;
import com.study.service.DownloadService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

@Service("downloadService")
public class DownloadServiceImpl extends BaseService<Download> implements DownloadService {

    @Override
    public PageInfo<Download> selectByPage(Download download, int start, int length,String startDate,String endDate) {
        int page = start / length + 1;
        Example example = new Example(Download.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(download.getUsername())) {
            criteria.andLike("username", "%" + download.getUsername() + "%");
        }
        if (StringUtil.isNotEmpty(download.getFileName())) {
            criteria.andLike("fileName", "%" + download.getFileName() + "%");
        }
        if (startDate!=null){
            criteria.andGreaterThan("createTime",startDate);
        }
        if (endDate!=null){
            criteria.andLessThan("createTime",endDate);
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<Download> downloadList = selectByExample(example);
        return new PageInfo<>(downloadList);
    }
}
