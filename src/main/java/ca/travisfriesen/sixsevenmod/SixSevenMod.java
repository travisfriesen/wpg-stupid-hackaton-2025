package ca.travisfriesen.sixsevenmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerPickItemEvents;

import net.fabricmc.fabric.impl.resource.loader.FabricResource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.PortalProcessor;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SixSevenMod implements ModInitializer {
	public static final String MOD_ID = "sixsevenmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private final Set<UUID> triggeredPlayers = new HashSet<>();
    public static final ResourceKey<Level> SIX_SEVEN_DIMENSION = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MOD_ID  , "six_seven"));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
        eventHandler();
	}

    public void eventHandler() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // iterate online players
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.level().dimension().equals(Level.OVERWORLD) || player.level().dimension().equals(Level.NETHER) || player.level().dimension().equals(Level.END)) {
                    boolean has67 = false;
                    // check player's inventory for any stack of size 67
                    for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
                        if (!stack.isEmpty() && stack.getCount() == 67) {
                            has67 = true;
                            break;
                        }
                    }

                    UUID playerId = player.getUUID();
                    if (has67 && !triggeredPlayers.contains(playerId)) {
                        // first time reaching the state -> perform action
                        player.displayClientMessage(Component.literal("You reached a stack of 67!"), false);
                        ServerLevel targetLevel = server.getLevel(SIX_SEVEN_DIMENSION);

                        if (targetLevel != null) {
                            BlockPos spawn = player.blockPosition();
                            Vec3 destPos = new Vec3(spawn.getX() + 0.5, spawn.getY(), spawn.getZ() + 0.5);
                            float yaw = player.getYRot();
                            float pitch = player.getXRot();

                            Set<Relative> relatives = Relative.union(Relative.DELTA, Relative.ROTATION);
                            TeleportTransition transition = new TeleportTransition(
                                    targetLevel,
                                    destPos,
                                    Vec3.ZERO,
                                    yaw,
                                    pitch,
                                    relatives,
                                    TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET)
                            );

                            player.teleport(transition);
                        } else {
                            LOGGER.warn("Target dimension {} not found on server", SIX_SEVEN_DIMENSION.location());
                        }

                        triggeredPlayers.add(playerId);
                    } else if (!has67 && triggeredPlayers.contains(playerId)) {
                        // player no longer has a 67-stack -> allow future triggers
                        triggeredPlayers.remove(playerId);
                    }
                }
            }
        });
    }
}