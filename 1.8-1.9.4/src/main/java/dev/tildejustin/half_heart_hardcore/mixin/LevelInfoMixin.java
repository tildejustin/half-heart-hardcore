package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.world.level.LevelInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LevelInfo.class)
public abstract class LevelInfoMixin {
    @ModifyVariable(
            method = "<init>(JLnet/minecraft/world/level/LevelInfo$GameMode;ZZLnet/minecraft/world/level/LevelGeneratorType;)V",
            at = @At(
                    value = "HEAD"
            ),
            ordinal = 0,
            argsOnly = true
    )
    private static LevelInfo.GameMode setGameMode(LevelInfo.GameMode gameMode) {
        return LevelInfo.GameMode.SURVIVAL;
    }

    @ModifyVariable(
            method = "<init>(JLnet/minecraft/world/level/LevelInfo$GameMode;ZZLnet/minecraft/world/level/LevelGeneratorType;)V",
            at = @At(
                    value = "HEAD"
            ),
            ordinal = 1,
            argsOnly = true
    )
    private static boolean setHardcore(boolean hardcore) {
        return true;
    }
}
