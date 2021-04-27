package testSpilt;

public class Main {
    public static void main(String[] args) {
        String url="https://www.digikey.com/en/products/detail/avx-corporation/TAJA106K006RNJ/478-1653-1-ND/563761";
        //https://www.digikey.com/en/products/detail/te-connectivity-amp-connectors/207314-1/292333
        int index=0;
        for(int i=0;i<8;i++){
            index=url.indexOf("/",index+i);
            url.substring(0,index+1);
        }
        //int index1=url.indexOf("/",8);

    }
}
