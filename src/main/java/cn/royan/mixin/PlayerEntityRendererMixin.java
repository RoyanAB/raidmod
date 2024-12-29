package cn.royan.mixin;

import cn.royan.render.InfoRender;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",at = @At(value = "HEAD"))
    public void render(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        Vec3d vec3d = new Vec3d(abstractClientPlayerEntity.getX(),abstractClientPlayerEntity.getY()+4,abstractClientPlayerEntity.getZ());
        Vec3d vec3d1 = new Vec3d(abstractClientPlayerEntity.getX(),abstractClientPlayerEntity.getY()+4.2,abstractClientPlayerEntity.getZ());
        Vec3d vec3d2 = new Vec3d(abstractClientPlayerEntity.getX(),abstractClientPlayerEntity.getY()+4.4,abstractClientPlayerEntity.getZ());
        InfoRender.drawString(matrixStack,vec3d,"X pos:"+abstractClientPlayerEntity.getX(),1,0.025F);
        InfoRender.drawString(matrixStack,vec3d1,"Y pos:"+abstractClientPlayerEntity.getY(),1,0.025F);
        InfoRender.drawString(matrixStack,vec3d2,"Z pos:"+abstractClientPlayerEntity.getZ(),1,0.025F);
    }
}
