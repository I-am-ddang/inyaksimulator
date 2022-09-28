package com.ddang_.inyaksimulator.managers

import com.ddang_.inyaksimulator.Inyaksimulator
import com.ddang_.inyaksimulator.Inyaksimulator.Companion.warn
import com.ddang_.inyaksimulator.objects.Warp
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class WarpManager {
    companion object {
        val warpList = arrayListOf<Warp>()

        fun getWarp(name: String) = warpList.find { it.name == name }

        fun setUp() {
            val folderFile = File(Inyaksimulator.instance.dataFolder, "${File.separator}WarpData${File.separator}")
            val fileList = folderFile.listFiles() ?: return

            for (file in fileList) {

                val warpData = YamlConfiguration.loadConfiguration(file)

                val name = file.name.replace(".yml", "")

                val worldC = warpData.getString("Loc.world")
                val xC = warpData.getString("Loc.x")
                val yC = warpData.getString("Loc.y")
                val zC = warpData.getString("Loc.z")
                val yawC = warpData.getString("Loc.yaw")
                val pitchC = warpData.getString("Loc.pitch")

                val world = Bukkit.getWorld(worldC) ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.world 에 입력된 값이 올바른 월드 이름이 아닙니다.").warn()
                    return
                }
                val x = xC.toDoubleOrNull() ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.x 에 입력된 값이 올바른 숫자(실수: 소수점 포함)가 아닙니다.").warn()
                    return
                }
                val y = yC.toDoubleOrNull() ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.y 에 입력된 값이 올바른 숫자(실수: 소수점 포함)가 아닙니다.").warn()
                    return
                }
                val z = zC.toDoubleOrNull() ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.z 에 입력된 값이 올바른 숫자(실수: 소수점 포함)가 아닙니다.").warn()
                    return
                }
                val yaw = yawC.toFloatOrNull() ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.yaw 에 입력된 값이 올바른 숫자(실수: 소수점 포함)가 아닙니다.").warn()
                    return
                }
                val pitch = pitchC.toFloatOrNull() ?: kotlin.run {
                    ("").warn()
                    ("[오류] WarpData 폴더 -> $name 파일 -> Loc.pitch 에 입력된 값이 올바른 숫자(실수: 소수점 포함)가 아닙니다.").warn()
                    return
                }


                val loc = Location(
                    world, x, y, z, yaw, pitch
                )

                Warp(
                    name,
                    loc
                )
            }
        }

        fun save(w: Warp) {
            val file = File(Inyaksimulator.instance.dataFolder, "${File.separator}WarpData${File.separator}${w.name}.yml")
            val warpData = YamlConfiguration.loadConfiguration(file)

            warpData["Loc.world"] = w.loc.world.name
            warpData["Loc.x"] = w.loc.x.toString()
            warpData["Loc.y"] = w.loc.y.toString()
            warpData["Loc.z"] = w.loc.z.toString()
            warpData["Loc.yaw"] = w.loc.yaw.toString()
            warpData["Loc.pitch"] = w.loc.pitch.toString()

            warpData.save(file)
        }
    }
}