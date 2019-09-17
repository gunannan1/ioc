package com.publicgroup.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.publicgroup.util.log.LogFactory;

/**
 * @author Yuxudong
 */
public class ScanPackageUtil {
	
	private static final Logger LOGGER=LogFactory.getGlobalLog();

    private ScanPackageUtil(){}

    public static List<String> getClassName(String packageName) {

        String filePath =ScanPackageUtil.class.getResource('/'+packageName.replace(".", "/")).getFile();
        return getClassName(filePath, null);

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
               
                if(LOGGER.isLoggable(Level.INFO)) {
                	LOGGER.info(childFilePath);
                }
                
                childFilePath = childFilePath.substring(
                        childFilePath.indexOf("com\\"),
                        childFilePath.lastIndexOf('.'));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }
}
