package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("MixinSuperClass")
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Unique
    private int count = 0;

    @Redirect(
            method = "method_5587",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(IIIIII)V",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/level/LevelProperties;isHardcore()Z"
                    )
            )
    )
    private void skipHeartRendering(InGameHud instance, int x, int y, int u, int v, int width, int height) {
        ClientPlayerEntity player = MinecraftClient.getInstance().field_3805;
        if (this.count < player.getMaxHealth() + player.getAbsorption()) {
            this.drawTexture(x, y, u, v, width, height);
        }
        this.count++;
    }

    @Inject(method = "method_5587", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/ControllablePlayerEntity;vehicle:Lnet/minecraft/entity/Entity;", opcode = Opcodes.GETFIELD))
    private void resetCount(CallbackInfo ci) {
        this.count = 0;
    }
}
