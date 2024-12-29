package cn.royan.mixin;

import cn.royan.render.InfoRender;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntityRenderer.class)
public abstract class ProjectileEntityRendererMixin<T extends PersistentProjectileEntity> extends EntityRenderer<T> {
    protected ProjectileEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "render(Lnet/minecraft/entity/projectile/PersistentProjectileEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "HEAD"))
    public void render(T persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        InfoRender.drawString(matrixStack,persistentProjectileEntity.getPos(),"X pos:"+persistentProjectileEntity.getX(),1,0.025F);
        System.out.println("1");
    }
}
