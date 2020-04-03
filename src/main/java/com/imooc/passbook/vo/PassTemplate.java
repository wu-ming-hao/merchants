package com.imooc.passbook.vo;

import com.imooc.passbook.constant.ErrorCode;
import com.imooc.passbook.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplate {

    /**所属商户id*/
    private Integer id;

    /** 优惠卷标题 */
    private String title;

    /** 优惠卷摘要 */
    private String summary;

    /** 优惠卷的详细信息 */
    private String desc;

    /** 最大个数限制 */
    private Long limit;

    /**优惠卷是否有token，用于商户核销*/
    private Boolean hasToken; //token 存储于 Redis Set中，每次领取从Redis中获取

    /** 优惠卷背景颜色 */
    private Integer background;

    /** 优惠卷开始时间 */
    private Date start;

    /** 优惠卷结束时间 */
    private Date end;

    /** 效验优惠卷对象的可用性
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode valiData(MerchantsDao merchantsDao){
        if(null == merchantsDao){
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }

}
