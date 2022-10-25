package net.sweenus.brilliantbows.abilities;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.item.custom.projectiles.RainArrow2;
import net.sweenus.brilliantbows.item.custom.projectiles.RicochetArrow;
import net.sweenus.brilliantbows.item.custom.projectiles.SeekerArrow;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.HelperMethods;
import net.sweenus.brilliantbows.util.SoundHelper;

import java.util.List;

import static net.sweenus.brilliantbows.abilities.BowMagicAbilityID.*;

public class BowMagicAbilities {
    static PositionedSoundInstance SOUND;
    static SoundHelper cls = new SoundHelper();
    public static final List<BowMagicAbilityID> BOW_MAGIC_ABILITY_ID_LIST =
            List.of(NORMAL_ARROW, HEAVENS_ARROW, RAIN_ARROW, RICOCHET_ARROW, TRISHOT_ARROW);

    public static int applyBowMagicAbility(LivingEntity livingEntity) {
        ItemStack customBowItemStack = livingEntity.getActiveItem();
        return customBowItemStack.getOrCreateNbt().getInt("bowmagic_selection");
    }

    public static void spawnHeavensArrows(LivingEntity shooter, ItemStack bow, World world, float arrowVelocity) {
        if (HelperMethods.hasLongbow(shooter) /*&& BOW_MAGIC_ABILITY_ID_LIST.get(applyBowMagicAbility(shooter)) == HEAVENS_ARROW*/) {
            int sradius = 50;
            int vradius = 50;
            double x = shooter.getX();
            double y = shooter.getY();
            double z = shooter.getZ();
            ServerWorld sworld = (ServerWorld) shooter.world;
            Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
            for (Entity entities : sworld.getOtherEntities(shooter, box, EntityPredicates.VALID_LIVING_ENTITY)) {
                if (entities != null) {
                    if (entities instanceof LivingEntity le) {
                        if (le.hasStatusEffect(StatusEffects.GLOWING)) {
                            SeekerArrow arrowEntity = new SeekerArrow(world, shooter);
                            HelperMethods.enchantmentHelper(bow, arrowEntity, arrowVelocity);
                            arrowEntity.initFromStack(bow);
                            arrowEntity.setVelocity(0, 10, 0);

                            world.spawnEntity(arrowEntity);
                            arrowEntity.setTarget(le); // Sets the target for the arrow
                            arrowEntity.damageMultiplier(arrowVelocity); // Should the arrow seek its target (Seeker arrows will duplicate themselves with this property later in the sequence)
                            le.removeStatusEffect(StatusEffects.GLOWING);
                        }
                    }
                }
            }
            sworld.playSoundFromEntity(null, shooter, SoundRegistry.HOLY_SUMMON.get() , SoundCategory.PLAYERS, 0.4f, 3f);
            cls.stopSound();
        }
    }

    public static void applyHeavensArrowEffects(LivingEntity shooter, World world) {
        if (HelperMethods.hasLongbow(shooter) /*&& BOW_MAGIC_ABILITY_ID_LIST.get(applyBowMagicAbility(shooter)) == HEAVENS_ARROW*/) {
            LivingEntity target = (LivingEntity) HelperMethods.getTargetedEntity(shooter, 256);
            if (target != null && !target.hasStatusEffect(StatusEffects.GLOWING))
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), shooter);

            if (!world.isClient) {
                SOUND = PositionedSoundInstance.master(SoundRegistry.HOLY_CHARGE.get(), 1F);
                cls.SoundSet(world, SOUND);
                cls.playSound();
            }
        }
    }

    public static void heavensArrowSweepInventoryTickUpdate(PlayerEntity shooter, World world) {
        if (HelperMethods.hasLongbow(shooter) /*&& BOW_MAGIC_ABILITY_ID_LIST.get(applyBowMagicAbility(shooter)) == HEAVENS_ARROW*/) {
            if (shooter.isUsingItem()) {
                int updateFrequency = 2;
                if (shooter.age % updateFrequency == 0) {
                    LivingEntity target = (LivingEntity) HelperMethods.getTargetedEntity(shooter, 256);
                    if (target != null && !target.hasStatusEffect(StatusEffects.GLOWING)) {
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), shooter);
                        world.playSoundFromEntity(null, shooter, SoundRegistry.TARGET_ACQUIRED.get(), SoundCategory.PLAYERS, 0.2f, 3f);
                    }
                }
            }
        }
    }

    public static void spawnRicochetArrow(LivingEntity owner, ItemStack bow, World world, float arrowVelocity) {
        RicochetArrow ricochetArrow = new RicochetArrow(world, owner);
        HelperMethods.enchantmentHelper(bow, ricochetArrow, arrowVelocity);
        ricochetArrow.setVelocity(owner, owner.getPitch(), owner.getYaw(), 0.0f, arrowVelocity * 1.2f, 5.0f);
        world.spawnEntity(ricochetArrow);
    }

    public static void spawnMultipleArrows(LivingEntity owner, ItemStack bow, World world, int numberOfArrows, float arrowVelocity, boolean arrowRain) {
        if (HelperMethods.hasLongbow(owner) /*&& BOW_MAGIC_ABILITY_ID_LIST.get(applyBowMagicAbility(owner)) == RAIN_ARROW*/ && arrowRain) {
            RainArrow2 rainArrow = new RainArrow2(world, owner);
            HelperMethods.enchantmentHelper(bow, rainArrow, arrowVelocity);
            rainArrow.setVelocity(owner, owner.getPitch(), owner.getYaw(), 0.0f, arrowVelocity * 1.2f, 5.0f);
            world.spawnEntity(rainArrow);
        } else if (HelperMethods.hasLongbow(owner) /*&& BOW_MAGIC_ABILITY_ID_LIST.get(applyBowMagicAbility(owner)) == TRISHOT_ARROW*/){
            ArrowEntity defaultArrow = new ArrowEntity(world, owner);
            defaultArrow.setVelocity(owner, owner.getPitch(), owner.getYaw(), 0.0f, arrowVelocity * 1.2f, 5.0f);
            world.spawnEntity(defaultArrow);
        }
        for (int i = 1; i < Math.min(numberOfArrows, 6); i++) {
            ArrowEntity defaultArrow = new ArrowEntity(world, owner);
            int randomizeYaw = (int) (Math.random() * 50) - 25;
            defaultArrow.setVelocity(owner, owner.getPitch(), owner.getYaw() + randomizeYaw, 0.0f, arrowVelocity * 1.2f, 5.0f);
            world.spawnEntity(defaultArrow);
        }
    }

    public static void spawnDefaultArrow(LivingEntity owner, ItemStack bow, World world, float arrowVelocity) {
        PersistentProjectileEntity defaultArrow = new ArrowEntity(world, owner);
        HelperMethods.enchantmentHelper(bow, defaultArrow, arrowVelocity);
        defaultArrow.setVelocity(owner, owner.getPitch(), owner.getYaw(), 0.0f, arrowVelocity * 3.0f, 1.0f);
        world.spawnEntity(defaultArrow);
    }

}
