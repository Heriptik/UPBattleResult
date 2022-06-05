package xyz.ultrapixelmon.pepefab.upbattleresult.Listeners;

import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.battles.controller.BattleControllerBase;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.enums.battle.BattleResults;
import com.pixelmonmod.pixelmon.enums.battle.EnumBattleEndCause;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.ultrapixelmon.pepefab.upbattleresult.Config.Config;
import xyz.ultrapixelmon.pepefab.upbattleresult.Utils.ChatUtils;

public class PlayerBattle {

    FMLCommonHandler server = FMLCommonHandler.instance();

    @SubscribeEvent
    public void onBattleEndEvent(BattleEndEvent event) {
        BattleControllerBase battleControllerBase = event.bc;
        int playersNumber = battleControllerBase.playerNumber;

        if (!battleControllerBase.isPvP()) return;
        if (playersNumber > 2) return;
        if (event.cause == EnumBattleEndCause.FORCE) return;

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
        } else {
            String winner = battleParticipant2.getDisplayName();
            String loser = battleParticipant1.getDisplayName();

            for (EntityPlayerMP players : server.getMinecraftServerInstance().getServer().getPlayerList().getPlayers()) {
                double playersRange = Math.sqrt((battleParticipant1.getEntity().posX - (int) players.posX) * (battleParticipant1.getEntity().posX - (int) players.posX) + (battleParticipant1.getEntity().posZ - (int) players.posZ) * (battleParticipant1.getEntity().posZ - (int) players.posZ));
                if (Config.Range >= playersRange) {
                    players.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating(Config.Message.replace("%winner%", winner).replace("%loser%", loser))));
                }
            }
        }
    }

}
