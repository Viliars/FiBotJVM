package viliars.fibot.commands;

import com.vk.api.sdk.objects.messages.Message;
import viliars.fibot.core.VkApi;


public abstract class Command implements Runnable {

    protected final Message message;
    protected VkApi vk;

    public Command(VkApi vk, Message message) {
        this.message = message;
        this.vk = vk;
    }
}
