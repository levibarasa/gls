/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.iso.iso8583;

import java.io.InputStream;
import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

/**
 *
 * @author Administrator
 */
public class Message {
    private static final Logger log = Logger.getLogger("simlogger");
    private String mti;
    private String field2;
    private String field3;
    private String field4;
    private String field11;
    private String field12;
    private String field17;
    private String field24;
    private String field32;
    private String field43;
    private String field49;
    private String field102;
    private String field103;
    private String field123;

    public Message() {

    }

    public void unpackMessage(String isoMsg) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream url = classLoader.getResourceAsStream("com/orig/stock/transaction/basic.xml");
        try {
            GenericPackager packager = new GenericPackager(url);
            ISOMsg m = new ISOMsg();
            m.setPackager(packager);
            m.unpack(isoMsg.getBytes());
            // print the DE list
            logISOMsg(m);
        } catch (Exception asd) {
            log.debug(asd);
        }
    }

    private static void logISOMsg(ISOMsg msg) {
        log.debug("----ISO MESSAGE-----");
        try {
            log.debug("  MTI : " + msg.getMTI());
            for (int i = 0; 1 < msg.getMaxField(); i++) {
                if (msg.hasField(i)) {
                    log.debug("    Field-" + i + " : " + msg.getString(i));
                }
            }
        } catch (ISOException e) {
           log.debug(e);
        } finally {
            log.debug("--------------------");
        }

    }

    /**
     * @return the mti
     */
    public String getMti() {
        return mti;
    }

    /**
     * @param mti the mti to set
     */
    public void setMti(String mti) {
        this.mti = mti;
    }

    /**
     * @return the field2
     */
    public String getField2() {
        return field2;
    }

    /**
     * @param field2 the field2 to set
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

    /**
     * @return the field3
     */
    public String getField3() {
        return field3;
    }

    /**
     * @param field3 the field3 to set
     */
    public void setField3(String field3) {
        this.field3 = field3;
    }

    /**
     * @return the field4
     */
    public String getField4() {
        return field4;
    }

    /**
     * @param field4 the field4 to set
     */
    public void setField4(String field4) {
        this.field4 = field4;
    }

    /**
     * @return the field11
     */
    public String getField11() {
        return field11;
    }

    /**
     * @param field11 the field11 to set
     */
    public void setField11(String field11) {
        this.field11 = field11;
    }

    /**
     * @return the field12
     */
    public String getField12() {
        return field12;
    }

    /**
     * @param field12 the field12 to set
     */
    public void setField12(String field12) {
        this.field12 = field12;
    }

    /**
     * @return the field17
     */
    public String getField17() {
        return field17;
    }

    /**
     * @param field17 the field17 to set
     */
    public void setField17(String field17) {
        this.field17 = field17;
    }

    /**
     * @return the field24
     */
    public String getField24() {
        return field24;
    }

    /**
     * @param field24 the field24 to set
     */
    public void setField24(String field24) {
        this.field24 = field24;
    }

    /**
     * @return the field32
     */
    public String getField32() {
        return field32;
    }

    /**
     * @param field32 the field32 to set
     */
    public void setField32(String field32) {
        this.field32 = field32;
    }

    /**
     * @return the field43
     */
    public String getField43() {
        return field43;
    }

    /**
     * @param field43 the field43 to set
     */
    public void setField43(String field43) {
        this.field43 = field43;
    }

    /**
     * @return the field49
     */
    public String getField49() {
        return field49;
    }

    /**
     * @param field49 the field49 to set
     */
    public void setField49(String field49) {
        this.field49 = field49;
    }

    /**
     * @return the field102
     */
    public String getField102() {
        return field102;
    }

    /**
     * @param field102 the field102 to set
     */
    public void setField102(String field102) {
        this.field102 = field102;
    }

    /**
     * @return the field103
     */
    public String getField103() {
        return field103;
    }

    /**
     * @param field103 the field103 to set
     */
    public void setField103(String field103) {
        this.field103 = field103;
    }

    /**
     * @return the field123
     */
    public String getField123() {
        return field123;
    }

    /**
     * @param field123 the field123 to set
     */
    public void setField123(String field123) {
        this.field123 = field123;
    }
}
