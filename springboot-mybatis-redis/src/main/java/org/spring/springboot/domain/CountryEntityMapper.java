package org.spring.springboot.domain;

import org.spring.springboot.dao.CountryEntity;

public interface CountryEntityMapper {
    int deleteByPrimaryKey(String code);

    int insert(CountryEntity record);

    int insertSelective(CountryEntity record);

    CountryEntity selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(CountryEntity record);

    int updateByPrimaryKey(CountryEntity record);
}