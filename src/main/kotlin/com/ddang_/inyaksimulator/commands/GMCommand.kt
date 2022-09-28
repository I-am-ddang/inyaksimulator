package com.ddang_.inyaksimulator.commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GMCommand: CommandExecutor {
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
            p.sendMessage("§6§l  게임모드 변경 §f/gm <0/1/2/3> [<player>]")
            p.sendMessage("§6§l  게임모드 변경 §7[<player>] 부분 입력 안하면 자신의 게임모드를 변경합니다.")
            p.sendMessage("§6§l  게임모드 변경 §7[<player>] 부분 입력하면 해당 플레이어의 게임모드를 변경합니다.")
            p.sendMessage("")
            p.sendMessage("§6§l  게임모드 변경 §70: 서바이벌 / 1: 크리에이티브 / 2: 모험 / 3: 관전자")
            
            return false
        }

        when (arg.size) {
            1 -> {
                when (arg[0]) {
                    "0" -> {
                        p.gameMode = GameMode.SURVIVAL
                        p.sendMessage("§a§l  게임모드 변경 §f자신의 게임모드를 서바이벌로 변경했습니다.")
                    }
                    "1" -> {
                        p.gameMode = GameMode.CREATIVE
                        p.sendMessage("§a§l  게임모드 변경 §f자신의 게임모드를 크리에이티브 모드로 변경했습니다.")
                    }
                    "2" -> {
                        p.gameMode = GameMode.ADVENTURE
                        p.sendMessage("§a§l  게임모드 변경 §f자신의 게임모드를 모험 모드로 변경했습니다.")
                    }
                    "3" -> {
                        p.gameMode = GameMode.SPECTATOR
                        p.sendMessage("§a§l  게임모드 변경 §f자신의 게임모드를 관전자 모드로 변경했습니다.")
                    }
                    else -> {
                        p.sendMessage("§c§l  게임모드 변경 §f0, 1, 2, 3 중 하나를 입력해주세요.")
                        return false
                    }
                }
            }
            2 -> {
                val targetPlayer = Bukkit.getPlayerExact(arg[1]) ?: kotlin.run {
                    p.sendMessage("§c§l  게임모드 변경 §f대상이 서버에 존재하지 않습니다.")
                    return false
                }
                when (arg[0]) {
                    "0" -> {
                        targetPlayer.gameMode = GameMode.SURVIVAL
                        p.sendMessage("§a§l  게임모드 변경 §6${targetPlayer.name}§f의 게임모드를 서바이벌로 변경했습니다.")
                    }
                    "1" -> {
                        targetPlayer.gameMode = GameMode.CREATIVE
                        p.sendMessage("§a§l  게임모드 변경 §6${targetPlayer.name}§f의 게임모드를 크리에이티브 모드로 변경했습니다.")
                    }
                    "2" -> {
                        targetPlayer.gameMode = GameMode.ADVENTURE
                        p.sendMessage("§a§l  게임모드 변경 §6${targetPlayer.name}§f의 게임모드를 모험 모드로 변경했습니다.")
                    }
                    "3" -> {
                        targetPlayer.gameMode = GameMode.SPECTATOR
                        p.sendMessage("§a§l  게임모드 변경 §6${targetPlayer.name}§f의 게임모드를 관전자 모드로 변경했습니다.")
                    }
                    else -> {
                        p.sendMessage("§c§l  게임모드 변경 §f0, 1, 2, 3 중 하나를 입력해주세요.")
                        return false
                    }
                }
            }
            else -> {
                p.sendMessage("§c§l  게임모드 변경 §f인자가 너무 많습니다. §7/gm <0/1/2/3> [<player>]")
                return false
            }
        }

        return false
    }
}