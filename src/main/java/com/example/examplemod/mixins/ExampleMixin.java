package com.example.examplemod.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedStoneWireBlock.class)
public class ExampleMixin {

    @Inject(method = "onPlace", at = @At("HEAD"), cancellable = true)
    private void testOnPlace(BlockState p_55630_, Level p_55631_, BlockPos p_55632_, BlockState p_55633_, boolean p_55634_, CallbackInfo cir) {
        System.out.println("Placed the redstone");
    }
}
