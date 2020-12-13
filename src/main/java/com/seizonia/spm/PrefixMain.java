package com.seizonia.spm;

import com.seizonia.spm.data.message.MessageLoadder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sun.misc.IOUtils;

import java.io.*;
import java.net.URL;

public class PrefixMain extends JavaPlugin {

    private static PrefixMain instance;

    @Override
    public void onEnable() {
        instance = this;
        loadFileRessource();
        System.out.println(MessageLoadder.getMessage("leave"));
    }

    @Override
    public void onDisable() {

    }

    private void loadFileRessource(){

        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File message = new File(dataFolder.getPath(), "messages.yml");
        File config = new File(dataFolder.getPath(), "config.yml");
        copyFileRessources(message, "messages");
        copyFileRessources(config, "config");

    }

    private void copyFileRessources(File destination, String ressourceName){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (!destination.exists()) {
            getLogger().severe("Creation of file " + ressourceName + "...");
            try {
                // read this file into InputStream
                inputStream = getResource(ressourceName + ".yml");

                // write the inputStream to a FileOutputStream
                outputStream = new FileOutputStream(destination);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                getLogger().severe("Creation of file " + ressourceName + " done !");

            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getServer()
                        .getLogger()
                        .severe(ChatColor.RED
                                + "Could not create location.yml!");
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    public static PrefixMain getInstance() { return instance; }

}
