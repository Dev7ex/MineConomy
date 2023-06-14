package com.dev7ex.mineconomy.api.event.user;

import com.dev7ex.mineconomy.api.user.EconomyUser;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
@Getter(AccessLevel.PUBLIC)
public abstract class EconomyUserEvent extends Event {

    private final EconomyUser user;

    public EconomyUserEvent(@NotNull final EconomyUser user) {
        this.user = user;
    }

}
