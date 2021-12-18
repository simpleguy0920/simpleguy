package com.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestRocketmq {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new
                DefaultMQProducer("shengyu_producer");
        producer.setNamesrvAddr("10.243.66.147:9876;10.243.66.144:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("testQueue", null, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.setDelayTimeLevel(1);
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }

}
