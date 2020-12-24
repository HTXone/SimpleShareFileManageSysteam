package com.aspect;

import com.Service.*;
import com.alibaba.fastjson.JSONObject;
import com.entity.History;
import com.entity.SFile;
import com.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Aspect
public class ReturnJsonAspect {

    @Autowired
    private DirService dirService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserSessionService userSessionService;

    public ReturnJsonAspect() {
        System.out.println("切面实例化了");
    }

    @Around("execution(String com.Control.DirControl.*Dir(..))")
    public String returnJsonDir(ProceedingJoinPoint joinPoint){
        System.out.println("切面执行了");
        try {
            if("true".equals((String)joinPoint.proceed())){
                int Did = userSessionService.getDid();
                if(dirService.GetDirByDid(Did)!=null)
                {
                    String  Path = dirService.GetPath(userSessionService.getDid());
                    userSessionService.setPath(Path);
                    List<SFile> files= dirService.GetFiles(Did);
                    JSONObject json = new JSONObject();
                    json.put("result","true");
                    json.put("data",files);
                    System.out.println("到达");
                    return json.toJSONString();
                }
            }
            else {
                JSONObject json = new JSONObject();
                json.put("result", "false");
                return json.toJSONString();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Around("execution(String com.Control.UserControl.*User(..))")
    public String returnJsonUser(ProceedingJoinPoint joinPoint){
        System.out.println("切面执行了");
        try {
            if("true".equals((String)joinPoint.proceed())){
                int Did = userSessionService.getDid();
                if(dirService.GetDirByDid(Did)!=null)
                {
                    List<User> users= userService.GetAllUser(Did);
                    JSONObject json = new JSONObject();
                    json.put("result","true");
                    json.put("data",users);
                    System.out.println("到达");
                    return json.toJSONString();
                }
            }
            else {
                JSONObject json = new JSONObject();
                json.put("result", "false");
                return json.toJSONString();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Around("execution(String com.Control.FileControl.*File(..))")
    public String returnJsonFile(ProceedingJoinPoint joinPoint){
        System.out.println("切面执行了 files");
        try {
            if("true".equals((String)joinPoint.proceed())){
                int Did = userSessionService.getDid();
                if(fileService.GetFiles(Did)!=null)
                {
                    List<SFile> files= fileService.GetFiles(Did);
                    JSONObject json = new JSONObject();
                    json.put("result","true");
                    json.put("data",files);
                    System.out.println("到达");
                    return json.toJSONString();
                }
            }
            else {
                JSONObject json = new JSONObject();
                json.put("result", "false");
                return json.toJSONString();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Around("execution(String com.Control.HistroryControl.*History(..))")
    public String returnJsonHistory(ProceedingJoinPoint joinPoint){
        System.out.println("切面执行了history");
        try {
            if("true".equals((String)joinPoint.proceed())){
                int Did = userSessionService.getDid();
                if(dirService.GetDirByDid(Did)!=null)
                {
                    List<History> histories = historyService.SearchHistoryByDid(userSessionService.getDid());
                    JSONObject json = new JSONObject();
                    json.put("result","true");
                    json.put("data",histories);
                    System.out.println("到达");
                    return json.toJSONString();
                }
            }
            else {
                JSONObject json = new JSONObject();
                json.put("result", "false");
                return json.toJSONString();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }




}
