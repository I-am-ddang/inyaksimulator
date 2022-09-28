package com.ddang_.inyaksimulator.commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SCommand: CommandExecutor {
    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {

        val p = sender

        if (p !is Player) {
            return false
        }

        val arg = args ?: return false

        if (arg.isEmpty()) {

            p.sendMessage("")
            p.sendMessage("§6§l  자신에게 텔레포트 §f/s <player>")
            p.sendMessage("§6§l  자신에게 텔레포트 §7대상을 자신의 위치로 이동시킵니다.")

            return false
        }

        when (arg.size) {
            1 -> {
                val targetPlayer = Bukkit.getPlayerExact(arg[0]) ?: kotlin.run {
                    p.sendMessage("§c§l  자신에게 텔레포트 §f대상이 서버에 존재하지 않습니다.")
                    return false
                }

                targetPlayer.teleport(p.location)

            }
            else -> {
                p.sendMessage("§c§l  자신에게 텔레포트 §f인자가 너무 많습니다. §7/s <player>")
                return false
            }
        }

        return false
    }
}