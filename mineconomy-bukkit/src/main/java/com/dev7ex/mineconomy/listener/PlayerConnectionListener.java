package com.dev7ex.mineconomy.listener;

import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.mineconomy.api.BukkitMineConomyApi;
import com.dev7ex.mineconomy.api.event.MineConomyListener;
import com.dev7ex.mineconomy.api.event.user.EconomyUserBalanceUpdateEvent;
import com.dev7ex.mineconomy.api.event.user.EconomyUserLoginEvent;
import com.dev7ex.mineconomy.api.event.user.EconomyUserLogoutEvent;
import com.dev7ex.mineconomy.api.user.EconomyUser;
import com.dev7ex.mineconomy.api.user.EconomyUserConfiguration;
import com.dev7ex.mineconomy.api.user.EconomyUserProperty;
import com.dev7ex.mineconomy.user.User;
import com.dev7ex.mineconomy.user.UserConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
public class PlayerConnectionListener extends MineConomyListener {

    public PlayerConnectionListener(@NotNull final BukkitMineConomyApi mineConomyApi) {
        super(mineConomyApi);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerLogin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        final EconomyUser user = new User(player.getUniqueId(), player.getName());
        final EconomyUserConfiguration userConfiguration = new UserConfiguration(user);
        final ParsedMap<EconomyUserProperty, Object> userData = userConfiguration.read(EconomyUserProperty.BALANCE);

        Bukkit.getPluginManager().callEvent(new EconomyUserLoginEvent(user));

        user.setBalance(userData.getInteger(EconomyUserProperty.BALANCE));
        user.setConfiguration(userConfiguration);

        super.getUserProvider().register(user);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final EconomyUser user = super.getUserProvider().getUser(player.getUniqueId()).orElseThrow();

        super.getUserProvider().saveUser(user, EconomyUserProperty.BALANCE);
        super.getUserProvider().unregister(player.getUniqueId());

        Bukkit.getPluginManager().callEvent(new EconomyUserLogoutEvent(user));
    }

}
