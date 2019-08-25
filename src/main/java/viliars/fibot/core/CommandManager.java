package viliars.fibot.core;

import com.vk.api.sdk.objects.messages.Message;
import viliars.fibot.commands.Command;
import viliars.fibot.commands.Repl;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface CommandFactory<T extends Command> {
    T create(VkApi vk, Message message);
}

public class CommandManager {

    private static HashMap<String, CommandFactory> commands = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    static {
        commands.put("/repl", Repl::new);
    }

    public static void execute (VkApi vk, Message message) {
        String command = message.getText().split(" ")[0];
        for(HashMap.Entry<String, CommandFactory> every : commands.entrySet()) {
            if(every.getKey().equals(command)) {
                executor.execute(every.getValue().create(vk, message));
            }
        }
    }
}