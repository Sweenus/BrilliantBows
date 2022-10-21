package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplosiveArrow extends ArrowEntity {

    @Override
    protected void onHit(LivingEntity target) {
        if (!this.world.isClient) {
            boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1f, bl, bl ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
                    boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
                    this.world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 1f, bl, bl ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
                    this.discard();
            }

    public ExplosiveArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
