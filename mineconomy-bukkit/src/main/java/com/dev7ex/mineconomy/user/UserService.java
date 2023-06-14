package com.dev7ex.mineconomy.user;

import com.dev7ex.common.bukkit.plugin.service.PluginService;
import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.mineconomy.api.user.EconomyUser;
import com.dev7ex.mineconomy.api.user.EconomyUserConfiguration;
import com.dev7ex.mineconomy.api.user.EconomyUserProperty;
import com.dev7ex.mineconomy.api.user.EconomyUserProvider;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
@Getter(AccessLevel.PUBLIC)
public class UserService implements PluginService, EconomyUserProvider {

    private final Map<UUID, EconomyUser> users = new HashMap<>();

    @Override
    public void onEnable() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final EconomyUser user = new User(player.getUniqueId(), player.getName());
            final EconomyUserConfiguration configuration = new UserConfiguration(user);

            user.setConfiguration(configuration);

            this.register(user);
        }
    }

    @Override
    public void onDisable() {
        this.users.clear();
    }

    @Override
    public void register(@NotNull final EconomyUser user) {
        this.users.put(user.getUniqueId(), user);
    }

    @Override
    public void unregister(@NotNull final UUID uniqueId) {
        this.users.remove(uniqueId);
    }

    @Override
    public Optional<EconomyUser> getUser(@NotNull final UUID uniqueId) {
        return Optional.ofNullable(this.users.get(uniqueId));
    }

    @Override
    public Optional<EconomyUser> getUser(@NotNull final String name) {
        return this.users.values().stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public void saveUser(@NotNull final EconomyUser user) {
        this.saveUser(user, EconomyUserProperty.UNIQUE_ID,
                EconomyUserProperty.NAME,
                EconomyUserProperty.BALANCE);
    }

    @Override
    public void saveUser(@NotNull final EconomyUser user, @NotNull final EconomyUserProperty... properties) {
        final ParsedMap<EconomyUserProperty, Object> data = new ParsedMap<>();

        for (final EconomyUserProperty property : properties) {
            switch (property) {
                case UNIQUE_ID:
                    data.put(property, user.getUniqueId());
                    break;

                case NAME:
                    data.put(property, user.getName());
                    break;

                case BALANCE:
                    data.put(property, user.getBalance());
                    break;

                default:
                    this.saveUser(user);
                    break;
            }
        }
        user.getConfiguration().write(data);
    }

}
