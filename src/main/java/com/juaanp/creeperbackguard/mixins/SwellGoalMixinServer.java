package com.juaanp.creeperbackguard.mixins;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(SwellGoal.class)
public class SwellGoalMixinServer {
    @Unique
    private double fovScale;
    @Shadow
    private LivingEntity target;
    @Shadow
    @Final
    private Creeper creeper;
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void swellGoal(CallbackInfo ci) {
        if (this.target instanceof Player) {
            Player player = (Player) this.target;
            LivingEntity creeper = this.creeper;
            fovScale = 70;

            Vec3 playerPos = player.getEyePosition();
            Vec3 creeperPos = creeper.getEyePosition();
            Vec3 distance = creeperPos.subtract(playerPos);

            if (!(player.getLookAngle().dot(distance.normalize()) >= Math.cos(Math.toRadians(fovScale)))) {
                ci.cancel();
            }
        }
    }
}