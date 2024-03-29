package me.fayorg.monkecraft.metall.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.IdentityHashMap;

public class BounceHandler {

    public static final IdentityHashMap<Entity, BounceHandler> BOUNCING_ENTITIES = new IdentityHashMap<>();

    public final LivingEntity entity;
    private int timer;
    private boolean wasInAir;
    private double bounce;
    private int bounceTick;
    private double lastMoveX;
    private double lastMoveZ;

    public BounceHandler(LivingEntity entity, double bounce) {
        this.entity = entity;
        this.timer = 0;
        this.wasInAir = false;
        this.bounce = bounce;

        if (bounce != 0) {
            bounceTick = entity.tickCount;
        }
        else {
            bounceTick = 0;
        }

        BOUNCING_ENTITIES.put(entity, this);
    }

    public void onPlayerTick(Player player) {
        if (player == entity && !player.isFallFlying()) {
            if (player.tickCount == bounceTick) {
                Vec3 playerMovement = player.getDeltaMovement();
                player.setDeltaMovement(playerMovement.x, bounce, playerMovement.z);
                bounceTick = 0;
            }

            if (!entity.onGround() && entity.tickCount != bounceTick && (lastMoveX != entity.getDeltaMovement().x || lastMoveZ != entity.getDeltaMovement().z)) {
                double d = 0.935D;
                Vec3 entityMovement = entity.getDeltaMovement();
                player.setDeltaMovement(entityMovement.x / d, entityMovement.y, entityMovement.z / d);
                entity.hasImpulse = true;
                lastMoveX = entity.getDeltaMovement().x;
                lastMoveZ = entity.getDeltaMovement().z;
            }

            if (wasInAir && entity.onGround()) {
                if (timer == 0) {
                    timer = entity.tickCount;
                }
                else if (entity.tickCount - timer > 5) {
                    BOUNCING_ENTITIES.remove(entity);
                }
            }
            else {
                timer = 0;
                wasInAir = true;
            }
        }
    }

    public static void addBounceHandler(LivingEntity entity) {
        addBounceHandler(entity, 0);
    }

    public static void addBounceHandler(LivingEntity entity, double bounce) {
        if (!(entity instanceof Player)) {
            return;
        }

        BounceHandler handler = BOUNCING_ENTITIES.get(entity);

        if (handler == null) {
            new BounceHandler(entity, bounce);
        }
        else if (bounce != 0) {
            handler.bounce = bounce;
            handler.bounceTick = entity.tickCount;
        }
    }
}
