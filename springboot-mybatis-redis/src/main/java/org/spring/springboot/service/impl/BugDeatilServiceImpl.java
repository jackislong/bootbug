package org.spring.springboot.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.annotaion.RedisCache;
import org.spring.springboot.dao.BugdetailEntity;
import org.spring.springboot.domain.BugdetailEntityMapper;
import org.spring.springboot.service.BugDeatilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-22.
 */
@Service
public class BugDeatilServiceImpl implements BugDeatilService{

    @Autowired
    BugdetailEntityMapper  mapper;

    @Autowired
    JdbcTemplate  jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int batchsave(List<BugdetailEntity> list) throws Exception {
        mapper.deleteAll();
        int  a=mapper.insertbatch(list);
        return a;
    }

    @Override
    @Transactional(readOnly = true)
    @RedisCache(expire = 10,unit = TimeUnit.SECONDS)
    public List<BugdetailEntity> findAll(Map map) {
        return mapper.selectAll(map);
    }

    @Override
    @Transactional(readOnly = true)
    public Object[] queryAllBug(String startdate,String endate) {
        List  parList = new ArrayList();
        StringBuilder  sql = new StringBuilder("SELECT dealname,count(1)  as c FROM bugdetail where 1=1");
        if(StringUtils.isNotBlank(startdate)){
            sql.append("  and  detectiontime >= ?");
            parList.add(startdate.replace("-",""));
        }
        if(StringUtils.isNotBlank( endate)){
            sql.append("  and  detectiontime <= ?");
            parList.add(endate.replace("-",""));
        }

        sql.append(" GROUP BY   dealname");
        List  list =  jdbcTemplate.query(sql.toString(), parList.toArray(), new RowMapper() {
            @Override
            public Object[] mapRow(ResultSet resultSet, int i) throws SQLException {
                Object[]  obj = new Object[2];
                obj[0] = resultSet.getString("dealname");
                obj[1] = resultSet.getInt("c");
                return obj;
            }
        });
        return  list.toArray();
    }

    @Override
    @Transactional(readOnly = true)
    public Map personAna(String name) {
        Map  result = new HashMap();
        List<Map> list = mapper.personAna(name);
        List  mList = new ArrayList();
        List  cList = new ArrayList();
        for(Map  map:list){
            mList.add(MapUtils.getString(map,"t"));
            cList.add(MapUtils.getInteger(map,"c"));
        }
        result.put("m",mList.toArray());
        result.put("c",cList.toArray());
        result.put("name",name);
        return result;
    }


}
