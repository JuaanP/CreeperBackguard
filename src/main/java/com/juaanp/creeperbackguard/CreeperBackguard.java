package com.juaanp.creeperbackguard;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;


@Mod("creeperbackguard")
public class CreeperBackguard
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public CreeperBackguard()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
