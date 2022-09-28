package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.utils.ItemUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Repairable

class FixCommand: CommandExecutor {
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

        if (arg.isNotEmpty()) {
            return false
        }

        val inv = p.inventory.contents ?: return false
        val armor = p.inventory.armorContents ?: return false

        val allList = arrayListOf<ItemStack>()
        for (item in inv) {
            if (item == null) {
                continue
            }
            if (!ItemUtil.checkRepairable(item)) {
                continue
            }
            if (item.durability == (0).toShort()) {
                continue
            }
            allList.add(item)
        }
        for (item in armor) {
            if (item == null) {
                continue
            }
            if (item.durability == (0).toShort()) {
                continue
            }
            allList.add(item)
        }

        if (allList.isEmpty()) {
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.fix.no"))
            return false
        }

        for (item in allList) {
            item.durability = 0
        }

        p.sendMessage(Inyaksimulator.instance.config.getString("Message.fix.done"))

        return false
    }
}