package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameGui.class)
public abstract class GameGuiMixin {
    @Shadow
    private Minecraft minecraft;
    @Unique
    private int count = 0;

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GameGui;drawTexture(IIIIII)V",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/WorldData;isHardcore()Z"
                    )
            )
    )
    private void skipHeartRendering(GameGui instance, int x, int y, int u, int v, int width, int height) {
        if (this.count < this.minecraft.player.getHealth())
            instance.drawTexture(x, y, u, v, width, height);
        this.count++;
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/entity/living/player/InputPlayerEntity;hasStatusEffect(Lnet/minecraft/entity/living/effect/StatusEffect;)Z",
                    ordinal = 3
            )
    )
    private void resetCount(CallbackInfo ci) {
        this.count = 0;
    }
}
