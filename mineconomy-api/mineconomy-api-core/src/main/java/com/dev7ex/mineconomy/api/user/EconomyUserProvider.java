package com.dev7ex.mineconomy.api.user;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public interface EconomyUserProvider {

    void register(@NotNull final EconomyUser user);

    void unregister(@NotNull final UUID uniqueId);

    Optional<EconomyUser> getUser(@NotNull final UUID uniqueId);

    Optional<EconomyUser> getUser(@NotNull final String name);

    Map<UUID, EconomyUser> getUsers();

    void saveUser(@NotNull final EconomyUser user);

    void saveUser(@NotNull final EconomyUser user, @NotNull final EconomyUserProperty... properties);

}
