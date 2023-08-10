package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends MobEntity {
    public PlayerEntityMixin(World world) {
        super(world);
    }

    @ModifyConstant(method = "method_2599", constant = @Constant(intValue = 20))
    private int setMaxHealth(int maxHealth) {
        return 1;
    }
}
