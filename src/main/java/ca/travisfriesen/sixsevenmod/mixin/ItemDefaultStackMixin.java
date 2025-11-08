package ca.travisfriesen.sixsevenmod.mixin;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BlockItem.class)
public abstract class ItemDefaultStackMixin {

    @ModifyArg(
            method = "<init>(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/item/Item$Properties;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties modifyBlockItemProperties(Item.Properties properties) {
        return properties.stacksTo(67);
    }

}
