package com.seizonia.spm.listener;

import com.seizonia.spm.PrefixMain;
import com.seizonia.spm.data.DataMain;
import com.seizonia.spm.data.player.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SendMessageListener implements Listener {

    private static final DataMain DATA_MAIN = PrefixMain.getInstance().getDataMain();

    @EventHandler
    public void playerMessage(AsyncPlayerChatEvent event){
        PlayerData playerData = DATA_MAIN.getPlayerDataMap().get(event.getPlayer().getUniqueId());
        event.setFormat(playerData.getChatPlayer().replace("%message%", "%2$s"));
    }

}
