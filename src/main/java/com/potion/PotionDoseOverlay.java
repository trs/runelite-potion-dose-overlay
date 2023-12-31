
package com.potion;

import net.runelite.api.ItemComposition;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.components.TextComponent;

import com.google.inject.Inject;

import java.awt.*;

public class PotionDoseOverlay extends WidgetItemOverlay {
    private final PotionDoseConfig config;
    private final ItemManager itemManager;

    @Inject
    PotionDoseOverlay(PotionDoseConfig config, ItemManager itemManager) {
        this.config = config;
        this.itemManager = itemManager;

        showOnInventory();
        showOnBank();
        showOnInterfaces(InterfaceID.KEPT_ON_DEATH, InterfaceID.GUIDE_PRICES, InterfaceID.LOOTING_BAG);
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
//        final PotionDoseIdentification potion = identifyPotion(itemId);
//        if (potion == null) {
//            return;
//        }

        final int count = getDoseCount(itemId);
        if (count < 1) return;

        graphics.setFont(FontManager.getRunescapeSmallFont());
        renderText(graphics, widgetItem.getCanvasBounds(), count);
    }

    private void renderText(Graphics2D graphics, Rectangle bounds, int count) {
        final TextComponent textComponent = new TextComponent();

        switch (config.overlayPosition()) {
            case TOP_RIGHT:
                textComponent.setPosition(new Point(bounds.x + bounds.width - 10, bounds.y + 10));
                break;
            case BOTTOM_RIGHT:
                textComponent.setPosition(new Point(bounds.x + bounds.width - 10, bounds.y + bounds.height - 1));
                break;
        }

        if (count == 1) {
            textComponent.setColor(Color.RED);
        } else if (count == 2) {
            textComponent.setColor(Color.YELLOW);
        } else {
            textComponent.setColor(Color.GREEN);
        }

        textComponent.setText(String.valueOf(count));
        textComponent.render(graphics);
    }

    private PotionDoseIdentification identifyPotion(final int itemId) {
        final int realItemID = itemManager.canonicalize(itemId);

        return PotionDoseIdentification.get(realItemID);
    }

    private int getDoseCount(final int itemId) {
        final int realItemID = itemManager.canonicalize(itemId);

        final ItemComposition item = itemManager.getItemComposition(realItemID);
        final String itemName = item.getName();

        if (itemName.contains("(4)")) return 4;
        else if (itemName.contains("(3)")) return 3;
        else if (itemName.contains("(2)")) return 2;
        else if (itemName.contains("(1)")) return 1;
        else return -1;
    }
}
