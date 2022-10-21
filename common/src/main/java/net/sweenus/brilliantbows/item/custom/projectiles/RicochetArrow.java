package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class RicochetArrow extends ArrowEntity {

    @Override
    protected void onHit(LivingEntity target) {
        int sradius = 8;
        int vradius = 2;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        ServerWorld sworld = (ServerWorld) this.world;
        Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
        for (Entity entities : sworld.getOtherEntities(target, box, EntityPredicates.VALID_LIVING_ENTITY)) {
            if (entities != null) {
                if (entities instanceof LivingEntity le) {
                    boolean dontbreak = false;

                    // Not really sure what you're trying to do with this if-else player check
                    if (le.isPlayer()) {
                        LivingEntity player = le;
                        dontbreak = true;
                    } else {
                        ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                        ArrowEntity arrowEntity = new RicochetArrow(world, target);
                        PersistentProjectileEntity persistentProjectileEntity = arrowEntity;
                        persistentProjectileEntity.setVelocity((le.getX() - target.getX()) / 6, (((le.getY() - target.getY()) - 0.5) / 6), (le.getZ() - target.getZ()) / 6);
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


                        if (dontbreak == false)
                            break;
                        else {dontbreak = false;}

                    }
                }
            }
        }

            //this.discard();
        }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

            }

    public RicochetArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
