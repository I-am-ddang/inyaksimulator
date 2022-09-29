package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.managers.WarpManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InfoComand: CommandExecutor {
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
            p.sendMessage("§6§l  정보 확인 §f/i §7[<player>] 부분 입력하면 해당 플레이어의 정보를 봅니다.")
            p.sendMessage("§6§l          정보 확인 §f: §6${p.name}")
            p.sendMessage("§f")

            return false
        }

        when (arg.size) {
            1 -> {

                val arg1 = arg[0]
                val target = Bukkit.getPlayerExact(arg1) ?: kotlin.run {
                    p.sendMessage("§c§l  정보 확인 §c${arg1}§f은(는) 서버에 존재하지 않은 플레이어입니다.")
                    return false
                }

                p.sendMessage("§a§l  정보 확인 §f: §6${target.name}")

            }
            else -> {
                p.sendMessage("§c§l  정보 확인 §f인자가 너무 많습니다. §7/info [<player>]")
                return false
            }
        }

        return false
    }
}