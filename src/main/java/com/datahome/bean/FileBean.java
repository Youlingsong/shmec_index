package com.datahome.bean;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/1/22 10:05
 */
public class FileBean {

    private String fileName;

    private String filePath;

    private String dir;

    private String order;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
