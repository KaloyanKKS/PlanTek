package io.github.llamarama.team.plantek;

import io.github.llamarama.team.plantek.init.PBlocks;
import io.github.llamarama.team.plantek.init.PItems;
import io.github.llamarama.team.plantek.init.PTags;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlanTek implements ModInitializer {

    public static final String MODID = "plantek";
    private static final Logger LOGGER = LogManager.getLogger("PlanTek");

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("And you thought plants couldn't get more interesting...");
        LOGGER.info("Oh wait... you didn't think that?!");
        PBlocks.init();
        PItems.init();
        PTags.init();
    }
}
