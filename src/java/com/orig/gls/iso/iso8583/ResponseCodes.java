/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.iso.iso8583;

/**
 *
 * @author Administrator
 */
public class ResponseCodes {

    public String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static String getResponse(String where) {

        if ("111".equals(where)) {
            return "Invalid Scheme Type";
        } else if ("114".equals(where)) {
            return "Invalid Account Number";
        } else if ("115".equals(where)) {
            return "Requested Function not Supported";
        } else if ("121".equals(where)) {
            return "Withdrawal Amount Exceeded";
        } else if ("913".equals(where)) {
            return "Duplicate Transmission";
        } else if ("904".equals(where)) {
            return "Format Error";
        } else if ("902".equals(where)) {
            return "Invalid Transaction";
        } else if ("119".equals(where)) {
            return "Transaction Not Permitted";
        } else if ("116".equals(where)) {
            return "Insufficient Funds";
        } else if ("188".equals(where)) {
            return "Invalid Rate / Currency Combination";
        } else if ("185".equals(where)) {
            return "Invalid Currency / Transaction Amount";
        } else if ("184".equals(where)) {
            return "Account is Closed/Frozen";
        } else if ("180".equals(where)) {
            return "Transfer Limit Exceeded";
        } else if ("907".equals(where)) {
            return "cbc services down";
        } else if ("909".equals(where)) {
            return "System Malfunction";
        } else if ("911".equals(where)) {
            return "Card Issuer/ cbc service timed out";
        } else if ("000".equals(where)) {
            return "Transaction Posted to Core Banking System Successfully";
        } else {
            return "The Response Code Is : " + where;
        }

    }
}
