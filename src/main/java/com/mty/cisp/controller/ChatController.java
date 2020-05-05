package com.mty.cisp.controller;


import com.mty.cisp.domain.User;
import com.mty.cisp.model.po.UserInfo;
import com.mty.cisp.model.vo.ResponseJson;
import com.mty.cisp.service.FriendsInfoService;
import com.mty.cisp.service.UserInfoService;
import com.mty.cisp.utils.Constant;
import com.mty.cisp.utils.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    FriendsInfoService friendsInfoService;

    @RequestMapping(path = {"/chat"})
    public String ChatController(Model model, HttpSession session){
        System.out.println("===Chat===");
        session.setAttribute(Constant.USER_TOKEN,"001");
        return "/chat";
    }

    @RequestMapping(path = {"/chat1"})
    public String Chat1Controller(Model model, HttpSession session){
        System.out.println("===Chat===");
        session.setAttribute(Constant.USER_TOKEN,"002");
        return "/chat";
    }

//    @RequestMapping(value = "/get_userinfo", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseJson getUserInfo(HttpSession session) {
//        Object userId = session.getAttribute(Constant.USER_TOKEN);
//        return userInfoService.getByUserId((String)userId);
//    }

    @RequestMapping(path = {"/chatchat"})
    public String ChatchatController(Model model, HttpSession session){
        System.out.println("===Chatchat===");
        return "/frontend/user/chatchat";
    }

    @RequestMapping(path = {"/user/addFriend"},method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson addFriendController(HttpServletRequest httpServletRequest, @RequestBody String addFriendId) {
        System.out.println("===addFriend==="+addFriendId);
        User user =(User)httpServletRequest.getSession().getAttribute("user");
        friendsInfoService.addFriend(user.getId(),Integer.parseInt(addFriendId));
        return new ReturnJson("add success!!!");
    }

    @RequestMapping(value = "/get_userinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getUserInfo(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        List<User> friendsInfo = friendsInfoService.getByUserId(user.getId());
        ArrayList<UserInfo> userInfoList = new ArrayList<>();
        for(User friend: friendsInfo){
            UserInfo u = new UserInfo(friend.getId().toString(), friend.getUsername(), friend.getPassword(), friend.getAvatar());
            System.out.println(u.toString());
            userInfoList.add(u);
        }
        UserInfo  userInfo= new UserInfo(user.getId().toString(), user.getUsername(), user.getPassword(), user.getAvatar());
        userInfo.setFriendList(userInfoList);
        return new ResponseJson().success()
                .setData("userInfo", userInfo);
    }

}
