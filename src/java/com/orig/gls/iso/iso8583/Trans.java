/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.iso.iso8583;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.log4j.Logger;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOResponseListener;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

/**
 *
 * @author Stanley Mungai
 */
public class Trans implements ISOResponseListener {

    private static final Logger log = Logger.getLogger("simlogger");
    ISOChannel channel;
    ISOMsg m;

    public Trans() {
        m = new ISOMsg();
    }
    private String response;

    @Override
    public void responseReceived(ISOMsg isomsg, Object o) {
        log.debug("Response Code Is : " + isomsg.getString(39));
        setResponse(ResponseCodes.getResponse(isomsg.getString(39)));
        if (isomsg.getString(39).equals("000")) {
            //Do Something
            log.debug("Transaction Posted Successfully");
        } else {
            String resp = ResponseCodes.getResponse(isomsg.getString(39));
            System.out.println("Cause of Failed Transaction is : " + resp);
            //Do Something
        }
    }

    public void processTransaction(String supplAccount, double invAmount, String invNumber, String supplName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream url = classLoader.getResourceAsStream("com/orig/stock/transaction/basic.xml");
            GenericPackager packager = new GenericPackager(url);
            m.setMTI("1200");
            m.set(2, "STOCKPOINT AUTO");
            m.set(3, "400000");
            m.set(4, String.valueOf(invAmount).replace(".", "") + "0");
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(1000000);
            String formatedInt;
            formatedInt = String.format("%06d", randomInt);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("YYYYMMddHHmmss");
            m.set(11, formatedInt);
            m.set(12, sdf1.format(new Date()));
            m.set(17, sdf.format(new Date()));
            m.set(24, "200");
            m.set(32, "000057");
            m.set(43, "STOCKPOINT/" + invNumber + "/" + supplName.substring(0, 3));
            m.set(49, "KES");
            // m.set(102, "57              001  " + "001011000126");//000170010000057
            m.set(102, "57              000  " + "000170010000057");
            m.set(103, "57              000  " + supplAccount);
            m.set(123, "CMN");
            channel = new ASCIIChannel("192.168.1.80", 40221, packager);//fin7
            if (!channel.isConnected()) {
                channel.connect();
                log.debug("Connected");
            }
            channel.send(m);
            log.debug("Sent");
            byte[] b = m.pack();
            log.debug("RESULT : " + new String(b));
            log.debug("Packed");
            ISOMsg msg = channel.receive();
            log.debug("Received");
            if (msg != null) {
            }
            responseReceived(msg, m);
        } catch (ISOException | IOException asd) {
            log.debug(asd);
        }
    }

    @Override
    public void expired(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

}
