//package testSolr;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//import testSolr.Dao.DBhelper;
//import textACK.Person;
//
//
//public class SolrTest {
//    //指定solr服务器的地址
//    private final static String SOLR_URL = "http://localhost:8811/solr/";
//
//    /**
//     * 创建SolrServer对象
//     *
//     * 该对象有两个可以使用，都是线程安全的
//     * 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的
//     * 2、 EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了
//     * 3、solr 4.0之后好像添加了不少东西，其中CommonsHttpSolrServer这个类改名为HttpSolrClient
//     *
//     * @return
//     */
////    public HttpSolrClient createSolrServer(){
////        HttpSolrClient solr = null;
////        solr = new HttpSolrClient(SOLR_URL);
////        return solr;
////    }
//
//
//    /**
//     * 往索引库添加文档
//     * @throws IOException
//     * @throws SolrServerException
//     */
//    public void addDoc() throws SolrServerException, IOException{
//        //构造一篇文档
//        List<Person> personList= DBhelper.getPerson();
//        for(Person person:personList){
//            SolrInputDocument document = new SolrInputDocument();
//            //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
//            HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL + "collection1");
//           // HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//
//            document.addField("id", person.getId());
//            document.addField("name", person.getName());
//            document.addField("description", person.getDescription());
//            //获得一个solr服务端的请求，去提交  ,选择具体的某一个solr core
//            solrServer.add(document);
//            solrServer.commit();
////            solrServer.optimize();
////            solrServer.shutdown();
//            solrServer.close();
//        }
//    }
//    /**
//     * 往索引库添加文档
//     * @throws IOException
//     * @throws SolrServerException
//     */
//    public void addDocOne() throws SolrServerException, IOException{
//
//            SolrInputDocument document = new SolrInputDocument();
//            //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
//            HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL + "new_core");
//       // HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//
//        document.addField("id", "100");
//            document.addField("name", "test");
//            document.addField("description", "test");
//            //获得一个solr服务端的请求，去提交  ,选择具体的某一个solr core
//        solrServer.add(document);
//        solrServer.commit();
//        solrServer.close();
////        solrServer.optimize();
//      //  solrServer.shutdown();
//            System.out.println("test");
//    }
//    /**
//     * 根据id从索引库删除文档
//     */
//    public void deleteDocumentById() throws Exception {
//        //选择具体的某一个solr core
//        HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL+"collection1");
//       // HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//
//        //删除文档
//        solrServer.deleteById("1");
//        solrServer.deleteById("7");
//        solrServer.deleteById("8");
//        solrServer.deleteById("9");
//        solrServer.deleteById("10");
//        solrServer.deleteById("11");
//        solrServer.deleteById("12");
//       // solrServer.deleteById("12345");
//        //删除所有的索引
//        //solr.deleteByQuery("*:*");
//        //提交修改
//        solrServer.commit();
//        solrServer.close();
////        solrServer.optimize();
////        solrServer.shutdown();
//    }
//
//    /**
//     * 查询
//     * @throws Exception
//     */
//    public void querySolr() throws Exception{
//        HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL+"spiderPro");
//
//       // HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//
//        SolrQuery query = new SolrQuery();
//        //下面设置solr查询参数
//        String webmpn="MR052A100JAATR1-ND";
//        query.set("q", "id:"+webmpn);// 参数q  查询所有
//       // query.set("id","000036db94e53cae88b413c191f37d29");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询
//
//        //参数fq, 给query增加过滤查询条件
//       // query.addFilterQuery("id:[0 TO 9]");//id为0-4
//
//        //给query增加布尔过滤条件
//        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据
//
//        //参数df,给query设置默认搜索域
//        //query.set("df", "description");
//
//        //参数sort,设置返回结果的排序规则
//        //query.setSort("id",SolrQuery.ORDER.desc);
//
//        //设置分页参数
////        query.setStart(0);
////        query.setRows(10);//每一页多少值
//
//        //参数hl,设置高亮
//       // query.setHighlight(true);
//        //设置高亮的字段
//       // query.addHighlightField("name");
//        //设置高亮的样式
////        query.setHighlightSimplePre("<font color='red'>");
////        query.setHighlightSimplePost("</font>");
//
//        //获取查询结果
//        QueryResponse response = solrServer.query(query);
//        //两种结果获取：得到文档集合或者实体对象
//
//        //查询得到文档的集合
//        SolrDocumentList solrDocumentList = response.getResults();
//        System.out.println("通过文档集合获取查询的结果"+solrDocumentList.size());
//        System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
//        //遍历列表
//        for (SolrDocument doc : solrDocumentList) {
//            System.out.println("id:"+doc.get("id")+"  webmpn:"+doc.get("webmpn"));
//        }
//
//        //得到实体对象
////        List<Person> tmpLists = response.getBeans(Person.class);
////        if(tmpLists!=null && tmpLists.size()>0){
////            System.out.println("通过文档集合获取查询的结果");
////            for(Person per:tmpLists){
////                System.out.println("id:"+per.getId()+"   name:"+per.getName()+"    description:"+per.getDescription());
////            }
////        }
//    }
//
//    public void testSubmit(){
//        //HttpSolrServer solrServer = new HttpSolrServer(SOLR_URL+"test");//http://127.0.0.1:8081/amall3-solr/collection1
//        HttpSolrClient solrServer = new HttpSolrClient(SOLR_URL+"test");
//        SolrInputDocument doc = new SolrInputDocument();
//        doc.addField("id", 2);
//        doc.addField("proid", "objectid");
////
////        doc.addField("mpn", "mpn".trim().replace("&", "_").replace(",", "_").replace("+", "_").replace("/", "_").replace("#", "_").replace(" ", "_").replace(".", "_"));
////        doc.addField("categoryid", "categoryid".replace("&", "_").replace(",", "_").replace("+", "_").replace("/", "_").replace("#", "_").replace(" ", "_").replace(".", "_"));
////
////        doc.addField("categoryname", "categoryname".replace("&", "_").replace(",", "_").replace("+", "_").replace("/", "_").replace("#", "_").replace(" ", "_").replace(".", "_"));
//
//        doc.addField("attrvalue", "te".toString().replace("&", "_").replace(",", "_").replace("+", "_").replace("/", "_").replace("#", "_").replace(" ", "_").replace(".", "_"));
//        doc.addField("ameyastock", "tes");
//        doc.addField("ameyamoq", "test");
//        doc.addField("ameyaprice", "tes");
//        doc.addField("supplierstock", "tes");
//        doc.addField("suppliermoq", "tes");
//        doc.addField("supplierprice", "tes");
//        try {
//            solrServer.add(doc);
//            solrServer.commit();
//            solrServer.close();
////            solrServer.optimize();
////            solrServer.shutdown();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void main(String[] args) throws Exception {
//        SolrTest solr = new SolrTest();
//        //solr.createSolrServer();
//       // solr.addDoc();
//       /// solr.addDocOne();
//       //// solr.deleteDocumentById();
//        //solr.querySolr();
//       // solr.testSubmit();
//       // new UploadTask().addDoc();
//        //new UploadTask().getTask();
//        new UploadTask().queryTaskDoc();
//    }
//}
