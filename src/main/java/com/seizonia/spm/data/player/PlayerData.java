package com.seizonia.spm.data.player;

import com.seizonia.spm.PrefixMain;
import com.seizonia.spm.api.PlayerChangePrefixEvent;
import com.seizonia.spm.data.DataMain;
import com.seizonia.spm.data.config.ConfigData;
import com.seizonia.spm.data.prefix.PrefixData;
import com.seizonia.spm.utils.TagBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PlayerData {

    private static final DataMain DATA_MAIN = PrefixMain.getInstance().getDataMain();
    private static final ConfigData CONFIG = ConfigData.getInstance();

    private String prefixEquip;
    private final UUID uuid;
    private String chatPlayer;
    private List<String> prefixList;
    private String name;

    public PlayerData(UUID uuid, String playerName){
        this.uuid = uuid;
        this.name = playerName;
        setPrefixEquip(CONFIG.getDefaultPrefix());
        this.prefixList = new ArrayList<>(Arrays.asList(CONFIG.getDefaultPrefix()));
    }

    public PlayerData(UUID uuid, String prefixEquip, List<String> prefixList, String playerName){
        this.uuid = uuid;
        this.name = playerName;
        setPrefixEquip(prefixEquip);
        this.prefixList = prefixList;
    }

    public void setPrefixEquip(String prefix){
        PrefixData newPrefix = DATA_MAIN.getPrefixDataMap().get(prefix);
        Player player = Bukkit.getPlayer(uuid);
        if(player == null) return;
        PlayerChangePrefixEvent playerChangePrefixEvent = new PlayerChangePrefixEvent(player, newPrefix);
        Bukkit.getPluginManager().callEvent(playerChangePrefixEvent);
        if(playerChangePrefixEvent.isCancelled()) return;
        if(!newPrefix.isHideTab()) player.setPlayerListName(ChatColor.translateAlternateColorCodes('&' ,convert(CONFIG.getFormatTab(), player, newPrefix)));
        this.chatPlayer = (!newPrefix.isHideChat()) ? ChatColor.translateAlternateColorCodes('&' ,convert(CONFIG.getFormatChat(), player, newPrefix)) : null;
        if(!newPrefix.isHideName()) changeDisplayName(player, newPrefix);
        this.prefixEquip = prefix;
    }

    private void changeDisplayName(Player player, PrefixData prefix) {
        TagBuilder.setTag(player, name, ChatColor.translateAlternateColorCodes('&' ,convert(CONFIG.getFormatName(), player, prefix)), "");
    }

    private String convert(String format, Player player, PrefixData prefixData){
        return format.replace("%prefix%", prefixData.getPrefixTab())
                .replace("%color%", prefixData.getColor())
                .replace("%player%", player.getName());
    }

    public UUID getUuid() { return uuid; }

    public String getPrefixEquip() { return prefixEquip; }

    public String getChatPlayer() { return chatPlayer; }

    public List<String> getPrefixList() { return prefixList; }

    public String getName() { return name; }
}
