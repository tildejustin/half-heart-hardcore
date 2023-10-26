package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldSettings.class)
public abstract class WorldSettingsMixin {
    @ModifyVariable(
            method = "<init>",
            at = @At(
                    value = "HEAD"
            ),
            ordinal = 0,
            argsOnly = true
    )
    private static int setGameMode(int gameMode) {
        return 0;
    }

    @ModifyVariable(
            method = "<init>",
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
