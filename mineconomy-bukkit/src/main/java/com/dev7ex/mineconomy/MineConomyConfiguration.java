package com.dev7ex.mineconomy;

import com.dev7ex.common.bukkit.configuration.ConfigurationProperties;
import com.dev7ex.common.bukkit.plugin.configuration.DefaultPluginConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
@ConfigurationProperties(fileName = "config.yml")
public class MineConomyConfiguration extends DefaultPluginConfiguration {

    public MineConomyConfiguration(@NotNull final Plugin plugin) {
        super(plugin);
    }

}
