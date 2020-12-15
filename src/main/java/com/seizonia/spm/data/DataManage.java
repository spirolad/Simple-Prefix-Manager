package com.seizonia.spm.data;

import com.seizonia.spm.data.player.PlayerData;

import java.util.UUID;

public interface DataManage {

    void save(PlayerData playerData);

    PlayerData load(UUID uuid);

    void create(PlayerData playerData);

    boolean hasAccount(UUID uuid);

    void loadPrefix();

    void savePrefix();

}
