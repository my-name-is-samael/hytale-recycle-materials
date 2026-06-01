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
import com.tontonsamael.config.RecycleMaterialsConfig;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SettingsUI extends InteractiveCustomUIPage<SettingsUI.RecycleMaterialsUIData> {
    private static final String ACTION = "Action";

    private enum SettingsActions {
        CLOSE, SOIL, SLABS, STAIRS, PILLARS, BEAMS, ROOFS, FENCES, WINDOWS, SALT, LEATHER_ROOFS,
    }

    public static class RecycleMaterialsUIData {
        public static final BuilderCodec<RecycleMaterialsUIData> CODEC = BuilderCodec.builder(RecycleMaterialsUIData.class, RecycleMaterialsUIData::new)
                .append(new KeyedCodec<>(ACTION, Codec.STRING),
                        (data, value) -> data.action = value,
                        data -> data.action).add()
                .build();

        private String action;
    }

    public SettingsUI(PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, RecycleMaterialsUIData.CODEC);
    }

    private boolean checkAccess(Store<EntityStore> store, Ref<EntityStore> ref) {
        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        assert uuidComponent != null;

        // permission
        if (PermissionsModule.get().hasPermission(uuidComponent.getUuid(), "recyclematerials.settings")) {
            return true;
        }

        // OP
        PermissionsModule perms = PermissionsModule.get();
        Set<String> groups = perms.getGroupsForUser(uuidComponent.getUuid());
        return groups.contains("hytale:Admin");
    }

    private void closePage(Ref<EntityStore> playerRef, Store<EntityStore> store) {
        Player player = store.getComponent(playerRef, Player.getComponentType());
        assert player != null;
        player.getPageManager().setPage(playerRef, store, Page.None);
    }

    private UICommandBuilder uiCmd;

    private void addEventBinding(@Nonnull UIEventBuilder events, @Nonnull String selectorId, @Nonnull SettingsActions action) {
        events.addEventBinding(CustomUIEventBindingType.Activating,
                String.format("#%s #Toggle", selectorId), EventData.of(ACTION, action.name()));
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref,
                      @Nonnull UICommandBuilder cmd,
                      @Nonnull UIEventBuilder events,
                      @Nonnull Store<EntityStore> store) {

        if (!checkAccess(store, ref)) {
            closePage(ref, store);
            return;
        }

        uiCmd = cmd;
        cmd.append("RecycleMaterials/Settings.ui");
        updateStates(RecycleMaterials.get().getConfig(), RecycleMaterials.get().getBootConfig());

        addEventBinding(events, "Soil", SettingsActions.SOIL);
        addEventBinding(events, "Slabs", SettingsActions.SLABS);
        addEventBinding(events, "Stairs", SettingsActions.STAIRS);
        addEventBinding(events, "Pillars", SettingsActions.PILLARS);
        addEventBinding(events, "Beams", SettingsActions.BEAMS);
        addEventBinding(events, "Roofs", SettingsActions.ROOFS);
        addEventBinding(events, "Fences", SettingsActions.FENCES);
        addEventBinding(events, "Windows", SettingsActions.WINDOWS);
        addEventBinding(events, "Salt", SettingsActions.SALT);
        addEventBinding(events, "LeatherRoofs", SettingsActions.LEATHER_ROOFS);

        events.addEventBinding(CustomUIEventBindingType.Activating,
                "#CloseButton", EventData.of(ACTION, SettingsActions.CLOSE.name()));
    }

    private void toggleLabelDirty(String selector) {
        uiCmd.set(selector + ".Style.TextColor", "#ffac4a");
        uiCmd.set(selector + ".Style.RenderBold", true);
    }

    private void toggleButtonOff(String selector) {
        uiCmd.set(selector + ".Text", Message.translation("recyclematerials.ui.button.off"));
        uiCmd.set(selector + ".Style.Default.Background", "#3d1a1a");
        uiCmd.set(selector + ".Style.Hovered.Background", "#4d2a2a");
        uiCmd.set(selector + ".Style.Pressed.Background", "#321b1b");
    }

    private void updateToggleLineState(boolean value, boolean bootValue, String selector) {
        if (value != bootValue) {
            toggleLabelDirty(selector + " #Label");
        }
        if (!value) {
            toggleButtonOff(selector + " #Toggle");
        }
    }

    private void updateStates(RecycleMaterialsConfig conf, RecycleMaterialsConfig bootConf) {
        updateToggleLineState(conf.isSoil(), bootConf.isSoil(), "#Soil");
        updateToggleLineState(conf.isSlabs(), bootConf.isSlabs(), "#Slabs");
        updateToggleLineState(conf.isStairs(), bootConf.isStairs(), "#Stairs");
        updateToggleLineState(conf.isPillars(), bootConf.isPillars(), "#Pillars");
        updateToggleLineState(conf.isBeams(), bootConf.isBeams(), "#Beams");
        updateToggleLineState(conf.isRoofs(), bootConf.isRoofs(), "#Roofs");
        updateToggleLineState(conf.isFences(), bootConf.isFences(), "#Fences");
        updateToggleLineState(conf.isWindows(), bootConf.isWindows(), "#Windows");
        updateToggleLineState(conf.isSalt(), bootConf.isSalt(), "#Salt");
        updateToggleLineState(conf.isLeatherRoofs(), bootConf.isLeatherRoofs(), "#LeatherRoofs");

        // footer dirty label
        if (conf.equals(bootConf)) {
            uiCmd.remove("#DirtyWarning");
        }
    }

    private boolean checkAndProcessAction(SettingsActions action, RecycleMaterialsUIData data, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        if (action.name().equals(data.action)) {
            setter.accept(!getter.get());
            RecycleMaterials.get().saveConfig();
            return true;
        }
        return false;
    }

    public void handleDataEvent(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl Store<EntityStore> store, @NonNullDecl RecycleMaterialsUIData data) {
        if (!checkAccess(store, ref) || SettingsActions.CLOSE.name().equals(data.action)) {
            closePage(ref, store);
            return;
        }

        assert uiCmd != null;
        RecycleMaterialsConfig conf = RecycleMaterials.get().getConfig();

        boolean updated = checkAndProcessAction(SettingsActions.SOIL, data, conf::isSoil, conf::setSoil) ||
                checkAndProcessAction(SettingsActions.SLABS, data, conf::isSlabs, conf::setSlabs) ||
                checkAndProcessAction(SettingsActions.STAIRS, data, conf::isStairs, conf::setStairs) ||
                checkAndProcessAction(SettingsActions.PILLARS, data, conf::isPillars, conf::setPillars) ||
                checkAndProcessAction(SettingsActions.BEAMS, data, conf::isBeams, conf::setBeams) ||
                checkAndProcessAction(SettingsActions.ROOFS, data, conf::isRoofs, conf::setRoofs) ||
                checkAndProcessAction(SettingsActions.FENCES, data, conf::isFences, conf::setFences) ||
                checkAndProcessAction(SettingsActions.WINDOWS, data, conf::isWindows, conf::setWindows) ||
                checkAndProcessAction(SettingsActions.SALT, data, conf::isSalt, conf::setSalt) ||
                checkAndProcessAction(SettingsActions.LEATHER_ROOFS, data, conf::isLeatherRoofs, conf::setLeatherRoofs);

        if (updated) {
            Player player = store.getComponent(ref, Player.getComponentType());
            assert player != null;
            player.getPageManager().openCustomPage(ref, store, new SettingsUI(playerRef));
        }
    }
}
