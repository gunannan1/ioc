package com.icbc.resourcereader.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractResource implements Resource{
    @Override
    public boolean FileExists() {
        // 尝试找到该文件
        try {
            return getFile().exists();
        }
        catch (IOException ex) {
            // 尝试打开流
            try {
                InputStream is = getInputStream();
                is.close();
                return true;
            }
            catch (Throwable isEx) {
                return false;
            }
        }
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + " 无法解析为绝对文件路径");
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
