package viliars.fibot.commands;

import com.vk.api.sdk.objects.messages.Message;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import viliars.fibot.core.VkApi;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReplTest {

    @Mock
    VkApi vk;

    @Mock
    Message message;

    @InjectMocks
    Repl repl;

    @Test(expected = NullPointerException.class)
    public void testNotEmpty() {
        when(message.getText()).thenReturn("/repl test repl");
        when(message.getPeerId()).thenReturn(1);

        repl.run();

        verify(vk, times(1)).messages();
    }

    @Test
    public void testEmpty() {
        when(message.getText()).thenReturn("/repl");
        when(message.getPeerId()).thenReturn(1);

        repl.run();

        verify(vk, never()).messages();
    }
}