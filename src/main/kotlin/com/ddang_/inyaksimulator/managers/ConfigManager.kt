package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator

class ConfigManager {
    companion object {
        fun set() {
            val config = Inyaksimulator.instance.config

            config.addDefault("Message.fix.done", "  §a§l보관함 수리 §f보관함 내 모든 아이템 수리가 완료되었습니다.")
            config.addDefault("Message.fix.no", "  §c§l보관함 수리 §f보관함 내에 수리할 아이템이 없습니다.")

            config.addDefault("Message.reload.done", "  §a§l콘피그 리로드 §f콘피그 리로드가 완료되었습니다.")

            config.addDefault("Message.spawn.done", "  §a§l스폰 이동 §f스폰으로 성공적으로 이동했습니다.")
            config.addDefault("Message.spawn.goStart", "  §a§l스폰 이동 §f스폰으로 이동을 요청했습니다.")
            config.addDefault("Message.spawn.going", "  §6§l스폰 이동 §f스폰으로 이동 중입니다. 남은 시간 %s 초")
            config.addDefault("Message.spawn.cancel.playerDamage", "  §c§l스폰 이동 §f피격을 받아 스폰 이동이 끊겼습니다.")
            config.addDefault("Message.spawn.cancel.tooFar", "  §c§l스폰 이동 §f이동 요청 위치에서 너무 멀어져 스폰 이동이 끊겼습니다.")

            config.addDefault("Message.info.title", "§6§l          정보 확인 §f: §6%playerName%")
            config.addDefault("Message.info.helmetName", "§f  투구: §7%itemName%")
            config.addDefault("Message.info.chestplateName", "§f  흉갑: §7%itemName%")
            config.addDefault("Message.info.leggingsName", "§f  각반: §7%itemName%")
            config.addDefault("Message.info.bootsName", "§f  부츠: §7%itemName%")
            config.addDefault("Message.info.swordName", "§f  검: §7%itemName%")

            config.addDefault("Message.info.totalStatTop", "§f  공격력 | 방어력 | 체력 | 흡혈률 | 흡혈량 ")
            config.addDefault("Message.info.totalStatBelow", "§f  %attack% | %defence% | %health% | %drainChance% | %drainAmount%")

            config.addDefault("UI.loreStat.preset", "%줄바꿈% 공격력: %attack% %줄바꿈% 방어력: %defence% %줄바꿈% 체력: %health% %줄바꿈% 흡혈률: %drainChance% %줄바꿈% 흡혈량: %drainAmount%")

            config.addDefault("UI.health.belowNametag", "§c❤")

            config.addDefault("Keyword.InfiniteDurability", "내구도 무한")

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