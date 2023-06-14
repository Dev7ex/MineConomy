package com.dev7ex.mineconomy.api.user;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
@Getter(AccessLevel.PUBLIC)
public enum EconomyUserProperty {

    UNIQUE_ID("unique-id"),
    NAME("name"),
    BALANCE("balance");

    private final String storagePath;

    EconomyUserProperty(@NotNull final String storagePath) {
        this.storagePath = storagePath;
    }

}
