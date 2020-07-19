package com.wugui.datax.admin.tool;

import com.wugui.datax.admin.datamapper.CloudAccountDOMapper;
import com.wugui.datax.admin.dataobject.CloudAccountDO;
import com.wugui.datax.admin.service.CloudAccountService;
import com.wugui.datax.admin.service.SourceServerService;
import com.wugui.datax.admin.service.SystemTaskService;
import com.wugui.datax.admin.service.model.CloudAccountModel;
import com.wugui.datax.admin.service.model.SourceServerModel;
import com.wugui.datax.admin.service.model.SystemTaskModel;
import com.wugui.datax.admin.util.Go2TencentCloudUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by jiahui on 2020-07-09 15:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCloud {

    @Autowired
    private CloudAccountService cloudAccountService;

    @Autowired
    private SourceServerService sourceServerService;

    @Autowired
    private SystemTaskService systemTaskService;

    @Autowired
    private CloudAccountDOMapper cloudAccountDOMapper;

    @Test
    public void testCloud(){
        cloudAccountService.deleteAccount(2);
    }

    @Test
    public void allCloud(){
        CloudAccountModel cloudAccountModel = new CloudAccountModel();
        cloudAccountModel.setAccesskeyId("aaa");
        cloudAccountModel.setAccesskeySecret("bbb");
        cloudAccountModel.setName("one");
        cloudAccountModel.setType(0);
        cloudAccountModel.setRemarks("test");
        cloudAccountService.addCloudAccount(cloudAccountModel);
    }

    @Test
    public void addServer(){
        SourceServerModel sourceServerModel = new SourceServerModel();
        sourceServerModel.setIp("192.168.22.221");
        sourceServerModel.setName("test");
        sourceServerModel.setType(0);
        sourceServerModel.setUsername("test");
        sourceServerModel.setPassword("11");
        sourceServerModel.setPort("22");
        sourceServerService.addSourceServer(sourceServerModel);
    }

    @Test
    public void addTask(){
        SystemTaskModel systemTaskModel = new SystemTaskModel();
        systemTaskModel.setBroadband(21);
        systemTaskModel.setCloudAccountId(1);
        systemTaskModel.setCloudServerIp("123");
        systemTaskModel.setName("test");
        systemTaskModel.setStartTime(new Date());
        systemTaskModel.setTargetArea("hangzhou");
        systemTaskModel.setSourceServerId(2);
        systemTaskModel.setStartType(1);
        systemTaskService.addSystemTask(systemTaskModel);
    }

    @Test
    public void sourceListTest(){
        List<SourceServerModel> list =  sourceServerService.getSourceList();

        System.out.println(list.get(0).getId());

    }

    @Test
    public void cloudList(){
        List<CloudAccountModel> list = cloudAccountService.getCloudList();

        System.out.println(list.get(0).getName());
    }

    @Test
    public void pageCloud(){
        int result = cloudAccountDOMapper.pageListCount(1,2,"");
        System.out.println(result);
    }

    @Test
    public void pageClouds(){
        List<CloudAccountDO> cloudAccountDOS = cloudAccountDOMapper.pageList(1, 10, "");

        System.out.println(cloudAccountDOS.get(0).getName());
        System.out.println(cloudAccountDOS.get(1).getName());
        //System.out.println(cloudAccountDOS.get(2).getName());

    }

    @Test
    public void start() {
        Go2TencentCloudUtil.startGoToTencent("192.168.22.242",22,"root","richinfo@139");
    }

    @Test
    public void stop() {
        Go2TencentCloudUtil.stopGoToTencent("192.168.22.242",22,"root","richinfo@139");
    }

}
