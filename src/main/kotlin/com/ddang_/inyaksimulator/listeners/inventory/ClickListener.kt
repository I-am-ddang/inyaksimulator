package com.ddang_.inyaksimulator.listeners.inventory

import com.ddang_.inyaksimulator.guis.CustomGUIHolder
import com.ddang_.inyaksimulator.managers.InventoryManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.PlayerInventory

class ClickListener: Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val p = e.whoClicked

        if (p !is Player) {
            return
        }

        if (e.inventory is PlayerInventory) {
            InventoryManager.checkInfiniteDura(p)
        }

        val holder = e.inventory.holder ?: return
        if (holder !is CustomGUIHolder) {
            return
        }
        holder.process(e)
    }
}