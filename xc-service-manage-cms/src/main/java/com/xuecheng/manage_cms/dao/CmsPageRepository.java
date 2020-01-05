package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {//泛型参数为实体类和实体类的主键
    //根据页面名称查询
    CmsPage findByPageName(String pageName);
    //页面名称、站点id、页面webpath的唯一性查询
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebpath);
}
