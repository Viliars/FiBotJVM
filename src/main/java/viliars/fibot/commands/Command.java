package viliars.fibot.commands;

import com.vk.api.sdk.objects.messages.Message;
import viliars.fibot.core.VKManager;

public abstract class Command implements Runnable {

    protected final Message message;
    protected VKManager vk;

    public Command(VKManager vk, Message message) {
        this.message = message;
        this.vk = vk;
    }
}