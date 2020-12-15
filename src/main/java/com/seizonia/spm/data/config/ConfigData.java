package com.seizonia.spm.data.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigData {

    private static ConfigData instance;
    private String prefixPlugin;
    private String defaultTab, defaultName, defaultChat, defaultColor;
    private String formatChat, formatTab, formatName;
    private String formatPermission;
    private String defaultPrefix;
    private boolean usePermission;

    public ConfigData(Configuration config){
        instance = this;

        // Load differents default prefix format
        ConfigurationSection sectionDefault = config.getConfigurationSection("default-prefix-format");
        assert sectionDefault != null;
        this.defaultTab = sectionDefault.getString("tab");
        this.defaultName = sectionDefault.getString("name");
        this.defaultChat = sectionDefault.getString("chat");
        this.defaultColor = sectionDefault.getString("color");

        // Load differents format
        ConfigurationSection sectionFormat = config.getConfigurationSection("default-format");
        this.formatChat = sectionFormat.getString("chat");
        this.formatTab = sectionFormat.getString("tab");
        this.formatName = sectionFormat.getString("name");

        // Load if the plugin work with permission
        this.usePermission = config.getBoolean("use-permission");

        // Load the format permission
        this.formatPermission = config.getString("format-permission");

        // Load the plugin prefix
        this.prefixPlugin = config.getString("prefix-plugin");

        // Load the default prefix
        this.defaultPrefix = config.getString("default-prefix");
    }

    public static ConfigData getInstance() { return instance; }

    public String getDefaultTab() { return defaultTab; }

    public void setDefaultTab(String defaultTab) { this.defaultTab = defaultTab; }

    public String getDefaultName() { return defaultName; }

    public void setDefaultName(String defaultName) { this.defaultName = defaultName; }

    public String getDefaultChat() { return defaultChat; }

    public void setDefaultChat(String defaultChat) { this.defaultChat = defaultChat; }

    public String getDefaultColor() { return defaultColor; }

    public void setDefaultColor(String defaultColor) { this.defaultColor = defaultColor; }

    public String getFormatChat() { return formatChat; }

    public void setFormatChat(String formatChat) { this.formatChat = formatChat; }

    public String getFormatTab() { return formatTab; }

    public void setFormatTab(String formatTab) { this.formatTab = formatTab; }

    public String getFormatName() { return formatName; }

    public void setFormatName(String formatName) { this.formatName = formatName; }

    public String getFormatPermission() { return formatPermission; }

    public void setFormatPermission(String formatPermission) { this.formatPermission = formatPermission; }

    public boolean isUsePermission() { return usePermission; }

    public void setUsePermission(boolean usePermission) { this.usePermission = usePermission; }

    public String getDefaultPrefix() { return defaultPrefix; }

    public void setDefaultPrefix(String defaultPrefix) { this.defaultPrefix = defaultPrefix; }

}
