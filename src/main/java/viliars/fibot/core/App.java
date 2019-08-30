package viliars.fibot.core;

import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private final static String configPath = "/config.properties";

    public static void main(String[] args) throws IOException, ApiException {
        Properties properties = readResource();
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
        InputStream inputStream = new FileInputStream(vkConfigPath);
        Properties vkProperties = readProperties(inputStream);
        String groupId = vkProperties.getProperty("groupId");
        String accessToken = vkProperties.getProperty("accessToken");
        return new GroupActor(Integer.parseInt(groupId), accessToken);
    }

    private static Properties readResource() throws IOException {
        InputStream inputStream = App.class.getResourceAsStream(configPath);
        return readProperties(inputStream);
    }

    private static Properties readProperties(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new FileNotFoundException("InputStream is Null");
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
