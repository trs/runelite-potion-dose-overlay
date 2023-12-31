package com.potion;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("potionDose")
public interface PotionDoseConfig extends Config
{
    @ConfigItem(
            keyName = "overlayPosition",
            name = "Overlay Position",
            description = "Where to show the dose overlay on the item"
    )
    default PoseDoseOverlayPosition overlayPosition()
    {
        return PoseDoseOverlayPosition.TOP_RIGHT;
    }
}
