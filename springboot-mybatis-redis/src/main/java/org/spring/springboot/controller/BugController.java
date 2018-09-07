package org.spring.springboot.controller;

import org.spring.springboot.Util.ParseExcelTest;
import org.spring.springboot.service.BugDeatilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-23.
 */
@Controller
public class BugController {



    @Autowired
    BugDeatilService deatilService;

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImg(MultipartFile file, HttpServletRequest request) {
        Map resultMap = new HashMap();
        try {
            if(file.getSize()==0){
                return resultMap;
            }
            List list = ParseExcelTest.readExcel(file);
            deatilService.batchsave(list);
            resultMap.put("code","0000");
            resultMap.put("data","");
            resultMap.put("msg","上传成功");
        } catch (Exception e) {
            resultMap.put("msg","上传异常");
        }
        //返回json
        return resultMap;
    }

    @RequestMapping(value = "/upload")
    public String  showupload(){
        return "upload";
    }

    @RequestMapping(value = "/bugana")
    public String bugAnalys(){
        return "buganalysis";
    }

    @RequestMapping(value = "/analysis",method = RequestMethod.POST)
    @ResponseBody
    public  Object[]  queryAllBug(HttpServletRequest  request){
         String  startdate = request.getParameter("startdate");
         String  enddate   = request.getParameter("enddate");
        return   deatilService.queryAllBug(startdate,enddate);
    }
}
