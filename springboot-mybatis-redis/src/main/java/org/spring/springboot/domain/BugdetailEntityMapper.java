package org.spring.springboot.domain;

import org.spring.springboot.dao.BugdetailEntity;

import java.util.List;
import java.util.Map;

public interface BugdetailEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BugdetailEntity record);

    int insertSelective(BugdetailEntity record);

    BugdetailEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BugdetailEntity record);

    int updateByPrimaryKey(BugdetailEntity record);

    int insertbatch(List<BugdetailEntity> list);

    List<BugdetailEntity> selectAll(Map parMap);

    int  deleteAll();

    List<Map>   personAna(String dealName);
}