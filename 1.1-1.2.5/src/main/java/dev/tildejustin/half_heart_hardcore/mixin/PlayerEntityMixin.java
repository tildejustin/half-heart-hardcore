package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.entity.living.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @ModifyConstant(method = "getMaxHealth", constant = @Constant(intValue = 20))
    private int setMaxHealth(int maxHealth) {
        return 1;
    }
}
