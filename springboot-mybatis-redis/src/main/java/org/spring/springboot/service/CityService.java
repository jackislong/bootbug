package org.spring.springboot.service;

import org.spring.springboot.dao.CityEntity;

import java.util.List;
import java.util.Map;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface CityService {
    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    CityEntity findCityById(int id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    int saveCity(CityEntity city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    int updateCity(CityEntity city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    int deleteCity(int id);


    List<CityEntity> findAllCity(Map parMa);

}
