package viliars.fibot.core;

import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws IOException, ClientException, ApiException {
        Properties properties = readProperties();
        GroupActor groupActor = createGroupActor(properties);

        HttpTransportClient httpClient = HttpTransportClient.getInstance();
        VkApi vk = new VkApi(httpClient, groupActor);

        CallbackHandler handler = new CallbackHandler(vk);
        handler.run();
    }

    private static GroupActor createGroupActor(Properties properties) {
        String groupId = properties.getProperty("groupId");
        String accessToken = properties.getProperty("accessToken");
        return new GroupActor(Integer.parseInt(groupId), accessToken);
    }

    private static Properties readProperties() throws IOException {
        FileInputStream inputStream = new FileInputStream("config.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("property file not found");
        }
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect properties file");
        } finally {
            inputStream.close();
        }
    }
}
