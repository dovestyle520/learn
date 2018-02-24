
package com.marquess.learn.dao;

import java.util.List;
import com.marquess.learn.entity.LeaveInfo;

public interface LeaveMapper {
    int getCount();
    void insert(LeaveInfo entity);
    void delete(String id);
    LeaveInfo getById(String id);
    List<LeaveInfo> getList();
    void update(LeaveInfo entity);
}

