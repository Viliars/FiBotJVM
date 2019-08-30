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
    private final static String configPath = "src/main/resources/config.properties";

    public static void main(String[] args) throws IOException, ApiException {
        Properties properties = readProperties(configPath);
        GroupActor groupActor = createGroupActor(properties);
        while (true) {
            try {
                HttpTransportClient httpClient = HttpTransportClient.getInstance();
                VkApi vk = new VkApi(httpClient, groupActor);

                CallbackHandler handler = new CallbackHandler(vk);
                handler.run();
            } catch (ClientException e) {
                System.out.println("ERROR - RELOAD");
            }
        }
    }

    private static GroupActor createGroupActor(Properties properties) throws IOException {
        String vkConfigPath = properties.getProperty("vkConfigPath");
        Properties vkProperties = readProperties(vkConfigPath);
        String groupId = vkProperties.getProperty("groupId");
        String accessToken = vkProperties.getProperty("accessToken");
        return new GroupActor(Integer.parseInt(groupId), accessToken);
    }

    private static Properties readProperties(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
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
