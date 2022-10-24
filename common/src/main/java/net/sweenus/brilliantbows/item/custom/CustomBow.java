package net.sweenus.brilliantbows.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.item.custom.projectiles.RainArrow;
import net.sweenus.brilliantbows.item.custom.projectiles.RainArrow2;
import net.sweenus.brilliantbows.item.custom.projectiles.RicochetArrow;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.HelperMethods;
import net.sweenus.brilliantbows.util.SoundHelper;

import java.util.List;

public class CustomBow extends BowItem {

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
                int selection = stack.getOrCreateNbt().getInt("bowmagic_selection");
                float f = getPullProgress(i);
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {

                        // 0 = Longbow | 1 = TriBow | 2 = RainBow | 3 = Ricochet Bow...

                        if (selection == 0 || selection == 2 || selection == 1 || selection == 3) { // If statement redundant atm, but may not be when more bow magic is added

                            ArrowItem arrowItem = (ArrowItem) (itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                            PersistentProjectileEntity persistentProjectileEntity2 = arrowItem.createArrow(world, itemStack, playerEntity);
                            PersistentProjectileEntity persistentProjectileEntity3 = arrowItem.createArrow(world, itemStack, playerEntity);

                            if (selection == 3) { // Set arrow entity to Ricochet Arrow
                                persistentProjectileEntity = new RicochetArrow(world, playerEntity);
                            }
                            else if (selection == 2) { // Set arrow entity to Ricochet Arrow
                                persistentProjectileEntity = new RainArrow2(world, playerEntity);
                            }
                            else {
                                persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                            }
                                persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                                persistentProjectileEntity2.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() + 9, 0.0F, f * 3.0F, 0.4F);
                                persistentProjectileEntity3.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw() - 9, 0.0F, f * 3.0F, 0.1F);

                            if (f == 1.0F) {
                                persistentProjectileEntity.setCritical(true);
                                persistentProjectileEntity2.setCritical(true);
                                persistentProjectileEntity3.setCritical(true);
                            }

                            int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                            if (j > 0) {
                                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) j * 0.5 + 0.5);
                                persistentProjectileEntity2.setDamage(persistentProjectileEntity2.getDamage() + (double)j * 0.5 + 0.5);
                                persistentProjectileEntity3.setDamage(persistentProjectileEntity3.getDamage() + (double)j * 0.5 + 0.5);
                            }

                            int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                            if (k > 0) {
                                persistentProjectileEntity.setPunch(k);
                                persistentProjectileEntity2.setPunch(k);
                                persistentProjectileEntity3.setPunch(k);

                            }

                            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                                persistentProjectileEntity.setOnFireFor(100);
                                persistentProjectileEntity2.setOnFireFor(100);
                                persistentProjectileEntity3.setOnFireFor(100);
                            }

                            stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                            if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                                persistentProjectileEntity2.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                                persistentProjectileEntity3.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                            }

                            world.spawnEntity(persistentProjectileEntity);
                            //If TriBow spawn two more arrow entities with yaw offsets (I'm sure there's a smarter way to do this without 3x the code)
                            if (selection == 1) {
                                world.spawnEntity(persistentProjectileEntity2);
                                world.spawnEntity(persistentProjectileEntity3);
                            }

                        }
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


        //Bow magic cycle


                ItemStack itemStack = user.getEquippedStack(EquipmentSlot.MAINHAND);
                int selection = itemStack.getOrCreateNbt().getInt("bowmagic_selection");
                int maxselection = itemStack.getOrCreateNbt().getInt("bowmagic_maxselection");

                if (user.isSneaking()) {

                    if (selection < maxselection)
                        itemStack.getOrCreateNbt().putInt("bowmagic_selection", selection + 1);
                    if (selection == maxselection)
                        itemStack.getOrCreateNbt().putInt("bowmagic_selection", 0);
                }


                //Bow leveling
                if (itemStack.getOrCreateNbt().getInt("bow_exp") < 100 && itemStack.getOrCreateNbt().getInt("bow_level") < 5) {
                    itemStack.getOrCreateNbt().putInt("bow_exp", itemStack.getOrCreateNbt().getInt("bow_exp") + 10);
                } else if (itemStack.getOrCreateNbt().getInt("bow_exp") >= 100) {
                    itemStack.getOrCreateNbt().putInt("bow_exp", 0);
                    if (itemStack.getOrCreateNbt().getInt("bow_level") < 5) {
                        itemStack.getOrCreateNbt().putInt("bow_level", itemStack.getOrCreateNbt().getInt("bow_level") + 1);
                        itemStack.getOrCreateNbt().putInt("bowmagic_maxselection", maxselection + 1);
                    }
                }
            }


        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        int selection = itemStack.getOrCreateNbt().getInt("bowmagic_selection");
        int maxselection = itemStack.getOrCreateNbt().getInt("bowmagic_maxselection");

        //1.19

        tooltip.add(Text.literal(""));


        if(itemStack.getOrCreateNbt().getInt("bow_level") < 5) {

            tooltip.add(Text.translatable("item.brilliantbows.bow_level",
                    (itemStack.getOrCreateNbt().getInt("bow_level"))).formatted(Formatting.GOLD));

            tooltip.add(Text.translatable("item.brilliantbows.bow_exp",
                    (itemStack.getOrCreateNbt().getInt("bow_exp"))).formatted(Formatting.GREEN));

        } else if(itemStack.getOrCreateNbt().getInt("bow_level") == 5) {
            tooltip.add(Text.translatable("item.brilliantbows.bow_level",
                    (itemStack.getOrCreateNbt().getInt("bow_level"))).formatted(Formatting.RED));
        }

        //tooltip.add(Text.translatable("item.brilliantbows.bow.selection",
                //(itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                //(itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));


        if (selection == 0)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.none",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 1)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.trishot",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 2)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.rain",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 3)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.ricochet",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));




}

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {

        //replace with onCraft later
        //if(stack.getOrCreateNbt().getFloat("bowmagic_selection") == 0)
            stack.getOrCreateNbt().putInt("bowmagic_selection", 1);
        stack.getOrCreateNbt().putInt("bow_level", 1);
        stack.getOrCreateNbt().putInt("bow_exp", 10);
        stack.getOrCreateNbt().putInt("bowmagic_maxselection", 3);

        return false;
    }








    public CustomBow(Settings settings) {
        super(settings);
    }
}
