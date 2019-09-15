package com.icbc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuxudong
 */
public class ScanPackageUtil {

    private ScanPackageUtil(){}

    public static List<String> getClassName(String packageName) {

        String filePath =ScanPackageUtil.class.getResource("/"+packageName.replace(".", "/")).getFile();
        List<String> fileNames = getClassName(filePath, null);
        return fileNames;
    }

    private static List<String> getClassName(String filePath,
                                             List<String> className) {

        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(),
                        myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(
                        childFilePath.indexOf("com\\"),
                        childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }
}
