package com.klef.fsad.sdp.service;

import com.klef.fsad.sdp.entity.Admin;

public interface AdminService 
{
    public String addAdmin(Admin admin);
    public Admin verifyAdminLogin(String username, String password);
}