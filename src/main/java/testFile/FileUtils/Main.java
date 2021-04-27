package testFile.FileUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        read();
    }

    public void write() throws IOException {
        /* 写文件
         * 1.这里只列出3种方式全参数形式，api提供部分参数的方法重载
         * 2.最后一个布尔参数都是是否是追加模式
         * 3.如果目标文件不存在，FileUtils会自动创建
         * */
        //static void:write(File file, CharSequence data, String encoding, boolean append)
        FileUtils.write(new File("D:/a/b/cxyapi.txt"), "程序换api", "UTF-8", true);

        //static void:writeLines(File file, Collection<?> lines, boolean append)
        List<String> lines = new ArrayList<String>();
        lines.add("欢迎访问:");
        lines.add("www.cxyapi.com");
        FileUtils.writeLines(new File("D:/a/b/cxyapi.txt"), lines, true);

        //static void:writeStringToFile(File file, String data, String encoding, boolean append)
        FileUtils.writeStringToFile(new File("D:/a/b/cxyapi.txt"), "作者：cxy", "UTF-8", true);

    }

    public static void read() throws IOException {
        //读文件
        //static String:readFileToString(File file, String encoding)
        System.out.println(FileUtils.readFileToString(new File("D:/a/b/cxyapi.txt"), "UTF-8"));

        //static List<String>:readLines(File file, String encoding)
        System.out.println(FileUtils.readLines(new File("D:/a/b/cxyapi.txt"), "UTF-8")); //返回一个list
    }

    public static void delete() throws IOException {
        //删除目录
        //static void:deleteDirectory(File directory)
        FileUtils.deleteDirectory(new File("D:/not/cxyapi"));

        //static boolean:deleteQuietly(File file)
        FileUtils.deleteQuietly(new File("D:/not/cxyapi")); //文件夹不是空任然可以被删除，永远不会抛出异常
    }

    public static void remove() throws IOException {
        //移动文件 或 文件夹
        //static void：moveDirectory(File srcDir, File destDir)
        FileUtils.moveDirectory(new File("D:/cxyapi1"), new File("D:/cxyapi2")); //注意这里 第二个参数文件不存在会引发异常
        //static void:moveDirectoryToDirectory(File src, File destDir, boolean createDestDir)
        FileUtils.moveDirectoryToDirectory(new File("D:/cxyapi2"), new File("D:/cxyapi3"), true);
        /* 上面两个方法的不同是：
         * moveDirectory：D:/cxyapi2里的内容是D:/cxyapi1的内容。
         * moveDirectoryToDirectory：D:/cxyapi2文件夹移动到到D:/cxyapi3里
         *
         * 下面的3个都比较简单没提供示例，只提供了api
         * 其中moveToDirectory和其他的区别是 它能自动识别操作文件还是文件夹
         */
        //static void:moveFileToDirectory(srcFile, destDir, createDestDir)
        //static void:moveFile(File srcFile, File destFile)
        //static void:moveToDirectory(File src, File destDir, boolean createDestDir)
    }
}

