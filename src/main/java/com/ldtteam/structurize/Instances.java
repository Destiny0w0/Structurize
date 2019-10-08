package com.ldtteam.structurize;

import com.ldtteam.structurize.client.render.EventRenderer;
import com.ldtteam.structurize.client.render.OptifineCompat;
import com.ldtteam.structurize.config.Configuration;
import com.ldtteam.structurize.network.NetworkChannel;
import com.ldtteam.structurize.pipeline.ComponentRegistries;
import com.ldtteam.structurize.util.constants.GeneralConstants;
import com.ldtteam.structurize.world.schematic.SchematicWorldType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;

/**
 * Class for storing mod-wide class instances
 * Can be called after {@link net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent}
 */
public class Instances
{
    private static final NetworkChannel GENERAL_NETWORK_CHANNEL;
    private static final Logger MOD_LOGGER;
    private static final Configuration MOD_CONFIG;
    private static final OptifineCompat OPTIFINE_COMPAT;
    private static final EventRenderer EVENT_RENDERER;
    private static final ComponentRegistries COMPONENT_REGISTRIES;
    private static final SchematicWorldType SCHEMATIC_WORLD_TYPE;

    static
    {
        GENERAL_NETWORK_CHANNEL = new NetworkChannel("net-channel");
        MOD_LOGGER = LogManager.getLogger(GeneralConstants.MOD_ID);
        MOD_CONFIG = new Configuration(ModLoadingContext.get().getActiveContainer());
        OPTIFINE_COMPAT = DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> new OptifineCompat());
        EVENT_RENDERER = DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> new EventRenderer());
        COMPONENT_REGISTRIES = new ComponentRegistries();
        SCHEMATIC_WORLD_TYPE = new SchematicWorldType();
    }

    /**
     * Private constructor to hide implicit public one.
     */
    private Instances()
    {
        /**
         * Intentionally left empty
         */
    }

    /**
     * @return mod network channel
     */
    public static NetworkChannel getNetwork()
    {
        return GENERAL_NETWORK_CHANNEL;
    }

    /**
     * @return mod logger
     */
    public static Logger getLogger()
    {
        return MOD_LOGGER;
    }

    /**
     * @return mod configurations
     */
    public static Configuration getConfig()
    {
        return MOD_CONFIG;
    }

    /**
     * @return optifine compatibility class for rendering
     */
    public static OptifineCompat getOptifineCompat()
    {
        return OPTIFINE_COMPAT;
    }

    /**
     * @return renderer for previews
     */
    public static EventRenderer getEventRenderer()
    {
        return EVENT_RENDERER;
    }

    /**
     * @return structure component scanner/placer registries
     */
    public static ComponentRegistries getComponentRegistries()
    {
        return COMPONENT_REGISTRIES;
    }

    /**
     * @return registered world type instance
     */
    public static SchematicWorldType getSchematicWorldType()
    {
        return SCHEMATIC_WORLD_TYPE;
    }
}