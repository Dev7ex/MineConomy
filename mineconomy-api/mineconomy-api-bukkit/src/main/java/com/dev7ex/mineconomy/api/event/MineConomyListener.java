package com.dev7ex.mineconomy.api.event;

import com.dev7ex.mineconomy.api.BukkitMineConomyApi;
import com.dev7ex.mineconomy.api.user.EconomyUserProvider;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public abstract class MineConomyListener implements Listener {

    private BukkitMineConomyApi mineConomyApi;

    public MineConomyListener(@NotNull final BukkitMineConomyApi mineConomyApi) {
        this.mineConomyApi = mineConomyApi;
    }

    public EconomyUserProvider getUserProvider() {
        return this.mineConomyApi.getUserProvider();
    }

}
