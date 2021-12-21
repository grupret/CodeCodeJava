package gaian.consul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateKeyValueInConsul {
    private static final String USER_AGENT = "Mozilla/5.0";

    private static String PUT_URL = "http://dev-ingress-gateway.gaiansolutions.com/v1/kv/config/engagement-trigger-service%2Cqa/KEY?dc=dc1";

    public static void main(String[] args) throws IOException, InterruptedException {

        sendPUT();
    }

    private static void sendPUT() throws IOException, InterruptedException {

        String[] keyValue = {
            "kafka.bootstrap.servers=b-1.kafka-cluster.a3kp5m.c4.kafka.ap-south-1.amazonaws.com:9092,b-2.kafka-cluster.a3kp5m.c4.kafka.ap-south-1.amazonaws.com:9092",
            "kafka.pushNotificationTaskConsumer.topic=engagement.delivery.twitter",
            "kafka.pushNotificationTaskConsumer.concurrency=1",
            "kafka.pushNotificationTaskConsumer.bootstrap.servers=${kafka.bootstrap.servers}",
            "kafka.pushNotificationTaskConsumer.enable.auto.commit=false",
            "kafka.pushNotificationTaskConsumer.group.id=engagements",
            "kafka.pushNotificationTaskConsumer.auto.offset.reset=earliest",
            "kafka.pushNotificationTaskConsumer.max.poll.records=10",
            "kafka.pushNotificationTaskConsumer.max.poll.interval.ms=600000",
            "kafka.pushNotificationTaskConsumer.session.timeout.ms=300000",
            "kafka.pushNotificationTaskConsumer.heartbeat.interval.ms=100",
            "kafka.pushNotificationTaskConsumer.partition.assignment.strategy=org.apache.kafka.clients.consumer.RoundRobinAssignor",
            "spring.data.mongodb.uri=mongodb://voxa:voxa123@172.31.13.67:27017,172.31.1.22:27017,172.31.10.23:27017/voxa-engagements?authSource=admin"};



        for (int i = 0; i < keyValue.length; i++) {
            PUT_URL = "http://dev-ingress-gateway.gaiansolutions.com/v1/kv/config/pushnotification-service%2Cqa/KEY?dc=dc1&flags=0";
            String[] KV = keyValue[i].split("=");
            PUT_URL = PUT_URL.replace("KEY", KV[0]);
            URL obj = new URL(PUT_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setDoOutput(true);
            String jsonInputString = KV[1];
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println("PUT Response Code:: " + responseCode);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        }
    }
}