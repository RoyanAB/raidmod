package cn.royan.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class InfoRender {

    public static void drawString(MatrixStack matrices, Vec3d vec3d, String text, int color, float size){
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        drawString(matrices, camera,(float)vec3d.x,(float)vec3d.y,(float)vec3d.z,text,color,size);
    }

    public static void drawString(MatrixStack matrices, BlockPos pos, String text, int color,float size){
        Vec3d vec3d = new Vec3d(pos.toCenterPos().toVector3f());
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        drawString(matrices, camera,(float)vec3d.x,(float)vec3d.y,(float)vec3d.z,text,color,size);
    }

    public static void drawString(MatrixStack matrices, Camera camera, float x,float y,float z, String text, int color, float size) {

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        float backgroundOpacity = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
        int backgroundColor = (int)(backgroundOpacity * 255.0f) << 24;
        double x1 = camera.getPos().x;
        double y1 = camera.getPos().y;
        double z1 = camera.getPos().z;
        matrices.push();
        matrices.translate((float)(x - x1), (float)(y - y1) + 0.07F, (float)(z - z1));
        matrices.multiply(MinecraftClient.getInstance().gameRenderer.getCamera().getRotation());
        matrices.scale(-size, -size, size);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float renderX = -textRenderer.getWidth(text) * 0.5F + 1;
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        textRenderer.draw(text, renderX, 0, color, false, matrix4f, immediate, TextRenderer.TextLayerType.SEE_THROUGH, backgroundColor, 0xF000F0);
        textRenderer.draw(text, renderX, 0, color, false, matrix4f, immediate, TextRenderer.TextLayerType.NORMAL, 0, 0xF000F0);
        immediate.draw();
        matrices.pop();

    }

}
