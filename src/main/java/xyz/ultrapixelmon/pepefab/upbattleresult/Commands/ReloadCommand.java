package xyz.ultrapixelmon.pepefab.upbattleresult.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import xyz.ultrapixelmon.pepefab.upbattleresult.Config.Config;
import xyz.ultrapixelmon.pepefab.upbattleresult.Utils.ChatUtils;
import xyz.ultrapixelmon.pepefab.upbattleresult.Utils.PermissionsUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends CommandBase {

    @Override
    public String getName() {
        return "upbattleresult";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "&cUsage: /upbattleresult";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if(args.length == 0){
            sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&cCommand usage : /upbattleresult <argument>")));
            return;
        }
        if(args.length == 1){
            if(args[0].matches("reload")){
                if(PermissionsUtils.hasPermission("upbattleresult.reload", (EntityPlayerMP) sender)){
                    Config.load();
                    sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&aConfiguration reload success.")));
                    return;
                }
                sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&cYou don't have permission to use this command.")));
                return;
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        possibleArgs.add("reload");
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionsUtils.canUse("upbattleresult.reload", sender);
    }

}
