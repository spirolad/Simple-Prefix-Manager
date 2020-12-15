package com.seizonia.spm.listener;

import com.seizonia.spm.PrefixMain;
import com.seizonia.spm.data.DataMain;
import com.seizonia.spm.data.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final static DataMain DATA_MAIN = PrefixMain.getInstance().getDataMain();

    @EventHandler
    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerData playerData;
        if(DATA_MAIN.hasAccount(player.getUniqueId())){
            playerData = DATA_MAIN.load(player.getUniqueId());
        }else {
            playerData = new PlayerData(player.getUniqueId(), player.getName());
            DATA_MAIN.create(playerData);
        }
        PrefixMain.getInstance().getDataMain().getPlayerDataMap().put(player.getUniqueId(), playerData);
    }

}
