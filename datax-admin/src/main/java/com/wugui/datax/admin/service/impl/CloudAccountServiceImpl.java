package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.datamapper.SystemTaskDOMapper;
import com.wugui.datax.admin.dataobject.CloudAccountDO;
import com.wugui.datax.admin.service.CloudAccountService;
import com.wugui.datax.admin.service.model.CloudAccountModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiahui on 2020-07-09 15:39
 */
@Service
public class CloudAccountServiceImpl implements CloudAccountService {

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @Autowired
    private SystemTaskDOMapper systemTaskDOMapper;

    @Override
    @Transactional
    public Boolean deleteAccount(Integer id) {
        if (cloudAccountDOMapper.deleteByPrimaryKey(id) == 1 && systemTaskDOMapper.deleteByCloudId(id) ==1){
            return true;
        }

        return false;
    }

    @Override
    public CloudAccountModel addCloudAccount(CloudAccountModel cloudAccountModel) {

        CloudAccountDO cloudAccountDO = this.convertCloudAccountDOFromCloudAccountModel(cloudAccountModel);
        cloudAccountDOMapper.insertSelective(cloudAccountDO);

        return cloudAccountModel;
    }

    @Override
    public List<CloudAccountModel> getCloudList() {
        List<CloudAccountDO> cloudAccountDOList = cloudAccountDOMapper.listCloud();
        List<CloudAccountModel> cloudAccountModels = cloudAccountDOList.stream().map(cloudAccountDO -> {
            CloudAccountModel cloudAccountModel = this.convertCloudAccountModelFromCloudAccountDO(cloudAccountDO);
            return cloudAccountModel;
        }).collect(Collectors.toList());

        return cloudAccountModels;
    }

    @Override
    public Boolean updateAccount(CloudAccountModel cloudAccountModel) {
        CloudAccountDO cloudAccountDO = this.convertCloudAccountDOFromCloudAccountModel(cloudAccountModel);
        cloudAccountDO.setUpdateDate(new Date());

        int resultInt = cloudAccountDOMapper.updateByPrimaryKeySelective(cloudAccountDO);

        if (resultInt == 1){
            return true;
        }

        return false;
    }

    private CloudAccountDO convertCloudAccountDOFromCloudAccountModel(CloudAccountModel cloudAccountModel){

        if (cloudAccountModel == null){
            return null;
        }

        CloudAccountDO cloudAccountDO = new CloudAccountDO();
        BeanUtils.copyProperties(cloudAccountModel,cloudAccountDO);
        cloudAccountDO.setCreateDate(new Date());
        cloudAccountDO.setStatus(0);
        return cloudAccountDO;

    }

    private CloudAccountModel convertCloudAccountModelFromCloudAccountDO(CloudAccountDO cloudAccountDO){
        if (cloudAccountDO == null){
            return null;
        }

        CloudAccountModel cloudAccountModel = new CloudAccountModel();
        BeanUtils.copyProperties(cloudAccountDO,cloudAccountModel);
        return cloudAccountModel;

    }



}
