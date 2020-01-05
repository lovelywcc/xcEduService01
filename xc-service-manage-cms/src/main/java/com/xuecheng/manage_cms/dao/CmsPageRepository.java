package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {//���Ͳ���Ϊʵ�����ʵ���������
    //����ҳ�����Ʋ�ѯ
    CmsPage findByPageName(String pageName);
    //ҳ�����ơ�վ��id��ҳ��webpath��Ψһ�Բ�ѯ
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebpath);
}
