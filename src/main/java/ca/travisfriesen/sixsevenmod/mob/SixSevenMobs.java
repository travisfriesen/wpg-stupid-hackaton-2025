package ca.travisfriesen.sixsevenmod.mob;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import ca.travisfriesen.sixsevenmod.mob.custom.SixSevenZombie;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SixSevenMobs {
    public static final EntityType<SixSevenZombie> SIX_SEVEN_ZOMBIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "six_seven_zombie"),
            EntityType.Builder.of(SixSevenZombie::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "six_seven_zombie")))
    );

    public static void registerSixSevenMobs(){
        SixSevenMod.LOGGER.info("Registering SixSevenMobs");

        FabricDefaultAttributeRegistry.register(SIX_SEVEN_ZOMBIE, SixSevenZombie.createAttributes());
    }
}
