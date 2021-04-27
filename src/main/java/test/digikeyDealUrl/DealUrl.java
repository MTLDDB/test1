package test.digikeyDealUrl;

public class DealUrl {
    public static void main(String[] args) {
        String url="https://www.digikey.com/en/products/detail/3m-tc/3M-CN4490-4-X-9-25-25/7587712";
        String[] mfrs=url.split("/");
        String[] params=url.split("/");
        String url1="https://www.digikey.com/product-detail/en/"+params[4]+"/"+params[5]+"/"+"1218630-1-ND"+"/"+params[6];
        int i=0;
        for (String mfr : mfrs) {
            System.out.println(mfr+"===="+i++);
        }
    }
}
