import com.vk.api.sdk.objects.messages.Message;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface CommandFactory<T extends Command> {
    T create(VKManager vk, Message message);
}

public class CommandManager {

    private static HashMap<String, CommandFactory> commands = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    static {
        commands.put("/repl", Repl::new);
    }

    public static void execute (Message message) {
        String command = message.getBody().split(" ")[0];
        for(HashMap.Entry<String, CommandFactory> every : commands.entrySet()) {
            if(every.getKey().equals(command)) {
                executor.execute(every.getValue().create(new VKManager(), message));
            }
        }
    }
}