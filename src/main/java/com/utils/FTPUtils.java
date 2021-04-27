package com.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class FTPUtils {

    //用于打印日志
    private static final Logger log = LoggerFactory.getLogger(FTPUtils.class);


    /**
     * 上传
     *
     * @param workingPath 服务器的工作目
     * @param inputStream 要上传文件的输入流
     * @param saveName    设置上传之后的文件名
     * @return
     */
    public static boolean upload(FTPClient ftpClient,String workingPath, InputStream inputStream, String saveName) {
        boolean flag = false;
        //1 测试连接
        if (ftpClient!=null) {
            try {
                //2 检查工作目录是否存在
               // ftpClient.setControlEncoding("UTF-8");
                workingPath=new String(workingPath.getBytes(),"ISO-8859-1");
                if (ftpClient.changeWorkingDirectory(workingPath)||ftpClient.makeDirectory(workingPath)) {
                    System.out.println(JSON.toJSONString(ftpClient.listFiles()));
                    ftpClient.changeWorkingDirectory(workingPath);
                    // 3 检查是否上传成功
                    //需要将VM options设置为-Dfile.encoding=utf-8
                    saveName=new String(saveName.getBytes(),"ISO-8859-1");// 转换后的目录名或文件名。
                    if (storeFile(ftpClient, saveName, inputStream)) {
                        flag = true;
                    }
                }
            } catch (IOException e) {
                log.error("工作目录不存在");
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 测试是否能连接
     *
     * @param hostname  ip或域名地址
     * @param port      端口
     * @param username  用户名
     * @param password  密码
     * @return 返回真则能连接
     */
    public static FTPClient connect( String hostname, int port, String username, String password) {
        boolean flag = false;
        FTPClient ftpClient=new FTPClient();
        try {
            //ftp初始化的一些参数
            ftpClient.connect(hostname, port);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            if (ftpClient.login(username, password)) {
                log.info("连接ftp成功");
                flag = true;
            } else {
                log.error("连接ftp失败，可能用户名或密码错误");
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            log.error("连接失败，可能ip或端口错误");
            e.printStackTrace();
        }
        return flag?ftpClient:null;
    }
    /**
     * 断开连接
     *
     * @param ftpClient
     * @throws Exception
     */
    public static void disconnect(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
                log.info("已关闭连接");
            } catch (IOException e) {
                log.error("没有关闭连接");
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件
     *
     * @param ftpClient
     * @param saveName        全路径。如/home/public/a.txt
     * @param fileInputStream 要上传的文件流
     * @return
     */
    public static boolean storeFile(FTPClient ftpClient, String saveName, InputStream fileInputStream) {
        boolean flag = false;
        try {
            if (ftpClient.storeFile(saveName, fileInputStream)) {
                flag = true;
                log.info("上传成功");
            }
        } catch (IOException e) {
            log.error("上传失败");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param ftpClient
     * @param pathName       全路径。如/home/public/a.txt
     * @return
     */
    public static boolean deleteFile(FTPClient ftpClient, String pathName) {
        boolean flag = false;
        try {
            if (ftpClient.deleteFile(pathName)) {
                flag = true;
                log.info("删除成功");
            }
        } catch (IOException e) {
            log.error("删除失败");
            e.printStackTrace();
        }
        return flag;
    }
}

