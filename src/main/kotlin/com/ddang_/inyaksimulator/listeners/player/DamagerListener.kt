package com.ddang_.inyaksimulator.listeners.player

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.managers.MemberManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class DamagerListener: Listener {
    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        val p = e.entity

        if (p !is Player) {
            return
        }

        val m = MemberManager.getMember(p.name) ?: return
        if (m.spawnCount <= 0) {
            return
        }

        //스폰 귀환 중 피해를 입음
        m.spawnCount = -1
        m.recallLocation = null

        p.sendMessage(Inyaksimulator.instance.config.getString("Message.spawn.cancel.playerDamage"))
    }
}