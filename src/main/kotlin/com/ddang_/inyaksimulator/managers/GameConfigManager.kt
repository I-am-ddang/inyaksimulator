package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.Inyaksimulator.Companion.warn
import com.ddang_.inyaksimulator.objects.GameConfig
import org.bukkit.Bukkit
import org.bukkit.Location

class GameConfigManager {
    companion object {
        fun set(): GameConfig {

            val spawnLoc = getSpawnLoc()

            return GameConfig(
                spawnLoc
            )
        }

        fun save() {

            val spawnLoc = Inyaksimulator.gameConfig.spawnLoc

            Inyaksimulator.instance.config["Loc.spawn.world"] = spawnLoc.world.name
            Inyaksimulator.instance.config["Loc.spawn.x"] = "${spawnLoc.x}"
            Inyaksimulator.instance.config["Loc.spawn.y"] = "${spawnLoc.y}"
            Inyaksimulator.instance.config["Loc.spawn.z"] = "${spawnLoc.z}"
            Inyaksimulator.instance.config["Loc.spawn.yaw"] = "${spawnLoc.yaw}"
            Inyaksimulator.instance.config["Loc.spawn.pitch"] = "${spawnLoc.pitch}"

            Inyaksimulator.instance.saveConfig()

        }

        private fun getSpawnLoc(): Location {
            //w

            val worldFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.world")

            val w = Bukkit.getWorld(worldFromConfig) ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.world 에 입력된 값이 올바른 월드 이름이 아닙니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            //x

            val xFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.x")

            val x = xFromConfig.toDoubleOrNull() ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.x 에 숫자(실수: 소수점 포함)를 입력하지 않았습니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            //y

            val yFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.y")

            val y = yFromConfig.toDoubleOrNull() ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.y 에 숫자(실수: 소수점 포함)를 입력하지 않았습니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            //z

            val zFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.z")

            val z = zFromConfig.toDoubleOrNull() ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.z 에 숫자(실수: 소수점 포함)를 입력하지 않았습니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            //pitch

            val pitchFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.pitch")

            val pitch = pitchFromConfig.toFloatOrNull() ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.pitch 에 숫자(실수: 소수점 포함)를 입력하지 않았습니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            //yaw

            val yawFromConfig = Inyaksimulator.instance.config.getString("Loc.spawn.yaw")

            val yaw = yawFromConfig.toFloatOrNull() ?: kotlin.run {
                ("").warn()
                ("[오류] config.yml 파일 -> Loc.spawn.yaw 에 숫자(실수: 소수점 포함)를 입력하지 않았습니다.").warn()
                return Location(
                    Bukkit.getWorld("world"),
                    0.0, 60.0, 0.0
                )
            }

            return Location(w, x, y, z, yaw, pitch)
        }
    }
}