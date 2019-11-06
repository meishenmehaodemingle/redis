package com.qf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 往redis中设置的数据都是 json
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *  opsForValue() 字符串相关的方法
     *  opsForSet() 集合相关的操作
     *  opsForHash() hash(map)相关的操作
     *  opsForList() 列表相关的操作
     * @return
     */
    @RequestMapping("/set")
    public Object set() {

        List<User> list = userMapper.selectAll();
        list.forEach(user -> {
            try {
                redisTemplate.opsForHash().put("users", user.getId(), objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return "success";
    }

    @RequestMapping("/get")
    public Object get() throws IOException {
        // 将取到的数据转换为对象
        String string = (String)redisTemplate.opsForHash().get("users", 70);

        logger.info(string);

        // readValue是反序列化Json
        User user = objectMapper.readValue(string, User.class);

        logger.info("姓名：" + user.getName());

        return user;
    }
}
