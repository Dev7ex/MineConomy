package com.dev7ex.mineconomy.user;

import com.dev7ex.common.bukkit.command.BukkitCommand;
import com.dev7ex.mineconomy.api.event.user.EconomyUserBalanceUpdateEvent;
import com.dev7ex.mineconomy.api.user.EconomyUser;
import com.dev7ex.mineconomy.api.user.EconomyUserConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class User implements EconomyUser {

    private final UUID uniqueId;
    private final String name;
    private EconomyUserConfiguration configuration;
    private int balance;

    public User(@NotNull final UUID uniqueId, @NotNull final String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    @Override
    public void decrementBalance() {
        Bukkit.getPluginManager().callEvent(new EconomyUserBalanceUpdateEvent(this, this.balance, this.balance--));
    }

    @Override
    public void incrementBalance() {
        Bukkit.getPluginManager().callEvent(new EconomyUserBalanceUpdateEvent(this, this.balance, this.balance++));
    }

    @Override
    public void addBalance(final int balance) {
        Bukkit.getPluginManager().callEvent(new EconomyUserBalanceUpdateEvent(this, this.balance, this.balance = this.balance + balance));
    }

    @Override
    public void removeBalance(final int balance) {
        Bukkit.getPluginManager().callEvent(new EconomyUserBalanceUpdateEvent(this, this.balance, this.balance = this.balance - balance));
    }

    @Override
    public void setBalance(final int balance) {
        Bukkit.getPluginManager().callEvent(new EconomyUserBalanceUpdateEvent(this, this.balance, this.balance = balance));
    }

}
