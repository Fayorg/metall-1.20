package me.fayorg.monkecraft.metall.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class SlingshotItem extends Item {
    public SlingshotItem() {
        super(new Item.Properties().stacksTo(1).durability(100));
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack hand = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.success(hand);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeLeft) {
        if(!(pLivingEntity instanceof Player player)) return;
        if(!player.onGround()) return;

        int timeUsed = getUseDuration(pStack) - pTimeLeft;

        // using a linear function to calculate the charge
        // float charge = timeUsed * 0.5f;

        // using a logarithmic function to calculate the charge
        float charge = (float) Math.log(timeUsed + 1) * 2;

        if(charge > 6) charge = 6;

        Vec3 look = player.getLookAngle().normalize();

        player.push(look.x * -charge, look.y * -charge / 3, look.z * -charge);

        pStack.hurtAndBreak(1, pLivingEntity, (entity) -> entity.broadcastBreakEvent(pLivingEntity.getUsedItemHand()));

        //TODO: add custom sound
        player.playSound(SoundEvents.SLIME_JUMP, 1, 1);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return pRepairCandidate.is(Tags.Items.SLIMEBALLS);
    }
}
