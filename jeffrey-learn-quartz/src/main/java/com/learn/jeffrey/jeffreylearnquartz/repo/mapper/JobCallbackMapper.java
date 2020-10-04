package com.learn.jeffrey.jeffreylearnquartz.repo.mapper;

import com.learn.jeffrey.jeffreylearnquartz.repo.po.JobCallbackPO;
import com.learn.jeffrey.jeffreylearnquartz.repo.po.JobCallbackExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface JobCallbackMapper {
    int countByExample(JobCallbackExample example);

    int deleteByExample(JobCallbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(JobCallbackPO record);

    int insertSelective(JobCallbackPO record);

    List<JobCallbackPO> selectByExample(JobCallbackExample example);

    JobCallbackPO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JobCallbackPO record, @Param("example") JobCallbackExample example);

    int updateByExample(@Param("record") JobCallbackPO record, @Param("example") JobCallbackExample example);

    int updateByPrimaryKeySelective(JobCallbackPO record);

    int updateByPrimaryKey(JobCallbackPO record);

    int  batchDelete(String[] ids);
}
