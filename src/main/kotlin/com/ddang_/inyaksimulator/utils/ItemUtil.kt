package com.ddang_.inyaksimulator.utils

import com.google.common.collect.Sets
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemUtil {
    companion object {

        val repairableList = Sets.newHashSet<Material>(
            Material.GOLD_SWORD,
            Material.IRON_SWORD,
            Material.DIAMOND_SWORD,
            Material.STONE_SWORD,
            Material.WOOD_SWORD,

            Material.STONE_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.GOLD_PICKAXE,
            Material.IRON_PICKAXE,
            Material.WOOD_PICKAXE,

            Material.DIAMOND_HOE,
            Material.GOLD_HOE,
            Material.IRON_HOE,
            Material.STONE_HOE,
            Material.WOOD_HOE,

            Material.DIAMOND_AXE,
            Material.IRON_AXE,
            Material.GOLD_AXE,
            Material.STONE_AXE,
            Material.WOOD_AXE,

            Material.DIAMOND_SPADE,
            Material.GOLD_SPADE,
            Material.STONE_SPADE,
            Material.IRON_SPADE,
            Material.WOOD_SPADE
        )

        fun checkRepairable(item: ItemStack): Boolean {
            return repairableList.contains(item.type)
        }

        fun giveUnbreakableIfAbsent(item: ItemStack): ItemStack {

            val meta = item.itemMeta.apply {
                if (!isUnbreakable) {
                    isUnbreakable = true
                }
            }
            item.itemMeta = meta

            return item
        }
    }
}