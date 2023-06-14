package com.dev7ex.mineconomy.command.economy;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.util.NumberUtil;
import com.dev7ex.mineconomy.MineConomyPlugin;
import com.dev7ex.mineconomy.api.user.EconomyUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
@CommandProperties(name = "set", permission = "mineconomy.command.economy.set")
public class SetCommand extends BukkitCommand implements TabCompleter {

    public SetCommand(@NotNull final BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if (arguments.length != 3) {
            commandSender.sendMessage(super.getConfiguration().getString("commands.economy.set.usage")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }

        if (MineConomyPlugin.getInstance().getUserProvider().getUser(arguments[1]).isEmpty()) {
            commandSender.sendMessage(super.getConfiguration().getString("player-not-found")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }

        if (!NumberUtil.isNumber(arguments[2])) {
            commandSender.sendMessage(super.getConfiguration().getString("commands.economy.set.wrong-argument")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }
        final int value = Integer.parseInt(arguments[2]);

        if (Integer.signum(value) == -1) {
            commandSender.sendMessage(super.getConfiguration().getString("commands.economy.set.wrong-argument")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }

        final EconomyUser user = MineConomyPlugin.getInstance().getUserProvider().getUser(arguments[1]).get();
        user.setBalance(value);
        commandSender.sendMessage(super.getConfiguration().getString("commands.economy.set.successfully-set")
                .replaceAll("%prefix%", super.getPrefix())
                .replaceAll("%player_name%", arguments[1])
                .replaceAll("%value%", arguments[2]));
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final Command command, @NotNull final String commandLabel, @NotNull final String[] arguments) {
        if (arguments.length != 2) {
            return Collections.emptyList();
        }
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }

}
