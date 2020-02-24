package com.ldtteam.structurize.pipeline.build;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.ldtteam.structurize.structure.IStructureBB;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Event data holder for build providers.
 * Holds prepared structure.
 */
@Deprecated
public class RawPlacer
{
    protected final List<BlockState> structureBlockPalette;
    /**
     * Structure blocks 3d array, indexes: y|z|x.
     */
    protected final short[][][] structureBlocks;
    protected final List<CompoundNBT> structureEntities;
    protected final Map<BlockPos, CompoundNBT> structureTileEntities;
    protected final IStructureBB structurePosition;
    protected final World structureWorld;

    protected RawPlacer(final RawPlacer placer)
    {
        this.structureBlockPalette = placer.structureBlockPalette;
        this.structureBlocks = placer.structureBlocks;
        this.structureEntities = placer.structureEntities;
        this.structureTileEntities = placer.structureTileEntities;
        this.structurePosition = placer.structurePosition;
        this.structureWorld = placer.structureWorld;
    }

    /*
     * public RawPlacer(final EventInfoHolder<?> event)
     * {
     * structureBlockPalette = event.getStructure().getBlockPalette();
     * structureBlocks = event.getStructure().getBlocks();
     * structureEntities = event.getStructure().getEntities();
     * structureTileEntities = event.getStructure().getTileEntities();
     * structurePosition = event.getPosition();
     * structureWorld = event.getWorld();
     * }
     */

    /**
     * Prepares TileEntity for world placing.
     *
     * @param teCompound TileEntity data
     * @param tePos      structure pos
     * @return constructed TileEntity and REAL world pos
     */
    public Tuple<TileEntity, BlockPos> transformDataToTileEntity(final CompoundNBT teCompound, final BlockPos tePos)
    {
        final BlockPos worldPos = structurePosition.getAnchor().add(tePos);
        final TileEntity te = TileEntity.create(teCompound);

        te.setWorldAndPos(structureWorld, worldPos);

        return new Tuple<TileEntity, BlockPos>(te, worldPos);
    }

    /**
     * Prepares Entity for world placing.
     * 
     * @param entityCompound entity data
     * @return constructed Entity and REAL world pos
     */
    public Tuple<Entity, Vec3d> transformDataToEntity(final CompoundNBT entityCompound)
    {
        final Optional<EntityType<?>> type = EntityType.readEntityType(entityCompound);
        if (!type.isPresent())
        {
            // Structurize.getLogger().warn("Can't find entity type.");
            return null;
        }
        final Entity entity = type.get().create(structureWorld);
        entity.deserializeNBT(entityCompound);
        final Vec3d newPos = entity.getPositionVector().add(new Vec3d(structurePosition.getAnchor()));
        entity.setPosition(newPos.getX(), newPos.getY(), newPos.getZ());
        return new Tuple<Entity, Vec3d>(entity, newPos);
    }

    public static BlockInfoPlacer getBlockStatePlacer(final BlockState blockState)
    {
        return null; // Structurize.getComponentRegistries().getBlockInfoPlacerRegistry().getValue(blockState.getBlock().getRegistryName());
    }

    public static EntityPlacer getEntityPlacer(final Entity entity)
    {
        return null; // Structurize.getComponentRegistries().getEntityPlacerRegistry().getValue(entity.getType().getRegistryName());
    }
}