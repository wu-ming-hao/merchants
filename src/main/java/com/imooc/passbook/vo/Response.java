package com.imooc.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /** 错误码，正确返回 0 */
    private Integer errorCode = 0;

    /** 错误信息，正确返回空字符串 */
    private String erroMsg = "";

    /** 返回对象 */
    private Object data ;

    public Response(Object data){
        this.data = data;
    }

}
