package me.swathing.swatcash.api;

import me.swathing.swatcash.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CashAPI {

    private static File fileData = Main.getInstance().getData();
    private static FileConfiguration data = YamlConfiguration.loadConfiguration(fileData);

    public static int getPlayerCash(Player player) {
        String playerName = player.getName().toLowerCase();
        if (playerExists(player)) {
            return data.getInt("players-data." + playerName + ".cash");
        }
        return 0;
    }

    public static void setPlayerCash(Player player, int amount) {
        try {
            String playerName = player.getName().toLowerCase();
            data.set("players-data." + playerName + ".cash", amount);
            data.save(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPlayerCash(Player player, int amount) {
        try {
            String playerName = player.getName().toLowerCase();
            data.set("players-data." + playerName + ".cash", getPlayerCash(player) + amount);
            data.save(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removePlayerCash(Player player, int amount) {
        try {
            String playerName = player.getName().toLowerCase();
            data.set("players-data." + playerName + ".cash", getPlayerCash(player) - amount);
            data.save(fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean playerExists(Player player) {
        String playerName = player.getName().toLowerCase();
        return data.contains("players-data." + playerName);
    }
}
