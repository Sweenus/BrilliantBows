package net.sweenus.brilliantbows.item.custom.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Iterator;

public class ScatterArrow extends ArrowEntity {

    @Override
    protected void onHit(LivingEntity target) {

    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        ServerWorld world = (ServerWorld) this.world;
        if (!world.isClient) {

            this.setVelocity((this.getX() - blockHitResult.getBlockPos().getX()) / 2.5, ((this.getY() - blockHitResult.getBlockPos().getY()) / 2.5), (this.getZ() - blockHitResult.getBlockPos().getZ()) / 2.5);
            this.setYaw(this.age);
            this.velocityModified = true;
            this.hasNoGravity();
        }

    }

    public ScatterArrow(World world, LivingEntity shooter) {
        super(world, shooter);
    }

    @Override
    public void tick() {

    }
}