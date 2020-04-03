package com.imooc.passbook.controller;

import com.imooc.passbook.service.IMerchantsServ;
import com.imooc.passbook.vo.CreateMerchantsRequest;
import com.imooc.passbook.vo.PassTemplate;
import com.imooc.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务 Controller</h1>
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {

    /**
     * 商户服务接口
     */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request) {
        log.info("CreateMerchants:{}", request);
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id) {
        log.info("buildMerchantsInfo:{}", id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate) {
        log.info("dorpTemplateMerchants:{}", passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }

}
