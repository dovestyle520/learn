

package com.marquess.learn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marquess.learn.dao.LeaveMapper;
import com.marquess.learn.entity.LeaveInfo;
import com.marquess.learn.service.LeaveService;
import com.marquess.learn.service.TestLeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{
  @Autowired
  private TestLeaveService testLeaveService;
  @Autowired
  private LeaveMapper leaveMapper;
  @Autowired
  private RuntimeService runtimeService;
  
  @Override
  public void addLeaveInfo(String msg) {
    LeaveInfo leaveInfo = new LeaveInfo();
    leaveInfo.setLeaveMsg(msg);
    String id = UUID.randomUUID().toString();
    leaveInfo.setId(id);
    /**
     * 新增一条记录到数据库
     */
    leaveMapper.insert(leaveInfo);
    /**
     * 启动流程引擎
     */
    testLeaveService.startProcess(id);
  }

  @Override
  public List<LeaveInfo> getByUserId(String userId) {
    ArrayList<LeaveInfo> leaveInfoList = new ArrayList<>();
    List<Task> list = testLeaveService.findTaskByUserId(userId);
    
    for(Task task : list) {
      ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
      /**
       * 获取业务流程的bussinessKey
       */
      String businessKey = result.getBusinessKey();
      LeaveInfo leaveInfo = leaveMapper.getById(businessKey);
      leaveInfo.setTaskId(task.getId());
      leaveInfoList.add(leaveInfo);
    }
    
    return leaveInfoList;
  }

  @Override
  public void completeTaskByUser(String taskId, String userId, String audit) {
    testLeaveService.completeTaskByUser(taskId, userId, audit);
  }
  
  @Override
  public LeaveInfo getById(String userId) {
    return leaveMapper.getById(userId);
  }
}

