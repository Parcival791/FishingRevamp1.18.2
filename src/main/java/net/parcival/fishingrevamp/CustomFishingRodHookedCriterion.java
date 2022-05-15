package net.parcival.fishingrevamp;

import net.minecraft.advancement.criterion.FishingRodHookedCriterion;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collection;

public class CustomFishingRodHookedCriterion extends FishingRodHookedCriterion {
    public static void trigger(ServerPlayerEntity player, ItemStack rod, CustomFishingBobberEntity bobber, Collection<ItemStack> fishingLoots) {}
}
