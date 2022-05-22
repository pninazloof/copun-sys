package com.jb.copunsysbhp2.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    COMPANY_EMAIL_EXIST("email is already exist"),
    COMPANY_NAME_EXIST("name is already exist"),
    COMPANY_NAME_EXIST_CANNOT_UPDATE("It is not possible to update a company with an existing name"),
    COMPANY_NOT_EXIST("Company not found"),
    CUSTOMER_EMAIL_EXIST("Unable to add a customer with an existing email"),
    CUSTOMER_EXIST("It is not possible to update a customer with an existing id"),
    ID_NOT_FOUND("id not found..."),
    COMPANY_EXIST_SAME_TITLE("It is not possible to add a coupon with an existing title"),
    COUPOM_NOT_EXIST("Coupon not found"),
    CANNOT_CHANGE_COMPANYID_FOT_COUPON("cannot update companyID for coupon"),
    CUSTOMER_PURCHASE_COUPON("You are not authorized to purchase a coupon because you have already purchased it"),
    AMOUNT_COUPON_IS_ZERO("The quantity of coupons in stock is equal to 0"),
    COUPON_EXPIRED("The coupon has expired");


    private String desc;

    ErrMsg(String desc) {
        this.desc = desc;
    }


}
