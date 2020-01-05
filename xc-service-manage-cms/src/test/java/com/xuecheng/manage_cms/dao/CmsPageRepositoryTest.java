package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author wangcc
 * @create 2019-12-27 23:27
 **/
@SpringBootTest//由于这个注解的存在，就能通过注入的方式将启动类扫描的对象注入进来
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll() {
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void testFindAllByExample(){
        int page =1;
        int size =10;
        Pageable pageable = PageRequest.of(page, size);
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<CmsPage> pageExample = Example.of(cmsPage,matcher);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageExample, pageable);
        System.out.println(cmsPages.getContent());
    }

    @Test
    public void testFindPage() {
        int page = 1;
        int size = 10;
        //PageRequest pageRequest = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }
    //修改
    @Test
    public void Update(){
        Optional<CmsPage> optional = cmsPageRepository.findById("5b");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("test01");
            cmsPageRepository.save(cmsPage);
        }
    }

}
