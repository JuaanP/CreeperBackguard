package com.juaanp.creeperbackguard;

import eu.midnightdust.lib.config.MidnightConfig;

public class CreeperBackguardConfig extends MidnightConfig {
    @Entry
    public static boolean igniteOnThirdPerson = false;
    @Entry
    public static boolean overrideFov = false;

    @Entry(min = 10, max = 80, isSlider = true)
    public static int fovValue = 70;
}