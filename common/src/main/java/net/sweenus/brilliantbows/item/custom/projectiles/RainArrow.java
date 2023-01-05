package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class RainArrow extends ArrowEntity {
    
    @Override
    public void tick() {

        if (!this.world.isClient()) {


            if (this.age % 1 == 0) {
                ArrowItem arrowItem = (ArrowItem) Items.ARROW;
                int randoma = (int) (Math.random() * 12);
                int randomb = (int) (Math.random() * 12);
                if (this.getOwner() != null) {
                    ArrowEntity arrowEntity = new RainArrow3(world, (LivingEntity) this.getOwner());
                    PersistentProjectileEntity persistentProjectileEntity = arrowEntity;
                    persistentProjectileEntity.updatePosition(this.getX() - 6 + randoma, this.getY() + 20, this.getZ() - 6 + randomb);
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
                    persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

                    world.spawnEntity(persistentProjectileEntity);
                }
                if (this.age % 200 == 0)
                    this.discard();
            }
        }
    }


    public RainArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
