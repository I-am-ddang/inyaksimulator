package com.ddang_.inyaksimulator.commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LoreCommand: CommandExecutor {
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
            p.sendMessage("§6§l  로어 설정 §f/lore 추가 <값>")
            p.sendMessage("§6§l  로어 설정 §7들고 있는 아이템의 로어에 추가 로어를 한 줄 더 적습니다.")
            p.sendMessage("§6§l  로어 설정 §f/lore 삭제 <줄>")
            p.sendMessage("§6§l  로어 설정 §7들고 있는 아이템의 로어 중 <줄> 에 있는 값을 제거합니다.")
            p.sendMessage("§6§l  로어 설정 §f/lore 수정 <줄> <값>")
            p.sendMessage("§6§l  로어 설정 §7들고 있는 아이템의 로어에 특정 <줄> 번째의 값을 <값>으로 바꿉니다.")

            return false
        }

        if (arg.size == 1) {
            when (arg[0]) {
                "추가" -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f명령어가 완전하지 않습니다. §7/lore 추가 <값>")

                    return false
                }
                "삭제" -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f명령어가 완전하지 않습니다. §7/lore 삭제 <줄>")
                    return false
                }
                "수정" -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f명령어가 완전하지 않습니다. §7/lore 수정 <줄> <값>")
                    return false
                }
                else -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f/lore 추가 <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 추가 로어를 한 줄 더 적습니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 삭제 <줄>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어 중 <줄> 에 있는 값을 제거합니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 수정 <줄> <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 특정 <줄> 번째의 값을 <값>으로 바꿉니다.")
                    return false
                }
            }
        } else if (arg.size == 2) {
            when (arg[0]) {
                "추가" -> {

                    if (p.inventory.itemInMainHand.type == Material.AIR) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f아이템을 들고 있어야 합니다. §7/lore 추가 <값>")
                        return false
                    }

                    val value = arg[1].replace("&", "§")

                    val item = p.inventory.itemInMainHand
                    val meta = item.itemMeta
                    val lore = meta.lore
                    val newLore = mutableListOf<String>()

                    if (lore == null) {
                        newLore.add("§f$value")
                        meta.lore = newLore
                        item.itemMeta = meta
                        p.sendMessage("")
                        p.sendMessage("§a§l  로어 설정 §f아이템에 로어를 적용했습니다.")
                        p.sendMessage("§a§l  로어 설정 §7-> $value")
                        return false
                    }

                    for (el in lore) {
                        newLore.add(el)
                    }
                    newLore.add("§f$value")

                    meta.lore = newLore
                    item.itemMeta = meta

                    p.sendMessage("")
                    p.sendMessage("§a§l  로어 설정 §f아이템에 로어를 적용했습니다.")
                    p.sendMessage("§a§l  로어 설정 §7-> $value")
                    return false
                }
                "삭제" -> {

                    if (p.inventory.itemInMainHand.type == Material.AIR) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f아이템을 들고 있어야 합니다. §7/lore 삭제 <줄>")
                        return false
                    }

                    val line = arg[1].toIntOrNull() ?: kotlin.run {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f<줄> 에는 숫자를 입력해야합니다. §7/lore 삭제 <줄>")
                        return false
                    }

                    val displayLine = line-1
                    if (displayLine < 0) {
                        p.sendMessage("")
                        p.sendMessage("§a§l  로어 설정 §f숫자는 1부터 입력해주세요. §7/lore 삭제 <줄>")
                        return false
                    }

                    val item = p.inventory.itemInMainHand
                    val meta = item.itemMeta
                    val lore = meta.lore
                    val newLore = mutableListOf<String>()

                    if (lore == null) {
                        p.sendMessage("")
                        p.sendMessage("§a§l  로어 설정 §f아이템의 로어가 없습니다")
                        return false
                    }

                    if (displayLine > lore.size-1) {
                        p.sendMessage("")
                        p.sendMessage("§a§l  로어 설정 §f로어의 최대 줄은 ${lore.size-1} 줄 입니다.§7/lore 삭제 <줄>")
                        return false
                    }

                    for (l in lore) {
                        newLore.add(l)
                    }

                    newLore.removeAt(displayLine)

                    meta.lore = newLore
                    item.itemMeta = meta

                    p.sendMessage("")
                    p.sendMessage("§a§l  로어 설정 §f아이템의 로어 중 $line 번째의 로어를 지웠습니다.")
                    return false
                }
                "수정" -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f명령어가 불완전합니다. §7/lore 수정 <줄> <값>")
                    return false
                }
                else -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f/lore 추가 <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 추가 로어를 한 줄 더 적습니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 삭제 <줄>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어 중 <줄> 에 있는 값을 제거합니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 수정 <줄> <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 특정 <줄> 번째의 값을 <값>으로 바꿉니다.")
                    return false
                }
            }
        } else if (arg.size > 2) {
            when (arg[0]) {
                "추가" -> {

                    if (p.inventory.itemInMainHand.type == Material.AIR) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f아이템을 들고 있어야 합니다. §7/lore 추가 <값>")
                        return false
                    }

                    var value = ""
                    for (a in 1 until arg.size) {
                        val specificArg = arg[a].replace("&", "§")
                        if (value.isEmpty()) {
                            value = specificArg
                            continue
                        }
                        value = "$value $specificArg"
                    }

                    val item = p.inventory.itemInMainHand
                    val meta = item.itemMeta
                    val lore = meta.lore
                    val newLore = mutableListOf<String>()

                    if (lore == null) {
                        newLore.add("§f$value")
                        meta.lore = newLore
                        item.itemMeta = meta
                        p.sendMessage("")
                        p.sendMessage("§a§l  로어 설정 §f아이템에 로어를 적용했습니다.")
                        p.sendMessage("§a§l  로어 설정 §7-> $value")
                        return false
                    }

                    for (el in lore) {
                        newLore.add(el)
                    }
                    newLore.add("§f$value")

                    meta.lore = newLore
                    item.itemMeta = meta

                    p.sendMessage("")
                    p.sendMessage("§a§l  로어 설정 §f아이템에 로어를 적용했습니다.")
                    p.sendMessage("§a§l  로어 설정 §7-> $value")
                    return false
                }
                "삭제" -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f명령어 인자가 너무 많습니다. §7/lore 삭제 <줄>")
                    return false
                }
                "수정" -> {

                    if (p.inventory.itemInMainHand.type == Material.AIR) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f아이템을 들고 있어야 합니다. §7/lore 수정 <줄> <값>")
                        return false
                    }

                    //arg[0] -> 수정, arg[1] -> 줄, arg[2]~ -> 값

                    val line = arg[1].toIntOrNull() ?: kotlin.run {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f줄은 숫자여야합니다. §7/lore 수정 <줄> <값>")
                        return false
                    }

                    if (line <= 0) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f숫자는 0 보다 커야합니다. §7/lore 수정 <줄> <값>")
                        return false
                    }

                    if (line > arg.size) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f숫자는 로어 줄 수보다 많을 수 없습니다. §7/lore 수정 <줄> <값>")
                        return false
                    }

                    var value = ""
                    for (a in 2 until arg.size) {
                        val specificArg = arg[a].replace("&", "§")
                        if (value.isEmpty()) {
                            value = "§f$specificArg"
                            continue
                        }
                        value = "$value $specificArg"
                    }

                    val item = p.inventory.itemInMainHand
                    val meta = item.itemMeta
                    val lore = meta.lore
                    val newLore = mutableListOf<String>()

                    if (lore == null) {
                        p.sendMessage("")
                        p.sendMessage("§c§l  로어 설정 §f해당 아이템의 로어가 없습니다. §7/lore 수정 <줄> <값>")
                        return false
                    }

                    for (el in lore) {
                        newLore.add(el)
                    }
                    newLore.removeAt(line-1)
                    newLore.add(line-1, "§f$value")

                    meta.lore = newLore
                    item.itemMeta = meta

                    p.sendMessage("")
                    p.sendMessage("§a§l  로어 설정 §f아이템에 로어를 적용했습니다.")
                    p.sendMessage("§a§l  로어 설정 §7-> 줄: $line ->  $value")
                    return false
                }
                else -> {
                    p.sendMessage("")
                    p.sendMessage("§c§l  로어 설정 §f/lore 추가 <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 추가 로어를 한 줄 더 적습니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 삭제 <줄>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어 중 <줄> 에 있는 값을 제거합니다.")
                    p.sendMessage("§c§l  로어 설정 §f/lore 수정 <줄> <값>")
                    p.sendMessage("§c§l  로어 설정 §7들고 있는 아이템의 로어에 특정 <줄> 번째의 값을 <값>으로 바꿉니다.")
                    return false
                }
            }
        }

        return false
    }
}