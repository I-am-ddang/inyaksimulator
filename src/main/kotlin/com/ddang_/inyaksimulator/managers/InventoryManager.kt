package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.utils.ItemUtil
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class InventoryManager {
    companion object {
        fun checkInfiniteDura(p: Player) {
            val pInv = p.inventory.contents ?: return
            val pArmor = p.inventory.armorContents ?: return

            val list = arrayListOf<ItemStack>()
            for (i in pInv) {
                if (i == null) continue
                list.add(i)
            }
            for (i in pArmor) {
                if (i == null) continue
                list.add(i)
            }

            for (targetItem in list) {
                val meta = targetItem.itemMeta
                val lore = meta.lore ?: continue

                for (line in lore) {
                    if (line.contains(Inyaksimulator.instance.config.getString("Keyword.InfiniteDurability"))) {
                        ItemUtil.giveUnbreakableIfAbsent(targetItem)
                    }
                }
            }
        }
    }
}