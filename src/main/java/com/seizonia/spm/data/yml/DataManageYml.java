package com.seizonia.spm.data.yml;

import com.seizonia.spm.PrefixMain;
import com.seizonia.spm.data.DataMain;
import com.seizonia.spm.data.DataManage;
import com.seizonia.spm.data.player.PlayerData;
import com.seizonia.spm.data.prefix.PrefixData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DataManageYml implements DataManage {

    private File repertoryPlayer;
    private static final PrefixMain PREFIX_MAIN = PrefixMain.getInstance();

    public DataManageYml(){
        this.repertoryPlayer = new File(PREFIX_MAIN.getDataFolder(), "profile");
        if(!this.repertoryPlayer.exists()){
            repertoryPlayer.mkdir();
        }
    }

    @Override
    public void save(PlayerData playerData) {
        Player player = Bukkit.getPlayer(playerData.getUuid());
        if(player == null) return;
        File playerFile = new File(repertoryPlayer.getPath(), player.getUniqueId().toString() + ".yml");
        setData(playerData, playerFile, player.getName());
    }

    @Override
    public PlayerData load(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if(player == null) return null;
        File playerFile = new File(repertoryPlayer.getPath(), player.getUniqueId().toString() + ".yml");
        YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(playerFile);
        String path = "player."+player.getName();
        String prefixEquip = ymlFile.getString(path + ".prefix-equip");
        String customName = ymlFile.getString(path + ".name");
        List<String> prefixList = ymlFile.getStringList(path + "prefix-list");
        return new PlayerData(uuid, prefixEquip, prefixList, customName);
    }

    @Override
    public void create(PlayerData playerData) {
        Player player = Bukkit.getPlayer(playerData.getUuid());
        if(player == null) return;
        File playerFile = new File(repertoryPlayer.getPath(), player.getUniqueId().toString() + ".yml");
        try {
            playerFile.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        setData(playerData, playerFile, player.getName());
    }

    private void setData(PlayerData playerData, File playerFile, String playerName){
        YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(playerFile);
        String path = "player."+playerName;
        ymlFile.set(path + ".prefix-equip", playerData.getPrefixEquip());
        ymlFile.set(path + ".prefix-list", playerData.getPrefixList());
        ymlFile.set(path + ".name", playerData.getName());
        save(playerFile, ymlFile);
    }

    @Override
    public boolean hasAccount(UUID uuid) {
        File playerFile = new File(repertoryPlayer.getPath(), uuid.toString() + ".yml");
        return playerFile.exists();
    }

    @Override
    public void loadPrefix() {
        System.out.println("LOAD PREFIX");
        File filePrefix = new File(PREFIX_MAIN.getDataFolder(), "prefix.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(filePrefix);
        ConfigurationSection sectionPrefix = yml.getConfigurationSection("prefix-list");
        DataMain dataMain = PREFIX_MAIN.getDataMain();

        for(String prefix : sectionPrefix.getKeys(false)){
            dataMain.getPrefixDataMap().put(prefix, new PrefixData(
                    prefix,
                    sectionPrefix.getString(prefix + ".format-tab"),
                    sectionPrefix.getString(prefix + ".format-chat"),
                    sectionPrefix.getString(prefix + ".color"),
                    sectionPrefix.getBoolean(prefix + ".hideTab"),
                    sectionPrefix.getBoolean(prefix + ".hideChat"),
                    sectionPrefix.getBoolean(prefix + ".hideName")));
        }
    }

    @Override
    public void savePrefix() {
        File filePrefix = new File(PREFIX_MAIN.getDataFolder(), "prefix.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(filePrefix);
        ConfigurationSection sectionPrefix = yml.getConfigurationSection("prefix-list");
        DataMain dataMain = PREFIX_MAIN.getDataMain();
        for(PrefixData prefixData : dataMain.getPrefixDataMap().values()){
            String path = "prefix-list." + prefixData.getName();
            sectionPrefix.set(path + ".format-tab", prefixData.getPrefixTab());
            sectionPrefix.set(path + ".format-chat", prefixData.getPrefixChat());
            sectionPrefix.set(path + ".color", prefixData.getColor());
            sectionPrefix.set(path + ".hideTab", prefixData.isHideTab());
            sectionPrefix.set(path + ".hideChat", prefixData.isHideChat());
            sectionPrefix.set(path + ".hideName", prefixData.isHideName());
        }
        save(filePrefix, yml);
    }

    private void save(File file, YamlConfiguration yml){
        try {
            yml.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
