package testSolr;

import com.alibaba.fastjson.JSONObject;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 商城solr更新以及新增逻辑
 */
@Component
public class SolrJAmallClient {
    /**
     * 更新product SOlR
     * @throws SQLException
     */
    public   void UpdateProductDocument(String objectid,String mpn) throws SQLException {

        //连接服务器
        String url = "http://127.0.0.1:8811/solr/core0";
        HttpSolrServer server = new HttpSolrServer(url);
        //创建document
        SolrInputDocument doc = new SolrInputDocument();
        try {
            doc.addField("id",objectid);
            doc.addField("mpn",mpn.trim().replace("&", "_").replace(",", "_").replace("+", "_").replace("/", "_").replace("#", "_").replace(" ", "_").replace(".", "_").replace("-","_"));
            server.add(doc);
            server.commit();
            //下面两个速度很慢
            //server.optimize();
            //server.shutdown();
            System.out.println("商城Solr更新或者新增成功，mpn是："+mpn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("商城Solr更新或新增失败，mpn是:"+mpn+" -----"+e.toString());
        }
    }
}
