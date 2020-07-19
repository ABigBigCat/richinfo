package com.wugui.datax.admin.service;

import com.wugui.datax.admin.service.model.SourceServerModel;

import java.util.List;

/**
 * Created by jiahui on 2020-07-10 15:52
 */
public interface SourceServerService {

    SourceServerModel addSourceServer(SourceServerModel sourceServerModel);

    List<SourceServerModel> getSourceList();

    Boolean deleteSource(Integer id);

    Boolean updateSource(SourceServerModel sourceServerModel);


}
