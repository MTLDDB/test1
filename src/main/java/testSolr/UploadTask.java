//package testSolr;
//
//
//import Task;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//import org.springframework.beans.factory.annotation.Autowired;
//import testSolr.Dao.DBhelper;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class UploadTask {
//    private final String SOLR_URL = "http://localhost:8983/solr/";
//    HttpSolrClient solrServer;
//    HttpSolrClient solrServer_pro;
//    ExecutorService executorService;
//    private final static AtomicInteger count = new AtomicInteger(0);
//
//    {
//        solrServer = new HttpSolrClient(SOLR_URL + "spiderTask");
//        solrServer_pro = new HttpSolrClient(SOLR_URL + "spiderPro");
//        executorService = Executors.newCachedThreadPool();
//    }
//
//    public void addDoc() throws SolrServerException, IOException {
//        List<SolrInputDocument> documents = new ArrayList<>();
//        List<Task> taskList = new ArrayList<>();
//        int count = 0;
//        String objectid = "0";
//        while (true) {
//            System.out.println("test");
//            taskList = DBhelper.getTask(objectid, 3000);
//            if (taskList.size() == 0) break;
//            objectid = taskList.get(taskList.size() - 1).getObjectid();
//            for (Task task : taskList) {
//                SolrInputDocument document = new SolrInputDocument();
//                document.addField("id", task.getObjectid());
//                document.addField("url", task.getUrl());
//                document.addField("status", task.getStatus());
//                documents.add(document);
//                count++;
//            }
//            solrServer.add(documents);
//            solrServer.commit();
//            documents.clear();
//            System.out.println("upload data " + count + " 条");
//        }
//        // solrServer.close();
//    }
//
//    public void deal(List<Task> taskList) {
//        int len = taskList.size();
//        int runSize = 0;
//        if (len < 50)
//            runSize = 1;
//        else
//            runSize = len / 50;
//        int listSize = len / runSize + 1;
//        for (int i = 0; i < runSize; i++) {
//            final List<Task> newList;
//            if ((i + 1) == runSize) {
//                int startIndex = (i * listSize);
//                int endIndex = len;
//                newList = taskList.subList(startIndex, endIndex);
//            } else {
//                int startIndex = i * runSize;
//                int endIndex = (i + 1) * runSize - 1;
//                newList = taskList.subList(startIndex, endIndex);
//            }
//            executorService.execute(new Thread() {
//                @Autowired
//                public void run() {
//                    List<String> ids = new ArrayList<>();
//                    for (Task task : newList) {
//                        SolrQuery query = new SolrQuery();
//                        try {
//                            if (task.getUrl().contains("/products/")) {
//                                ids.add(task.getId());
//                            } else {
//                                // String id= UUID.nameUUIDFromBytes(task.getUrl().getBytes()).toString().replace("-","");
//                                List<String> stringList = Arrays.asList(task.getUrl().toString().trim().split("/"));
//                                int len = stringList.size();
//                                String id = stringList.get(len - 2);
//                                query.set("q", "id:" + id.replace("[", "%5B").replace("]", "%5D"));
//
//                                QueryResponse response_pro = solrServer_pro.query(query);
//                                SolrDocumentList solrDocumentList_pro = response_pro.getResults();
//                                if (solrDocumentList_pro.size() == 0) {
//                                    continue;
//                                } else {
//                                    ids.add(task.getId());
//                                    count.addAndGet(1);
//                                }
//                            }
//                        } catch (SolrServerException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    try {
//                        if (ids.size() > 0)
//                            solrServer.deleteById(ids);
//                    } catch (SolrServerException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//    }
//
//    public void getTask() {//from database
//        List<Task> taskList = new ArrayList<>();
//        String objectid = "0";
//        int num = 0;
//        while (true) {
//            taskList = DBhelper.getTask(objectid, 3000);
//            //System.out.println(JSON.toJSONString(taskList));
//            if (taskList.size() == 0) break;
//            objectid = taskList.get(taskList.size() - 1).getObjectid();
//            int len = taskList.size();
//            int runSize = 20;
//            int listSize = len / runSize + 1;
//            for (int i = 0; i < runSize; i++) {
//                final List<Task> newList;
//                if ((i + 1) == runSize) {
//                    int startIndex = (i * listSize);
//                    int endIndex = len;
//                    newList = taskList.subList(startIndex, endIndex);
//                } else {
//                    int startIndex = i * runSize;
//                    int endIndex = (i + 1) * runSize - 1;
//                    newList = taskList.subList(startIndex, endIndex);
//                }
//                executorService.execute(new Thread() {
//                    @Autowired
//                    public void run() {
//                        for (Task task : newList) {
//                            String objectid = task.getObjectid();
//                            try {
//                                SolrQuery query = new SolrQuery();
//                                query.set("q", "id:" + objectid);
//                                QueryResponse response = solrServer.query(query);
//                                //两种结果获取：得到文档集合或者实体对象
//                                //查询得到文档的集合
//                                SolrDocumentList solrDocumentList = response.getResults();
//                                if (solrDocumentList.size() == 0) {
//                                    // System.out.println("test");
//                                    continue;
//                                } else {
//                                    solrServer.deleteById(objectid);
//                                    solrServer.commit();
//                                    //  count.addAndGet(1);
//                                }
//                                //System.out.println(objectid+"被删除");
//                            } catch (SolrServerException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//
//                            }
//                        }
//                    }
//                });
//            }
//            // System.out.println(count.get() + "条任务被删除");
//            // break;
//        }
//    }
//
//    public void queryTaskDoc() {//from solr
//        int start = 0;
//        int count1 = 0;
//        while (true) {
//            try {
//                SolrQuery query = new SolrQuery();
//                query.set("q", "id:*");
//                query.setStart(start);
//                query.setRows(1000);//每一页多少值
//                QueryResponse response = solrServer.query(query);
//                //两种结果获取：得到文档集合或者实体对象
//                //查询得到文档的集合
//                List<Task> taskList = response.getBeans(Task.class);
//                count1 = count1 + taskList.size();
//                System.out.println(start);
//                if (taskList.size() == 0) System.exit(0);
//                deal(taskList);
//                start = start + 1000 ;//- count.get();
//                count.set(0);
////                for(Task task:taskList){
////                    if(task.getUrl().contains("/products/")){
////                        solrServer.deleteById(task.getId());
////                        solrServer.commit();
////                    }
////                    String id= UUID.nameUUIDFromBytes(task.getUrl().getBytes()).toString().replace("-","");
////                    query.set("q", "id:"+id);
////                    QueryResponse response_pro=solrServer_pro.query(query);
////                    SolrDocumentList solrDocumentList_pro = response_pro.getResults();
////                    if (solrDocumentList_pro.size() == 0) {
////                        continue;
////                    } else {
////                        solrServer.deleteById(task.getId());
////                        solrServer.commit();
////                        //count.addAndGet(1);
////                    }
////                }
//                //System.out.println(objectid+"被删除");
//            } catch (SolrServerException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//            }
//        }
//
//    }
//
//}
