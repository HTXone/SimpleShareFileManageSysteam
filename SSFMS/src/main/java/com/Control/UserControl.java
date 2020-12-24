package com.Control;

import com.Service.DirService;
import com.Service.FileService;
import com.Service.UserService;
import com.Service.UserSessionService;
import com.alibaba.fastjson.JSONObject;
import com.entity.SDir;
import com.entity.UD;
import com.entity.User;
import com.unit.TimeGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value="/user")
public class UserControl {
    @Autowired
    private UserService service;

    @Autowired
    private DirService dirService;

    @Autowired
    private UserSessionService userSessionService;

    @RequestMapping(value = "/login.do",method = {RequestMethod.POST})
    public ModelAndView login(@RequestParam(required = true)String username, @RequestParam(required = true)String password, HttpServletRequest req, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        System.out.println(username+" "+password);
        if(userSessionService.getPWD()!=null){
            username = userSessionService.getUsername();
            password = userSessionService.getPWD();
        }
        userSessionService.setPWD(null);
        try{
            UD ud = service.Login(username,password);
            User user = ud.getUserInfo();
            SDir dir = ud.getDirInfo();
            if(user != null){
                userSessionService.setUid(user.getUserId());
                userSessionService.setUsername(username);
                userSessionService.setDid(dir.getDirId());
                userSessionService.setOwnerId(dir.getUserId());
                userSessionService.setPath(dir.getDirPath());
                response.sendRedirect("../index_.html");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("../index.jsp");
        mv.addObject("loginfall",true);
        return mv;
    }

    @RequestMapping(value = "/logout.do",method = {RequestMethod.GET})
    public ModelAndView logout(HttpServletRequest req, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        try{
            userSessionService.setUid(0);
            userSessionService.setUsername("");
            userSessionService.setPath("");
            userSessionService.setDid(0);
            userSessionService.setOwnerId(0);
            response.sendRedirect("../index.jsp");
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value="/whoami.do",produces = {"application/json"})
    public @ResponseBody
    String whoami(){
        String username = userSessionService.getUsername();
        System.out.println(username);
        HashMap<String,String> name = new HashMap<>();
        if(username!=null){
            name.put("username",username);
            System.out.println(userSessionService.getOwnerId()+" "+ userSessionService.getUid());
            if(userSessionService.getOwnerId() == userSessionService.getUid()){
                name.put("isOwner","true");
            }else{
                name.put("isOwner","false");
            }
        }
        else{
            name.put("username","未登录");
        }
        return JSONObject.toJSONString(name);
    }

    @RequestMapping(value = "/register.do",method = {RequestMethod.POST})
    public String register(@RequestParam(required = true)String username, @RequestParam(required = true)String password,@RequestParam(required = true)String factoryname, Model model){
        if(service.register(username,password,factoryname)){         //直接在Service中完成注册
            userSessionService.setUsername(username);
            userSessionService.setPWD(password);
            return "forward:/user/login.do";
        }
        else{
            return "redirect:user/register.do";
        }
    }

    @RequestMapping(value = "/addUser.do",method = {RequestMethod.POST},produces = {"application/json"})
    public @ResponseBody String addUser(@RequestParam(required = true)String username,@RequestParam(required = true)String PWD){
        if(service.addUser(userSessionService.getDid(),username,PWD)){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/delete.do",method = {RequestMethod.POST},produces = {"application/json"})
    public @ResponseBody String deleteUser(@RequestParam(required = true)long uid){
        if(service.UserDelete(userSessionService.getDid(),uid)){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/update.do",method = {RequestMethod.POST},produces = {"application/json"})
    public @ResponseBody String UpdateUser(long uid,String userName,String PWD){
        if(service.UserUpdate(uid,userName,PWD)){
            return "true";
        }
        return "false";
    }
    @RequestMapping(value = "/user.do",produces = {"application/json"})
    public @ResponseBody String GetAllUser(){
        return "true";
    }

    @RequestMapping("/select.do")
    public @ResponseBody String SelectUName(String UserName){
        JSONObject json = new JSONObject();
        json.put("result","true");
        json.put("data",service.GetSelectUser(userSessionService.getDid(), UserName));
        return json.toJSONString();
    }


}
