package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangcc
 * @create 2020-01-05 17:55
 **/
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

}
