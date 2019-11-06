package com.qf.dao;

import com.qf.pojo.Company;
import org.apache.ibatis.annotations.Mapper;
import com.qf.base.BaseMapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

}
