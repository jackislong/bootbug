package org.spring.springboot.controller;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.service.BugDeatilService;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-22.
 */
@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    BugDeatilService  deatilService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public ModelAndView findOneCity(@PathVariable("id") int id) {
        ModelAndView  modelAndView= new ModelAndView();
        modelAndView.addObject(cityService.findCityById(id));
        modelAndView.setViewName("city");
        return modelAndView;
    }



    @RequestMapping(value = "/buglist")
    @ResponseBody
    public Map bugList(HttpServletRequest request){

        Map   parMap = new HashMap();
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer size = Integer.valueOf(request.getParameter("limit"));
        String   checker =request.getParameter("checker");
        String   startdate =request.getParameter("startdate");
        String   enddate =request.getParameter("enddate");
        parMap.put("start",(page-1)*size);
        parMap.put("end",size);
        if(StringUtils.isNotBlank(checker)){
            parMap.put("dealname",checker);
        }
        if(StringUtils.isNotBlank(startdate)){
            parMap.put("startdate",startdate.replace("-",""));
        }
        if(StringUtils.isNotBlank(enddate)){
            parMap.put("enddate",enddate.replace("-",""));
        }
        List  list =  deatilService.findAll(parMap);

        parMap.put("start",0);
        parMap.put("end",Integer.MAX_VALUE);
        List countList = deatilService.findAll(parMap);
        Map  resultMap = new HashMap();
        resultMap.put("code","0000");
        resultMap.put("msg","");
        resultMap.put("count",countList.size());
        resultMap.put("data",list);
        return  resultMap;
    }


    @RequestMapping(value = "/showbug")
    public String showAllBug(){
        return "allbug";
    }



    @RequestMapping(value = "/showcity")
    @ResponseBody
    public Map showCity(HttpServletRequest  request){
        String sql = "SELECT  count(1)  as c from  city ";
        Map  mapcount =  jdbcTemplate.queryForMap(sql);
        Integer  count = MapUtils.getInteger(mapcount,"c",0);
        Map   parMap = new HashMap();
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer size = Integer.valueOf(request.getParameter("limit"));
        parMap.put("st",(page-1)*size);
        parMap.put("end",size);
        List list= cityService.findAllCity(parMap);
        Map  responeResult = new HashMap();
        responeResult.put("code","0000");
        responeResult.put("msg","");
        responeResult.put("count",count);
        responeResult.put("data",list);
        return  responeResult;
    }

    @RequestMapping(value = "city")
    public String  city(){
        return "city";
    }

}
