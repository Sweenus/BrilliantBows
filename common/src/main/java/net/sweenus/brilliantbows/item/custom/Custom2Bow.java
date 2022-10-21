package net.sweenus.brilliantbows.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class Custom2Bow extends BowItem {


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
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

                                        ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                                        //persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                                        persistentProjectileEntity.setVelocity((le.getX() - user.getX()) / 5, (((le.getY() - user.getY()) - 0.5)  / 5), (le.getZ() - user.getZ())  / 5);
                                        if (f == 1.0F) {
                                            persistentProjectileEntity.setCritical(true);
                                        }

                                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                                        if (j > 0) {
                                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) j * 0.5 + 0.5);
                                        }

                                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                                        if (k > 0) {
                                            persistentProjectileEntity.setPunch(k);
                                        }

                                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                                            persistentProjectileEntity.setOnFireFor(100);
                                        }

                                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                                        }

                                        world.spawnEntity(persistentProjectileEntity);

                                    }

                                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.8F, 1.3F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
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
                    }
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        //How do we highlight target
        // If you're going to keep the radii the same in multiple bows, consider making a helper method to reduce
        // duplicate code
        int sradius = 50;
        int vradius = 50;
        double x = user.getX();
        double y = user.getY();
        double z = user.getZ();
        Box box = new Box(x + sradius, y + vradius, z + sradius, x - sradius, y - vradius, z - sradius);
        for (Entity entities : world.getOtherEntities(user, box, EntityPredicates.VALID_LIVING_ENTITY)) {
            if (entities instanceof LivingEntity livingEntity) {
                if (!livingEntity.hasStatusEffect(StatusEffects.GLOWING))
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0), user);
            }
        }
        return super.use(world, user, hand);
    }








    public Custom2Bow(Settings settings) {
        super(settings);
    }
}
