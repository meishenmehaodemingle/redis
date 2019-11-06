package com.qf.controller;

import com.qf.dao.CompanyMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyMapper companyMapper;

    @RequestMapping
    public Object getCompanys(){
        return companyMapper.selectAll();  // List [{}, {}]
    }
}
