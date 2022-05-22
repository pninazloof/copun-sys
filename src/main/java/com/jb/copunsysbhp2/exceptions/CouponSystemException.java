package com.jb.copunsysbhp2.exceptions;


public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getDesc());
    }
}