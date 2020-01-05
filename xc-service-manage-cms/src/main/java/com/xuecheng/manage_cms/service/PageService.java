package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     * ҳ���ѯ����
     * @param page ҳ�룬��1��ʼ����
     * @param size ÿҳ��¼��
     * @param queryPageRequest ��ѯ����
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        if (queryPageRequest==null){
            queryPageRequest= new QueryPageRequest();
        }
        //�Զ���������ѯ
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //��ҳ����
        if(page <=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//�����б�
        queryResult.setTotal(all.getTotalElements());//�����ܼ�¼��
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    //����ҳ��
    public CmsPageResult add(CmsPage cmsPage){
        //ҳ�����ơ�վ��id��ҳ��webpath��Ψһ��
        String str = null;
        int length = str.length();
        CmsPage page = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (page!=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        //�������������ֶ�ȥ��ѯ������鵽����ҳ���Ѿ����ڣ���ѯ�����������
        if (page==null) {
            //����dao����ҳ��
            cmsPage.setPageId(null);//��mongodb�Զ�����
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    //����ҳ��id��ѯҳ��
    public CmsPage getById(String id){
        Optional<CmsPage> page = cmsPageRepository.findById(id);
        if(page.isPresent()){
            CmsPage cmsPage = page.get();
            return cmsPage;
        }
        return null;
    }

    //�޸�ҳ��
    public CmsPageResult update(String id,CmsPage cmsPage){
        //����id�����ݿ��ѯҳ����Ϣ
        CmsPage page = getById(id);
        if (page!=null){
            page.setTemplateId(cmsPage.getTemplateId());
            page.setSiteId(cmsPage.getSiteId());
            page.setPageAliase(cmsPage.getPageAliase());
            page.setPageName(cmsPage.getPageName());
            page.setPageWebPath(cmsPage.getPageWebPath());
            page.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            cmsPageRepository.save(page);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    //����idɾ��ҳ��
    public ResponseResult delete(String id){
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById(id);
        if (optionalCmsPage.isPresent()){
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
