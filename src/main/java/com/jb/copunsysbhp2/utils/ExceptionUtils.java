package com.jb.copunsysbhp2.utils;


import com.jb.copunsysbhp2.exceptions.CouponSystemException;
import com.jb.copunsysbhp2.exceptions.ErrMsg;

public class ExceptionUtils {


    public static CouponSystemException notFound(){
        return new CouponSystemException(ErrMsg.ID_NOT_FOUND);
    }
}