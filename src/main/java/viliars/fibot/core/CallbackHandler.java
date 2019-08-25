package viliars.fibot.core;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.objects.messages.Message;

public class CallbackHandler extends CallbackApiLongPoll {

    private VkApi vk;


    public CallbackHandler(VkApi vk) {
        super(vk, vk.getActor());
        this.vk = vk;
    }

    @Override
    public void messageNew(Integer groupId, Message message) {
        if (message != null) {
            CommandManager.execute(vk, message);
        }
    }
}