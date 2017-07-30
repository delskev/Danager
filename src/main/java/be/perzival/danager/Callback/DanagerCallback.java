package be.perzival.danager.Callback;

import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;

/**
 * Created by Perzival on 30/07/2017.
 */
public class DanagerCallback implements FutureCallback<DiscordAPI> {
    public void onSuccess(DiscordAPI discordAPI) {
//        // register listener
//        discordAPI.registerListener(new MessageCreateListener() {
//            public void onMessageCreate(DiscordAPI api, Message message) {
//            }
//        });
    }

    public void onFailure(Throwable throwable) {
    }
}
