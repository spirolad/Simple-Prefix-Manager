package com.seizonia.spm.data;

import com.seizonia.spm.data.config.ConfigData;
import com.seizonia.spm.data.player.PlayerData;
import com.seizonia.spm.data.prefix.PrefixData;
import com.seizonia.spm.data.sql.DataManageSql;
import com.seizonia.spm.data.yml.DataManageYml;
import org.bukkit.configuration.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataMain {

    private Map<String, PrefixData> prefixDataMap;
    private Map<UUID, PlayerData> playerDataMap;
    private DataManage dataManage;

    public DataMain(Configuration config){
        new ConfigData(config);
        this.prefixDataMap = new HashMap<>();
        this.playerDataMap = new HashMap<>();
        this.dataManage = (config.getBoolean("MySQL.use-sql")) ? new DataManageSql() : new DataManageYml();
    }

    public void loadPrefix(){ dataManage.loadPrefix(); }

    public void save(PlayerData playerData){ dataManage.save(playerData);}

    public PlayerData load(UUID uuid){ return dataManage.load(uuid);}

    public void create(PlayerData playerData){ dataManage.create(playerData);}

    public boolean hasAccount(UUID uuid) { return dataManage.hasAccount(uuid);}

    public Map<UUID, PlayerData> getPlayerDataMap() { return playerDataMap; }

    public Map<String, PrefixData> getPrefixDataMap() { return prefixDataMap; }

}
