package com.seizonia.spm.api;

import com.seizonia.spm.data.prefix.PrefixData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangePrefixEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private boolean cancelled;
    private final PrefixData prefix;

    public PlayerChangePrefixEvent(Player player, PrefixData prefix){
        this.player = player;
        this.prefix = prefix;
        this.cancelled = false;
    }

    public Player getPlayer() { return player; }

    public PrefixData getPrefix() { return prefix; }

    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public String getEventName() { return super.getEventName(); }

    @Override
    public boolean isCancelled() { return cancelled; }

    @Override
    public void setCancelled(boolean b) { this.cancelled = b; }

}
