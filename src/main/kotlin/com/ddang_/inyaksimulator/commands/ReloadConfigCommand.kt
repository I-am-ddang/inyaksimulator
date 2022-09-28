package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.Inyaksimulator
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReloadConfigCommand: CommandExecutor {
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

        if (!p.isOp) {
            return false
        }

        val arg = args ?: return false

        if (arg.isNotEmpty()) {
            return false
        }

        Inyaksimulator.instance.reloadConfig()

        p.sendMessage(Inyaksimulator.instance.config.getString("Message.reload.done"))

        return false
    }
}