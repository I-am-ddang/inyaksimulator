package com.ddang_.inyaksimulator

import com.ddang_.inyaksimulator.commands.*
import com.ddang_.inyaksimulator.listeners.inventory.ClickListener
import com.ddang_.inyaksimulator.managers.GameConfigManager
import com.ddang_.inyaksimulator.objects.GameConfig
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler

class Inyaksimulator : JavaPlugin() {

    companion object{

        //버킷에 정보를 띄우기 위한 리시버입니다.
        fun String.info() = Bukkit.getLogger().info(this)

        //버킷에 경고를 띄우기 위한 리시버입니다.
        fun String.warn() = Bukkit.getLogger().warning(this)

        //서버 전체 메시지 출력을 위한 리시버입니다.
        fun String.broad() = Bukkit.broadcastMessage(this)

        //Long 틱마다 반복을 위한 리시버입니다.
        fun Long.rt(delay: Long = 1, run: Runnable) = scheduler.runTaskTimer(instance, run, delay, this)

        //Long 틱 뒤에 작동을 위한 리시버입니다.
        fun Long.rl(run: Runnable) = scheduler.runTaskLater(instance, run, this)


        //인스턴스 변수 모음
        lateinit var instance: Plugin
        lateinit var scheduler: BukkitScheduler
            private set
        lateinit var players: MutableCollection<out Player>
            private set

        lateinit var gameConfig: GameConfig
            private set
    }

    //이벤트 리스너 목록입니다.
    private val events = arrayOf(
        ClickListener()
    )

    override fun onEnable() {

        //인스턴스 변수를 잡습니다.
        players = server.onlinePlayers
        instance = this
        scheduler = server.scheduler

        //콘피그
        config.addDefault("Message.fix.done", "  §a§l보관함 수리 §f보관함 내 모든 아이템 수리가 완료되었습니다.")
        config.addDefault("Message.spawn.done", "  §a§l스폰 이동 수리 §f스폰으로 성공적으로 이동했습니다.")

        config.addDefault("Loc.spawn.world", "world")
        config.addDefault("Loc.spawn.x", "0.0")
        config.addDefault("Loc.spawn.y", "60.0")
        config.addDefault("Loc.spawn.z", "0.0")
        config.addDefault("Loc.spawn.pitch", "0.0")
        config.addDefault("Loc.spawn.yaw", "180.0")

        config.options().copyDefaults(true)
        saveConfig()

        //추가 인스턴스 변수

        gameConfig = GameConfigManager.set()

        //이벤트 리스너를 서버에 등록합니다.
        server.pluginManager.apply {
            events.forEach {
                registerEvents(it, this@Inyaksimulator)
            }
        }

        //명령어 등록
        getCommand("fix")?.executor = FixCommand()
        getCommand("gm")?.executor = GMCommand()
        getCommand("info")?.executor = InfoComand()
        getCommand("s")?.executor = SCommand()
        getCommand("setspawn")?.executor = SetSpawnCommand()
        getCommand("tp")?.executor = TPCommand()
        getCommand("warp")?.executor = WarpCommand()
    }

    override fun onDisable() {
        GameConfigManager.save()
    }
}