package com.kalends.filemanager.util;

import com.kalends.filemanager.pojo.FileInfo;

import java.util.Comparator;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class AllSort implements Comparator<FileInfo> {

    public int type ;

    public AllSort(int type){
        this.type = type;
    }

    @Override
    public int compare(FileInfo file1, FileInfo file2) {
        //0：按字母排序 1：按日期排序 2：按大小排序
        if (type == 1){
           return dateSort(file1,file2);
        }else if (type == 2){
           return  sizeSort(file1,file2);
        }else{
           return nameSort(file1,file2);
        }
    }
    //按大小排序
    public int sizeSort(FileInfo file1, FileInfo file2){
        if (file1.isDir() && file2.isDir()){
            return file1.getSize() > file2.getSize() ? 1:0;
        }else if (file1.isDir() && file2.isFile()){
            return -1;
        }else if (file1.isFile() && file2.isDir()){
            return 1;
        }else{
            return file1.getSize() > file2.getSize() ? 1:0;
        }
    }


    //按日期排序
    public int dateSort(FileInfo file1, FileInfo file2){
        if (file1.isDir() && file2.isDir()){
            return file1.getTime().before(file2.getTime()) ? 1:-1;
        }else if (file1.isDir() && file2.isFile()){
            return -1;
        }else if (file1.isFile() && file2.isDir()){
            return 1;
        }else{
            return file1.getTime().before(file2.getTime()) ? 1:-1;
        }
    }


    //按名称排序
    public int nameSort(FileInfo file1, FileInfo file2){
        if (file1.isDir() && file2.isDir()){
            return file1.getName().compareToIgnoreCase(file2.getName());
        }else if (file1.isDir() && file2.isFile()){
            return -1;
        }else if (file1.isFile() && file2.isDir()){
            return 1;
        }else{
            return file1.getName().compareToIgnoreCase(file2.getName());
        }
    }
}
