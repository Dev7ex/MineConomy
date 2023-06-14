package com.dev7ex.mineconomy.api.event.user;

import com.dev7ex.mineconomy.api.user.EconomyUser;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
public class EconomyUserLoginEvent extends EconomyUserEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public EconomyUserLoginEvent(@NotNull final EconomyUser user) {
        super(user);
    }

    public final HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
