package ca.travisfriesen.sixsevenmod.renderer;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import ca.travisfriesen.sixsevenmod.mob.custom.SixSevenZombie;
import ca.travisfriesen.sixsevenmod.model.SixSevenZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class SixSevenZombieRenderer extends MobRenderer<SixSevenZombie, LivingEntityRenderState, SixSevenZombieModel<LivingEntityRenderState>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "textures/entity/six_seven_zombie.png");

    public SixSevenZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new SixSevenZombieModel<>(context.bakeLayer(SixSevenZombieModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
