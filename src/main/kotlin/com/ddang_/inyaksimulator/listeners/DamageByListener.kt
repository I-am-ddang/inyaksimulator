package com.ddang_.inyaksimulator.listeners

import com.ddang_.inyaksimulator.Inyaksimulator.Companion.broad
import com.ddang_.inyaksimulator.managers.MemberManager
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import kotlin.random.Random

class DamageByListener: Listener {
    @EventHandler
    fun onDamageBy(e: EntityDamageByEntityEvent) {
        val victim = e.entity
        val damager = e.damager

        if (damager is Player) {
            val m = MemberManager.getMember(damager.name) ?: return

            e.damage = e.damage + m.loreStat.attack

            val drainChance = Random.nextInt(100)
            if (drainChance > m.loreStat.healthDrainChance) {
                return
            }
            //흡혈 발동
            val drainAmount = m.loreStat.healthDrainAmount

            val maxHealth = damager.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue
            if (damager.health + drainAmount > maxHealth) {
                damager.health = maxHealth
                return
            }
            damager.health += drainAmount
        }

        if (victim is Player) {

            val m = MemberManager.getMember(victim.name) ?: return

            e.damage = e.damage * m.loreStat.defence/100
        }
    }
}