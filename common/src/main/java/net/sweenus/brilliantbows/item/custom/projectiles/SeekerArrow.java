package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.registry.ItemsRegistry;
import net.sweenus.brilliantbows.registry.SoundRegistry;

public class SeekerArrow extends ArrowEntity {

    LivingEntity ourTarget;
    float multiplier;

    public void setTarget(LivingEntity target) {
        this.ourTarget = target;
    }
    public void damageMultiplier(Float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void tick() {

        if (!this.world.isClient()) {


            if (this.age % 20 == 0) {
                int randoma = (int) (Math.random() * 12);
                int randomb = (int) (Math.random() * 12);


                if (ourTarget != null) {
                    ArrowEntity arrowEntity = new SeekerArrow2(world, (LivingEntity) this.getOwner());
                    arrowEntity.updatePosition(ourTarget.getX(), ourTarget.getY() + 10, ourTarget.getZ());
                    arrowEntity.setVelocity(0, -3 * multiplier, 0);
                    arrowEntity.setCritical(true);

                    int j = EnchantmentHelper.getLevel(Enchantments.POWER, this.asItemStack());
                    if (j > 0) {
                        arrowEntity.setDamage(arrowEntity.getDamage() + (double) j * 0.5 + 0.5);
                    }

                    int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, this.asItemStack());
                    if (k > 0) {
                        arrowEntity.setPunch(k);
                    }

                    if (EnchantmentHelper.getLevel(Enchantments.FLAME, this.asItemStack()) > 0) {
                        arrowEntity.setOnFireFor(100);
                    }
                    //No farming arrows
                    arrowEntity.pickupType = PickupPermission.CREATIVE_ONLY;

                    world.spawnEntity(arrowEntity);

                    world.spawnEntity(arrowEntity);
                    ((SeekerArrow2) arrowEntity).setTarget(ourTarget); // Sets the target for the arrow
                    ((SeekerArrow2) arrowEntity).damageMultiplier(multiplier); // Sets the target for the arrow
                    world.playSoundFromEntity (null, ourTarget, SoundRegistry.HOLY_HIT.get() , SoundCategory.PLAYERS, 0.3f, 2.5f);

                }

                this.discard();
                }
            }
        }


    public SeekerArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
