package com.tontonsamael.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.tontonsamael.ui.SettingsUI;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class RecycleMaterialsCommand extends AbstractPlayerCommand {

    public RecycleMaterialsCommand() {
        super("recyclematerials", "recyclematerials.command.description");
        this.setPermissionGroups("OP");
        this.requirePermission("recyclematerials.settings");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext context, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        if (context.sender() instanceof Player player) {
            SettingsUI page = new SettingsUI(playerRef);
            player.getPageManager().openCustomPage(ref, store, page);
        }
    }
}
