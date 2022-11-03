package xyz.ultrapixelmon.pepefab.upbattleresult.Listeners;

import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.battles.controller.BattleControllerBase;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.commands.EndBattleCommand;
import com.pixelmonmod.pixelmon.enums.battle.BattleResults;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.ultrapixelmon.pepefab.upbattleresult.Config.Config;
import xyz.ultrapixelmon.pepefab.upbattleresult.Utils.ChatUtils;

public class PlayerBattle {

    FMLCommonHandler server = FMLCommonHandler.instance();
    public static boolean endBattleCommand;

    @SubscribeEvent
    public void onBattleEndEvent(BattleEndEvent event) {
        BattleControllerBase battleControllerBase = event.bc;
        int playersNumber = battleControllerBase.playerNumber;

        if (!battleControllerBase.isPvP()){
            endBattleCommand = false;
            return;
        }
        if (playersNumber > 2) return;
        if (endBattleCommand == true) {
            endBattleCommand = false;
            return;
        }

        BattleParticipant battleParticipant1 = event.results.keySet().asList().get(0);
        BattleParticipant battleParticipant2 = event.results.keySet().asList().get(1);

        if (event.results.get(battleParticipant1) == BattleResults.VICTORY) {
            String winner = battleParticipant1.getDisplayName();
            String loser = battleParticipant2.getDisplayName();

            for (EntityPlayerMP players : server.getMinecraftServerInstance().getServer().getPlayerList().getPlayers()) {
                double playersRange = Math.sqrt((battleParticipant1.getEntity().posX - (int) players.posX) * (battleParticipant1.getEntity().posX - (int) players.posX) + (battleParticipant1.getEntity().posZ - (int) players.posZ) * (battleParticipant1.getEntity().posZ - (int) players.posZ));
                if (Config.Range >= playersRange) {
                    players.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating(Config.Message.replace("%winner%", winner).replace("%loser%", loser))));
                }
            }
        }
        if (event.results.get(battleParticipant2) == BattleResults.VICTORY) {
            String winner = battleParticipant2.getDisplayName();
            String loser = battleParticipant1.getDisplayName();

            for (EntityPlayerMP players : server.getMinecraftServerInstance().getServer().getPlayerList().getPlayers()) {
                double playersRange = Math.sqrt((battleParticipant2.getEntity().posX - (int) players.posX) * (battleParticipant2.getEntity().posX - (int) players.posX) + (battleParticipant2.getEntity().posZ - (int) players.posZ) * (battleParticipant2.getEntity().posZ - (int) players.posZ));
                if (Config.Range >= playersRange) {
                    players.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating(Config.Message.replace("%winner%", winner).replace("%loser%", loser))));
                }
            }
        }
    }

    @SubscribeEvent
    public void onEndBattleCommand(CommandEvent event){
        if(event.getCommand() instanceof EndBattleCommand){
            endBattleCommand = true;
        }
    }
}
