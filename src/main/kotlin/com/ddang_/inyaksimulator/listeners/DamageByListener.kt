package com.ddang_.inyaksimulator.listeners

import com.ddang_.inyaksimulator.Inyaksimulator.Companion.broad
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageByListener: Listener {
    @EventHandler
    fun onDamageBy(e: EntityDamageByEntityEvent) {
        val victim = e.entity
        val damager = e.damager

        if (damager !is Player) {
            return
        }
    }
}