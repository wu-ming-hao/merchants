package com.imooc.passbook.service;

import com.imooc.passbook.vo.CreateMerchantsRequest;
import com.imooc.passbook.vo.PassTemplate;
import com.imooc.passbook.vo.Response;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;
import sun.misc.Request;

/**
 * <H1>对商户服务接口定义</H1>
 */
public interface IMerchantsServ {


    /**
     * <h2>创建商户服务</h2>
     * @param request {@link CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     * */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * <h2>根据 id 构造商户信息</h2>
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * <h2> 投放优惠卷 </h2>
     * @param template {@link PassTemplate} 优惠卷对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);
}
