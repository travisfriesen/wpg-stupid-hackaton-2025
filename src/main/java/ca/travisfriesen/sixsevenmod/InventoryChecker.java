package ca.travisfriesen.sixsevenmod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public final class InventoryChecker {
    private InventoryChecker() {}

    public static boolean hasStackOf67Blocks(Player player) {
        if (player == null) return false;

        // Main inventory
        for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem && stack.getCount() == 67) {
                return true;
            }
        }

        return false;
    }
}
