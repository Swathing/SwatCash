package me.swathing.swatcash.commands;

import me.swathing.swatcash.api.CashAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0 || !player.hasPermission("cash.admin")) {
                player.sendMessage("§aCash§f: " + CashAPI.getPlayerCash(player));
                return true;
            }

            if(args.length != 3) {
                player.sendMessage("§cUso correto: /cash <set/add/remove>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                player.sendMessage("§cNão foi possível encontrar o jogador no momento.");
                return true;
            }

            String subCMD = args[0].toLowerCase();
            int amount = Integer.parseInt(args[2]);
            switch (subCMD) {
                case "set":
                    CashAPI.setPlayerCash(target, amount);
                    player.sendMessage("§aVocê setou §f" + amount + " §ade cash para o jogador §f" + target.getName() + "§a.");
                    break;
                case "add":
                    CashAPI.addPlayerCash(target, amount);
                    player.sendMessage("§aVocê adicionou §f" + amount + " §ade cash para o jogador §f" + target.getName() + "§a.");
                    break;
                case "remove":
                    CashAPI.removePlayerCash(target, amount);
                    player.sendMessage("§aVocê removeu §f" + amount + " §acash do jogador §f" + target.getName() + "§a.");
                    break;
                default:
            }

        } else {
            sender.sendMessage("§cApenas jogadores podem usar este comando!");
        }
        return false;
    }
}
