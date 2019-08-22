import com.vk.api.sdk.objects.messages.Message;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Repl extends Command {
    public Repl(VKManager vk, Message message) {
        super(vk, message);
    }

    public void run() {
        StringJoiner joiner = new StringJoiner(" ");
        String text = message.getBody();
        String[] list = text.split(" ");
        if(list.length > 1) {
            for(int i = 1; i < list.length; i++)
                joiner.add(list[i]);

            super.vk.sendMessage(joiner.toString(), message.getUserId());
        }
    }
}
