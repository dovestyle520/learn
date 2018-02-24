

package com.marquess.learn.service;

import java.util.List;

import com.marquess.learn.entity.LeaveInfo;

public interface LeaveService {
    /**
     * 新增一条请假单记录
     * @param msg
     */
    void addLeaveInfo(String msg);
    /**
     * 查询待办流程
     * @param userId
     * @return
     */    
    List<LeaveInfo> getByUserId(String userId);
    /**
     * 完成任务
     * @param taskId
     * @param userId
     * @param audit
     */    
    void completeTaskByUser(String taskId, String userId, String audit);
}

