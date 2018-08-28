package org.spring.springboot.domain;


import org.spring.springboot.dao.CountrylanguageEntityKey;

public interface CountrylanguageEntityMapper {
    int deleteByPrimaryKey(CountrylanguageEntityKey key);

    int insert(CountrylanguageEntityKey record);

    int insertSelective(CountrylanguageEntityKey record);

    CountrylanguageEntityKey selectByPrimaryKey(CountrylanguageEntityKey key);

    int updateByPrimaryKeySelective(CountrylanguageEntityKey record);

    int updateByPrimaryKey(CountrylanguageEntityKey record);
}