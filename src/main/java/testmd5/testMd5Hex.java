package testmd5;

import org.apache.commons.codec.digest.DigestUtils;

public class testMd5Hex {
    public static void main(String[] args) {
        String id = DigestUtils.md5Hex("");
        String id1 = DigestUtils.md5Hex("");
        System.out.println(id);
        System.out.println(id1);
    }

}
