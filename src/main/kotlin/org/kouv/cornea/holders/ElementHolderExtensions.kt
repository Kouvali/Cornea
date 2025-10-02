package org.kouv.cornea.holders

import eu.pb4.polymer.virtualentity.api.ElementHolder
import eu.pb4.polymer.virtualentity.api.attachment.*
import eu.pb4.polymer.virtualentity.api.elements.*
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.chunk.WorldChunk
import org.kouv.cornea.annotations.ExperimentalPolymerApi
import org.kouv.cornea.annotations.InternalPolymerApi

public inline fun elementHolder(block: ElementHolder.() -> Unit = {}): ElementHolder = ElementHolder().apply(block)

@InternalPolymerApi
public inline fun ElementHolder.blockBoundAttachment(
    chunk: WorldChunk,
    state: BlockState,
    blockPos: BlockPos,
    pos: Vec3d,
    isTicking: Boolean,
    block: BlockBoundAttachment.() -> Unit = {}
): BlockBoundAttachment =
    org.kouv.cornea.attachments.blockBoundAttachment(this, chunk, state, blockPos, pos, isTicking, block)

@ExperimentalPolymerApi
public inline fun ElementHolder.blockBoundAttachmentOf(
    world: ServerWorld,
    blockPos: BlockPos,
    state: BlockState,
    block: BlockBoundAttachment.() -> Unit = {}
): BlockBoundAttachment? = org.kouv.cornea.attachments.blockBoundAttachmentOf(this, world, blockPos, state, block)

@ExperimentalPolymerApi
public inline fun ElementHolder.blockBoundAttachmentOf(
    world: ServerWorld,
    chunk: WorldChunk,
    blockPos: BlockPos,
    state: BlockState,
    block: BlockBoundAttachment.() -> Unit = {}
): BlockBoundAttachment? =
    org.kouv.cornea.attachments.blockBoundAttachmentOf(this, world, chunk, blockPos, state, block)

@ExperimentalPolymerApi
public inline fun ElementHolder.blockBoundAttachmentFromMoving(
    world: ServerWorld,
    pos: BlockPos,
    state: BlockState,
    block: BlockBoundAttachment.() -> Unit = {}
): BlockBoundAttachment? = org.kouv.cornea.attachments.blockBoundAttachmentFromMoving(this, world, pos, state, block)

public inline fun ElementHolder.chunkAttachment(
    chunk: WorldChunk,
    pos: Vec3d,
    isTicking: Boolean,
    block: ChunkAttachment.() -> Unit = {}
): ChunkAttachment = org.kouv.cornea.attachments.chunkAttachment(this, chunk, pos, isTicking, block)

public inline fun ElementHolder.chunkAttachmentOf(
    world: ServerWorld,
    pos: BlockPos,
    block: HolderAttachment.() -> Unit = {}
): HolderAttachment = org.kouv.cornea.attachments.chunkAttachmentOf(this, world, pos, block)

public inline fun ElementHolder.chunkAttachmentOfTicking(
    world: ServerWorld,
    pos: BlockPos,
    block: HolderAttachment.() -> Unit = {}
): HolderAttachment = org.kouv.cornea.attachments.chunkAttachmentOfTicking(this, world, pos, block)

public inline fun ElementHolder.chunkAttachmentOf(
    world: ServerWorld,
    pos: Vec3d,
    block: HolderAttachment.() -> Unit = {}
): HolderAttachment = org.kouv.cornea.attachments.chunkAttachmentOf(this, world, pos, block)

public inline fun ElementHolder.chunkAttachmentOfTicking(
    world: ServerWorld,
    pos: Vec3d,
    block: HolderAttachment.() -> Unit = {}
): HolderAttachment = org.kouv.cornea.attachments.chunkAttachmentOfTicking(this, world, pos, block)

public inline fun ElementHolder.entityAttachment(
    entity: Entity,
    isTicking: Boolean,
    block: EntityAttachment.() -> Unit = {}
): EntityAttachment = org.kouv.cornea.attachments.entityAttachment(this, entity, isTicking, block)

public inline fun ElementHolder.entityAttachmentOf(
    entity: Entity,
    block: EntityAttachment.() -> Unit = {}
): EntityAttachment = org.kouv.cornea.attachments.entityAttachmentOf(this, entity, block)

public inline fun ElementHolder.entityAttachmentOfTicking(
    entity: Entity,
    block: EntityAttachment.() -> Unit = {}
): EntityAttachment = org.kouv.cornea.attachments.entityAttachmentOfTicking(this, entity, block)

public inline fun ElementHolder.identifiedUniqueEntityAttachment(
    id: Identifier,
    entity: Entity,
    isTicking: Boolean,
    block: IdentifiedUniqueEntityAttachment.() -> Unit = {}
): IdentifiedUniqueEntityAttachment =
    org.kouv.cornea.attachments.identifiedUniqueEntityAttachment(id, this, entity, isTicking, block)

public inline fun ElementHolder.identifiedUniqueEntityAttachmentOf(
    id: Identifier,
    entity: Entity,
    block: IdentifiedUniqueEntityAttachment.() -> Unit = {}
): IdentifiedUniqueEntityAttachment =
    org.kouv.cornea.attachments.identifiedUniqueEntityAttachmentOf(id, this, entity, block)

public inline fun ElementHolder.identifiedUniqueEntityAttachmentOfTicking(
    id: Identifier,
    entity: Entity,
    block: IdentifiedUniqueEntityAttachment.() -> Unit = {}
): IdentifiedUniqueEntityAttachment =
    org.kouv.cornea.attachments.identifiedUniqueEntityAttachmentOfTicking(id, this, entity, block)

public inline fun ElementHolder.manualAttachment(
    world: ServerWorld,
    noinline posSupplier: () -> Vec3d,
    block: ManualAttachment.() -> Unit = {}
): ManualAttachment = org.kouv.cornea.attachments.manualAttachment(this, world, posSupplier, block)

public inline fun ElementHolder.blockDisplayElement(block: BlockDisplayElement.() -> Unit = {}): BlockDisplayElement =
    addElement(org.kouv.cornea.elements.blockDisplayElement(block))

public inline fun ElementHolder.blockDisplayElement(
    state: BlockState,
    block: BlockDisplayElement.() -> Unit = {}
): BlockDisplayElement = addElement(org.kouv.cornea.elements.blockDisplayElement(state, block))

public inline fun <T : Entity> ElementHolder.entityElement(
    entity: T,
    world: ServerWorld,
    block: EntityElement<T>.() -> Unit = {}
): EntityElement<T> = addElement(org.kouv.cornea.elements.entityElement(entity, world, block))

public inline fun <T : Entity> ElementHolder.entityElement(
    entity: T,
    world: ServerWorld,
    handler: VirtualElement.InteractionHandler,
    block: EntityElement<T>.() -> Unit = {}
): EntityElement<T> = addElement(org.kouv.cornea.elements.entityElement(entity, world, handler, block))

public inline fun <T : Entity> ElementHolder.entityElement(
    type: EntityType<T>,
    world: ServerWorld,
    block: EntityElement<T>.() -> Unit = {}
): EntityElement<T> = addElement(org.kouv.cornea.elements.entityElement(type, world, block))

public inline fun <T : Entity> ElementHolder.entityElement(
    type: EntityType<T>,
    world: ServerWorld,
    handler: VirtualElement.InteractionHandler,
    block: EntityElement<T>.() -> Unit = {}
): EntityElement<T> = addElement(org.kouv.cornea.elements.entityElement(type, world, handler, block))

public inline fun ElementHolder.interactionElement(block: InteractionElement.() -> Unit = {}): InteractionElement =
    addElement(org.kouv.cornea.elements.interactionElement(block))

public inline fun ElementHolder.interactionElement(
    handler: VirtualElement.InteractionHandler,
    block: InteractionElement.() -> Unit = {}
): InteractionElement = addElement(org.kouv.cornea.elements.interactionElement(handler, block))

public inline fun ElementHolder.interactionElementRedirect(
    redirect: Entity,
    block: InteractionElement.() -> Unit = {}
): InteractionElement = addElement(org.kouv.cornea.elements.interactionElementRedirect(redirect, block))

public inline fun ElementHolder.itemDisplayElement(block: ItemDisplayElement.() -> Unit = {}): ItemDisplayElement =
    addElement(org.kouv.cornea.elements.itemDisplayElement(block))

public inline fun ElementHolder.itemDisplayElement(
    stack: ItemStack,
    block: ItemDisplayElement.() -> Unit = {}
): ItemDisplayElement = addElement(org.kouv.cornea.elements.itemDisplayElement(stack, block))

public inline fun ElementHolder.itemDisplayElement(
    item: Item,
    block: ItemDisplayElement.() -> Unit = {}
): ItemDisplayElement = addElement(org.kouv.cornea.elements.itemDisplayElement(item, block))

public inline fun ElementHolder.markerElement(block: MarkerElement.() -> Unit = {}): MarkerElement =
    addElement(org.kouv.cornea.elements.markerElement(block))

public inline fun ElementHolder.mobAnchorElement(block: MobAnchorElement.() -> Unit = {}): MobAnchorElement =
    addElement(org.kouv.cornea.elements.mobAnchorElement(block))

public inline fun ElementHolder.simpleEntityElement(
    type: EntityType<*>,
    block: SimpleEntityElement.() -> Unit = {}
): SimpleEntityElement = addElement(org.kouv.cornea.elements.simpleEntityElement(type, block))

public inline fun ElementHolder.textDisplayElement(block: TextDisplayElement.() -> Unit = {}): TextDisplayElement =
    addElement(org.kouv.cornea.elements.textDisplayElement(block))

public inline fun ElementHolder.textDisplayElement(
    text: Text,
    block: TextDisplayElement.() -> Unit = {}
): TextDisplayElement = addElement(org.kouv.cornea.elements.textDisplayElement(text, block))

public class HolderStartWatchingScope @PublishedApi internal constructor(
    public val networkHandler: ServerPlayNetworkHandler
) {
    public val player: ServerPlayerEntity get() = networkHandler.player
}

public inline fun ElementHolder.onStartWatching(crossinline block: HolderStartWatchingScope.() -> Unit): Unit =
    (this as ElementHolderHook).`cornea$addStartWatchingListener` { HolderStartWatchingScope(it).block() }

public class HolderStopWatchingScope @PublishedApi internal constructor(
    public val networkHandler: ServerPlayNetworkHandler
) {
    public val player: ServerPlayerEntity get() = networkHandler.player
}

public inline fun ElementHolder.onStopWatching(crossinline block: HolderStopWatchingScope.() -> Unit): Unit =
    (this as ElementHolderHook).`cornea$addStopWatchingListener` { HolderStopWatchingScope(it).block() }

public class HolderTickScope @PublishedApi internal constructor(
    public val ticks: Int
)

public inline fun ElementHolder.onTick(crossinline block: HolderTickScope.() -> Unit) {
    var ticks = 0
    (this as ElementHolderHook).`cornea$addTickListener` { HolderTickScope(ticks++).block() }
}

public class HolderAttachmentSetScope @PublishedApi internal constructor(
    public val attachment: HolderAttachment,
    public val oldAttachment: HolderAttachment?
)

public inline fun ElementHolder.onAttachmentSet(crossinline block: HolderAttachmentSetScope.() -> Unit): Unit =
    (this as ElementHolderHook).`cornea$addAttachmentSetListener` { attachment, oldAttachment ->
        HolderAttachmentSetScope(attachment, oldAttachment).block()
    }

public class HolderAttachmentRemovedScope @PublishedApi internal constructor(
    public val oldAttachment: HolderAttachment
)

public inline fun ElementHolder.onAttachmentRemoved(crossinline block: HolderAttachmentRemovedScope.() -> Unit): Unit =
    (this as ElementHolderHook).`cornea$addAttachmentRemovedListener` { oldAttachment ->
        HolderAttachmentRemovedScope(oldAttachment).block()
    }