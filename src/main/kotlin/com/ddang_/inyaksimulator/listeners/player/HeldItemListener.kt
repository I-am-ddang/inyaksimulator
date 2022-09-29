package com.ddang_.inyaksimulator.listeners.player

import com.ddang_.inyaksimulator.managers.InventoryManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent

class HeldItemListener: Listener {
    @EventHandler
    fun onHeldItem(e: PlayerItemHeldEvent) {
        val p = e.player
        val item = p.inventory.getItem(e.newSlot) ?: return

        InventoryManager.checkInfiniteDura(p)
    }
}