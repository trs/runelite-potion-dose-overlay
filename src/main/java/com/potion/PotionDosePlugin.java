package com.potion;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Potion Dose Overlay"
)
public class PotionDosePlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private  PotionDoseOverlay overlay;

	@Inject
	private Client client;

	@Inject
	private PotionDoseConfig config;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Provides
	PotionDoseConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PotionDoseConfig.class);
	}
}
