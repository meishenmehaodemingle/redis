package com.qf.controller;

//import com.qf.dao.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatisplus
 * J2EE: 是由一系列组规范成用于企业级开发的规则。
 * JSP、Servlet、JPA、JMS、JTA
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private UserRepository userRepository;

    @RequestMapping
    public Object getPageData(@RequestParam(defaultValue = "1") Integer currentPage) {
        PageHelper.startPage(currentPage, 10);

        List<User> list = userMapper.selectData();

        return PageInfo.of(list);

//        return userRepository.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    public Object add(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try{
            user.setCreatetime(new Date());
            user.setPassword("123456");
            userMapper.addUser(user);
            map.put("code", 1);
            map.put("msg", "数据添加成功");
        }catch(Exception ex) {
            ex.printStackTrace();
            map.put("code", -1);
            map.put("msg", "数据添加失败，请联系管理员.");
        }
        return map;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        try{
            userMapper.deleteByPrimaryKey(id);
            map.put("code", 1);
            map.put("msg", "数据删除成功");
        }catch(Exception ex) {
            ex.printStackTrace();
            map.put("code", -1);
            map.put("msg", "数据删除失败，请联系管理员.");
        }
        return map;
    }
}
