package com.ddang_.inyaksimulator.guis

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class CustomGUIHolder: InventoryHolder {
    override fun getInventory(): Inventory? {
        return null
    }

    abstract fun process(e: InventoryClickEvent)
}