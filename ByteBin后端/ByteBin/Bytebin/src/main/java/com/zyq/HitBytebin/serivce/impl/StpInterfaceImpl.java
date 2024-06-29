package com.zyq.HitBytebin.serivce.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.zyq.HitBytebin.model.entity.Role;
import com.zyq.HitBytebin.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roles = new ArrayList<>();
        String userId = loginId.toString();
        Long id = Long.valueOf(userId);
        int role = userService.getRole(id);
        roles.add(Role.getRole(role));
        return roles;
    }
}
