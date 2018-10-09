package org.spring.springboot.controller;

import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.Util.ParseExcelTest;
import org.spring.springboot.service.BugDeatilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 文件上传页面
     * @return
     */
    @RequestMapping(value = "/upload")
    public String  showupload(){
        return "upload";
    }

    /**
     * bug分析页面
     * @return
     */
    @RequestMapping(value = "/bugana")
    public String bugAnalys(){
        return "buganalysis";
    }
    @RequestMapping(value = "/personana")
    public String personAna(){
        return "personana";
    }

    @RequestMapping(value = "/analysis",method = RequestMethod.POST)
    @ResponseBody
    public  Object[]  queryAllBug(HttpServletRequest  request){
         String  startdate = request.getParameter("startdate");
         String  enddate   = request.getParameter("enddate");
        return   deatilService.queryAllBug(startdate,enddate);
    }

    @RequestMapping(value = "/personanalysis",method = RequestMethod.POST)
    @ResponseBody
    public Map personAnalysis(@RequestParam(value ="dealname",defaultValue = "兰勤川") String dealname){
        return deatilService.personAna(dealname);
    }


    /**
     * 项目BUG页面
     * @return
     */
    @RequestMapping(value = "/projanaly")
    public String  projana(){
        return "projana";
    }

    @RequestMapping(value = "/projanalysis",method = RequestMethod.POST)
    @ResponseBody
    public Map projAnalysis(@RequestParam(value ="projname",defaultValue = "EC直销产品") String projname){
        if(StringUtils.isBlank(projname) || "null".equals(projname)){
            projname = "EC直销产品";
        }
        return deatilService.analyProj(projname);
    }


    @RequestMapping(value = "/allproj",method = RequestMethod.POST)
    @ResponseBody
    public List allproj(){
        return deatilService.queryAllproj();
    }


    @RequestMapping(value = "/dealnameana")
    public String dealnameana(){
        return "dealnameana";
    }

    @RequestMapping(value = "/dealnameanalysis",method = RequestMethod.POST)
    @ResponseBody
    public Map dealnameanalysis(@RequestParam(value ="dealname",defaultValue = "") String dealname){
        if(StringUtils.isBlank(dealname) || "null".equals(dealname)){
            dealname = "兰勤川";
        }
        return deatilService.queryBugByGroupByPerson(dealname);
    }


    @RequestMapping(value = "/alldealname",method = RequestMethod.POST)
    @ResponseBody
    public List alldealname(){
        return deatilService.queryalldealname();
    }
}
