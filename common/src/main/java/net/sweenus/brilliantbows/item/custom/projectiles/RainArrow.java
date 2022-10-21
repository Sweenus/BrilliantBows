package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.sweenus.brilliantbows.item.custom.RainBow;

public class RainArrow extends ArrowEntity {

    @Override
    public void tick() {

        if (!this.world.isClient()) {


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
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

                world.spawnEntity(persistentProjectileEntity);
                if (this.age % 200 == 0)
                    this.discard();
            }
        }
    }


    public RainArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

}
