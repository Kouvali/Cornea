package org.kouv.cornea.holders;

import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface ElementHolderHook {
    void cornea$addStartWatchingListener(Consumer<? super ServerPlayNetworkHandler> consumer);

    void cornea$addStopWatchingListener(Consumer<? super ServerPlayNetworkHandler> consumer);

    void cornea$addTickListener(Runnable runnable);

    void cornea$addAttachmentSetListener(BiConsumer<? super HolderAttachment, ? super @Nullable HolderAttachment> consumer);

    void cornea$addAttachmentRemovedListener(Consumer<? super HolderAttachment> consumer);
}
