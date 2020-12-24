package com.Control;

import com.Service.DirService;
import com.Service.UserSessionService;
import com.entity.SDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/Dir")
public class DirControl {

    @Autowired
    DirService dirService;

    @Autowired
    UserSessionService userSessionService;

    @RequestMapping(value = "/SDir.do",produces = {"application/json"})
    public @ResponseBody
    String getAllDir(){
        return "true";//切面会自动返回完整plan列表
    }

    @RequestMapping(value = "/DirSize.do",produces = {"application/json"})
    public String UpdateSize(int Did, int Size){
        if(userSessionService.getDid()!=Did){
            return "false";
        }
        if(dirService.SizeUpdate(Did,Size)){
            return "true";
        }
        return "false";
    }


}
