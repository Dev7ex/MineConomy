package com.dev7ex.mineconomy.api.user;

import com.dev7ex.common.map.ParsedMap;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public interface EconomyUserConfiguration {

    ParsedMap<EconomyUserProperty, Object> read();

    ParsedMap<EconomyUserProperty, Object> read(@NotNull final EconomyUserProperty... properties);

    void write(@NotNull final ParsedMap<EconomyUserProperty, Object> userData);

    void saveFile();

}
