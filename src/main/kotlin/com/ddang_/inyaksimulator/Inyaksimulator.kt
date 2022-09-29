package com.ddang_.inyaksimulator

import com.ddang_.inyaksimulator.commands.*
import com.ddang_.inyaksimulator.listeners.DamageByListener
import com.ddang_.inyaksimulator.listeners.inventory.ClickListener
import com.ddang_.inyaksimulator.listeners.player.DamageListener
import com.ddang_.inyaksimulator.listeners.player.JoinQuitListener
import com.ddang_.inyaksimulator.managers.ConfigManager
import com.ddang_.inyaksimulator.managers.GameConfigManager
import com.ddang_.inyaksimulator.managers.MemberManager
import com.ddang_.inyaksimulator.managers.WarpManager
import com.ddang_.inyaksimulator.objects.GameConfig
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
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

    //멤버 셋
    private fun setMembers() {
        players.forEach {
            MemberManager.set(it)
        }
    }

    //멤버 세이브
    private fun saveMembers() {
        players.forEach {
            MemberManager.save(it)
        }
    }

    //워프 세이브
    private fun saveWarps() {
        WarpManager.warpList.forEach {
            WarpManager.save(it)
        }
    }

    //로어 스탯 메커니즘
    private fun loreMechanism() {
        (1L).rt {
            players.forEach {

                val m = MemberManager.getMember(it.name) ?: return@forEach

                val affectedLoreStatList = arrayListOf<ItemStack>()

                for (armor in it.inventory.armorContents) {
                    affectedLoreStatList.add(armor)
                }
                affectedLoreStatList.add(it.inventory.itemInMainHand)

                var STAT_ATTACK = 0
                var STAT_DEFENCE = 0
                var STAT_MORE_HEALTH = 0
                var STAT_HEALTH_DRAIN_CHANGE = 0
                var STAT_HEALTH_DRAIN_AMOUNT = 0

                for (item in affectedLoreStatList) {
                    val lore = item?.itemMeta?.lore ?: continue

                    for (line in lore) {
                        if (line.contains("공격력")) {
                            ChatColor.stripColor(line)

                            val numberOnly = line.replace(Regex("[^0-9]"), "")
                            val int = numberOnly.toIntOrNull() ?: continue
                            STAT_ATTACK += int

                        } else if (line.contains("방어력")) {
                            ChatColor.stripColor(line)
                            val numberOnly = line.replace(Regex("[^0-9]"), "")
                            val int = numberOnly.toIntOrNull() ?: continue
                            STAT_DEFENCE += int

                        } else if (line.contains("체력")) {
                            ChatColor.stripColor(line)
                            val numberOnly = line.replace(Regex("[^0-9]"), "")
                            val int = numberOnly.toIntOrNull() ?: continue
                            STAT_MORE_HEALTH += int

                        } else if (line.contains("흡혈률")) {
                            ChatColor.stripColor(line)
                            val numberOnly = line.replace(Regex("[^0-9]"), "")
                            val int = numberOnly.toIntOrNull() ?: continue
                            STAT_HEALTH_DRAIN_CHANGE += int

                        } else if (line.contains("흡혈량")) {
                            ChatColor.stripColor(line)
                            val numberOnly = line.replace(Regex("[^0-9]"), "")
                            val int = numberOnly.toIntOrNull() ?: continue
                            STAT_HEALTH_DRAIN_AMOUNT += int
                        }
                    }
                }

                //제한 범위 내로 깎기
                if (STAT_DEFENCE >= 100) {
                    STAT_DEFENCE = 100
                }
                if (STAT_HEALTH_DRAIN_CHANGE >= 100) {
                    STAT_HEALTH_DRAIN_CHANGE = 100
                }

                m.loreStat.attack = STAT_ATTACK
                m.loreStat.defence = STAT_DEFENCE
                m.loreStat.moreHealth = STAT_MORE_HEALTH
                m.loreStat.healthDrainChance = STAT_HEALTH_DRAIN_CHANGE
                m.loreStat.healthDrainAmount = STAT_HEALTH_DRAIN_AMOUNT

                val maxHealth = it.getAttribute(Attribute.GENERIC_MAX_HEALTH).defaultValue
                it.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue = maxHealth + STAT_MORE_HEALTH
            }
        }
    }

    //스폰 메커니즘
    private fun spawnMechanism() {
        (20L).rt {
            players.forEach {
                val m = MemberManager.getMember(it.name) ?: return@forEach

                if (m.spawnCount <= 0) {
                    return@forEach
                }

                if (m.recallLocation == null) {
                    return@forEach
                }

                val nowLoc = it.location
                val recallLoc = m.recallLocation ?: return@forEach

                if (recallLoc.distance(nowLoc) > 2) {
                    m.spawnCount = -1
                    m.recallLocation = null

                    it.sendMessage(config.getString("Message.spawn.cancel.tooFar"))
                    return@forEach
                }

                val message = config.getString("Message.spawn.going").replace("%s", "${m.spawnCount}")
                it.sendMessage(message)

                m.spawnCount--

                if (m.spawnCount <= 0) {
                    m.spawnCount = -1
                    m.recallLocation = null

                    it.teleport(gameConfig.spawnLoc)
                    it.sendMessage(config.getString("Message.spawn.done"))
                }
            }
        }
    }

    //이벤트 리스너 목록입니다.
    private val events = arrayOf(
        ClickListener(),
        JoinQuitListener(),
        DamageListener(),
        DamageByListener()
    )

    override fun onEnable() {

        //인스턴스 변수를 잡습니다.
        players = server.onlinePlayers
        instance = this
        scheduler = server.scheduler

        //콘피그
        ConfigManager.set()

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
        getCommand("i")?.executor = InfoComand()
        getCommand("s")?.executor = SCommand()
        getCommand("setspawn")?.executor = SetSpawnCommand()
        getCommand("spawn")?.executor = SpawnCommand()
        getCommand("tp")?.executor = TPCommand()
        getCommand("warp")?.executor = WarpCommand()
        getCommand("isreload")?.executor = ReloadConfigCommand()
        getCommand("setwarp")?.executor = SetwarpCommand()
        getCommand("lore")?.executor = LoreCommand()

        //스폰 메커니즘
        spawnMechanism()

        //멤버 셋
        setMembers()

        //워프
        WarpManager.setUp()

        //로어 메커니즘
        loreMechanism()
    }

    override fun onDisable() {

        //콘피그 저장
        GameConfigManager.save()

        //멤버 세이브
        saveMembers()

        //워프
        saveWarps()
    }
}