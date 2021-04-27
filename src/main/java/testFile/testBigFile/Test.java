package testFile.testBigFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String[] OctopartTitles = {
                "manufacturer", "mpn",
                "sku", "distributor-url",
                "quantity", "price-break-1",
                "price-usd-1", "price-eur-1",
                "price-break-2", "price-usd-2",
                "price-eur-2", "description",
                "datasheet-url", "image-url",
                "attributes", "category",
                "moq", "order-multiple",
                "on-order-quantity",
                "on-order-eta", "packaging",
                "factory-lead-days", "factory-pack-quantity", "datecode"};
        Test test=new Test();
        List<String[]> dataListNew=new ArrayList<>();
         List<String[]> dataList=importCsv(new File("D:\\amallDP\\root\\20201113111104\\ADfiles\\Octopart.csv"));
        // List<String> dataList=importCsv(new File("C:\\Users\\PC\\Desktop\\dp\\Octopart.csv"));
        if(dataList!=null && !dataList.isEmpty()){

//            for(int i=0; i<dataList.size();i++ ){
//                if(i!=0){//不读取第一行
//                    String s=dataList.get(i);
//                    String[] as = s.split(",");
//                    System.out.println(as[0]);
//                    dataListNew.add(as);
//                }
//            }
            System.out.println("start");
            File file = new File("C:\\Users\\PC\\Desktop\\dp\\test.csv");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                test.exportCSV(fos,dataList,OctopartTitles);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("end");


        }

    }
    /**
     * 写入
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String[]> importCsv(File file){
        List<String[]> dataList=new ArrayList<>();

        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line.split(","));
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }


    public static void testOutMemory() throws IOException {
        //绝对路径
        String inPath = "D:\\repository.zip";
        String outPath = "E:\\桌面文件\\testfile\\bigFileTest.zip";

        File inFile = new File(inPath);
        File outFile = new File(outPath);
        if (!inFile.exists()) {
            //TODO 文件不存在
        }
        long fileLength = inFile.length();//文件大小，我的测试值为： 1820595407

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) fileLength);//这一行会抛出异常
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile));
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);

        int buf_size = 1024;
        byte[] buffer = new byte[buf_size];
        int len = 0;
        while (-1 != (len = in.read(buffer, 0, buf_size))) {
            bos.write(buffer, 0, len);
        }

        byte[] data = bos.toByteArray();
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
        bos.close();
        System.out.println("测试完成");
    }


    public static void testOutMemory2() throws IOException {
        // 绝对路径
        String inPath = "D:\\repository.zip";
        String outPath = "E:\\桌面文件\\testfile\\bigFileTest.zip";

        File inFile = new File(inPath);
        File outFile = new File(outPath);
        if (!inFile.exists()) {
            // TODO 文件不存在
        }
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile));
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);

        int buf_size = 1024;
        byte[] buffer = new byte[buf_size];
        int len = 0;
        while (-1 != (len = in.read(buffer, 0, buf_size))) {
            fileOutputStream.write(buffer, 0, len);//一次仅传输1K，不会溢出
        }

        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println("测试完成");

    }

    public void exportCSV(OutputStream out, List<String[]> datas, String[] title) {
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            //所有数据整合
            System.out.println(datas.size());
            List<String> dataList = getRows(datas, title);
            System.out.println(dataList.size()+"new");
            osw = new OutputStreamWriter(out, "GB2312");
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.write(data +"\r");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private List<String> getRows(List<String[]> datas, String[] title) {
        List<String> rows = new ArrayList<>();
        if (datas != null) {
            datas.remove(0);
            datas.add(0, title);
        }
        if (datas != null && datas.size() > 0) {
            while (datas.size()>0) {
                String[] strs=datas.get(0);
                if (strs == null) {
                    continue;
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < strs.length; i++) {
                    String value = strs[i] == null ? "" : strs[i];
                    boolean quoteFlag = false;//标记是否添加过双引号
                    if (value.contains("\"")) { //若发现有双引号  需要将字符串中的一个双引号替换为两个 并且需前后加双引号
                        value = value.replaceAll("\"", "\"\"");
                        value = "\"" + value + "\"";
                        quoteFlag = true;
                    }
                    if (value.contains(",") && !quoteFlag) { //若发现有逗号  需前后加引号
                        value = "\"" + value + "\"";
                    }
                    stringBuffer.append(value).append(",");
                }
                rows.add(stringBuffer.toString());
                datas.remove(0);
            }
        }
        return rows;
    }

}