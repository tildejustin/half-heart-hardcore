package dev.tildejustin.half_heart_hardcore.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique
    private int count = 0;

    @Redirect(
            method = "method_979",
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
        if (this.count < Minecraft.getMinecraft().playerEntity.method_2599())
            instance.drawTexture(x, y, u, v, width, height);
        this.count++;
    }

    @Inject(method = "method_979", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 1))
    private void resetCount(CallbackInfo ci) {
        this.count = 0;
    }
}
