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

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int insertbatch(List<BugdetailEntity> list);

    /**
     * 查询
     * @param parMap
     * @return
     */
    List<BugdetailEntity> selectAll(Map parMap);

    /**
     * 删除bugdetail表的数据
     * @return
     */
    int  deleteAll();

    /**
     * 分组查询处理的BUG数
     * @param dealName
     * @return
     */
    List<Map>   personAna(String dealName);

    /**
     * 分组查询所有项目的处理人的BUG数
     * @param _proname
     * @return
     */
    List<Map>  analyProj(String _proname);

    /**
     * 获取所有的项目名称
     * @return
     */
    List<Map> queryAllProj();

    List<Map>  querybuggroupbyperon(String dealname);

    /**
     * 查询所有的开发人员
     * @return
     */
    List<Map> queryalldealname();
}