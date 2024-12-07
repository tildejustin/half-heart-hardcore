package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "initializeAttributes", at = @At(value = "TAIL"))
    private void setMaxHealth(CallbackInfo ci) {
        this.getAttributeContainer().get(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1);
    }
}
