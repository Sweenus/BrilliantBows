package net.sweenus.brilliantbows.item.custom;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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
import net.sweenus.brilliantbows.item.custom.projectiles.RicochetArrow;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.SoundHelper;

public class RicochetBow extends BowItem {

    PositionedSoundInstance SOUND;
    SoundHelper cls = new SoundHelper();


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
                        ArrowEntity arrowEntity = new RicochetArrow(world, playerEntity);
                        arrowEntity.initFromStack(stack);
                        arrowEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            arrowEntity.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            arrowEntity.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
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

                    }

                    int choose_sound = (int) (Math.random() * 40);
                    if (choose_sound <= 10)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT.get(), SoundCategory.PLAYERS, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 20 && choose_sound > 10)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_2.get(), SoundCategory.PLAYERS, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 30 && choose_sound > 20)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_3.get(), SoundCategory.PLAYERS, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (choose_sound <= 40 && choose_sound > 30)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundRegistry.BOW_SHOOT_4.get(), SoundCategory.PLAYERS, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

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
        if (!world.isClient)
            cls.stopSound();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {

            int choose_sound = (int) (Math.random() * 30);
            if (choose_sound <= 10)
                SOUND = PositionedSoundInstance.master(SoundRegistry.BOW_PULL_LONG.get(), 0.6F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F, 0.5F);
            if (choose_sound <= 20 && choose_sound > 10)
                SOUND = PositionedSoundInstance.master(SoundRegistry.BOW_PULL_LONG_2.get(), 0.6F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F, 0.5F);
            if (choose_sound <= 30 && choose_sound > 20)
                SOUND = PositionedSoundInstance.master(SoundRegistry.BOW_PULL_LONG_3.get(), 0.6F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F, 0.5F);

            cls.SoundSet(world, SOUND);
            cls.playSound();
        }


        return super.use(world, user, hand);
    }








    public RicochetBow(Settings settings) {
        super(settings);
    }
}
