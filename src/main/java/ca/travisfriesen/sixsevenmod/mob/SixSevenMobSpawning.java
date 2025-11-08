package ca.travisfriesen.sixsevenmod.mob;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Predicate;

public class SixSevenMobSpawning {

    public static void addSpawns() {
        // Set spawn placement rules
        SpawnPlacements.register(
                SixSevenMobs.SIX_SEVEN_ZOMBIE,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> true // Always allow spawning
        );

        // Create a predicate that checks if the biome is in the six_seven dimension
        Predicate<BiomeSelectionContext> sixSevenDimensionSelector = context -> {
            // Check if this biome can generate in the six_seven dimension
            ResourceKey<LevelStem> dimensionKey = ResourceKey.create(
                Registries.LEVEL_STEM,
                ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "six_seven")
            );

            // Check if the biome can generate in the six_seven dimension
            return context.canGenerateIn(dimensionKey);
        };

        // Add spawns to biomes in the six_seven dimension
        BiomeModifications.addSpawn(
                sixSevenDimensionSelector,
                MobCategory.CREATURE,
                SixSevenMobs.SIX_SEVEN_ZOMBIE,
                100, // Weight - higher = more common (100 is very high)
                4,   // Min group size
                8    // Max group size
        );

        SixSevenMod.LOGGER.info("Registered SixSevenZombie spawns for six_seven dimension");
    }
}


