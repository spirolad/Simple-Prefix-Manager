package com.seizonia.spm.data.message;

import com.seizonia.spm.PrefixMain;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessageLoadder {

    private static Map<String, String> messageMap;

    static {
        messageMap = new HashMap<>();
        File file = new File(PrefixMain.getInstance().getDataFolder(), "messages.yml");
        ConfigurationSection section = YamlConfiguration.loadConfiguration(file).getConfigurationSection("message");
        for(String messageId : section.getKeys(false)){
            messageMap.put(messageId, section.getString(messageId));
        }
    }
    public static String getMessage(String id){
        return messageMap.getOrDefault(id, "NO MESSAGE FOUND");
    }

}
