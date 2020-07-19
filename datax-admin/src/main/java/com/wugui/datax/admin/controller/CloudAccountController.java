package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.datamapper.SourceServerDOMapper;
import com.wugui.datax.admin.dataobject.CloudAccountDO;
import com.wugui.datax.admin.service.CloudAccountService;
import com.wugui.datax.admin.service.model.CloudAccountModel;
import com.wugui.datax.admin.service.model.SourceServerModel;
import com.wugui.datax.admin.service.model.SystemTaskModel;
import com.wugui.datax.admin.util.Go2TencentCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiahui on 2020-07-10 10:23
 */
@RestController
@RequestMapping("/api/cloud")
@Api(tags = "云账号接口")
public class CloudAccountController extends BaseController{

    @Autowired
    private CloudAccountService cloudAccountService;

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @PostMapping("/del")
    @ApiOperation("删除云账号")
    public ReturnT delAccount(@RequestParam(name = "id")Integer id){
        Boolean result = cloudAccountService.deleteAccount(id);

        return new ReturnT(result);

    }

    @PostMapping("/update")
    @ApiOperation("更新云账号")
    public ReturnT updateCloudAccount(@RequestBody CloudAccountModel cloudAccountModel){

        Boolean result = cloudAccountService.updateAccount(cloudAccountModel);

        return new ReturnT(result);
    }

    @PostMapping("/add")
    @ApiOperation("添加云账号")
    public ReturnT allCloudAccount(@RequestBody CloudAccountModel cloudAccountModel){

        CloudAccountModel accountModel = cloudAccountService.addCloudAccount(cloudAccountModel);

        return new ReturnT(accountModel);
    }

    @GetMapping("/pageList")
    @ApiOperation("云账号列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String username) {

        // page list
        List<CloudAccountDO> list = cloudAccountDOMapper.pageList((current - 1) * size, size, username);
        int recordsTotal = cloudAccountDOMapper.pageListCount((current - 1) * size, size, username);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", recordsTotal);        // 总记录数
        maps.put("recordsFiltered", recordsTotal);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        maps.put("page",(recordsTotal + size - 1) / size);//分页数
        return new ReturnT<>(maps);
    }

    @GetMapping("/list")
    @ApiOperation("云账号列表")
    public ReturnT<List<CloudAccountModel>> list() {

        // page list
        List<CloudAccountModel> list = cloudAccountService.getCloudList();
        return new ReturnT<>(list);
    }

    @GetMapping("/listRegions")
    @ApiOperation("查看地域列表")
    public ReturnT<List<String>> listRegions(int accountid) {
       // Go2TencentCloudUtil.listDescribeRegions("","");
        ArrayList<String> lists = new ArrayList<>();
        lists.add("ap-beijing");
        lists.add("ap-guangzhou");
        lists.add("ap-hongkong");
        lists.add("ap-shanghai");
        lists.add("ap-shanghai-fsi");
        lists.add("ap-shenzhen-fsi");
        lists.add("ap-singapore");
        lists.add("na-siliconvalley");
        lists.add("na-toronto");
        return new ReturnT<>(lists);
    }

}
