package testzip;



import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class main {
    private static String encode="utf-8";

    public static void main(String[] args) throws IOException {

        String str = "ABCDEFG";
        File my = new File("C:\\Users\\PC\\Desktop\\student.sql");
        BufferedReader reader = null;
        String tempString= new String();
        try {
            reader = new BufferedReader(new FileReader(my));

            // 一次读入一行，直到读入null为文件结束
            while (reader.readLine() != null) {

                tempString +=  reader.readLine() +"\n";

            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(tempString.getBytes(encode));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( out.toByteArray());

        byte[] b=out.toByteArray();

        ByteArrayOutputStream out1= new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out1.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( out1.toString());
    }
}

