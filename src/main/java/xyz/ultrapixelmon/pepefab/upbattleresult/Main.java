package xyz.ultrapixelmon.pepefab.upbattleresult;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import xyz.ultrapixelmon.pepefab.upbattleresult.Commands.ReloadCommand;
import xyz.ultrapixelmon.pepefab.upbattleresult.Config.Config;
import xyz.ultrapixelmon.pepefab.upbattleresult.Listeners.PlayerBattle;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main
{
    public static final String MODID = "upbattleresult";
    public static final String NAME = "UPBattleResult";
    public static final String VERSION = "0.1";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        Config.load();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Pixelmon.EVENT_BUS.register(new PlayerBattle());
        logger.info("[UPBattleResult] Plugin initialisation Version: " + Main.VERSION);
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new ReloadCommand());
    }
}
