package com.dev7ex.mineconomy.api;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public class MineConomyProvider {

    @Getter(AccessLevel.PUBLIC)
    private static MineConomyApi mineConomyApi;

    public static void registerApi(@NotNull final MineConomyApi mineConomyApi) {
        MineConomyProvider.mineConomyApi = mineConomyApi;
    }

    public static void unregisterApi() {
        MineConomyProvider.mineConomyApi = null;
    }

}
