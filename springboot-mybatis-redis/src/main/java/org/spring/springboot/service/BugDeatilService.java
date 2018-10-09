package org.spring.springboot.service;

import org.spring.springboot.dao.BugdetailEntity;

import java.util.List;
import java.util.Map;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-22.
 */
public interface BugDeatilService {

    /**
     * 批量保存
     * @param list
     * @return
     * @throws Exception
     */
    int batchsave(List<BugdetailEntity> list) throws Exception;

    /**
     * 查询
     * @param map
     * @return
     */
    public List<BugdetailEntity> findAll(Map map);

    /**
     * BUG分析
     * @param st
     * @param end
     * @return
     */
    Object[] queryAllBug(String st,String end);

    Map personAna(String name);

    Map analyProj(String proname);

    List queryAllproj();

    Map   queryBugByGroupByPerson(String dealname);

    List  queryalldealname();
}
