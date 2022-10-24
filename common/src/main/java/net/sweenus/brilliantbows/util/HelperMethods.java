package net.sweenus.brilliantbows.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.sweenus.brilliantbows.registry.ItemsRegistry;

public class HelperMethods {

    /*
     * Taken heavily from ZsoltMolnarrr's CombatSpells
     * https://github.com/ZsoltMolnarrr/CombatSpells/blob/main/common/src/main/java/net/combatspells/utils/TargetHelper.java#L72
     */
    public static Entity getTargetedEntity(Entity user, int range) {
        Vec3d rayCastOrigin = user.getEyePos();
        Vec3d userView = user.getRotationVec(1.0F).normalize().multiply(range);
        Vec3d rayCastEnd = rayCastOrigin.add(userView);
        Box searchBox = user.getBoundingBox().expand(range, range, range);
        EntityHitResult hitResult = ProjectileUtil.raycast(user, rayCastOrigin, rayCastEnd, searchBox,
                (target) -> !target.isSpectator() && target.canHit() && target instanceof LivingEntity, range * range);
        if (hitResult != null) {
            return hitResult.getEntity();
        }
        return null;
    }

    public static boolean hasLongbow(LivingEntity livingEntity) {
        if (livingEntity.getActiveItem().isOf(ItemsRegistry.LONGBOW.get())) {
            ItemStack getEquippedMainHandStack = livingEntity.getEquippedStack(EquipmentSlot.MAINHAND);
            ItemStack getEquippedOffHandStack = livingEntity.getEquippedStack(EquipmentSlot.OFFHAND);
            return getEquippedMainHandStack.getItem() == ItemsRegistry.LONGBOW.get().asItem()
                    || getEquippedOffHandStack.getItem() == ItemsRegistry.LONGBOW.get().asItem();
        }
        return false;
    }
}