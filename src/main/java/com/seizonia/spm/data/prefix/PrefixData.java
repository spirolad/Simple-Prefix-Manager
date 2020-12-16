package com.seizonia.spm.data.prefix;

public class PrefixData {

    private final String name;
    private String prefixTab, prefixChat, color;
    private boolean hideTab, hideChat, hideName;

    public PrefixData(String name, String prefixTab, String prefixChat, String color, boolean hideTab, boolean hideChat, boolean hideName) {
        this.name = name;
        this.prefixTab = prefixTab;
        this.prefixChat = prefixChat;
        this.color = color;
        this.hideTab = hideTab;
        this.hideChat = hideChat;
        this.hideName = hideName;
    }

    public void edit(EditionArea area, String newContent){
        switch (area){
            case TAB:
                this.prefixTab = newContent;
                break;
            case CHAT:
                this.prefixChat = newContent;
                break;
            case COLOR:
                this.color = newContent;
                break;
        }
    }

    public String getName() { return name; }

    public String getPrefixTab() { return prefixTab; }

    public String getPrefixChat() { return prefixChat; }

    public String getColor() { return color; }

    public boolean isHideTab() { return hideTab; }

    public boolean isHideChat() { return hideChat; }

    public boolean isHideName() { return hideName; }

}
