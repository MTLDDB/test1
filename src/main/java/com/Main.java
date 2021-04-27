package com;

import com.entity.TSeriesEntity;
import com.utils.C3P0Util;
import com.utils.FTPTools;
import com.utils.FTPUtils;
import com.utils.JudgeEncode;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {





//    public static void main(String[] args) {
//        try {
//            String name="新建文本文档.txt";
//            byte[] nameByt=name.getBytes();
//            System.out.println("+++++++++++++++++++++++");
//            for (byte b : nameByt) {
//                System.out.println(b);
//            }
//            System.out.println("+++++++++++++++++++++++");
//            System.out.println(JudgeEncode.getEncoding(name));
//            System.out.println(new String("新建文本文档.txt".getBytes(),"ISO-8859-1"));
//            maintest();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws FileNotFoundException ,Exception{
        String hostname = "115.159.40.140";
        int port = 21;
        String username = "ftpuser";
        String password = "huang0724";
        String workingPath = "/特斯特";
        String str = "C:\\Users\\PC\\Desktop\\新建文本文档.txt";
        File file=new File(str);
        InputStream fileInputStream = new FileInputStream(file);
        String saveName = file.getName();//"新建文本文档.txt";
       // saveName=new String(saveName.getBytes(),"ISO-8859-1");// 转换后的目录名或文件名。
        System.out.println(saveName);
        FTPClient ftpClient = FTPUtils.connect(hostname, port, username, password);
      //  FTPUtils.deleteFile(ftpClient,"task.txt");
        if(ftpClient!=null){
            System.out.println(FTPUtils.upload(ftpClient,workingPath, fileInputStream, saveName));
        }
        FTPUtils.disconnect(ftpClient);
       // System.out.println(FTPTools.upload( hostname, port, username, password, workingPath, fileInputStream, saveName));
    }

    public static void maintest() throws FileNotFoundException ,Exception{
        String hostname = "115.159.40.140";
        int port = 21;
        String username = "ftpuser";
        String password = "huang0724";
        String workingPath = "/test222";
        String str = "C:\\Users\\PC\\Desktop\\新建文本文档.txt";
        File file=new File(str);
        InputStream fileInputStream = new FileInputStream(file);
        String saveName = file.getName();//"新建文本文档.txt";
        System.out.println(saveName);
        FTPClient ftpClient = FTPUtils.connect(hostname, port, username, password);
      //  FTPUtils.deleteFile(ftpClient,"task.txt");
        if(ftpClient!=null){
            System.out.println(FTPUtils.upload(ftpClient,workingPath, fileInputStream, saveName));
        }
        FTPUtils.disconnect(ftpClient);
       // System.out.println(FTPTools.upload( hostname, port, username, password, workingPath, fileInputStream, saveName));
    }
}
