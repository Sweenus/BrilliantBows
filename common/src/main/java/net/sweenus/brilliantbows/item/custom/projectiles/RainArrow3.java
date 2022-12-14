package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class RainArrow3 extends ArrowEntity {

    @Override
    protected void onHit(LivingEntity target) {
        if (!this.world.isClient) {
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.world.isClient) {
            this.discard();
            int randoma = (int) (Math.random() * 100);
            if (randoma < 50)
                world.playSound(null, getBlockX(), getBlockY(), getBlockZ(), SoundEvents.ENTITY_ARROW_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    public RainArrow3(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
