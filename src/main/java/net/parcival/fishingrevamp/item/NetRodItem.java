package net.parcival.fishingrevamp.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.parcival.fishingrevamp.CustomFishingBobberEntity;

public class NetRodItem extends FishingRodItem implements Vanishable {
    // copied from FishingRodItem (mappings)
    public NetRodItem(Item.Settings settings) {
        super(settings);
    }
    private CustomFishingBobberEntity fishHook;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (fishHook != null) {
            if (!world.isClient) {
                int i = fishHook.use(itemStack);
                itemStack.damage(i, user, p -> p.sendToolBreakStatus(hand));
                fishHook = null;
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            world.emitGameEvent(user, GameEvent.FISHING_ROD_REEL_IN, user);
        } else {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            if (!world.isClient) {
                int i = EnchantmentHelper.getLure(itemStack);
                int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
                CustomFishingBobberEntity newBobber = new CustomFishingBobberEntity(user, world, j, i);
                fishHook = newBobber;
                world.spawnEntity(newBobber);
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            world.emitGameEvent(user, GameEvent.FISHING_ROD_CAST, user);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
