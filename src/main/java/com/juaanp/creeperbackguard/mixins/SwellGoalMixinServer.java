package com.juaanp.creeperbackguard.mixins;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
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
            Vec2 targetPos = new Vec2((float) player.getX(), (float) player.getZ());
            Vec2 creeperPos = new Vec2((float) creeper.getX(),(float) creeper.getZ());
            Vec2 distance = new Vec2(creeperPos.x - targetPos.x, creeperPos.y - targetPos.y);
            if (!(Mth.cos(player.getYRot() * 0.017453292F) * distance.x + Mth.sin(player.getYRot() * 0.017453292F) * distance.y >= Math.cos(Math.toRadians(fovScale)))) {
                ci.cancel();
            }
        }
    }
}