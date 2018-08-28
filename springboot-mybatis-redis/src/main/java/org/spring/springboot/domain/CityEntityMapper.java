package org.spring.springboot.domain;


import org.spring.springboot.dao.CityEntity;

import java.util.List;
import java.util.Map;

public interface CityEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CityEntity record);

    int insertSelective(CityEntity record);

    CityEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CityEntity record);

    int updateByPrimaryKey(CityEntity record);

    List<CityEntity> showAllcity(Map parMap);
}