package testmd5;
import java.util.UUID;

public class uuid {

        public static void main(String[] args) {


            System.out.println(UUID.randomUUID().toString().replace("-", ""));
            System.out.println(UUID.randomUUID().version());

            System.out.println(UUID.nameUUIDFromBytes("https://www.digikey.com/product-detail/en/apem-inc/420037B14/420037B14-ND/1797612".getBytes()).toString().replace("-", ""));


            System.out.println(UUID.nameUUIDFromBytes("https://www.digikey.com/product-detail/en/apem-inc/420037B14/420037B14-ND/1797612".getBytes()).toString());

        }
    }
