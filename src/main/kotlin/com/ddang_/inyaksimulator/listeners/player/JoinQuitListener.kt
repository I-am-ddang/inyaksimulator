package com.ddang_.inyaksimulator.listeners.player

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.managers.MemberManager
import com.ddang_.inyaksimulator.managers.PlayerListManager
import com.ddang_.inyaksimulator.managers.ScoreboardManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuitListener: Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player

        p.scoreboard = Inyaksimulator.board

        MemberManager.set(p)

        ScoreboardManager.registerHealth(p)

        PlayerListManager.set(p)
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val p = e.player

        MemberManager.save(p)
    }
}