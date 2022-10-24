package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class SeekerArrow2 extends ArrowEntity {

    // Redundant class - keeping here for later
    LivingEntity ourTarget;
    float multiplier;

    public void setTarget(LivingEntity target) {
        this.ourTarget = target;
    }
    public void damageMultiplier(Float multiplier) {
        this.multiplier = multiplier;
    }


    public SeekerArrow2(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
