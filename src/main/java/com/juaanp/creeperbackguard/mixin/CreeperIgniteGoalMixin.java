package com.juaanp.creeperbackguard.mixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwellGoal.class)
public class CreeperIgniteGoalMixin {
    @Unique
    private double creeperBackguard$fovScale;
    @Shadow private LivingEntity target;
    @Shadow @Final private Creeper creeper;
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void creeperIgnitionGoal(CallbackInfo ci) {
        if (this.target instanceof Player) {
            Entity player = this.target;
            Entity creeper = this.creeper;
            if (player.level.isClientSide) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft != null) {
                    Options gameOptions = minecraft.options;
                    if (gameOptions != null) {
                        creeperBackguard$fovScale = Math.min(gameOptions.fov().get(), 80);
                    }
                }
            } else {
                creeperBackguard$fovScale = 70;
            }
            Vec3 targetPos = player.getEyePosition(1.0f);
            Vec3 creeperPos = creeper.getEyePosition(1.0f);
            Vec3 distance = creeperPos.subtract(targetPos);
            if (!(player.getViewVector(1.0f).dot(distance.normalize()) >= Math.cos(Math.toRadians(creeperBackguard$fovScale)))) {
                ci.cancel();
            }
        }
    }
}