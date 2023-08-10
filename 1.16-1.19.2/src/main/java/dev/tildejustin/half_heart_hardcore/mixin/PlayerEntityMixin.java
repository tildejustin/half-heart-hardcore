package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = {
                    "Lnet/minecraft/class_1657;<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;FLcom/mojang/authlib/GameProfile;Lnet/minecraft/class_7428;)V",
                    "Lnet/minecraft/class_1657;<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;FLcom/mojang/authlib/GameProfile;)V",
                    "Lnet/minecraft/class_1657;<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lcom/mojang/authlib/GameProfile;)V"
            }, at = @At(value = "TAIL"),
            require = 1
    )
    private void setMaxHealth(CallbackInfo ci) {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1);
        this.setHealth(this.getMaxHealth());
    }
}
