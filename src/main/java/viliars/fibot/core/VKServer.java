package viliars.fibot.core;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import viliars.fibot.core.CommandManager;
import viliars.fibot.core.VKCore;

public class VKServer {

    public static VKCore vkCore;
    static {
        try {
            vkCore = new VKCore();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NullPointerException, ApiException, InterruptedException {

        System.out.println("Старт сервера");
        while (true) {
            Thread.sleep(300);
            try {
                Message message = vkCore.getMessage();
                if (message != null) {
                    CommandManager.execute(message);
                }
            } catch (ClientException e) {
                System.out.println("Возникли проблемы");
                final int RECONNECT_TIME = 10000;
                System.out.println("Повторное соединение через " + RECONNECT_TIME / 1000 + " секунд");
                Thread.sleep(RECONNECT_TIME);

            }
        }
    }
}