package com.dev7ex.mineconomy.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
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
@CommandProperties(name = "balance", permission = "mineconomy.command.balance")
public class BalanceCommand extends BukkitCommand implements TabCompleter {

    public BalanceCommand(@NotNull final BukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean execute(@NotNull final CommandSender commandSender, @NotNull final String[] arguments) {
        if ((arguments.length > 1) && (!commandSender.hasPermission("mineconomy.command.balance.other"))) {
            commandSender.sendMessage(super.getConfiguration().getString("commands.balance.usage")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }

        if (arguments.length > 1) {
            commandSender.sendMessage(super.getConfiguration().getString("commands.balance.usage")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }

        if (arguments.length == 0) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(super.getConfiguration().getString("player-command")
                        .replaceAll("%prefix%", super.getPrefix()));
                return true;
            }
            final EconomyUser user = MineConomyPlugin.getInstance().getUserProvider().getUser(commandSender.getName()).orElseThrow();
            commandSender.sendMessage(super.getConfiguration().getString("commands.balance.message")
                    .replaceAll("%prefix%", super.getPrefix())
                    .replaceAll("%value%", String.valueOf(user.getBalance())));
            return true;
        }

        if (MineConomyPlugin.getInstance().getUserProvider().getUser(arguments[0]).isEmpty()) {
            commandSender.sendMessage(super.getConfiguration().getString("player-not-found")
                    .replaceAll("%prefix%", super.getPrefix()));
            return true;
        }
        final EconomyUser user = MineConomyPlugin.getInstance().getUserProvider().getUser(arguments[0]).orElseThrow();
        commandSender.sendMessage(super.getConfiguration().getString("commands.balance.message-other")
                .replaceAll("%prefix%", super.getPrefix())
                        .replaceAll("%player_name%", user.getName())
                .replaceAll("%value%", String.valueOf(user.getBalance())));
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final Command command, @NotNull final String commandLabel, @NotNull final String[] arguments) {
        if (arguments.length != 1) {
            return Collections.emptyList();
        }

        if (!commandSender.hasPermission("mineconomy.command.balance.other")) {
            return Collections.emptyList();
        }
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }

}
