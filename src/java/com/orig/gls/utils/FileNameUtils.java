/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.utils;

import java.io.File;

/**
 *
 * @author Stanley Mungai
 */
public class FileNameUtils {

    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    private static String getFilenameWithoutExtension(String filename) {
        String[] fileparts = filename.split(getFileExtension(filename));
        String file = "";
        for (String filepart : fileparts) {
            file = fileparts[0];
        }
        return file;
    }

    public static String getNewFileName(String filename) {
        File aFile = new File(filename);
        int fileNo = 0;
        String newName = "";
        while (aFile.exists() && !aFile.isDirectory()) {
            fileNo++;
            newName = filename.replaceAll(getFileExtension(filename), "(" + fileNo + ")" + getFileExtension(filename));
        }
        return newName;
    }
}
