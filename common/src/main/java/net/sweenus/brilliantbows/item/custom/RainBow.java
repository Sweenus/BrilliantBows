package net.sweenus.brilliantbows.item.custom;

import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
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
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.RandomSeed;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.item.custom.projectiles.ExplosiveArrow;
import net.sweenus.brilliantbows.item.custom.projectiles.RainArrow;
import net.sweenus.brilliantbows.item.custom.projectiles.RainArrow2;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.SoundHelper;

import java.util.List;

public class RainBow extends BowItem {

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
                        ArrowEntity arrowEntity = new RainArrow2(world, playerEntity);
                        ArrowEntity arrowEntity2 = new ArrowEntity(world, playerEntity);
                        ArrowEntity arrowEntity3 = new ArrowEntity(world, playerEntity);
                        ArrowEntity arrowEntity4 = new ArrowEntity(world, playerEntity);
                        ArrowEntity arrowEntity5 = new ArrowEntity(world, playerEntity);
                        ArrowEntity arrowEntity6 = new ArrowEntity(world, playerEntity);
                        arrowEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 1.2F, 1.0F);
                        if (f == 1.0F) {
                            arrowEntity.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            arrowEntity.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                            arrowEntity2.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                            arrowEntity3.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                            arrowEntity4.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                            arrowEntity5.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                            arrowEntity6.setDamage(arrowEntity.getDamage() + (double)j * 0.5 + 0.5);
                        }

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            arrowEntity.setPunch(k);
                            arrowEntity2.setPunch(k);
                            arrowEntity3.setPunch(k);
                            arrowEntity4.setPunch(k);
                            arrowEntity5.setPunch(k);
                            arrowEntity6.setPunch(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                            arrowEntity.setOnFireFor(100);
                            arrowEntity2.setOnFireFor(100);
                            arrowEntity3.setOnFireFor(100);
                            arrowEntity4.setOnFireFor(100);
                            arrowEntity5.setOnFireFor(100);
                            arrowEntity6.setOnFireFor(100);
                        }

                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(arrowEntity);

                        int randomizeYaw = (int) (Math.random() * 25) + 1;
                        arrowEntity2.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() + randomizeYaw, 0.0F, f * 1.2F, 5F);
                        arrowEntity3.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() + randomizeYaw, 0.0F, f * 1.2F, 5F);
                        arrowEntity4.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() + randomizeYaw, 0.0F, f * 1.2F, 5F);
                        arrowEntity5.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() - randomizeYaw, 0.0F, f * 1.2F, 5F);
                        arrowEntity6.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() - randomizeYaw, 0.0F, f * 1.2F, 5F);
                        world.spawnEntity(arrowEntity2);
                        world.spawnEntity(arrowEntity3);
                        world.spawnEntity(arrowEntity4);
                        world.spawnEntity(arrowEntity5);
                        world.spawnEntity(arrowEntity6);

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
                        itemStack.decrement(6);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    playerEntity.getItemCooldownManager().set(this, 120);
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

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip1").formatted(Formatting.GOLD));
        tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip2").formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip3").formatted(Formatting.BLUE));
    }








    public RainBow(Settings settings) {
        super(settings);
    }
}
