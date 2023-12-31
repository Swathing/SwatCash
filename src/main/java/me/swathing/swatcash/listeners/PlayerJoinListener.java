package me.swathing.swatcash.listeners;

import me.swathing.swatcash.api.CashAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!CashAPI.playerExists(player)) {
            CashAPI.setPlayerCash(player, 0);
        }
    }
}
