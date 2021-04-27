package testJedis;

import org.apache.http.impl.client.HttpClients;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class subscribe {

    public static void main(String[] args) {
        JedisPubSub subscriber=new JedisPubSub() {
            public void onMessage(String channel, String message) {
                System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
            }

            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                        channel, subscribedChannels));
            }

            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                        channel, subscribedChannels));

            }
            @Override
            public int getSubscribedChannels() {
                return super.getSubscribedChannels();
            }
        };
        Jedis jedis=new Jedis("localhost");
        jedis.subscribe(subscriber,"chat");
    }


}
