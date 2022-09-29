package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

class ScoreboardManager {
    companion object {
        fun registerHealth(p: Player) {
            val board = p.scoreboard
            val obj = board.getObjective(Inyaksimulator.instance.config.getString("UI.health.belowNametag")) ?: kotlin.run {
                val obj2 = board.registerNewObjective(Inyaksimulator.instance.config.getString("UI.health.belowNametag"), "health")
                obj2.displaySlot = DisplaySlot.BELOW_NAME
            }
        }
    }
}