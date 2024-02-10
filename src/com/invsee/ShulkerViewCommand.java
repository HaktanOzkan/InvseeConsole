package com.invsee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class ShulkerViewCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CheckClass cc = new CheckClass();
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
							for (ItemStack shulkerItem : shulkerInventory.getContents()) {
								if (shulkerItem != null && !shulkerItem.getType().equals(Material.AIR)) {
									ItemMeta itemMeta = shulkerItem.getItemMeta();
									String ItemType = shulkerItem.getType().name();
									List<String> listitem = new ArrayList<>();
									if (itemMeta.hasEnchants()) {
										if(cc.itemflagcheck(itemMeta) == true) {
											Set<ItemFlag> itemFlags = itemMeta.getItemFlags();
	                						List<String> itemFlagList = new ArrayList<>();
	                						for (ItemFlag itemFlag : itemFlags) {
	                							itemFlagList.add(itemFlag.name());
	                						}
	                						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
		                				    List<String> enchantlist = new ArrayList<>();
		                				    for (Enchantment enchant : enchants.keySet()) {
		                				        int level = enchants.get(enchant);
		                				        enchantlist.add(enchant.getKey().getKey() + " " + level);
		                				    }
		                				    cc.listitemadd(listitem, slot, ItemType, itemMeta, shulkerItem, enchantlist, itemFlagList, 0);
										} else {
	                						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
		                				    List<String> enchantlist = new ArrayList<>();
		                				    for (Enchantment enchant : enchants.keySet()) {
		                				        int level = enchants.get(enchant);
		                				        enchantlist.add(enchant.getKey().getKey() + " " + level);
		                				    }
		                				    cc.listitemadd(listitem, slot, ItemType, itemMeta, shulkerItem, enchantlist, null, 1);
	                					}
									} else {
										cc.listitemadd(listitem, slot, ItemType, itemMeta, shulkerItem, null, null, 2);
	                				}
									Bukkit.getServer().getConsoleSender().sendMessage(listitem.toString());
								}
							}
						}
					} else {
						Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] The specified player does not have a Shulker Box in slot " + slot);
					}
				}
			} else {
				Bukkit.getServer().getConsoleSender().sendMessage("§cUsage: /shulkerview <player_name> <slot_number>");
			}
		} else {
			sender.sendMessage("§c[INVSEECONSOLE] You are not authorized to use this command!");
		}
		return false;
	}

}
