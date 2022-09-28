package com.ddang_.inyaksimulator.commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TPCommand: CommandExecutor {
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
            p.sendMessage("§6§l  텔레포트 §f/tp <player> [<player>]")
            p.sendMessage("§6§l  텔레포트 §7[<player>] 부분 입력 안하면 <player> 의 위치로 이동합니다.")
            p.sendMessage("§6§l  텔레포트 §7[<player>] 부분 입력하면 <player> 를 [<player>] 의 위치로 이동시킵니다.")

            return false
        }

        when (arg.size) {
            1 -> {
                val targetPlayer = Bukkit.getPlayerExact(arg[0]) ?: kotlin.run {
                    p.sendMessage("§c§l  텔레포트 §f대상이 서버에 존재하지 않습니다.")
                    return false
                }

                p.teleport(targetPlayer.location)
                
            }
            2 -> {
                val targetPlayer = Bukkit.getPlayerExact(arg[0]) ?: kotlin.run {
                    p.sendMessage("§c§l  텔레포트 §f첫번째 대상이 서버에 존재하지 않습니다.")
                    return false
                }

                val targetPlayer2 = Bukkit.getPlayerExact(arg[1]) ?: kotlin.run {
                    p.sendMessage("§c§l  텔레포트 §f두번째 대상이 서버에 존재하지 않습니다.")
                    return false
                }

                targetPlayer.teleport(targetPlayer2.location)
                p.sendMessage("§a§l  텔레포트 §6${targetPlayer.name} §f을(를) §6${targetPlayer2.name} §f에게 옮겼습니다.")
            }
            else -> {
                p.sendMessage("§c§l  텔레포트 §f인자가 너무 많습니다. §7/tp <player> [<player>]")
                return false
            }
        }

        return false
    }
}