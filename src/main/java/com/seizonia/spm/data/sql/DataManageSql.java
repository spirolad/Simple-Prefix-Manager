package com.seizonia.spm.data.sql;

import com.seizonia.spm.data.DataManage;
import com.seizonia.spm.data.player.PlayerData;

import java.util.UUID;

public class DataManageSql implements DataManage {


    @Override
    public void save(PlayerData playerData) {

    }

    @Override
    public PlayerData load(UUID uuid) {
        return null;
    }

    @Override
    public void create(PlayerData playerData) {

    }

    @Override
    public boolean hasAccount(UUID uuid) {
        return false;
    }

    @Override
    public void loadPrefix() {

    }

    @Override
    public void savePrefix() {

    }
}
