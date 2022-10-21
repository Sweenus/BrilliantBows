package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class RainArrow extends ArrowEntity {

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

    @Override
    public void tick() {

        if (!this.world.isClient()) {
            int sradius = 15;
            int vradius = 10;
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            ServerWorld sworld = (ServerWorld) this.world;
            Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
            for (Entity entities : sworld.getOtherEntities(this, box, EntityPredicates.VALID_LIVING_ENTITY)) {

                if (entities != null) {
                    if (entities instanceof LivingEntity le) {

                        // Not really sure what you're trying to do with this if-else player check
                        if (le.isPlayer()) {LivingEntity player = le;}
                        else {
                            le.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20, 1), this);
                            ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, this.asItemStack(), le);
                            persistentProjectileEntity.setVelocity(this, this.getPitch(), le.getYaw() - this.getYaw(), 0.0F, 1 * 3.0F, 1.0F);
                            persistentProjectileEntity.setCritical(true);

                            int j = EnchantmentHelper.getLevel(Enchantments.POWER, this.asItemStack());
                            if (j > 0) {
                                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) j * 0.5 + 0.5);
                            }

                            int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, this.asItemStack());
                            if (k > 0) {
                                persistentProjectileEntity.setPunch(k);
                            }

                            if (EnchantmentHelper.getLevel(Enchantments.FLAME, this.asItemStack()) > 0) {
                                persistentProjectileEntity.setOnFireFor(100);
                            }

                            world.spawnEntity(persistentProjectileEntity);

                        }
                    }

                    world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);


                    }
                }
            }
        }


    public RainArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
