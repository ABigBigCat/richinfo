package com.wugui.datax.admin.service;

import com.wugui.datax.admin.service.model.CloudAccountModel;

import java.util.List;

/**
 * Created by jiahui on 2020-07-09 15:33
 */
public interface CloudAccountService {

    Boolean deleteAccount(Integer id);

    CloudAccountModel addCloudAccount(CloudAccountModel cloudAccountModel);

    List<CloudAccountModel> getCloudList();

    Boolean updateAccount(CloudAccountModel cloudAccountModel);


}
