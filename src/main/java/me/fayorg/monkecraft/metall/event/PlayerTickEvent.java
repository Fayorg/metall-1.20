package me.fayorg.monkecraft.metall.event;

import me.fayorg.monkecraft.metall.api.BounceHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

public class PlayerTickEvent {

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        // Slingshot Item
        Player player = event.player;
        BounceHandler bounceHandler = BounceHandler.BOUNCING_ENTITIES.get(player);
        if (bounceHandler != null) {
            bounceHandler.onPlayerTick(player);
        }
    }

}
