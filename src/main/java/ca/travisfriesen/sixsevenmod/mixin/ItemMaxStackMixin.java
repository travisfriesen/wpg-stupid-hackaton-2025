package ca.travisfriesen.sixsevenmod.mixin;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Item.Properties.class)
public abstract class ItemMaxStackMixin {

    @Shadow
    public abstract <T> Item.Properties component(DataComponentType<T> dataComponentType, T object);

    /**
     * @author Travis
     * @reason To make items stack to 67 instead of 64
     */
    @Overwrite
    public Item.Properties stacksTo(int i) {
//        SixSevenMod.LOGGER.info("ItemMaxStackMixin stacksTo " + i);
        if (i > 2) {
            return this.component(DataComponents.MAX_STACK_SIZE, 67);
        } else {
            return this.component(DataComponents.MAX_STACK_SIZE, i);
        }
    }
}