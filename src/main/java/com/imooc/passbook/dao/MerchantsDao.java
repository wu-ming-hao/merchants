package com.imooc.passbook.dao;

import com.imooc.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantsDao extends JpaRepository<Merchants, Integer> {


    /**
     * 根据id获取商户对象
     * @param id 商户id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);


    /**
     * 根据名称获取商户对象
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);
}
