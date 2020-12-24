package com.Control;


import com.Service.FileService;
import com.Service.HistoryService;
import com.Service.UserService;
import com.Service.UserSessionService;
import com.alibaba.fastjson.JSONObject;
import com.entity.History;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;

@Controller
@RequestMapping(value="/File")
public class FileControl {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/uploadFile.do")
    public @ResponseBody String uploadFile(MultipartFile file, HttpServletRequest req){

        long size = file.getSize();  //上传文件的大小
        if(fileService.UploadFile(file,userSessionService.getPath(),userSessionService.getDid())){
            req.setAttribute("message", "上传成功！");
            return "true";
        }
        return "false";
    }

    @RequestMapping("/updateFile.do")
    public @ResponseBody String updateFile(MultipartFile file, int fid, HttpServletRequest req){
        if(fileService.UpdateFile(file,userSessionService.getPath(),userSessionService.getDid(),fid)){
            req.setAttribute("message", "上传成功！");
            return "true";
        }
        return "false";
    }

    @RequestMapping("/downloadFile.do")
    public @ResponseBody String downloadFile(int downloadId, HttpServletRequest req, HttpServletResponse rep){
        FileInputStream fin = null;
        if(fileService.DownLoadFile(downloadId,rep)){
            req.setAttribute("message","下载");
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/encodingFile.do",method = {RequestMethod.POST})
    public @ResponseBody String EncodingFile(@RequestParam(required = true)int Fid, @RequestParam(required = true)String PWD, HttpServletRequest req, HttpServletResponse rep){
        if(fileService.FileEncoding(Fid,userSessionService.getPath(),PWD)){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/decodingFile.do",method = {RequestMethod.POST})
    public @ResponseBody String DecodingFile(@RequestParam(required = true)int Fid,@RequestParam(required = true)String PWD,HttpServletRequest req,HttpServletResponse rep){
        if(fileService.FileDecoding(Fid,userSessionService.getPath(),PWD)){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/gzipFile.do")
    public @ResponseBody String GzipFile(@RequestParam(required = true)int Fid,HttpServletRequest req,HttpServletResponse rep){
        if(fileService.FileZip(Fid, userSessionService.getPath())){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/rzipFile.do")
    public @ResponseBody String ReGzipFile(@RequestParam(required = true)int Fid,HttpServletRequest req,HttpServletResponse rep){
        if(fileService.FileRzip(Fid, userSessionService.getPath())){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/deleteFile.do")
    public @ResponseBody String DeleteFile(int Fid,HttpServletRequest req,HttpServletResponse rep){
        if(fileService.FileDelete(Fid,userSessionService.getPath())){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/reNameFile.do")
    public @ResponseBody String ReNameFile(int Fid,String NewName,HttpServletRequest req,HttpServletResponse rep){
        if(fileService.FileRename(Fid, userSessionService.getPath(), NewName)){
            return "true";
        }
        return "false";
    }

    @RequestMapping("/select.do")
    public @ResponseBody String SelectND(String FName){
        JSONObject json = new JSONObject();
        json.put("result","true");
        json.put("data",fileService.FileFind(FName,userSessionService.getDid()));
        return json.toJSONString();
    }

    @RequestMapping(value = "/SFile.do",produces = {"application/json"})
    public @ResponseBody
    String getAllFile(){
        return "true";//切面会自动返回完整plan列表
    }


}
