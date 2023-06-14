package com.dev7ex.mineconomy.user;

import com.dev7ex.common.map.ParsedMap;
import com.dev7ex.mineconomy.MineConomyPlugin;
import com.dev7ex.mineconomy.api.user.EconomyUser;
import com.dev7ex.mineconomy.api.user.EconomyUserConfiguration;
import com.dev7ex.mineconomy.api.user.EconomyUserProperty;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Dev7ex
 * @since 14.06.2023
 */
public class UserConfiguration implements EconomyUserConfiguration {

    private final EconomyUser user;
    private File configurationFile;
    private YamlConfiguration fileConfiguration;

    @SneakyThrows
    public UserConfiguration(final EconomyUser user) {
        this.user = user;
        this.configurationFile = new File(MineConomyPlugin.getInstance().getSubFolder("user")
                + File.separator + user.getUniqueId().toString() + ".yml");

        if (!this.configurationFile.exists()) {
            this.configurationFile.createNewFile();
        }
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configurationFile);
        this.fileConfiguration.addDefault(EconomyUserProperty.UNIQUE_ID.getStoragePath(), user.getUniqueId().toString());
        this.fileConfiguration.addDefault(EconomyUserProperty.NAME.getStoragePath(), user.getName());
        this.fileConfiguration.addDefault(EconomyUserProperty.BALANCE.getStoragePath(), user.getBalance());
        this.fileConfiguration.options().copyDefaults(true);
        this.saveFile();
    }

    @Override
    public ParsedMap<EconomyUserProperty, Object> read() {
        final ParsedMap<EconomyUserProperty, Object> userData = new ParsedMap<>();

        Arrays.stream(EconomyUserProperty.values()).forEach(property -> {
            switch (property) {
                case UNIQUE_ID:
                    userData.put(property, UUID.fromString(Objects.requireNonNull(this.fileConfiguration.getString(property.getStoragePath()))));
                    break;

                case NAME:
                    userData.put(property, this.fileConfiguration.getString(property.getStoragePath()));
                    break;

                case BALANCE:
                    userData.put(property, this.fileConfiguration.getInt(property.getStoragePath()));
                    break;
            }
        });
        return userData;
    }

    @Override
    public ParsedMap<EconomyUserProperty, Object> read(final EconomyUserProperty... properties) {
        if (properties == null) {
            return this.read();
        }
        final ParsedMap<EconomyUserProperty, Object> userData = new ParsedMap<>();

        for (final EconomyUserProperty property : properties) {
            switch (property) {
                case UNIQUE_ID:
                    userData.put(property, UUID.fromString(Objects.requireNonNull(this.fileConfiguration.getString(property.getStoragePath()))));
                    break;

                case NAME:
                    userData.put(property, this.fileConfiguration.getString(property.getStoragePath()));
                    break;

                case BALANCE:
                    userData.put(property, this.fileConfiguration.getInt(property.getStoragePath()));
                    break;

                default:
                    break;
            }
        }
        return userData;
    }

    @Override
    public void write(final ParsedMap<EconomyUserProperty, Object> userData) {
        for (final EconomyUserProperty property : userData.keySet()) {
            switch (property) {
                case UNIQUE_ID:
                    this.fileConfiguration.set(property.getStoragePath(), userData.getUUID(property).toString());
                    break;

                case NAME:
                    this.fileConfiguration.set(property.getStoragePath(), userData.getString(property));
                    break;

                case BALANCE:
                    this.fileConfiguration.set(property.getStoragePath(), userData.getInteger(property));
                    break;
            }
        }
        this.saveFile();
    }

    @Override
    @SneakyThrows
    public void saveFile() {
        this.fileConfiguration.save(this.configurationFile);
    }

}
