package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

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
                    ArrowItem arrowItem = (ArrowItem) Items.ARROW;
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

                }

                world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F));
                this.discard();
                }
            }
        }


    public SeekerArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
