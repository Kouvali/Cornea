package org.kouv.cornea.mixin;

import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.kouv.cornea.holders.ElementHolderHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mixin(value = ElementHolder.class, remap = false)
public abstract class ElementHolderMixin implements ElementHolderHook {
    @Unique
    private final List<Consumer<? super ServerPlayNetworkHandler>> cornea$startWatchingListeners = new CopyOnWriteArrayList<>();
    @Unique
    private final List<Consumer<? super ServerPlayNetworkHandler>> cornea$stopWatchingListeners = new CopyOnWriteArrayList<>();
    @Unique
    private final List<Runnable> cornea$tickListeners = new CopyOnWriteArrayList<>();
    @Unique
    private final List<BiConsumer<? super HolderAttachment, ? super @Nullable HolderAttachment>> cornea$attachmentSetListeners = new CopyOnWriteArrayList<>();
    @Unique
    private final List<Consumer<? super HolderAttachment>> cornea$attachmentRemovedListeners = new CopyOnWriteArrayList<>();

    @Override
    public void cornea$addStartWatchingListener(Consumer<? super ServerPlayNetworkHandler> consumer) {
        Objects.requireNonNull(consumer);
        cornea$startWatchingListeners.add(consumer);
    }

    @Override
    public void cornea$addStopWatchingListener(Consumer<? super ServerPlayNetworkHandler> consumer) {
        Objects.requireNonNull(consumer);
        cornea$stopWatchingListeners.add(consumer);
    }

    @Override
    public void cornea$addTickListener(Runnable runnable) {
        Objects.requireNonNull(runnable);
        cornea$tickListeners.add(runnable);
    }

    @Override
    public void cornea$addAttachmentSetListener(BiConsumer<? super HolderAttachment, ? super @Nullable HolderAttachment> consumer) {
        Objects.requireNonNull(consumer);
        cornea$attachmentSetListeners.add(consumer);
    }

    @Override
    public void cornea$addAttachmentRemovedListener(Consumer<? super HolderAttachment> consumer) {
        Objects.requireNonNull(consumer);
        cornea$attachmentRemovedListeners.add(consumer);
    }

    @Inject(method = "startWatching(Lnet/minecraft/server/network/ServerPlayNetworkHandler;)Z", at = @At(value = "RETURN"))
    private void cornea$startWatching(ServerPlayNetworkHandler player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            cornea$startWatchingListeners.forEach(consumer -> consumer.accept(player));
        }
    }

    @Inject(method = "stopWatching(Lnet/minecraft/server/network/ServerPlayNetworkHandler;)Z", at = @At(value = "RETURN"))
    private void cornea$stopWatching(ServerPlayNetworkHandler player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            cornea$stopWatchingListeners.forEach(consumer -> consumer.accept(player));
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Leu/pb4/polymer/virtualentity/api/ElementHolder;onTick()V"))
    private void cornea$tick(CallbackInfo ci) {
        cornea$tickListeners.forEach(Runnable::run);
    }

    @Inject(method = "onAttachmentSet", at = @At(value = "TAIL"))
    private void cornea$onAttachmentSet(HolderAttachment attachment, @Nullable HolderAttachment oldAttachment, CallbackInfo ci) {
        cornea$attachmentSetListeners.forEach(consumer -> consumer.accept(attachment, oldAttachment));
    }

    @Inject(method = "onAttachmentRemoved", at = @At(value = "TAIL"))
    private void cornea$onAttachmentRemoved(HolderAttachment oldAttachment, CallbackInfo ci) {
        cornea$attachmentRemovedListeners.forEach(consumer -> consumer.accept(oldAttachment));
    }
}
