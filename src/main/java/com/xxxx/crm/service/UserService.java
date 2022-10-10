package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.RegisterModel;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.PhoneUtil;
import com.xxxx.crm.utils.UserIDBase64;
import com.xxxx.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;


    public UserModel userLogin(String userName, String userPwd){
        checkLoginParams(userName,userPwd);
        User user=userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户姓名不存在!");
        checkUserPwd(userPwd,user.getUserPwd());
        return buildUserInfo(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer userId,String oldPwd,String newPwd,String repeatPwd){
        User user=userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null,"待更新记录不存在！");
        checkPasswordParams(user,oldPwd,newPwd,repeatPwd);
        user.setUserPwd(newPwd);
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改密码失败！");
    }

    private void checkPasswordParams(User user,String oldPwd, String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码不能为空！");
        AssertUtil.isTrue(!user.getUserPwd().equals(oldPwd),"原始密码不正确！");
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        AssertUtil.isTrue(newPwd.equals(oldPwd),"新密码不能与原始密码相同！");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),"确认密码与新密码不一致！");
    }

    private void checkPasswordParams(String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),"确认密码与新密码不一致！");
    }


    private UserModel buildUserInfo(User user) {
        UserModel userModel=new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    private void checkUserPwd(String userPwd, String userPwd1) {
        //userPwd= Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(userPwd1),"用户密码不正确");
    }

    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");
    }

    private void checkRegisterParams(String userName) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户帐号不能为空!");
        User user=userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user!=null,"该用户已存在");
    }

    private void checkUserInfoParams(RegisterModel registerModel) {
        AssertUtil.isTrue(StringUtils.isBlank(registerModel.getTrueName()),"用户真实姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(registerModel.getPhone()),"用户手机号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(registerModel.getEmail()),"用户邮箱不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(registerModel.getPhone()),"用户手机号输入不合法");
    }
    private void checkUserInfoParams(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"用户真实姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"用户手机号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()),"用户邮箱不能为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getPhone()),"用户手机号输入不合法");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void userRegister(RegisterModel registerModel) {
        checkRegisterParams(registerModel.getUserName());
        checkPasswordParams(registerModel.getUserPwd(),registerModel.getRepeatPwd());
        checkUserInfoParams(registerModel);
        User user=new User();
        user.setUserName(registerModel.getUserName());
        user.setUserPwd(registerModel.getUserPwd());
        user.setTrueName(registerModel.getTrueName());
        user.setEmail(registerModel.getEmail());
        user.setPhone(registerModel.getPhone());
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        int num=userMapper.insertSelective(user);
        AssertUtil.isTrue(num!=1,"注册失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User userUpdate(User user) {
        checkUserInfoParams(user);
        int num=userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(num!=1,"更新失败");
        User newUser=userMapper.selectByPrimaryKey(user.getId());
        return newUser;
    }
}
