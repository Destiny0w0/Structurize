package com.ldtteam.structurize.blocks;

import com.ldtteam.structurize.blocks.cactus.*;
import com.ldtteam.structurize.blocks.decorative.*;
import com.ldtteam.structurize.blocks.schematic.BlockSolidSubstitution;
import com.ldtteam.structurize.blocks.schematic.BlockSubstitution;
import com.ldtteam.structurize.blocks.types.TimberFrameType;
import com.ldtteam.structurize.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create the modBlocks.
 * References to the blocks can be made here
 * <p>
 * We disabled the following finals since we are neither able to mark the items as final, nor do we want to provide public accessors.
 */
@SuppressWarnings({"squid:ClassVariableVisibilityCheck", "squid:S2444", "squid:S1444", "squid:S1820",})

public final class ModBlocks
{
    /*
     * Creating objects for all blocks in the mod.
     * References can be made to here.
     */

    private static final List<BlockTimberFrame>         timberFrames = new ArrayList<>();

    public static BlockSubstitution      blockSubstitution;
    public static BlockSolidSubstitution blockSolidSubstitution;

    /**
     * Utility blocks.
     */
    public static BlockPaperwall   blockPaperWall;
    public static BlockShingle     blockShingleOak;
    public static BlockShingle     blockShingleBirch;
    public static BlockShingle     blockShingleJungle;
    public static BlockShingle     blockShingleSpruce;
    public static BlockShingle     blockShingleDarkOak;
    public static BlockShingle     blockShingleAcacia;
    public static BlockShingleSlab blockShingleSlab;

    public static BlockCactusPlank      blockCactusPlank;
    public static BlockCactusDoor       blockCactusDoor;
    public static BlockCactusTrapdoor   blockCactusTrapdoor;
    public static BlockCactusStair      blockCactusStair;
    public static BlockMinecoloniesSlab blockCactusSlab;
    public static BlockCactusFence      blockCactusFence;
    public static BlockCactusFenceGate  blockCactusFenceGate;

    public static MultiBlock multiBlock;

    public static List<BlockTimberFrame> getTimberFrames()
    {
        return new ArrayList<>(timberFrames);
    }

    /**
     * Private constructor to hide the implicit public one.
     */
    private ModBlocks()
    {
        /*
         * Intentionally left empty.
         */
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        final IForgeRegistry<Block> registry = event.getRegistry();
        blockCactusPlank = new BlockCactusPlank().registerBlock(registry);
        blockCactusDoor = new BlockCactusDoor().registerBlock(registry);
        blockCactusTrapdoor = new BlockCactusTrapdoor().registerBlock(registry);
        blockCactusSlab = new BlockMinecoloniesSlab(Block.Properties.from(blockCactusPlank),"blockcactusslab").registerBlock(registry);

        blockCactusStair = new BlockCactusStair(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK)).registerBlock(registry);
        blockCactusFence = new BlockCactusFence().registerBlock(registry);
        blockCactusFenceGate = new BlockCactusFenceGate(BlockPlanks.EnumType.OAK).registerBlock(registry);

        blockSolidSubstitution = new BlockSolidSubstitution().registerBlock(registry);
        blockSubstitution = new BlockSubstitution().registerBlock(registry);
        blockPaperWall = new BlockPaperwall().registerBlock(registry);

        blockShingleOak = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.OAK.getName()).registerBlock(registry);
        blockShingleJungle = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.JUNGLE.getName()).registerBlock(registry);
        blockShingleBirch = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.BIRCH.getName()).registerBlock(registry);
        blockShingleSpruce = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.SPRUCE.getName()).registerBlock(registry);
        blockShingleDarkOak = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.DARK_OAK.getName()).registerBlock(registry);
        blockShingleAcacia = new BlockShingle(new BlockPlanks().getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA),
          BlockShingle.BLOCK_PREFIX + "_" + BlockPlanks.EnumType.ACACIA.getName()).registerBlock(registry);
        blockShingleSlab = new BlockShingleSlab().registerBlock(registry);

        for (final BlockPlank.EnumType type : BlockPlanks.EnumType.values())
        {
            for (final TimberFrameType frameType : TimberFrameType.values())
            {
                timberFrames.add(new BlockTimberFrame(BlockTimberFrame.BLOCK_NAME + "_" + type.getName() + "_" + frameType).registerBlock(registry));
            }
        }

        multiBlock = new MultiBlock().registerBlock(registry);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().group(ModCreativeTabs.STRUCTURIZE);
        blockSolidSubstitution.registerItemBlock(registry, properties);
        blockSubstitution.registerItemBlock(registry, properties);
        blockPaperWall.registerItemBlock(registry, properties);
        blockShingleOak.registerItemBlock(registry, properties);
        blockShingleBirch.registerItemBlock(registry, properties);
        blockShingleJungle.registerItemBlock(registry, properties);
        blockShingleSpruce.registerItemBlock(registry, properties);
        blockShingleDarkOak.registerItemBlock(registry, properties);
        blockShingleAcacia.registerItemBlock(registry, properties);
        blockShingleSlab.registerItemBlock(registry, properties);

        blockCactusPlank.registerItemBlock(registry, properties);
        blockCactusTrapdoor.registerItemBlock(registry, properties);
        blockCactusStair.registerItemBlock(registry, properties);
        blockCactusSlab.registerItemBlock(registry, properties);
        blockCactusFence.registerItemBlock(registry, properties);
        blockCactusFenceGate.registerItemBlock(registry, properties);

        for (final BlockTimberFrame frame: timberFrames)
        {
            frame.registerItemBlock(registry, properties);
        }

        multiBlock.registerItemBlock(registry, properties);
    }
}