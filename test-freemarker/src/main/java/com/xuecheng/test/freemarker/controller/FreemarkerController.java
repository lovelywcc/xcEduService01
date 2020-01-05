package com.xuecheng.test.freemarker.controller;

import com.xuecheng.test.freemarker.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author wangcc
 * @create 2020-01-05 14:47
 **/
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/banner")
    public String banner(Map<String,Object> map){
        //使用resttemplate请求轮播图获取模型数据
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f", Map.class);
        Map body = entity.getBody();
        //设置模型数据
        map.putAll(body);
        return "index_banner";
    }

    //测试1
    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        //map里面的数据会作为request域响应给用户，freemarker模板会读取到map数据
        //向数据模型放数据
        map.put("name","王小子");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
//        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus",stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        //向数据模型放数据
        map.put("stu1",stu1);
        //向数据模型放数据
        map.put("stuMap",stuMap);
        //返回模板文件名称,返回freemarker模板的位置，基于resources/templates路径的
        return "test1";
    }
}
