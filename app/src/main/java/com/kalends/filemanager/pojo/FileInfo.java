package com.kalends.filemanager.pojo;

import java.io.File;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class FileInfo {

    private String name ;
    private int imageId;

    private Long size;

    private Date time;

    private Boolean isDir;

    private Boolean isFile;

    public FileInfo(File file, int imageId){
        this.name = file.getName();
        this.imageId = imageId;
        this.size = file.length();
        this.time = new Date(file.lastModified());
        this.isDir = file.isDirectory();
        this.isFile = file.isFile();
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public Date getTime() {
        return time;
    }

    public Boolean isDir() {
        return isDir;
    }

    public Boolean isFile() {
        return isFile;
    }
}
