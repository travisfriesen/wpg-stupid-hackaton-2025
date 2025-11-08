# Six Seven Zombie Spawning Configuration

## Current Setup

The Six Seven Zombie is configured to spawn with the following settings:

- **Spawn Weight**: 100 (very high - they'll be common)
- **Group Size**: 4-8 zombies per spawn
- **Spawn Location**: Currently set to spawn in ALL biomes
- **Spawn Type**: ON_GROUND (like normal mobs)

## Spawning in the six_seven Dimension

Once the `six_seven` dimension is created, the zombies will automatically spawn there because they spawn in all biomes.

### To Restrict Spawning to ONLY the six_seven Dimension:

When the six_seven dimension is fully implemented, update `SixSevenMobSpawning.java`:

```java
// Replace BiomeSelectors.all() with:
BiomeModifications.addSpawn(
    context -> {
        // Get the dimension key from the biome context
        var dimensionKey = context.getBiomeRegistryEntry().getKey();
        // Check if it's in the six_seven dimension
        return dimensionKey != null && 
               dimensionKey.location().equals(
                   ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "six_seven")
               );
    },
    MobCategory.CREATURE,
    SixSevenMobs.SIX_SEVEN_ZOMBIE,
    100, // Weight
    4,   // Min group
    8    // Max group
);
```

## Adjusting Spawn Rates

Edit the values in `SixSevenMobSpawning.java`:

- **Weight (100)**: Higher = more common. Set to 10-20 for rare, 50 for common, 100+ for very common
- **Min Group (4)**: Minimum zombies in a spawn group
- **Max Group (8)**: Maximum zombies in a spawn group

## Files Modified

- `/src/main/java/ca/travisfriesen/sixsevenmod/mob/SixSevenMobSpawning.java` - Spawn rules
- `/src/main/java/ca/travisfriesen/sixsevenmod/SixSevenMod.java` - Registers spawns on initialization

