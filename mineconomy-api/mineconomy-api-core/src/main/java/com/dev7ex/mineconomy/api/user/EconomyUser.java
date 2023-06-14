package com.dev7ex.mineconomy.api.user;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public interface EconomyUser {

    UUID getUniqueId();

    String getName();

    EconomyUserConfiguration getConfiguration();

    void setConfiguration(@NotNull final EconomyUserConfiguration configuration);

    int getBalance();

    void setBalance(int balance);

    void decrementBalance();

    void incrementBalance();

    void addBalance(final int balance);

    void removeBalance(final int balance);

}
