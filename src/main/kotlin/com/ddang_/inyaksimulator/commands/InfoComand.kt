package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.managers.MemberManager
import com.ddang_.inyaksimulator.managers.WarpManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot

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

            val m = MemberManager.getMember(p.name) ?: return false

            p.sendMessage("")
            p.sendMessage("§6§l  정보 확인 §f/i §7[<player>] 부분 입력하면 해당 플레이어의 정보를 봅니다.")
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.title").replace("%playerName%", p.name))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.helmetName").replace("%itemName%",
                p.inventory.getItem(EquipmentSlot.HEAD)?.itemMeta?.displayName ?: "없음"
            ))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.chestplateName").replace("%itemName%",
                p.inventory.getItem(EquipmentSlot.CHEST)?.itemMeta?.displayName ?: "없음"
            ))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.leggingsName").replace("%itemName%",
                p.inventory.getItem(EquipmentSlot.LEGS)?.itemMeta?.displayName ?: "없음"
            ))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.bootsName").replace("%itemName%",
                p.inventory.getItem(EquipmentSlot.FEET)?.itemMeta?.displayName ?: "없음"
            ))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.swordName").replace("%itemName%",
                p.inventory.getItem(EquipmentSlot.HAND)?.itemMeta?.displayName ?: "없음"
            ))
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.totalStatTop"))
            p.sendMessage(
                Inyaksimulator.instance.config.getString("Message.info.totalStatBelow")
                    .replace("%attack%", "${m.loreStat.attack}")
                    .replace("%defence%", "${m.loreStat.defence}")
                    .replace("%health%", "${m.loreStat.moreHealth}")
                    .replace("%drainChance%", "${m.loreStat.healthDrainChance}")
                    .replace("%drainAmount%", "${m.loreStat.healthDrainAmount}")
            )


            return false
        }

        when (arg.size) {
            1 -> {

                val arg1 = arg[0]
                val target = Bukkit.getPlayerExact(arg1) ?: kotlin.run {
                    p.sendMessage("§c§l  정보 확인 §c${arg1}§f은(는) 서버에 존재하지 않은 플레이어입니다.")
                    return false
                }

                val m = MemberManager.getMember(target.name) ?: return false

                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.title").replace("%playerName%", target.name))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.helmetName").replace("%itemName%", "${target.inventory.getItem(EquipmentSlot.HEAD).itemMeta?.displayName}"))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.chestplateName").replace("%itemName%", "${target.inventory.getItem(EquipmentSlot.CHEST).itemMeta?.displayName}"))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.leggingsName").replace("%itemName%", "${target.inventory.getItem(EquipmentSlot.LEGS).itemMeta?.displayName}"))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.bootsName").replace("%itemName%", "${target.inventory.getItem(EquipmentSlot.FEET).itemMeta?.displayName}"))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.swordName").replace("%itemName%", "${target.inventory.getItem(EquipmentSlot.HAND).itemMeta?.displayName}"))
                p.sendMessage(Inyaksimulator.instance.config.getString("Message.info.totalStatTop"))
                p.sendMessage(
                    Inyaksimulator.instance.config.getString("Message.info.totalStatBelow")
                        .replace("%attack%", "${m.loreStat.attack}")
                        .replace("%defence%", "${m.loreStat.defence}")
                        .replace("%health%", "${m.loreStat.moreHealth}")
                        .replace("%drainChance%", "${m.loreStat.healthDrainChance}")
                        .replace("%drainAmount%", "${m.loreStat.healthDrainAmount}")
                )

            }
            else -> {
                p.sendMessage("§c§l  정보 확인 §f인자가 너무 많습니다. §7/info [<player>]")
                return false
            }
        }

        return false
    }
}