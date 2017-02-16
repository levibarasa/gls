/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.sms;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Stanley Mungai
 */
public class SendSms {

    public SendSms() {

    }

    public static void sendResponseSms(String phoneNumber, String message) {
        SMSGateway gateway = new SMSGateway("gatungo1234", "9233ec070b07ba8d4758c30543c32fd545d178e2f127d6e4b1a3db44191bd631");
        try {
            JSONArray results = gateway.sendMessage(phoneNumber, message);

            for (int i = 0; i < results.length(); ++i) {
                JSONObject result = results.getJSONObject(i);
                String[] costs = result.getString("cost").split(" ");
                Double cost;
                String cst = "";
                for (String cost1 : costs) {
                    cst = costs[1];
                }
                cost = Double.parseDouble(cst);
            }
        } catch (Exception e) {
            System.out.println("Encountered an error while sending " + e.getMessage());
        }
    }
}
