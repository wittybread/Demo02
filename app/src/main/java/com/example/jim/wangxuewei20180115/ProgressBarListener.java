package com.example.jim.wangxuewei20180115;

/**
 * Created by jim on 2018/1/15.
 */

public interface ProgressBarListener {
    /**
     * 获取文件的长度
     */
    void getMax(int length);

    /**
     * 获取每次下载的长度
     */
    void getDownload(int length);
}
