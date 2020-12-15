package com.seizonia.spm.listener;

import com.seizonia.spm.PrefixMain;
import com.seizonia.spm.data.DataMain;
import com.seizonia.spm.data.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final static DataMain DATA_MAIN = PrefixMain.getInstance().getDataMain();

    @EventHandler
    public void quit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = DATA_MAIN.getPlayerDataMap().get(player.getUniqueId());
        if(!DATA_MAIN.hasAccount(player.getUniqueId())) DATA_MAIN.create(playerData);
        else DATA_MAIN.save(playerData);
        DATA_MAIN.getPlayerDataMap().remove(player.getUniqueId());
    }

}
