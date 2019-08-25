package viliars.fibot.core;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public class VkApi extends VkApiClient {

    private GroupActor actor;

    public VkApi(TransportClient transportClient, GroupActor actor) {
        super(transportClient);
        this.actor = actor;
    }

    public GroupActor getActor() {
        return actor;
    }
}
