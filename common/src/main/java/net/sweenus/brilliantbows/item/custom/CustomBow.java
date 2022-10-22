package net.sweenus.brilliantbows.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.HelperMethods;

public class CustomBow extends BowItem {


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
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5);
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

                    int choose_sound = (int) (Math.random() * 40);
                    if (choose_sound <= 10)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT.get(), SoundCategory.PLAYERS, 0.5F, 2.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 20 && choose_sound > 10)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_2.get(), SoundCategory.PLAYERS, 0.5F, 1.5F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 30 && choose_sound > 20)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_3.get(), SoundCategory.PLAYERS, 0.5F, 1.5F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 40 && choose_sound > 30)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_4.get(), SoundCategory.PLAYERS, 0.5F, 1.5F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        int choose_sound = (int) (Math.random() * 30);
        if (choose_sound <= 10)
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundRegistry.BOW_PULL_LONG.get(), SoundCategory.PLAYERS, 0.5F, 0.7F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F);
        if (choose_sound <= 20 && choose_sound > 10)
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundRegistry.BOW_PULL_LONG_2.get(), SoundCategory.PLAYERS, 0.5F, 0.7F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F);
        if (choose_sound <= 30 && choose_sound > 20)
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundRegistry.BOW_PULL_LONG_3.get(), SoundCategory.PLAYERS, 0.5F, 0.7F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F);


        return super.use(world, user, hand);
    }








    public CustomBow(Settings settings) {
        super(settings);
    }
}
