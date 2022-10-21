package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.profiling.jfr.event.ServerTickTimeEvent;
import net.minecraft.world.World;

public class RainArrow2 extends ArrowEntity {

    @Override
    protected void onHit(LivingEntity target) {

        if (!this.world.isClient()) {


            for (int i = 25; i > 0; i--) {
                if (this.age % 1 == 0) {
                    ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                    int randoma = (int) (Math.random() * 12);
                    int randomb = (int) (Math.random() * 12);
                    PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, this.asItemStack(), (LivingEntity) this.getOwner());
                    persistentProjectileEntity.updatePosition(this.getX() - 6 + randoma, this.getY() + 20, this.getZ() - 6 + randomb);
                    //persistentProjectileEntity.setPos(this.getX() - 6 + randoma, this.getY() +20, this.getZ() - 6 + randomb);
                    persistentProjectileEntity.setVelocity(0, -3, 0);
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
                    //No farming arrows
                    persistentProjectileEntity.pickupType = PickupPermission.CREATIVE_ONLY;

                    world.spawnEntity(persistentProjectileEntity);
                    if (this.age % 200 == 0)
                        this.discard();
                }
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {

        if (!this.world.isClient()) {

            int sradius = 8;
            int vradius = 2;
            int arrowcount = 0;
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            ServerWorld sworld = (ServerWorld) this.world;
            Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
            for (Entity entities : sworld.getOtherEntities(this, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                if (entities != null) {
                    if (entities.getEntityName() == this.getEntityName()) {
                        arrowcount += 1;

                    }
                }
            }

            if (arrowcount > 10) {
                this.discard();
            }

            if (arrowcount < 10) {
                for (int i = 10; i > 0; i--) {
                    ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                    int randoma = (int) (Math.random() * 12);
                    int randomb = (int) (Math.random() * 12);
                    int randomc = (int) (Math.random() * 100);
                    PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, this.asItemStack(), (LivingEntity) this.getOwner());
                    persistentProjectileEntity.updatePosition(this.getX() - 6 + randoma, this.getY() + 20, this.getZ() - 6 + randomb);
                    //persistentProjectileEntity.setPos(this.getX() - 6 + randoma, this.getY() +20, this.getZ() - 6 + randomb);
                    persistentProjectileEntity.setVelocity(0, -3, 0);
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
                    //No farming arrows
                    persistentProjectileEntity.pickupType = PickupPermission.CREATIVE_ONLY;

                    world.spawnEntity(persistentProjectileEntity);

                    if (randomc < 10) {
                        ArrowItem arrowItem2 = (ArrowItem) Items.ARROW;
                        ArrowEntity arrowEntity = new RainArrow(world, (LivingEntity) this.getOwner());
                        PersistentProjectileEntity persistentProjectileEntity2 = arrowEntity;
                        persistentProjectileEntity2.updatePosition(this.getX() - 6 + randoma, this.getY() + 20, this.getZ() - 6 + randomb);
                        //persistentProjectileEntity.setPos(this.getX() - 6 + randoma, this.getY() +20, this.getZ() - 6 + randomb);
                        persistentProjectileEntity2.setVelocity(0, -3, 0);
                        persistentProjectileEntity2.setCritical(true);

                        int jj = EnchantmentHelper.getLevel(Enchantments.POWER, this.asItemStack());
                        if (jj > 0) {
                            persistentProjectileEntity2.setDamage(persistentProjectileEntity2.getDamage() + (double) j * 0.5 + 0.5);
                        }

                        int kk = EnchantmentHelper.getLevel(Enchantments.PUNCH, this.asItemStack());
                        if (kk > 0) {
                            persistentProjectileEntity2.setPunch(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, this.asItemStack()) > 0) {
                            persistentProjectileEntity2.setOnFireFor(100);
                        }
                        //No farming arrows
                        persistentProjectileEntity2.pickupType = PickupPermission.CREATIVE_ONLY;

                        world.spawnEntity(persistentProjectileEntity2);


                    }


                    if (this.age % 200 == 0)
                        this.discard();
                }
            }
        }
    }


    public RainArrow2(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
