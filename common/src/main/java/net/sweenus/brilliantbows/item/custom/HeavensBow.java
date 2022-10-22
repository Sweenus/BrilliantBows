package net.sweenus.brilliantbows.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.MovingMinecartSoundInstance;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.item.custom.projectiles.SeekerArrow;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.HelperMethods;
import net.sweenus.brilliantbows.util.SoundHelper;

public class HeavensBow extends BowItem {

    PositionedSoundInstance SOUND;
    SoundHelper cls = new SoundHelper();

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 172000;
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                // Custom pullspeed should match that of the PredicateProvider
                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = (float)i / 40.0F;
                f = (f * f + f * 2.0F) / 3.0F;
                if (f > 1.0F) {
                    f = 1.0F;
                }

                if (!((double) f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        // If you're going to keep the radii the same in multiple bows, consider making a helper method to reduce
                        // duplicate code
                        int sradius = 50;
                        int vradius = 50;
                        double x = user.getX();
                        double y = user.getY();
                        double z = user.getZ();
                        ServerWorld sworld = (ServerWorld) user.world;
                        Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
                        for (Entity entities : sworld.getOtherEntities(user, box, EntityPredicates.VALID_LIVING_ENTITY)) {

                            if (entities != null) {
                                if (entities instanceof LivingEntity le) {
                                    if (le.hasStatusEffect(StatusEffects.GLOWING)) {

                                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                                        ArrowEntity arrowEntity = new SeekerArrow(world, playerEntity);
                                        arrowEntity.initFromStack(stack);
                                        //arrowEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3F, 1.0F);
                                        arrowEntity.setVelocity(0, 10, 0);
                                        if (f == 1.0F) {
                                            arrowEntity.setCritical(true);
                                        }

                                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                                        if (j > 0) {
                                            arrowEntity.setDamage(arrowEntity.getDamage() + (double) j * 0.5 + 0.5);
                                        }

                                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                                        if (k > 0) {
                                            arrowEntity.setPunch(k);
                                        }

                                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                                            arrowEntity.setOnFireFor(100);
                                        }

                                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                                            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                                        }

                                        world.spawnEntity(arrowEntity);
                                        ((SeekerArrow) arrowEntity).setTarget(le); // Sets the target for the arrow
                                        ((SeekerArrow) arrowEntity).damageMultiplier(f); // Should the arrow seek its target (Seeker arrows will duplicate themselves with this property later in the sequence)
                                        le.removeStatusEffect(StatusEffects.GLOWING);

                                    }

                                    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                                        itemStack.decrement(1);
                                        if (itemStack.isEmpty()) {
                                            playerEntity.getInventory().removeOne(itemStack);
                                        }
                                    }

                                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                                }
                            }
                        }
                        sworld.playSoundFromEntity (null, playerEntity, SoundRegistry.HOLY_SUMMON.get() , SoundCategory.PLAYERS, 0.4f, 3f);
                        cls.stopSound();
                    }
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        LivingEntity target = (LivingEntity) HelperMethods.getTargetedEntity(user, 256);
        if (target != null && !target.hasStatusEffect(StatusEffects.GLOWING))
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), user);

        if (!world.isClient) {
            SOUND = PositionedSoundInstance.master(SoundRegistry.HOLY_CHARGE.get(), 1F);
                cls.SoundSet(world, SOUND);
                cls.playSound();
        }

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && (entity instanceof PlayerEntity player)) {

            //Glow targets when bow is being pulled (with tiny delay)
            if (((PlayerEntity) entity).isUsingItem()) {
                if (player.getEquippedStack(EquipmentSlot.MAINHAND) == stack || player.getEquippedStack(EquipmentSlot.OFFHAND) == stack) {
                    int lfrequency = (int) 2;
                    if (player.age % lfrequency == 0) {
                        LivingEntity target = (LivingEntity) HelperMethods.getTargetedEntity(entity, 256);
                        if (target != null && !target.hasStatusEffect(StatusEffects.GLOWING)) {
                            target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), entity);
                            world.playSoundFromEntity(null, entity, SoundRegistry.TARGET_ACQUIRED.get(), SoundCategory.PLAYERS, 0.2f, 3f);
                        }


                    }
                }
            }
        }
    }








    public HeavensBow(Settings settings) {
        super(settings);
    }
}
