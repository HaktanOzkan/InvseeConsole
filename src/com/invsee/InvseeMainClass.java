package com.invsee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class InvseeMainClass extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] Hello, my name is Haktan. Thank you all for using my plugin. Please don't forget to give stars from spigotmc.org :)");
		getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] InvseeConsole plugin is active and working!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if(command.getName().equalsIgnoreCase("invseeconsole")) {
				if(args.length > 0) {
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
	                if (target == null) {
	                	getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] Please enter an online player name.");
	                	getServer().getConsoleSender().sendMessage("§cUsage: /invseeconsole <player_name>");
	                } else {
	                	Inventory inv = target.getInventory();
	                	if(inv.isEmpty()) {
	                		getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] The inventory of player §f" + target.getName() + "§c is completely empty!");
	                	} else {
	                		for (int slot = 0; slot < inv.getSize(); slot++) {
	                			ItemStack itemStack = inv.getItem(slot);
	                			if (itemStack != null) {
	                				ItemMeta itemMeta = itemStack.getItemMeta();
	                				String ItemType = itemStack.getType().name();
	                				List<String> listitem = new ArrayList<>();
	                				if (itemMeta.hasEnchants()) {
	                					if(itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) || itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS) ||
	                							itemMeta.hasItemFlag(ItemFlag.HIDE_DYE) || itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) ||
	                							itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON) || itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS) ||
	                							itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
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
		                				    listitem.add("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + ItemType +
		                				    		"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
		                				    		"] [§6AMOUNT:§r " + itemStack.getAmount() +
		        	                				"] [§cENCHANTS: §r" + enchantlist.toString() +
		        	                				"] [§bLORE: §r" + itemMeta.getLore() +
		        	                				"] [§dITEM_FLAG: §r" + itemFlagList);
	                					} else {
	                						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
		                				    List<String> enchantlist = new ArrayList<>();
		                				    for (Enchantment enchant : enchants.keySet()) {
		                				        int level = enchants.get(enchant);
		                				        enchantlist.add(enchant.getKey().getKey() + " " + level);
		                				    }
	                						listitem.add("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + ItemType +
		                							"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
		                							"] [§6AMOUNT:§r " + itemStack.getAmount() +
		        	                				"] [§cENCHANTS: §r" + enchantlist.toString() +
		        	                				"] [§bLORE: §r" + itemMeta.getLore() +
		        	                				"] [§dITEM_FLAG: §r" + "null");
	                					}
	                				} else {
	                					listitem.add("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + ItemType +
	                							"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
	                							"] [§6AMOUNT:§r " + itemStack.getAmount() +
	        	                				"] [§cENCHANTS: §r" + "null" +
	        	                				"] [§bLORE: §r" + itemMeta.getLore() +
	        	                				"] [§dITEM_FLAG: §r" + "null");
	                				}
		                			getServer().getConsoleSender().sendMessage(listitem.toString());
	                			}
	                		}
	                	}
	                }
				} else {
                	getServer().getConsoleSender().sendMessage("§cUsage: /invseeconsole <player_name>");
				}
			}
			if(command.getName().equalsIgnoreCase("shulkerview")) {
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
		                					if(itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) || itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS) ||
		                							itemMeta.hasItemFlag(ItemFlag.HIDE_DYE) || itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) ||
		                							itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON) || itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS) ||
		                							itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
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
			                				    listitem.add("[§6ITEM_TPYE: §r" + ItemType +
			                				    		"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
			                				    		"] [§6AMOUNT:§r " + shulkerItem.getAmount() +
			        	                				"] [§cENCHANTS: §r" + enchantlist.toString() +
			        	                				"] [§bLORE: §r" + itemMeta.getLore() +
			        	                				"] [§dITEM_FLAG: §r" + itemFlagList);
		                					} else {
		                						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
			                				    List<String> enchantlist = new ArrayList<>();
			                				    for (Enchantment enchant : enchants.keySet()) {
			                				        int level = enchants.get(enchant);
			                				        enchantlist.add(enchant.getKey().getKey() + " " + level);
			                				    }
		                						listitem.add("[§6ITEM_TPYE: §r" + ItemType +
			                							"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
			                							"] [§6AMOUNT:§r " + shulkerItem.getAmount() +
			        	                				"] [§cENCHANTS: §r" + enchantlist.toString() +
			        	                				"] [§bLORE: §r" + itemMeta.getLore() +
			        	                				"] [§dITEM_FLAG: §r" + "null");
		                					}
		                				} else {
		                					listitem.add("[§6ITEM_TPYE: §r" + ItemType +
		                							"] [§aNAME:§r " + itemMeta.getDisplayName().toString() +
		                							"] [§6AMOUNT:§r " + shulkerItem.getAmount() +
		        	                				"] [§cENCHANTS: §r" + "null" +
		        	                				"] [§bLORE: §r" + itemMeta.getLore() +
		        	                				"] [§dITEM_FLAG: §r" + "null");
		                				}
										getServer().getConsoleSender().sendMessage(listitem.toString());
									}
								}
							} 
						} else {
							sender.sendMessage("§c[INVSEECONSOLE] The specified player does not have a Shulker Box in slot " + slot);
						}
					}
				} else {
					getServer().getConsoleSender().sendMessage("§cUsage: /shulkerview <player_name> <slot_number>");
				}
			}
			if(command.getName().equalsIgnoreCase("shulkerreset")) {
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
								getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] All items in the player's shulkerbox in " + slot + " slot have been deleted. A list of deleted items >>");
								for (ItemStack shulkerItem : shulkerInventory.getContents()) {
									if (shulkerItem != null && !shulkerItem.getType().equals(Material.AIR)) {
										shulkerItem.setAmount(0);
									}
								}
								blockMeta.setBlockState(shulkerBox);
								item.setItemMeta(blockMeta);
							}
						} else {
							sender.sendMessage("§c[INVSEECONSOLE] The specified player does not have a Shulker Box in slot " + slot);
						}
					}
				} else {
					getServer().getConsoleSender().sendMessage("§cUsage: /shulkerreset <player_name> <slot_number>");
				}
			}
		} else {
			Player p = (Player) sender;
			if(p.hasPermission("invsee.console.command")) {
				p.sendMessage("§a[INVSEECONSOLE] §fYou can only use this command from the console.");
			} else {
				p.sendMessage("§a[INVSEECONSOLE] §fThis command can only be used from the console and you are not permission !");
			}
		}
		return false;
	}
	
	
}
