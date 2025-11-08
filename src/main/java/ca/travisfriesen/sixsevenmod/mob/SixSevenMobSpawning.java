package ca.travisfriesen.sixsevenmod.mob;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

public class SixSevenMobSpawning {

    public static void addSpawns() {
        // Set spawn placement rules
        SpawnPlacements.register(
                SixSevenMod.SIX_SEVEN_ZOMBIE,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> true // Always allow spawning
        );

        // Add spawns to biomes
        // TODO: Once the six_seven dimension is fully implemented with biomes,
        // use a custom selector to restrict spawning to that dimension only
        BiomeModifications.addSpawn(
                BiomeSelectors.all(), // Spawns in all biomes (will work in six_seven dimension when created)
                MobCategory.CREATURE,
                SixSevenMobs.SIX_SEVEN_ZOMBIE,
                100, // Weight - higher = more common (100 is very high)
                4,   // Min group size
                8    // Max group size
        );

        SixSevenMod.LOGGER.info("Registered SixSevenZombie spawns for six_seven dimension");
    }
}

