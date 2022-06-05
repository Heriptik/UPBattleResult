package xyz.ultrapixelmon.pepefab.upbattleresult.Config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static Integer Range;
    public static String Message;

    public static void load(){
        Configuration config = new Configuration(new File("config/UPBattleResult/config.json"));
        config.load();

        Range = config.getInt("rangeAroundPlayerBattle", Configuration.CATEGORY_GENERAL, 35, 0, 999, "The range around battle that players can receive win / lose message.");
        Message = config.getString("message", Configuration.CATEGORY_GENERAL, "&e%winner% win battle vs %loser%", "Message send to players participant and around %winner%, %loser%");
        config.save();
    }

}
