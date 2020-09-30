package com.meikinfo.erukaprovide.erukaprovide.dao;

import com.meikinfo.erukaprovide.erukaprovide.domain.TLogger;
import com.meikinfo.erukaprovide.erukaprovide.domain.TLoggerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLoggerMapper {
    long countByExample(TLoggerExample example);

    int deleteByExample(TLoggerExample example);

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(TLogger record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(TLogger record);

    List<TLogger> selectByExample(TLoggerExample example);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    TLogger selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLogger record, @Param("example") TLoggerExample example);

    int updateByExample(@Param("record") TLogger record, @Param("example") TLoggerExample example);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TLogger record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TLogger record);
}