package com.juaanp.creeperbackguard.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperIgniteGoal.class)
public class CreeperIgniteGoalMixin {

    @Unique
    private double fovScale;

    @Shadow
    private LivingEntity target;

    @Shadow
    @Final
    private CreeperEntity creeper;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void creeperIgnitionGoal(CallbackInfo ci) {
        if (this.target instanceof PlayerEntity) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();

            if (minecraftClient != null) {
                GameOptions gameOptions = minecraftClient.options;

                if (gameOptions != null) {
                    fovScale = Math.min(gameOptions.getFov().getValue(), 80);

                    Vec3d targetPos = this.target.getCameraPosVec(1.0f);
                    Vec3d creeperPos = this.creeper.getCameraPosVec(1.0f);
                    Vec3d distance = creeperPos.subtract(targetPos);
                    if (!(this.target.getRotationVec(1.0f).dotProduct(distance.normalize()) >= Math.cos(Math.toRadians(fovScale)))) {
                        ci.cancel();
                    }
                }
            }
        }
    }
}