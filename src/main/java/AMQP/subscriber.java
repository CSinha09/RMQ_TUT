package AMQP;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class subscriber {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection(common.AMQP_URL);
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (s, delivery) -> {
            System.out.println(new String(delivery.getBody(), "UTF-8"));
        };

        CancelCallback cancelCallback = s -> {
            System.out.println(s);
        };
        channel.basicConsume(common.DEFAULT_QUEUE, true, deliverCallback, cancelCallback);
    }
}
