

package com.marquess.learn.controller;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marquess.learn.service.LeaveService;

@RestController
public class LeaveController {
  private static Logger logger = LoggerFactory.getLogger(LeaveController.class);
  @Autowired
  private LeaveService leaveService;
  
  /**
   * 发起申请，新增信息
   * @param msg
   * @param request
   * @param model
   * @return
   */  
  @RequestMapping("/addLeaveInfo")
  public String addLeaveInfo(String msg, HttpServletRequest request, Model model) {
    leaveService.addLeaveInfo(msg);
    return "新增成功...";
  }

  /**
   * 查询当前用户的任务列表
   * @param userId
   * @param request
   * @return
   */  
  @RequestMapping("/getTaskByUserId")
  public Object getTaskByUserId(String userId, HttpServletRequest request) {
    return leaveService.getByUserId(userId);
  }
  
  @RequestMapping("/completeTask")
  public String completeTask(String taskId, String userId, String audit, HttpServletRequest request) {
    leaveService.completeTaskByUser(taskId, userId, audit);
    return "审批完成...";
  }
}

