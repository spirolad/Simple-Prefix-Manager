package com.seizonia.spm.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.UUID;

public class TagBuilder {

    private String prefix;
    private String suffix;
    private Team team;
    private Scoreboard scoreboard;

    public TagBuilder(String name, String prefix, String suffix, Scoreboard current, Player player) throws Exception {

        this.prefix = prefix;
        this.suffix = suffix;
        this.team = current.getTeam(name);
        this.scoreboard = current;

        if(this.team == null) this.team = current.registerNewTeam(name);

        this.team.setCanSeeFriendlyInvisibles(false);
        this.team.setAllowFriendlyFire(false);

        int prefixLength = 0;
        int suffixLength = 0;

        if (prefix != null) prefixLength = prefix.length();
        if (suffix != null) suffixLength = suffix.length();
        if (prefixLength >= 32) {
            player.sendMessage("§eVotre préfixe est trop grand ! Contactez un administrateur pour règler le problème");
            System.out.println("Problème de taile de préfixe: " + prefix + player.getName());
            throw new Exception("prefix and suffix lenghts are greater than 16");
        }

        if (suffix != null) this.team.setSuffix(ChatColor.translateAlternateColorCodes('&', suffix));
        if (prefix != null) this.team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));

    }

    public TagBuilder(String name, String prefix, String suffix, Player player) throws Exception {
        this(name, prefix, suffix, Bukkit.getScoreboardManager().getMainScoreboard(), player);

    }

    public void set(Player player){
        this.team.addPlayer(player);
        player.setScoreboard(scoreboard);
    }

    public void remove(Player player){
        team.removePlayer(player);
    }

    public void clear(UUID uuid){
        remove(Bukkit.getPlayer(uuid));
    }

    public void setAll(Collection<Player> players){
        for (Player player : players){
            set(player);
        }
    }

    public void setAll(Player[] players){
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = players).length;
        for (int i = 0; i < j; i++){
            Player player = arrayOfPlayer[i];
            set(player);
        }
    }

    public void setPrefix(String prefix){
        this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        this.team.setPrefix(this.prefix);
    }

    public void setSuffix(String suffix){
        this.suffix = ChatColor.translateAlternateColorCodes('&', suffix);
        this.team.setSuffix(this.suffix);
    }

    public String getPrefix(){
        return this.prefix;
    }

    public String getSuffix(){
        return this.suffix;
    }

    public Team getTeam(){
        return this.team;
    }

    public void removeTeam(){
        this.team.unregister();
    }

    public Scoreboard getScoreboard(){
        return scoreboard;
    }

    public static void setTag(Player player, String name, String prefix, String suffix) {
        try {
            new TagBuilder(name, prefix, suffix, player).set(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}