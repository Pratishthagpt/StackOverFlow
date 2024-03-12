package com.pratishtha.dev.StackOverFlow.controllers;

import com.pratishtha.dev.StackOverFlow.dtos.BlockAndUnBLockDTO;
import com.pratishtha.dev.StackOverFlow.exceptions.InvalidAuthorizationException;
import com.pratishtha.dev.StackOverFlow.exceptions.UserNotFoundException;
import com.pratishtha.dev.StackOverFlow.services.AdminService;
import com.pratishtha.dev.StackOverFlow.services.MemberService;
import com.pratishtha.dev.StackOverFlow.services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController extends ModeratorController{

    private AdminService adminService;

    @Autowired
    public AdminController(MemberService memberService, ModeratorService moderatorService, AdminService adminService) {
        super(memberService, moderatorService);
        this.adminService = adminService;
    }

    public void blockMember (BlockAndUnBLockDTO blockAndUnBLockDTO) {
        try {
            adminService.blockMember(blockAndUnBLockDTO.getMemberIdToBeBlocked(), blockAndUnBLockDTO.getMemberIdWhoWillBlock());
        }catch (UserNotFoundException | InvalidAuthorizationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unBlockMember (BlockAndUnBLockDTO blockAndUnBLockDTO) {
        try {
            adminService.unBlockMember(blockAndUnBLockDTO.getMemberIdToBeBlocked(), blockAndUnBLockDTO.getMemberIdWhoWillBlock());
        }catch (UserNotFoundException | InvalidAuthorizationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
