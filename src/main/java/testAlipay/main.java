package testAlipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.BaseClient.Config;


public class main {
    private static String SERVER_URL="https://openapi.alipaydev.com/gateway.do";
    private static String APP_ID = "2016102400752339";
    private static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQ8zA6xdiqLOCUK2sP3i3x1d4Buda0SMaWHLexlou5jPBaAK5U9NRofiq31XL3vA/qr2h3Rxhm8gTbVJWgXLON4D6amsaJ12MRNe0WFEficKNuBHGtcUohBXcEPswgW4c5pZ5/9RxN6YK3GmLOWB2gh+ytzJlofHUIq+lBdS3OrhRK4nCAy8sZwKuffAmL0mYX9fTB8aBnQH9SsLk1zst9B8eePOly6TPHKGzJk/D/HVSS33VBiNcoiINu8V8zg9ghKbLpO+0aMf4vXgZviXGw4CX8IOMbvhVg8XwAiaIFBz2o9E4UT7KZB8FZ9ILBJDIYBwNJnt2iZHDtEHbmZUuHAgMBAAECggEBAILmrCqXm8hrSNFmuIQXAOUCYECsxKIBix3rh1TggQSrrC+Lp+RfSiensQpdwDrL4o/touj9EqN9czdkFtBAh4Lb/Y1/s0audfAIBDlJTXXIhT8I0mRxCCH0Hnx9/bhHEELWN+KUx9/wvrR/JrX0S6U6u1pliDEdHFAdeEQ9/4VCwl1TAAjP8LcEXKfuu2Av1gaPxdC1afS9U/cFazj6l/SrdDfVyXRGatx97OgyAHHV/Wuozvtcl/peRRU2T3286+Wo+CaMzWBst04qoXLY4cp0u5uublZTpmSD3Xjn7zp7jJiRgmASpg29nDnVUgbPpVkrVb3Di8ifg8MCeGw+PfECgYEAxLVJUT4+q40cnFbUgjGJ/1JPQczDZSlNNrySTmUsIuLQMyq3OLeqrigJW/989a1PXWl85EucED/1CYgJTVWUx+jAahBwAmGEIEPCDlMF3pU47I0s2v04CkYREkIHoq4CzMNuJw4JX7IMvRsEz9S6nZ4dQUdAGEx22W0qdD+aJMkCgYEAvKQNVxxQjVZelhI/Yl3JFguHef6DjCX4DkYeVp0RioFHf3enKd1TJAGBA1QDzM8bE/UetwYLEzjYnbbhMq8F2wc0s4SkMUomu7cmnRFf5DieSI6/znd9KQGC4Ds8+eI9mMWpm6/uOR7/BaZR9ckHDyeTZrZRxUUhlgSeYSispc8CgYAmkCO/eaRvnUpum9ctbujO1SQUkj/8JQyuHmnQVKVCcZS/czBYPimH2vF6x6uHvmJAvd1uJCSESwWOyMyKDK0OQ+L06CQNU3DDooT4qe4MpNR0C3kvtVS3LuVJ3wgALH0AB5Agt+++gvlcWV+oHtw26jhyA7XdDaB/H5ejEpT1gQKBgQCGnFJgrPXWRaGtWUwdt2r87jE4nqPkaFvAY8zWZbLUsTgB/7Irr6HaebBEXblwdwGNlkbat9dDWjdJI+1DK530FApJaAd6nL1TCnjlcDaLNtPMVwEMJjwL/OYVeHAkqIrn73T+geZuoG2+7FZkG5WL/OO0Ubm8GX7D/HvAPTQmBQKBgAIxUet//ofcFm9ooQg7k7s1OUxE0isOCQgxSQsMSKkgQ04hs2Q/2qWErlzhuDYpvv6dp9dk+5LgiOszVtXOAXiQ/EblygYcTdDn7StXOmAjnqSnoPbvi9yrjAYLh0SwbNGSWM7MFmGtSXkLK+Jcm7BYTJ5AegX6Zo5H4ovlMi4y";
    private static String FORMAT = "json";
    private static String CHARSET = "utf-8";
    private static String SIGN_TYPE = "RSA2";
    private static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvopwIgUQ9IexTueEZsXbou3yDN8EiZTntFWQ1h4xJ0HQSD9q2VwFZ74C3A5JJDGxhImw36+ieOUWtdsjNPlZg2nagksc+xugxfnn1ScS6YoF5JvUoKzH4V5clomK0tOyvdoqfXVLmzR997JxDdwph6x+4dpvXV1vUyONMAxJTW6GZsg8wjWq7FA+m0FkVe9qMNX6+5soz/YkSDg/qzG1ZEXM9ebndncjU2sr4o2tFWmajkSVUwith6cbohf5hBHzSqive22YU5+qpydIDkp4aPR80tH6KF1VYcfZPz/Q/6onk5S/5EtQLsm41gF1oO4MGhTXEQa9ajCGtUo8R7grfQIDAQAB";
    public static void main(String[] args) {
        try {

            // 1. 创建AlipayClient实例
           // AlipayClient alipayClient = new DefaultAlipayClient(getClientParams());
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,SIGN_TYPE);  //获得初始化的AlipayClient

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
            request.setBizContent("{" +
                    "\"out_trade_no\":\"20150320010101001\"," +
                    "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "\"total_amount\":88.88," +
                    "\"subject\":\"Iphone6 16G\"," +
                    "\"body\":\"Iphone6 16G\"," +
                    "\"time_expire\":\"2016-12-31 10:05:01\"," +
                    "      \"goods_detail\":[{" +
                    "        \"goods_id\":\"apple-01\"," +
                    "\"alipay_goods_id\":\"20010001\"," +
                    "\"goods_name\":\"ipad\"," +
                    "\"quantity\":1," +
                    "\"price\":2000," +
                    "\"goods_category\":\"34543238\"," +
                    "\"categories_tree\":\"124868003|126232002|126252004\"," +
                    "\"body\":\"特价手机\"," +
                    "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                    "        }]," +
                    "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                    "\"extend_params\":{" +
                    "\"sys_service_provider_id\":\"2088511833207846\"," +
                    "\"hb_fq_num\":\"3\"," +
                    "\"hb_fq_seller_percent\":\"100\"," +
                    "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
                    "\"card_type\":\"S0JP0000\"" +
                    "    }," +
                    "\"goods_type\":\"0\"," +
                    "\"timeout_express\":\"90m\"," +
                    "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
                    "\"royalty_info\":{" +
                    "\"royalty_type\":\"ROYALTY\"," +
                    "        \"royalty_detail_infos\":[{" +
                    "          \"serial_no\":1," +
                    "\"trans_in_type\":\"userId\"," +
                    "\"batch_no\":\"123\"," +
                    "\"out_relation_id\":\"20131124001\"," +
                    "\"trans_out_type\":\"userId\"," +
                    "\"trans_out\":\"2088101126765726\"," +
                    "\"trans_in\":\"2088101126708402\"," +
                    "\"amount\":0.1," +
                    "\"desc\":\"分账测试1\"," +
                    "\"amount_percentage\":\"100\"" +
                    "          }]" +
                    "    }," +
                    "\"sub_merchant\":{" +
                    "\"merchant_id\":\"2088000603999128\"," +
                    "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
                    "    }," +
                    "\"merchant_order_no\":\"20161008001\"," +
                    "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                    "\"store_id\":\"NJ_001\"," +
                    "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
                    "\"qr_pay_mode\":\"1\"," +
                    "\"qrcode_width\":100," +
                    "\"settle_info\":{" +
                    "        \"settle_detail_infos\":[{" +
                    "          \"trans_in_type\":\"cardAliasNo\"," +
                    "\"trans_in\":\"A0001\"," +
                    "\"summary_dimension\":\"A0001\"," +
                    "\"settle_entity_id\":\"2088xxxxx;ST_0001\"," +
                    "\"settle_entity_type\":\"SecondMerchant、Store\"," +
                    "\"amount\":0.1" +
                    "          }]," +
                    "\"settle_period_time\":\"7d\"" +
                    "    }," +
                    "\"invoice_info\":{" +
                    "\"key_info\":{" +
                    "\"is_support_invoice\":true," +
                    "\"invoice_merchant_name\":\"ABC|003\"," +
                    "\"tax_num\":\"1464888883494\"" +
                    "      }," +
                    "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
                    "    }," +
                    "\"agreement_sign_params\":{" +
                    "\"personal_product_code\":\"GENERAL_WITHHOLDING_P\"," +
                    "\"sign_scene\":\"INDUSTRY|CARRENTAL\"," +
                    "\"external_agreement_no\":\"test\"," +
                    "\"external_logon_id\":\"13852852877\"," +
                    "\"sign_validity_period\":\"2m\"," +
                    "\"third_party_type\":\"PARTNER\"," +
                    "\"buckle_app_id\":\"1001164\"," +
                    "\"buckle_merchant_id\":\"268820000000414397785\"," +
                    "\"promo_params\":\"{\\\"key\\\",\\\"value\\\"}\"" +
                    "    }," +
                    "\"integration_type\":\"PCWEB\"," +
                    "\"request_from_url\":\"https://\"," +
                    "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"," +
                    "\"ext_user_info\":{" +
                    "\"name\":\"李明\"," +
                    "\"mobile\":\"16587658765\"," +
                    "\"cert_type\":\"IDENTITY_CARD\"," +
                    "\"cert_no\":\"362334768769238881\"," +
                    "\"min_age\":\"18\"," +
                    "\"fix_buyer\":\"F\"," +
                    "\"need_check_info\":\"F\"" +
                    "    }" +
                    "  }");
            AlipayTradePagePayResponse response = alipayClient.execute(request);
            System.out.println(response.getBody());
            if(response.isSuccess()){
                System.out.println("tets");
            }

        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static CertAlipayRequest getClientParams() {
        CertAlipayRequest certParams = new CertAlipayRequest();
        certParams.setServerUrl("https://openapi.alipay.com/gateway.do");
        //请更换为您的AppId
        certParams.setAppId("2019091767145019");
        //请更换为您的PKCS8格式的应用私钥
        certParams.setPrivateKey("MIIEvQIBADANB ... ...");
        //请更换为您使用的字符集编码，推荐采用utf-8
        certParams.setCharset("utf-8");
        certParams.setFormat("json");
        certParams.setSignType("RSA2");
        //请更换为您的应用公钥证书文件路径
        certParams.setCertPath("C:\\Users\\PC\\Desktop\\key\\null_公钥.txt");
        //请更换您的支付宝公钥证书文件路径
        certParams.setAlipayPublicCertPath("C:\\Users\\PC\\Desktop\\key\\null_公钥.txt");
        //更换为支付宝根证书文件路径
        certParams.setRootCertPath("C:\\Users\\PC\\Desktop\\key\\null.csr");
        return certParams;
    }

    private static AlipayOpenOperationOpenbizmockBizQueryRequest getRequest() {
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        model.setBizNo("test");
        request.setBizModel(model);
        return request;
    }
}
