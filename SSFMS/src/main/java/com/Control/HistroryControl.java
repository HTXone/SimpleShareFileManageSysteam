package com.Control;

import com.Service.HistoryService;
import com.Service.UserSessionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/History")
public class HistroryControl {
    @Autowired
    HistoryService historyService;
    @Autowired
    UserSessionService userSessionService;

    @RequestMapping(value = "/history.do",produces = {"application/json"})
    public @ResponseBody
    String getAllHistory(){
        return "true";
    }

    @RequestMapping(value = "/select.do",produces = {"application/json"})
    public @ResponseBody String SelectByUFD(String UName,String FName){
        JSONObject json = new JSONObject();
        json.put("result","true");
        if(UName.equals("")||UName == null||UName.equals(" ")){
            json.put("data",historyService.SearchHistoryByFileNameD(FName,userSessionService.getDid()));
            return json.toJSONString();
        }else if(FName.equals("")||FName == null||FName.equals(" ")){
            json.put("data",historyService.SearchHistoryByUName(UName,userSessionService.getDid()));
            return json.toJSONString();
        }
        else{
            json.put("data",historyService.SearchHistoryByFileNameU(UName,FName,userSessionService.getDid()));
            return json.toJSONString();
        }
    }


}
