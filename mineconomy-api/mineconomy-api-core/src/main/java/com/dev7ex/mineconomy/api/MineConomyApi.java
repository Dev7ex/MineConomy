package com.dev7ex.mineconomy.api;

import com.dev7ex.mineconomy.api.user.EconomyUserProvider;

import java.io.File;

/**
 * @author Dev7ex
 * @since 13.06.2023
 */
public interface MineConomyApi {

    EconomyUserProvider getUserProvider();

    File getUserFolder();

}
