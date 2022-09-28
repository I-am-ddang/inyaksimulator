package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator

class ConfigManager {
    companion object {
        fun set() {
            val config = Inyaksimulator.instance.config

            config.addDefault("Message.fix.done", "  §a§l보관함 수리 §f보관함 내 모든 아이템 수리가 완료되었습니다.")

            config.addDefault("Message.reload.done", "  §a§l콘피그 리로드 §f콘피그 리로드가 완료되었습니다.")

            config.addDefault("Message.spawn.done", "  §a§l스폰 이동 §f스폰으로 성공적으로 이동했습니다.")
            config.addDefault("Message.spawn.goStart", "  §a§l스폰 이동 §f스폰으로 이동을 요청했습니다.")
            config.addDefault("Message.spawn.going", "  §6§l스폰 이동 §f스폰으로 이동 중입니다. 남은 시간 %s 초")
            config.addDefault("Message.spawn.cancel.playerDamage", "  §c§l스폰 이동 §f피격을 받아 스폰 이동이 끊겼습니다.")
            config.addDefault("Message.spawn.cancel.tooFar", "  §c§l스폰 이동 §f이동 요청 위치에서 너무 멀어져 스폰 이동이 끊겼습니다.")

            config.addDefault("Spawn.goTime", "5")

            config.addDefault("Loc.spawn.world", "world")
            config.addDefault("Loc.spawn.x", "0.0")
            config.addDefault("Loc.spawn.y", "60.0")
            config.addDefault("Loc.spawn.z", "0.0")
            config.addDefault("Loc.spawn.pitch", "0.0")
            config.addDefault("Loc.spawn.yaw", "180.0")

            config.options().copyDefaults(true)
            Inyaksimulator.instance.saveConfig()
        }
    }
}