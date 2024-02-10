package com.invsee;

import java.util.List;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CheckClass {
	
	public void listitemadd(List<String> listitem, int slot, String itemType, ItemMeta itemmeta, ItemStack itemStack, List<String> enchantList, List<String> itemFlagList, int num) {
		if(num == 0) {
			listitem.add(("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + itemType +"] [§aNAME:§r " + itemmeta.getDisplayName().toString() +"] [§6AMOUNT:§r " + itemStack.getAmount() +"] [§cENCHANTS: §r" + enchantList.toString() +"] [§bLORE: §r" + itemmeta.getLore() +"] [§dITEM_FLAG: §r" + itemFlagList));
		} else if(num == 1) {
			listitem.add("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + itemType +"] [§aNAME:§r " + itemmeta.getDisplayName().toString() +"] [§6AMOUNT:§r " + itemStack.getAmount() +"] [§cENCHANTS: §r" + enchantList.toString() +"] [§bLORE: §r" + itemmeta.getLore() +"] [§dITEM_FLAG: §r" + "null");
		} else if(num == 2) {
			listitem.add("[SLOT: "+ slot + "] [§6ITEM_TPYE: §r" + itemType +"] [§aNAME:§r " + itemmeta.getDisplayName().toString() +"] [§6AMOUNT:§r " + itemStack.getAmount() +"] [§cENCHANTS: §r" + "null" +"] [§bLORE: §r" + itemmeta.getLore() +"] [§dITEM_FLAG: §r" + "null");
		}
}
	
	public boolean itemflagcheck(ItemMeta itemMeta) {
		if(itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES) || itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS) || itemMeta.hasItemFlag(ItemFlag.HIDE_DYE) || itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) || itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON) || itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS) || itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
			return true;
		} else {
			return false;
		}
	}

}
