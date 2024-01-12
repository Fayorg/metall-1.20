package me.fayorg.monkecraft.metall.event;

import me.fayorg.monkecraft.metall.item.SlimeArmor;
import me.fayorg.monkecraft.metall.api.BounceHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class FallEvent {

    public static void onFallEvent(LivingFallEvent event) {

        ItemStack boots = event.getEntity().getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
        if(!(boots.getItem() instanceof SlimeArmor)) return;

        if(!event.getEntity().isShiftKeyDown() && event.getDistance() > 3) {
            event.setDamageMultiplier(0.0f);
            event.setDistance(0.0f);
            event.getEntity().fallDistance = 0.0f;

            if(event.getEntity().level().isClientSide) {
                event.getEntity().setDeltaMovement(event.getEntity().getDeltaMovement().x() / 0.95f, event.getEntity().getDeltaMovement().y() * -0.90f, event.getEntity().getDeltaMovement().z() / 0.95f);
                event.getEntity().hasImpulse = true;
                event.getEntity().setOnGround(false);
            } else {
                event.setCanceled(true);
            }

            event.getEntity().playSound(SoundEvents.SLIME_SQUISH, 1.0f, 1.0f);

            for (int i = 0; i < 8; i++) {
                float random1 = event.getEntity().getRandom().nextFloat() * 6.2831855F;
                float random2 = event.getEntity().getRandom().nextFloat() * 0.5F + 0.5F;
                float xOffset = Mth.sin(random1) * 0.5F * random2;
                float yOffset = Mth.cos(random1) * 0.5F * random2;
                event.getEntity().level().addParticle(ParticleTypes.ITEM_SLIME, event.getEntity().getX() + xOffset, event.getEntity().getY(), event.getEntity().getZ() + yOffset, 0, 0, 0);
            }

            BounceHandler.addBounceHandler(event.getEntity(), event.getEntity().getDeltaMovement().y);

        } else if(event.getEntity().isShiftKeyDown() && !event.getEntity().level().isClientSide) {
            event.setDamageMultiplier(0.3f);
        }

    }

    public static void onPlayerFlyFallEvent(LivingFallEvent event) {
        ItemStack boots = event.getEntity().getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
        if(!(boots.getItem() instanceof SlimeArmor)) return;

        if(!event.getEntity().isShiftKeyDown() && event.getDistance() > 3) {
            event.setDamageMultiplier(0.0f);
            event.setDistance(0.0f);
            event.getEntity().fallDistance = 0.0f;

            if(event.getEntity().level().isClientSide) {
                event.getEntity().setDeltaMovement(event.getEntity().getDeltaMovement().x() / 0.95f, event.getEntity().getDeltaMovement().y() * -0.90f, event.getEntity().getDeltaMovement().z() / 0.95f);
                event.getEntity().hasImpulse = true;
                event.getEntity().setOnGround(false);
            } else {
                event.setCanceled(true);
            }

            event.getEntity().playSound(SoundEvents.SLIME_SQUISH, 1.0f, 1.0f);

            for (int i = 0; i < 8; i++) {
                float random1 = event.getEntity().getRandom().nextFloat() * 6.2831855F;
                float random2 = event.getEntity().getRandom().nextFloat() * 0.5F + 0.5F;
                float xOffset = Mth.sin(random1) * 0.5F * random2;
                float yOffset = Mth.cos(random1) * 0.5F * random2;
                event.getEntity().level().addParticle(ParticleTypes.ITEM_SLIME, event.getEntity().getX() + xOffset, event.getEntity().getY(), event.getEntity().getZ() + yOffset, 0, 0, 0);
            }

            BounceHandler.addBounceHandler(event.getEntity(), event.getEntity().getDeltaMovement().y);

        } else if(event.getEntity().isShiftKeyDown() && !event.getEntity().level().isClientSide) {
            event.setDamageMultiplier(0.3f);
        }
    }

}
