package com.tontonsamael.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tontonsamael.RecycleMaterials;
import com.tontonsamael.config.RecycleCategory;
import com.tontonsamael.config.RecycleMaterialsConfig;
import com.tontonsamael.config.RecyclePreset;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

public class SettingsUI extends InteractiveCustomUIPage<SettingsUI.UIData> {
    private static final String ACTION = "Action";
    private static final String TARGET = "Target";
    private static final String ROW_DOC = "RecycleMaterials/Row.ui";

    private static final String ACTION_CLOSE = "close";
    private static final String ACTION_TOGGLE = "toggle";
    private static final String ACTION_PRESET = "preset";
    private static final String ACTION_CRAFT_TIME = "craftTime";
    private static final String ACTION_CRAFT_TIME_DRAG = "craftTimeDrag";

    public static class UIData {
        public static final BuilderCodec<UIData> CODEC = BuilderCodec.builder(UIData.class, UIData::new)
                .append(new KeyedCodec<>(ACTION, Codec.STRING), (data, value) -> data.action = value, data -> data.action).add()
                .append(new KeyedCodec<>(TARGET, Codec.STRING), (data, value) -> data.target = value, data -> data.target).add()
                .append(new KeyedCodec<>("@CraftTime", Codec.INTEGER), (data, value) -> data.craftTime = value, data -> data.craftTime).add()
                .build();

        private String action;
        private String target;
        private Integer craftTime;
    }

    /** Where each category row lives in its list, so updates can address it. */
    private final Map<RecycleCategory, String> rowSelectors = new EnumMap<>(RecycleCategory.class);

    public SettingsUI(PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, UIData.CODEC);
    }

    private static boolean hasAccess(Store<EntityStore> store, Ref<EntityStore> ref) {
        UUIDComponent uuid = store.getComponent(ref, UUIDComponent.getComponentType());
        if (uuid == null) return false;
        PermissionsModule perms = PermissionsModule.get();
        return perms.hasPermission(uuid.getUuid(), "recyclematerials.settings")
                || perms.getGroupsForUser(uuid.getUuid()).contains("hytale:Admin");
    }

    private void closePage(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player != null) player.getPageManager().setPage(ref, store, Page.None);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder cmd, @Nonnull UIEventBuilder events, @Nonnull Store<EntityStore> store) {
        if (!hasAccess(store, ref)) {
            closePage(ref, store);
            return;
        }

        cmd.append("RecycleMaterials/Settings.ui");

        // One row per category, in its group's list.
        Map<RecycleCategory.Group, Integer> counts = new EnumMap<>(RecycleCategory.Group.class);
        for (RecycleCategory category : RecycleCategory.values()) {
            String list = category.group == RecycleCategory.Group.STRUCTURE ? "#StructureRows" : "#MaterialsRows";
            int index = counts.merge(category.group, 1, Integer::sum) - 1;
            cmd.append(list, ROW_DOC);

            String row = list + "[" + index + "]";
            rowSelectors.put(category, row);
            cmd.set(row + " #Label.Text", Message.translation("recyclematerials.ui." + category.lang));
            cmd.set(row + " #Desc.Text", Message.translation("recyclematerials.ui." + category.lang + ".desc"));

            EventData toggle = EventData.of(ACTION, ACTION_TOGGLE).append(TARGET, category.name());
            events.addEventBinding(CustomUIEventBindingType.Activating, row + " #On", toggle);
            events.addEventBinding(CustomUIEventBindingType.Activating, row + " #Off", toggle);
        }

        for (RecyclePreset preset : RecyclePreset.values()) {
            events.addEventBinding(CustomUIEventBindingType.Activating, "#Preset" + capitalize(preset.name()),
                    EventData.of(ACTION, ACTION_PRESET).append(TARGET, preset.name()));
        }
        events.addEventBinding(CustomUIEventBindingType.Activating, "#CloseButton", EventData.of(ACTION, ACTION_CLOSE));
        // While dragging, only the label follows. The binding must not lock the interface, or the drag is interrupted.
        // The value is applied (saved, recipes swapped) when the mouse button is released.
        events.addEventBinding(CustomUIEventBindingType.ValueChanged, "#CraftTime",
                EventData.of(ACTION, ACTION_CRAFT_TIME_DRAG).append("@CraftTime", "#CraftTime.Value"), false);
        events.addEventBinding(CustomUIEventBindingType.MouseButtonReleased, "#CraftTime",
                EventData.of(ACTION, ACTION_CRAFT_TIME).append("@CraftTime", "#CraftTime.Value"));

        RecycleMaterialsConfig config = RecycleMaterials.get().getConfig();
        cmd.set("#CraftTime.Value", config.getCraftTimeTenths());
        applyState(cmd, config);
    }

    private static String capitalize(String enumName) {
        return enumName.charAt(0) + enumName.substring(1).toLowerCase();
    }

    /** Pushes the config state (switches and preset label) into the window. */
    private void applyState(UICommandBuilder cmd, RecycleMaterialsConfig config) {
        for (RecycleCategory category : RecycleCategory.values()) {
            String row = rowSelectors.get(category);
            boolean on = config.isEnabled(category);
            cmd.set(row + " #On.Visible", on);
            cmd.set(row + " #Off.Visible", !on);
        }

        String preset = "custom";
        for (RecyclePreset p : RecyclePreset.values()) {
            if (p.matches(config)) {
                preset = p.lang;
                break;
            }
        }
        cmd.set("#PresetStatus.Text", Message.translation("recyclematerials.ui.preset.current." + preset));
        cmd.set("#CraftTimeValue.Text", String.format(java.util.Locale.ROOT, "%.1f s", config.getCraftTimeSeconds()));
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull UIData data) {
        if (!hasAccess(store, ref) || ACTION_CLOSE.equals(data.action)) {
            closePage(ref, store);
            return;
        }

        RecycleMaterialsConfig config = RecycleMaterials.get().getConfig();
        if (ACTION_CRAFT_TIME_DRAG.equals(data.action)) {
            if (data.craftTime != null) {
                config.setCraftTimeTenths(data.craftTime);
                UICommandBuilder cmd = new UICommandBuilder();
                cmd.set("#CraftTimeValue.Text", String.format(java.util.Locale.ROOT, "%.1f s", config.getCraftTimeSeconds()));
                sendUpdate(cmd, false);
            }
            return;
        }
        boolean changed = false;
        try {
            if (ACTION_TOGGLE.equals(data.action)) {
                config.toggle(RecycleCategory.valueOf(data.target));
                changed = true;
            } else if (ACTION_PRESET.equals(data.action)) {
                config.apply(RecyclePreset.valueOf(data.target));
                changed = true;
            } else if (ACTION_CRAFT_TIME.equals(data.action) && data.craftTime != null) {
                config.setCraftTimeTenths(data.craftTime);
                changed = true;
            }
        } catch (IllegalArgumentException ignored) {
            // unknown target from a stale or forged event
        }

        if (changed) {
            RecycleMaterials.get().applyConfigChange();
            UICommandBuilder cmd = new UICommandBuilder();
            applyState(cmd, config);
            sendUpdate(cmd, false);
        }
    }
}
