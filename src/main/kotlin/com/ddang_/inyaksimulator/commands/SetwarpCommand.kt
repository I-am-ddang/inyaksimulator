package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.managers.WarpManager
import com.ddang_.inyaksimulator.objects.Warp
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetwarpCommand: CommandExecutor {
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
            p.sendMessage("§6§l  워프 지정 §f/setwarp <워프 이름>")

            return false
        }

        when (arg.size) {
            1 -> {
                val warp = WarpManager.getWarp(arg[0])

                return if (warp == null) {
                    
                    val made = Warp(arg[0], p.location)
                    p.sendMessage("§a§l  워프 지정 §6${made.name}§f의 이름을 가진 워프를 성공적으로 만들었습니다.")
                    false
                } else {

                    warp.loc = p.location
                    p.sendMessage("§a§l  워프 지정 §f기존 §6${warp.name}§f 의 위치를 새로 저장했습니다.")
                    false
                }

            }
            else -> {
                p.sendMessage("§c§l  워프 지정 §f인자가 너무 많습니다. §7/warp [<warp>]")
                return false
            }
        }

        return false
    }
}