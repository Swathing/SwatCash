package me.swathing.swatcash;

import me.swathing.swatcash.commands.CashCommand;
import me.swathing.swatcash.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private File data;

    private static Main instance;

    @Override
    public void onEnable() {
        setInstance(this);
        setupData();

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        Bukkit.getPluginCommand("cash").setExecutor(new CashCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public void setInstance(Main instance) {
        this.instance = instance;
    }

    public void setupData() {
        this.data = new File("plugins/SwatCash/data.yml");
        if (!data.exists()) {
            data.getParentFile().mkdirs();
            try {
                data.createNewFile();
            } catch (IOException e) {
                getLogger().log(Level.WARNING, "Unexpected error while creating file data.yml: ", e);
                System.exit(0);
            }
        }
    }

    public File getData() {
        return data;
    }
}
