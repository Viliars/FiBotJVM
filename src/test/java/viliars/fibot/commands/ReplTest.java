package viliars.fibot.commands;

import com.vk.api.sdk.objects.messages.Message;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import viliars.fibot.core.VKManager;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReplTest {

    @Mock
    VKManager vk;

    @Mock
    Message message;

    @InjectMocks
    Repl repl;

    @Test
    public void testNotEmpty() {
        when(message.getBody()).thenReturn("/repl test repl");
        when(message.getUserId()).thenReturn(1);

        repl.run();

        verify(vk, times(1)).sendMessage("test repl", 1);
    }

    @Test
    public void testEmpty() {
        when(message.getBody()).thenReturn("/repl");
        when(message.getUserId()).thenReturn(1);

        repl.run();

        verify(vk, never()).sendMessage("", 1);
    }
}