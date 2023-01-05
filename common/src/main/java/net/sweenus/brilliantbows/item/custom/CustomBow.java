package net.sweenus.brilliantbows.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.abilities.BowMagicAbilities;
import net.sweenus.brilliantbows.registry.SoundRegistry;
import net.sweenus.brilliantbows.util.SoundHelper;

import java.util.List;

public class CustomBow extends BowItem {

    PositionedSoundInstance SOUND;
    SoundHelper cls = new SoundHelper();
    private static int generalTimer = 20;

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
                float arrowVelocity = getPullProgress(i);
                if (!((double)arrowVelocity < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        switch (selection) {
                            //noinspection RedundantSuppression
                            case 0 -> //noinspection DuplicateBranchesInSwitch
                                    BowMagicAbilities.spawnDefaultArrow(playerEntity, stack, world, arrowVelocity);
                            case 1 -> BowMagicAbilities.spawnConeArrows(playerEntity, stack, world,3, arrowVelocity, 18);
                            case 2 -> BowMagicAbilities.spawnMultipleArrows(playerEntity, stack, world,5, arrowVelocity, 5, true);
                            case 3 -> BowMagicAbilities.spawnRicochetArrow(playerEntity, stack, world, arrowVelocity);
                            case 4 -> BowMagicAbilities.spawnHeavensArrows(playerEntity, stack, world, arrowVelocity);
                            case 5 -> BowMagicAbilities.spawnConeArrows(playerEntity, stack, world,8, arrowVelocity, 32);
                            case 6 -> BowMagicAbilities.spawnConeArrows(playerEntity, stack, world,12, arrowVelocity, 64);
                            case 7 -> BowMagicAbilities.spawnConeArrows(playerEntity, stack, world,16, arrowVelocity, 360);
                            default -> BowMagicAbilities.spawnDefaultArrow(playerEntity, stack, world, arrowVelocity);
                        }
                    }

                    int choose_sound = (int) (Math.random() * 40);
                    SoundEvent soundEvent = switch (choose_sound / 10) {
                        //noinspection RedundantSuppression
                        case 0 -> //noinspection DuplicateBranchesInSwitch
                                SoundRegistry.BOW_SHOOT.get();
                        case 1 -> SoundRegistry.BOW_SHOOT_2.get();
                        case 2 -> SoundRegistry.BOW_SHOOT_3.get();
                        case 3 -> SoundRegistry.BOW_SHOOT_4.get();
                        default -> SoundRegistry.BOW_SHOOT.get();
                    };
                    if (soundEvent != null)
                        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), soundEvent, SoundCategory.PLAYERS, 0.5F, 1F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

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
            SoundEvent soundEvent = switch (choose_sound / 10) {
                //noinspection RedundantSuppression
                case 0 -> //noinspection DuplicateBranchesInSwitch
                        SoundRegistry.BOW_PULL_LONG.get();
                case 1 -> SoundRegistry.BOW_PULL_LONG_2.get();
                case 2 -> SoundRegistry.BOW_PULL_LONG_3.get();
                default -> SoundRegistry.BOW_PULL_LONG.get();
            };
            if (soundEvent != null)
                SOUND = PositionedSoundInstance.master(soundEvent, 0.6F / (world.getRandom().nextFloat() * 0.4F + 1.0F) + 1 * 0.5F, 0.5F);

            cls.SoundSet(world, SOUND);
            cls.playSound();


            //Bow magic cycle


            ItemStack itemStack = user.getEquippedStack(EquipmentSlot.MAINHAND);
            int selection = itemStack.getOrCreateNbt().getInt("bowmagic_selection");
            int maxselection = itemStack.getOrCreateNbt().getInt("bowmagic_maxselection");

            if (selection == 4) {
                BowMagicAbilities.applyHeavensArrowEffects(user, world);
            }
            if (selection == 99) {
                BowMagicAbilities.refreshRapidFireCounters(user, world, 5, 120, 10);
            }

            //Replaced with swinging check
            /*if (user.isSneaking()) {

                if (selection < maxselection)
                    itemStack.getOrCreateNbt().putInt("bowmagic_selection", selection + 1);
                if (selection == maxselection)
                    itemStack.getOrCreateNbt().putInt("bowmagic_selection", 1);
            }

             */


            //Replaced with random maxselection onCraft - may use leveling later
            //Bow leveling
            /*
            if (itemStack.getOrCreateNbt().getInt("bow_exp") < 100 && itemStack.getOrCreateNbt().getInt("bow_level") < 100) {
                itemStack.getOrCreateNbt().putInt("bow_exp", itemStack.getOrCreateNbt().getInt("bow_exp") + 10);
            } else if (itemStack.getOrCreateNbt().getInt("bow_exp") >= 100) {
                itemStack.getOrCreateNbt().putInt("bow_exp", 0);
                if (itemStack.getOrCreateNbt().getInt("bow_level") < 100) {
                    itemStack.getOrCreateNbt().putInt("bow_level", itemStack.getOrCreateNbt().getInt("bow_level") + 1);
                    itemStack.getOrCreateNbt().putInt("bowmagic_maxselection", maxselection + 1);
                }
            }
            */
        }


        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        int selection = stack.getOrCreateNbt().getInt("bowmagic_selection");
        int maxselection = stack.getOrCreateNbt().getInt("bowmagic_maxselection");
        if (selection == 4) {
            if (!world.isClient && (entity instanceof PlayerEntity player)) {
                BowMagicAbilities.heavensArrowSweepInventoryTickUpdate(player, world);
            }
        }
        if (selection == 99) {
            if (!world.isClient && (entity instanceof PlayerEntity player)) {
                BowMagicAbilities.rapidFireInventoryTickUpdate(player, world, stack, 120, 10);
            }
        }

        //Cycle bow magic

        if (generalTimer > 0)
                generalTimer --;

        if (!world.isClient && (entity instanceof PlayerEntity player)) {
            if (player.handSwinging && generalTimer < 1) {

                if (selection < maxselection)
                    stack.getOrCreateNbt().putInt("bowmagic_selection", selection + 1);
                if (selection == maxselection)
                    stack.getOrCreateNbt().putInt("bowmagic_selection", 1);

                generalTimer = 30;
            }
        }

        //Failsafe max magics assign
        if (maxselection == 0) {
            int choose = (int) (Math.random() * 8);
            stack.getOrCreateNbt().putInt("bowmagic_maxselection", choose);
            stack.getOrCreateNbt().putInt("bowmagic_selection", 1);
        }
        else if (maxselection == 8) {
            stack.getOrCreateNbt().putInt("bowmagic_selection", 1);
            stack.getOrCreateNbt().putInt("bowmagic_maxselection", 7);
        }

    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        int selection = itemStack.getOrCreateNbt().getInt("bowmagic_selection");
        int maxselection = itemStack.getOrCreateNbt().getInt("bowmagic_maxselection");

        //1.19

        tooltip.add(Text.literal(""));


        /*
        if(itemStack.getOrCreateNbt().getInt("bow_level") < 100) {

            tooltip.add(Text.translatable("item.brilliantbows.bow_level",
                    (itemStack.getOrCreateNbt().getInt("bow_level"))).formatted(Formatting.GOLD));

            tooltip.add(Text.translatable("item.brilliantbows.bow_exp",
                    (itemStack.getOrCreateNbt().getInt("bow_exp"))).formatted(Formatting.GREEN));

        } else if(itemStack.getOrCreateNbt().getInt("bow_level") == 100) {
            tooltip.add(Text.translatable("item.brilliantbows.bow_level",
                    (itemStack.getOrCreateNbt().getInt("bow_level"))).formatted(Formatting.RED));
        }
         */

        if (selection == 0)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.none",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 1) {
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.trishot",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("item.brilliantbows.tribow.tooltip1"));
            tooltip.add(Text.translatable("item.brilliantbows.tribow.tooltip2"));
        }
        if (selection == 2) {
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.rain",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip1"));
            tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip2"));
            tooltip.add(Text.translatable("item.brilliantbows.rainbow.tooltip3"));
        }
        if (selection == 3)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.ricochet",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 4)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.heaven",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 5)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.wave",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 6)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.tsunami",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
        if (selection == 7)
            tooltip.add(Text.translatable("item.brilliantbows.bow.magic.arcannihilator",
                    (itemStack.getOrCreateNbt().getInt("bowmagic_selection")),
                    (itemStack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.WHITE));
    }

    @Override
    public Text getName(ItemStack stack) {
        int selection = stack.getOrCreateNbt().getInt("bowmagic_selection");
        if (selection == 0)
            return Text.translatable("item.brilliantbows.powerless",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 1)
            return Text.translatable("item.brilliantbows.tribow",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 2)
            return Text.translatable("item.brilliantbows.rainbow",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 3)
            return Text.translatable("item.brilliantbows.ricochet",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 4)
            return Text.translatable("item.brilliantbows.heaven",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 5)
            return Text.translatable("item.brilliantbows.wave",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 6)
            return Text.translatable("item.brilliantbows.tsunami",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else if (selection == 7)
            return Text.translatable("item.brilliantbows.arcannihilator",
                    (stack.getOrCreateNbt().getInt("bowmagic_maxselection"))).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
        else return Text.translatable(this.getTranslationKey(stack)).formatted(Formatting.GOLD, Formatting.BOLD, Formatting.UNDERLINE);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {

        //replace with onCraft later
        //if(stack.getOrCreateNbt().getFloat("bowmagic_selection") == 0)
        /*
        stack.getOrCreateNbt().putInt("bowmagic_selection", 1);
        stack.getOrCreateNbt().putInt("bow_level", 1);
        stack.getOrCreateNbt().putInt("bow_exp", 10);
        stack.getOrCreateNbt().putInt("bowmagic_maxselection", 5);
        */

        int choose = (int) (Math.random() * 8);
        stack.getOrCreateNbt().putInt("bowmagic_maxselection", choose);
        stack.getOrCreateNbt().putInt("bowmagic_selection", 1);

        return false;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        if(world.isClient) return;

        int choose = (int) (Math.random() * 8);
        stack.getOrCreateNbt().putInt("bowmagic_maxselection", choose);
    }








    public CustomBow(Settings settings) {
        super(settings);
    }
}
