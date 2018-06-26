package com.study.service;

import com.github.pagehelper.PageInfo;
import com.study.model.Download;

public interface DownloadService extends IService<Download> {
    PageInfo<Download> selectByPage(Download download, int start, int length, String startDate, String endDate);
}
