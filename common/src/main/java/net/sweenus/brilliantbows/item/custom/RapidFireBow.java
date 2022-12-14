package net.sweenus.brilliantbows.item.custom;

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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.abilities.BowMagicAbilities;
import net.sweenus.brilliantbows.item.custom.projectiles.ExplosiveArrow;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.HelperMethods;

public class RapidFireBow extends BowItem {

    int shootPeriod;
    int shootCount;
    int shootInterval;




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
                        ArrowEntity arrowEntity = new ExplosiveArrow(world, playerEntity);
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
                        shootPeriod = 140;
                        shootCount = 1;
                        shootInterval = 120 - (shootCount * 20);

                    }

                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
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
        if (!world.isClient) {
            shootPeriod = 140;
            shootCount = 1;
            shootInterval = 120 - (shootCount * 20);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && (entity instanceof PlayerEntity player)) {

            //Glow targets when bow is being pulled (with tiny delay)
            if (((PlayerEntity) entity).isUsingItem()) {
                if (player.getEquippedStack(EquipmentSlot.MAINHAND) == stack || player.getEquippedStack(EquipmentSlot.OFFHAND) == stack) {
                    shootPeriod --;
                    if (shootPeriod > 0 && shootPeriod == shootInterval) {

                        shootCount ++;
                        shootInterval = 120 - (shootCount * 20);
                        BowMagicAbilities.spawnDefaultArrow((LivingEntity) entity, stack, world, 1.2f);
                        }


                    }
                }
            }
        }








    public RapidFireBow(Settings settings) {
        super(settings);
    }
}
