package com.invsee;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class ShulkerResetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			if(args.length == 2) {
				Player target = Bukkit.getServer().getPlayerExact(args[0]);
				int slot = Integer.parseInt(args[1]);
				if (target != null) {
					ItemStack item = target.getInventory().getItem(slot);
					if (item != null && item.getType() == Material.SHULKER_BOX) {
						BlockStateMeta blockMeta = (BlockStateMeta) item.getItemMeta();
						if (blockMeta != null && blockMeta.getBlockState() instanceof ShulkerBox) {
							ShulkerBox shulkerBox = (ShulkerBox) blockMeta.getBlockState();
							Inventory shulkerInventory = shulkerBox.getInventory();
							Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] All items in the player's shulkerbox in " + slot + " slot have been deleted. A list of deleted items >>");
							for (ItemStack shulkerItem : shulkerInventory.getContents()) {
								if (shulkerItem != null && !shulkerItem.getType().equals(Material.AIR)) {
									shulkerItem.setAmount(0);
								}
							}
							blockMeta.setBlockState(shulkerBox);
							item.setItemMeta(blockMeta);
						}
					} else {
						Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] The specified player does not have a Shulker Box in slot " + slot);
					}
				}
			} else {
				Bukkit.getServer().getConsoleSender().sendMessage("§cUsage: /shulkerreset <player_name> <slot_number>");
			}
		} else {
			sender.sendMessage("§c[INVSEECONSOLE] You are not authorized to use this command!");
		}
		return false;
	}

}
