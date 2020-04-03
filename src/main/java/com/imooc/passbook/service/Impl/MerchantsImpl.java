package com.imooc.passbook.service.Impl;

import com.alibaba.fastjson.JSON;
import com.imooc.passbook.constant.Constants;
import com.imooc.passbook.constant.ErrorCode;
import com.imooc.passbook.dao.MerchantsDao;
import com.imooc.passbook.entity.Merchants;
import com.imooc.passbook.service.IMerchantsServ;
import com.imooc.passbook.vo.CreateMerchantsRequest;
import com.imooc.passbook.vo.CreateMerchantsResponse;
import com.imooc.passbook.vo.PassTemplate;
import com.imooc.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <h1>商户服务接口实现</h1>
 */
@Slf4j
@Service
public class MerchantsImpl implements IMerchantsServ {

    /** Merchants数据库接口 */
    private final MerchantsDao merchantsDao;

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public MerchantsImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErroMsg(errorCode.getDesc());
        } else {
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Merchants merchants = merchantsDao.findById(id);
        if (null == merchants) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErroMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);
        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.valiData(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErroMsg(errorCode.getDesc());
        }else{
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(Constants.TEMPLATE_TOPIC,Constants.TEMPLATE_TOPIC,passTemplate);
            log.info("DropPassTemplate:{}",passTemplate);
        }
        return response;
    }
}
