package com.juaanp.creeperbackguard;

import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

@Mod("creeperbackguard")
public class CreeperBackguard {

    public CreeperBackguard() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.creeperbackguard.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }
}