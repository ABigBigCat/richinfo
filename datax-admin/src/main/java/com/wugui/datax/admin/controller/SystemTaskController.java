package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.datamapper.SourceServerDOMapper;
import com.wugui.datax.admin.datamapper.SystemTaskDOMapper;
import com.wugui.datax.admin.dataobject.CloudAccountDO;
import com.wugui.datax.admin.dataobject.SourceServerDO;
import com.wugui.datax.admin.dataobject.SystemTaskDO;
import com.wugui.datax.admin.service.SystemTaskService;
import com.wugui.datax.admin.service.model.SystemTaskModel;
import com.wugui.datax.admin.util.Go2TencentCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiahui on 2020-07-13 09:07
 */
@RestController
@RequestMapping("/api/task")
@Api(tags = "系统盘任务接口")
public class SystemTaskController {

    @Autowired
    private SystemTaskService systemTaskService;

    @Autowired
    private SystemTaskDOMapper systemTaskDOMapper;

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @Autowired
    private SourceServerDOMapper sourceServerDOMapper;

    @PostMapping("/add")
    @ApiOperation("添加系统盘任务")
    public ReturnT addSystemTask(@RequestBody SystemTaskModel systemTaskModel){

        SystemTaskModel taskModel = systemTaskService.addSystemTask(systemTaskModel);

        return new ReturnT(taskModel);

    }

    @GetMapping("/pageList")
    @ApiOperation("系统盘任务列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        // page list
        //List<SystemTaskDO> list = systemTaskDOMapper.pageList((current - 1) * size, size, username);
        List<SystemTaskModel> list = systemTaskService.listTask((current - 1) * size, size, username);
        int recordsTotal = systemTaskDOMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        maps.put("page",(recordsTotal + size - 1) / size);//分页数
        return new ReturnT<>(maps);
    }

    @PostMapping("/del")
    @ApiOperation("删除系统盘任务")
    public ReturnT delTask(@RequestParam(name = "id")Integer id){
        Boolean result = systemTaskService.deleteTask(id);

        return new ReturnT(result);

    }

    @PostMapping("/update")
    @ApiOperation("更新系统盘任务")
    public ReturnT updateTask(@RequestBody SystemTaskModel systemTaskModel){
        Boolean result = systemTaskService.updateTask(systemTaskModel);

        return new ReturnT(result);
    }


    @PostMapping("/startTencentCloud")
    @ApiOperation("启动腾讯云在线迁移")
    public ReturnT<String> startTencentCloud(@RequestBody SystemTaskModel systemTaskModel) {
        systemTaskModel.setStatus(2);
        systemTaskService.updateTask(systemTaskModel);
        Integer sourceServerId = systemTaskModel.getSourceServerId();
        SourceServerDO sourceServerDO = sourceServerDOMapper.selectByPrimaryKey(sourceServerId);
        Go2TencentCloudUtil.startGoToTencent(sourceServerDO.getIp(),Integer.parseInt(sourceServerDO.getPort()),sourceServerDO.getUsername(),sourceServerDO.getPassword());
        return  ReturnT.SUCCESS;
    }


    @PostMapping("/stopTencentCloud")
    @ApiOperation("停止腾讯云在线迁移")
    public ReturnT<String> stopTencentCloud(@RequestBody SystemTaskModel systemTaskModel) {
        systemTaskModel.setStatus(4);
        systemTaskService.updateTask(systemTaskModel);
        Integer sourceServerId = systemTaskModel.getSourceServerId();
        SourceServerDO sourceServerDO = sourceServerDOMapper.selectByPrimaryKey(sourceServerId);
        Go2TencentCloudUtil.stopGoToTencent(sourceServerDO.getIp(),Integer.parseInt(sourceServerDO.getPort()),sourceServerDO.getUsername(),sourceServerDO.getPassword());
        return  ReturnT.SUCCESS;
    }


    @GetMapping("/tailLogs")
    @ApiOperation("查看腾讯云迁移时运行日志")
    public ReturnT<ArrayList<String>> tailLogs(@RequestParam(name = "sourceServerId")Integer sourceServerId) {
        SourceServerDO sourceServerDO = sourceServerDOMapper.selectByPrimaryKey(sourceServerId);
        ArrayList logs = Go2TencentCloudUtil.tailLog(sourceServerDO.getIp(),Integer.parseInt(sourceServerDO.getPort()),
                sourceServerDO.getUsername(),sourceServerDO.getPassword(),500);
        return  new ReturnT<>(logs);
    }

    @GetMapping("/getInstanceIdList")
    @ApiOperation("查看instance列表")
    public ReturnT<List<String>> getInstanceIdList(@RequestParam(name = "accountid")Integer accountid,
                                                   @RequestParam(name = "region")String region
    ) {
        CloudAccountDO cloudAccountDO = cloudAccountDOMapper.selectByPrimaryKey(accountid);
        String accesskeyId = cloudAccountDO.getAccesskeyId();
        String accesskeySecret = cloudAccountDO.getAccesskeySecret();
        List<String> instanceIdList = Go2TencentCloudUtil.getInstanceIdList(accesskeyId, accesskeySecret, region);
        return  new ReturnT<>(instanceIdList);
    }

}
