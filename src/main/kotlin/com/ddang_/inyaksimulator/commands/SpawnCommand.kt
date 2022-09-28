package com.ddang_.inyaksimulator.commands

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.Inyaksimulator.Companion.warn
import com.ddang_.inyaksimulator.managers.MemberManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnCommand: CommandExecutor {
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

        if (p.isOp) {
            p.teleport(Inyaksimulator.gameConfig.spawnLoc)
            p.sendMessage(Inyaksimulator.instance.config.getString("Message.spawn.done"))
            return false
        }

        val m = MemberManager.getMember(p.name) ?: return false
        val spawnGoTimeFromConfig = Inyaksimulator.instance.config.getString("Spawn.goTime")

        val spawnGoTime = spawnGoTimeFromConfig.toIntOrNull() ?: kotlin.run {
            ("").warn()
            ("[오류] config.yml 파일 -> Spawn.goTime 에 숫자(정수: 소수점 없음)를 입력하지 않았습니다.").warn()
            return false
        }
        m.spawnCount = spawnGoTime
        m.recallLocation = p.location

        val message = Inyaksimulator.instance.config.getString("Message.spawn.goStart")

        p.sendMessage(message)

        return false
    }
}