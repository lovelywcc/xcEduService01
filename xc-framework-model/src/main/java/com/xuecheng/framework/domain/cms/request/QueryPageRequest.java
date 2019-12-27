package com.xuecheng.framework.domain.cms.request;

import lombok.Data;

/**
 * @author wangcc
 * @create 2019-12-27 0:25
 **/
@Data
public class QueryPageRequest {
    //接受页面查询的条件
    //站点id
    private String siteId;
    //页面id
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模板id
    private String templateId;
    //...用于以后参数的扩展
}
