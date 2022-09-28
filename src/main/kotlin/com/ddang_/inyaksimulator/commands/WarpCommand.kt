package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.managers.WarpManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WarpCommand: CommandExecutor {
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

            val warpList = WarpManager.warpList
            val list = arrayListOf<String>()
            for (warp in warpList) {
                list.add(warp.name)
            }
            var warpArray = ""
            for (warpName in list) {
                if (warpArray.isNotEmpty()) {
                    warpArray = "$warpArray, $warpName"
                    continue
                }
                warpArray = warpName
            }

            if (warpArray.isEmpty()) {
                warpArray = "§c저장된 워프가 없습니다. /setwarp 로 워프를 만드세요."
            }

            p.sendMessage("")
            p.sendMessage("§6§l  워프 §7[<warp>] 적으면 해당 워프로 이동합니다.")
            p.sendMessage("§6§l             워프 목록")
            p.sendMessage("§f${warpArray}")

            return false
        }

        when (arg.size) {
            1 -> {
                val warp = WarpManager.getWarp(arg[0]) ?: kotlin.run {
                    p.sendMessage("§c§l  워프 §f해당 이름의 워프가 존재하지 않습니다.")
                    return false
                }

                p.teleport(warp.loc)
                p.sendMessage("§a§l  워프 §6${warp.name}§f으(로) 성공적으로 이동했습니다.")

            }
            else -> {
                p.sendMessage("§c§l  워프 §f인자가 너무 많습니다. §7/warp [<warp>]")
                return false
            }
        }

        return false
    }
}