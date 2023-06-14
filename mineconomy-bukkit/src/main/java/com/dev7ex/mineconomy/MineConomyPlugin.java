package com.dev7ex.mineconomy;

import com.dev7ex.common.bukkit.plugin.BukkitPlugin;
import com.dev7ex.common.bukkit.plugin.PluginProperties;
import com.dev7ex.common.bukkit.plugin.service.PluginServiceOrder;
import com.dev7ex.mineconomy.api.BukkitMineConomyApi;
import com.dev7ex.mineconomy.api.MineConomyApi;
import com.dev7ex.mineconomy.api.MineConomyProvider;
import com.dev7ex.mineconomy.command.BalanceCommand;
import com.dev7ex.mineconomy.command.EconomyCommand;
import com.dev7ex.mineconomy.listener.PlayerConnectionListener;
import com.dev7ex.mineconomy.user.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
@Getter(AccessLevel.PUBLIC)
@PluginProperties(metricsId = 18745, metrics = true, requiredFacilisVersion = "1.0.1-SNAPSHOT")
public class MineConomyPlugin extends BukkitPlugin implements BukkitMineConomyApi {

    private MineConomyConfiguration configuration;

    private UserService userProvider;

    @Override
    public void onLoad() {
        super.createDataFolder();
        super.createSubFolder("user");

        this.configuration = new MineConomyConfiguration(this);
        this.configuration.load();
    }

    @Override
    public void onEnable() {
        MineConomyProvider.registerApi(this);
    }

    @Override
    public void onDisable() {
        MineConomyProvider.unregisterApi();
    }

    @Override
    public void registerCommands() {
        super.registerCommand(new BalanceCommand(this));
        super.registerCommand(new EconomyCommand(this));
    }

    @Override
    public void registerListeners() {
        super.registerListener(new PlayerConnectionListener(this));
    }

    @Override
    public void registerServices() {
        super.registerService(this.userProvider = new UserService());
    }

    @Override
    public File getUserFolder() {
        return super.getSubFolder("user");
    }

    public static MineConomyPlugin getInstance() {
        return JavaPlugin.getPlugin(MineConomyPlugin.class);
    }

}
