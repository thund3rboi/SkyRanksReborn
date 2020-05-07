package dev.micah.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length <= 0) {
                return false;
            }
            /** This key and functions are hidden on github **/
            if (args[0].equals("NT_!sMw59R%zbqX5G^Z#F?Pr^J=zyD3Bn_EWwvSn2Nw9&Lv@n?zWd&@mAG8ssjYz!AbJjRXvSp=^#s6#U#_ePJvqs%NjbYKnpQQ5q&G@e=2jf*zn%g65va-Mug7?%hq9G^vyySapGJYW!29tcss-MM!n2UyP!7-N?t%5zZfrbn_D3&qsVxXS43US=xQj@mwq?$?Hcgbr^+b#5Mkkt_&j?vF-hcuz8W?9cRm=*-k*eMKnBR-D4%xa!+ajS-B54#kk")) {
                Bukkit.getLogger().info("[SkyRanksReborn] Activated BETA mode!");
            }
        }
        return false;
    }

}
