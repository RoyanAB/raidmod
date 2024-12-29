package cn.royan.mixin;

import cn.royan.render.HopperRender;
import cn.royan.render.InfoRender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Mixin(WorldRenderer.class)
public class WorldRenderMixin {

	@Inject(method = "render", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/render/WorldRenderer;renderChunkDebugInfo(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/Camera;)V", ordinal = 0))
	private void renderInjecr(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci){
	Vec3d vec3d = new Vec3d(0,0,0);
	InfoRender.drawString(matrices,vec3d,"awa",1,0.012F);
	HopperRender.render(matrices);
	}
}