package com.ddang_.inyaksimulator.listeners

import com.ddang_.inyaksimulator.Inyaksimulator.Companion.broad
import com.ddang_.inyaksimulator.managers.MemberManager
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

        val m = MemberManager.getMember(damager.name) ?: return

        damager.sendMessage("스탯 적용 후 피해량: ${e.damage}")

        e.damage = e.damage + m.loreStat.attack

        damager.sendMessage("스탯 적용 후 피해량: ${e.damage}")
    }
}