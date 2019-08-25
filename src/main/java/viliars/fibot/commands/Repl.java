package viliars.fibot.commands;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import viliars.fibot.core.VkApi;

import java.util.Random;
import java.util.StringJoiner;

public class Repl extends Command {
    private Random random = new Random();

    public Repl(VkApi vk, Message message) {
        super(vk, message);
    }

    public void run() {
        StringJoiner joiner = new StringJoiner(" ");
        String text = message.getText();
        String[] list = text.split(" ");
        if (list.length > 1) {
            for (int i = 1; i < list.length; i++)
                joiner.add(list[i]);

            try {
                vk.messages().send(vk.getActor()).message(joiner.toString())
                        .peerId(message.getPeerId())
                        .randomId(random.nextInt()).execute();
            } catch (ApiException | ClientException e) {
                e.printStackTrace();
            }
        }
    }
}
