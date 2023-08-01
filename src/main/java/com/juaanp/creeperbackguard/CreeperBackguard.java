package com.juaanp.creeperbackguard;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreeperBackguard implements ModInitializer {

    public static final String MOD_ID = "creeperbackguard";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("CreeperBackguard Init");
        MidnightConfig.init(MOD_ID, CreeperBackguardConfig.class);
    }
}
