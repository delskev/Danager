package be.perzival.danager.Callback;

import be.perzival.danager.listener.DanagerJoinServerListener;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;

/**
 * Created by Perzival on 30/07/2017.
 */
public class DanagerCallback implements FutureCallback<DiscordAPI> {
    public void onSuccess(DiscordAPI discordAPI) {
        discordAPI.registerListener(new DanagerJoinServerListener());
    }

    public void onFailure(Throwable throwable) {
    }
}
