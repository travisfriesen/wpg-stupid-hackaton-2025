package ca.travisfriesen.sixsevenmod;

import ca.travisfriesen.sixsevenmod.mob.SixSevenMobs;
import ca.travisfriesen.sixsevenmod.model.SixSevenZombieModel;
import ca.travisfriesen.sixsevenmod.renderer.SixSevenZombieRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SixSevenModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register entity renderer
		EntityRendererRegistry.register(SixSevenMobs.SIX_SEVEN_ZOMBIE, SixSevenZombieRenderer::new);

		// Register model layer
		EntityModelLayerRegistry.registerModelLayer(SixSevenZombieModel.LAYER_LOCATION, SixSevenZombieModel::createBodyLayer);
	}
}