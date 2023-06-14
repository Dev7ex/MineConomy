package com.dev7ex.mineconomy.command;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.common.bukkit.command.CommandProperties;
import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.mineconomy.command.economy.AddCommand;
import com.dev7ex.mineconomy.command.economy.HelpCommand;
import com.dev7ex.mineconomy.command.economy.RemoveCommand;
import com.dev7ex.mineconomy.command.economy.SetCommand;
import com.google.common.collect.Lists;
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
@CommandProperties(name = "economy", permission = "mineconomy.command.economy")
public class EconomyCommand extends BukkitCommand implements TabCompleter {

    public EconomyCommand(@NotNull final BukkitPlugin plugin) {
        super(plugin);

        super.registerSubCommand(new AddCommand(plugin));
        super.registerSubCommand(new HelpCommand(plugin));
        super.registerSubCommand(new RemoveCommand(plugin));
        super.registerSubCommand(new SetCommand(plugin));
    }

    @Override
    public boolean execute(final CommandSender commandSender, final String[] arguments ) {
        final BukkitCommand helpCommand = super.getSubCommands().get("help");

        if((arguments.length == 0) || (arguments.length > 3)) {
            return helpCommand.execute(commandSender, arguments);
        }

        if (super.getSubCommand(arguments[0].toLowerCase()).isEmpty()) {
            return helpCommand.execute(commandSender, arguments);
        }
        return super.execute(super.getSubCommand(arguments[0].toLowerCase()).get(), commandSender, arguments);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull final CommandSender commandSender, @NotNull final Command command, @NotNull final String commandLabel, @NotNull final String[] arguments) {
        if (arguments.length == 1) {
            return Lists.newArrayList(super.getSubCommands().keySet());
        }
        if (super.getSubCommand(arguments[0].toLowerCase()).isEmpty()) {
            return Collections.emptyList();
        }
        final BukkitCommand subCommand = super.getSubCommand(arguments[0].toLowerCase()).get();

        if (!(subCommand instanceof TabCompleter)) {
            return Collections.emptyList();
        }
        return ((TabCompleter) subCommand).onTabComplete(commandSender, command, commandLabel, arguments);
    }

}
