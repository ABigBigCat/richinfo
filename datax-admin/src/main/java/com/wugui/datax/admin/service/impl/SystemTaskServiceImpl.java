package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.datamapper.SourceServerDOMapper;
import com.wugui.datax.admin.datamapper.SystemTaskDOMapper;
import com.wugui.datax.admin.dataobject.SourceServerDO;
import com.wugui.datax.admin.dataobject.SystemTaskDO;
import com.wugui.datax.admin.service.SystemTaskService;
import com.wugui.datax.admin.service.model.SystemTaskModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiahui on 2020-07-10 16:27
 */
@Service
public class SystemTaskServiceImpl implements SystemTaskService {

    @Autowired
    private SystemTaskDOMapper systemTaskDOMapper;

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @Autowired
    private SourceServerDOMapper sourceServerDOMapper;

    @Override
    public SystemTaskModel addSystemTask(SystemTaskModel systemTaskModel) {
        SystemTaskDO systemTaskDO = this.convertSystemTaskDOFromSystemTaskModel(systemTaskModel);

        systemTaskDOMapper.insertSelective(systemTaskDO);

        return systemTaskModel;
    }

    @Override
    public Boolean deleteTask(Integer id) {
        if (systemTaskDOMapper.deleteByPrimaryKey(id) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<SystemTaskModel> listTask(Integer offset, Integer pagesize, String name) {
        List<SystemTaskDO> systemTaskDOS = systemTaskDOMapper.pageList(offset,pagesize,name);
        List<SystemTaskModel> systemTaskModels = systemTaskDOS.stream().map(systemTaskDO -> {
            SystemTaskModel systemTaskModel = this.convertSystemTaskModelFromSystemTaskDO(systemTaskDO);
            return systemTaskModel;
        }).collect(Collectors.toList());


        return systemTaskModels;
    }

    @Override
    public Boolean updateTask(SystemTaskModel systemTaskModel) {
        SystemTaskDO systemTaskDO = this.convertSystemTaskDOFromSystemTaskModel(systemTaskModel);
        systemTaskDO.setUpdateTime(new Date());

        if (systemTaskDOMapper.updateByPrimaryKeySelective(systemTaskDO) == 1){
            return true;
        }

        return false;
    }

    private SystemTaskDO convertSystemTaskDOFromSystemTaskModel(SystemTaskModel systemTaskModel){
        if (systemTaskModel == null){
            return null;
        }

        SystemTaskDO systemTaskDO = new SystemTaskDO();
        BeanUtils.copyProperties(systemTaskModel,systemTaskDO);
        systemTaskDO.setCreateTime(new Date());
        if(systemTaskModel.getStatus() == null || systemTaskModel.getStatus().equals("")) {
            systemTaskDO.setStatus(0);
        }
        systemTaskDO.setUpdateTime(new Date());
        return  systemTaskDO;

    }

    private SystemTaskModel convertSystemTaskModelFromSystemTaskDO(SystemTaskDO systemTaskDO){
        if (systemTaskDO == null){
            return null;
        }

        SystemTaskModel systemTaskModel = new SystemTaskModel();
        BeanUtils.copyProperties(systemTaskDO,systemTaskModel);

        SourceServerDO serverDO = sourceServerDOMapper.selectByPrimaryKey(systemTaskDO.getSourceServerId());

        systemTaskModel.setSourceServerName(serverDO.getName());
        systemTaskModel.setSourceServerSystemType(serverDO.getType());
        systemTaskModel.setCloudAccountName(serverDO.getName());
        return systemTaskModel;
    }
}
