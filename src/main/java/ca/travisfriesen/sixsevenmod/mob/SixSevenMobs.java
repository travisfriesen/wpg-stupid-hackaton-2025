package ca.travisfriesen.sixsevenmod.mob;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import ca.travisfriesen.sixsevenmod.mob.custom.SixSevenZombie;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.entity.MobCategory;

public class SixSevenMobs {
    public static final EntityType<SixSevenZombie> ZOMBIES = ResourceKey.create(
            Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "sixSevenZombie"))


    public static void registerSixSevenMobs(){
        SixSevenMod.LOGGER.info("Registering SixSevenMobs");
    }
}
