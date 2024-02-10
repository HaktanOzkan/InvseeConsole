package com.invsee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvConsoleCommand implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			if(args.length > 0) {
				Player target = Bukkit.getServer().getPlayerExact(args[0]);
				if (target == null) {
					Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] Please enter an online player name.");
					Bukkit.getServer().getConsoleSender().sendMessage("§cUsage: /invseeconsole <player_name>");
                } else {
                	if(args.length > 1) {
                		if(args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("delete")) {
                			if(args.length > 2) {
                				int slot = Integer.parseInt(args[2]);
                				slotDelete(target, slot);
                			} else {
                				Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> del/delete <slot>");
                			}
                        } else if(args[1].equalsIgnoreCase("delall") || args[1].equalsIgnoreCase("deleteall")) {
                        	target.getInventory().clear();
                        	Bukkit.getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] All items have been deleted from §f" + target.getName() + "§a's inventory.");
                        } else if(args[1].equalsIgnoreCase("replace")) {
                        	if(args.length > 2) {
                        		int slot = Integer.parseInt(args[2]);
                        		if(args.length > 3) {
                        			Material material = Material.getMaterial(args[3]);
                        			if (material != null) {
                            			if(args.length > 4) {
                            				int amount = Integer.parseInt(args[4]);
                            				if(amount == 0) {
                            					Bukkit.getServer().getConsoleSender().sendMessage("§cIt is not possible to replace the §f" + target.getInventory().getItem(slot) + " §citem in slot §f" + slot + " §cof the players inventory with 0 quantity of §f" + material + " §citem! Please enter a number greater than 0!");
                            				} else {
                                    			ItemStack stack = new ItemStack(material, amount);
                                                target.getInventory().setItem(slot, stack);
                                                Bukkit.getServer().getConsoleSender().sendMessage("§cThe inventory slot §f" + slot + " §cof the player has been arranged to contain the §f" + material.toString() + " §citem!");
                            				}
                            			} else {
                                			Bukkit.getServer().getConsoleSender().sendMessage("§aThis allows you to replace the item in the specified slot number of the player's inventory with another item.");
                                            Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> replace <slot_number> <item_material> <amount>");
                                		}
                        			} else {
                        				Bukkit.getServer().getConsoleSender().sendMessage("§cThe item you specified is not a material name. Please try again.");
                        				Bukkit.getServer().getConsoleSender().sendMessage("§chttps://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html");
                        			}
                        		} else {
                        			Bukkit.getServer().getConsoleSender().sendMessage("§aThis allows you to replace the item in the specified slot number of the player's inventory with another item.");
                                    Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> replace <slot_number> <item_material> <amount>");
                        		}
                        	} else {
                        		Bukkit.getServer().getConsoleSender().sendMessage("§aThis allows you to replace the item in the specified slot number of the player's inventory with another item.");
                        		Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> replace <slot_number> <item_material> <amount>");
                        		}
                        } else {
                        	Bukkit.getServer().getConsoleSender().sendMessage("§aAll usage codes for the InvseeConsole plugin are as follows:");
                        	Bukkit.getServer().getConsoleSender().sendMessage("");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> - This allows you to see all items in the player's inventory.");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> del/delete <slot_number> - This allows you to delete the item in the specified slot of the player's inventory.");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> delall/deletall - This allows you to delete all items in the player's inventory.");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> replace <slot_number> <item_material> <amount> - This allows you to replace the item in the specified slot of the player's inventory with another item of your choice.");
                        	Bukkit.getServer().getConsoleSender().sendMessage("");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /shulkerview <player_name> <slot_number> - This shows the items inside the shulker box item in the specified slot of the player's inventory.");
                        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /shulkerreset <player_name> <slot_number> - This clears the contents of the shulker box item in the specified slot of the player's inventory.");
                        }
                	} else {
                		inventorySee(target);
                	}
                }
			} else {Bukkit.getServer().getConsoleSender().sendMessage("§aAll usage codes for the InvseeConsole plugin are as follows:");
        	Bukkit.getServer().getConsoleSender().sendMessage("");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> - This allows you to see all items in the player's inventory.");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> del/delete <slot_number> - This allows you to delete the item in the specified slot of the player's inventory.");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> delall/deletall - This allows you to delete all items in the player's inventory.");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /invseeconsole <player_name> replace <slot_number> <item_material> <amount> - This allows you to replace the item in the specified slot of the player's inventory with another item of your choice.");
        	Bukkit.getServer().getConsoleSender().sendMessage("");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /shulkerview <player_name> <slot_number> - This shows the items inside the shulker box item in the specified slot of the player's inventory.");
        	Bukkit.getServer().getConsoleSender().sendMessage("§cUSAGE: /shulkerreset <player_name> <slot_number> - This clears the contents of the shulker box item in the specified slot of the player's inventory.");}
		} else {sender.sendMessage("§c[INVSEECONSOLE] You are not authorized to use this command!");}
		return false;
	}
	
	private void slotDelete(Player player, int slot) {
		ItemStack item = player.getInventory().getItem(slot);
		if (item == null || item.getType() == Material.AIR) {
			Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] Item does not exist in §f" + player.getName() + "§c's inventory.");
		} else {
			player.getInventory().setItem(slot, null);
			player.updateInventory();
			Bukkit.getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] Item §f" + item.getType().name() + "§a has been deleted from §f" + player.getName() + "§a's inventory.");
		}
	}
	
	private void inventorySee(Player player) {
		CheckClass cc = new CheckClass();
		Inventory inv = player.getInventory();
		if(inv.isEmpty()) {Bukkit.getServer().getConsoleSender().sendMessage("§c[INVSEECONSOLE] The inventory of player §f" + player.getName() + "§c is completely empty!");
		} else {
    		for (int slot = 0; slot < inv.getSize(); slot++) {
    			ItemStack itemStack = inv.getItem(slot);
    			if (itemStack != null) {
    				ItemMeta itemMeta = itemStack.getItemMeta();
    				String ItemType = itemStack.getType().name();
    				List<String> listitem = new ArrayList<>();
    				if (itemMeta.hasEnchants()) {
    					if(cc.itemflagcheck(itemMeta) == true) {
    						Set<ItemFlag> itemFlags = itemMeta.getItemFlags();
    						List<String> itemFlagList = new ArrayList<>();
    						for (ItemFlag itemFlag : itemFlags) {itemFlagList.add(itemFlag.name());}
    						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
        				    List<String> enchantlist = new ArrayList<>();
        				    for (Enchantment enchant : enchants.keySet()) {int level = enchants.get(enchant); enchantlist.add(enchant.getKey().getKey() + " " + level);}
        				    cc.listitemadd(listitem, slot, ItemType, itemMeta, itemStack, enchantlist, itemFlagList, 0);
    					} else {
    						Map<Enchantment, Integer> enchants = itemMeta.getEnchants();
        				    List<String> enchantlist = new ArrayList<>();
        				    for (Enchantment enchant : enchants.keySet()) {int level = enchants.get(enchant); enchantlist.add(enchant.getKey().getKey() + " " + level);}
        				    cc.listitemadd(listitem, slot, ItemType, itemMeta, itemStack, enchantlist, null, 1);
    					}
    				} else {cc.listitemadd(listitem, slot, ItemType, itemMeta, itemStack, null, null, 2);}
    				Bukkit.getServer().getConsoleSender().sendMessage(listitem.toString());
    			}
    		}
    	}
	}
}
