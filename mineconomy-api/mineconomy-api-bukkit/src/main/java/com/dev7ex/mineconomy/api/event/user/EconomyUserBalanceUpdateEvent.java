package com.dev7ex.mineconomy.api.event.user;

import com.dev7ex.mineconomy.api.user.EconomyUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class EconomyUserBalanceUpdateEvent extends EconomyUserEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final int oldBalance;
    private int newBalance;

    public EconomyUserBalanceUpdateEvent(@NotNull final EconomyUser user, final int oldBalance, final int newBalance) {
        super(user);
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public final HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
