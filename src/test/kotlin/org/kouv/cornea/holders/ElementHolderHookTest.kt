package org.kouv.cornea.holders

import eu.pb4.polymer.virtualentity.api.ElementHolder
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment
import io.mockk.*
import net.minecraft.server.network.ServerPlayNetworkHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.kouv.cornea.tests.FabricExtension
import kotlin.test.Test

@ExtendWith(FabricExtension::class)
class ElementHolderHookTest {
    private lateinit var elementHolder: ElementHolder
    private lateinit var elementHolderHook: ElementHolderHook

    @BeforeEach
    fun setUp() {
        elementHolder = elementHolder()
        elementHolderHook = elementHolder as ElementHolderHook
    }

    @Test
    fun `startWatching should invoke startWatching listeners`() {
        // given
        val mockNetworkHandler = mockk<ServerPlayNetworkHandler>(relaxed = true)
        val mockListener = mockk<(ServerPlayNetworkHandler) -> Unit>()

        every { mockListener(any()) } just runs

        elementHolderHook.`cornea$addStartWatchingListener`(mockListener)

        // when
        elementHolder.startWatching(mockNetworkHandler)

        // then
        verify { mockListener(mockNetworkHandler) }
    }

    @Test
    fun `stopWatching should invoke stopWatching listeners`() {
        // given
        val mockNetworkHandler = mockk<ServerPlayNetworkHandler>(relaxed = true)
        val mockListener = mockk<(ServerPlayNetworkHandler) -> Unit>()

        every { mockListener(any()) } just runs

        elementHolder.startWatching(mockNetworkHandler)
        elementHolderHook.`cornea$addStopWatchingListener`(mockListener)

        // when
        elementHolder.stopWatching(mockNetworkHandler)

        // then
        verify { mockListener(mockNetworkHandler) }
    }

    @Test
    fun `tick should invoke tick listeners when attachment is not null`() {
        // given
        val mockAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockListener = mockk<() -> Unit>()

        every { mockListener() } just runs

        elementHolder.attachment = mockAttachment
        elementHolderHook.`cornea$addTickListener`(mockListener)

        // when
        elementHolder.tick()

        // then
        verify { mockListener() }
    }

    @Test
    fun `tick should not invoke tick listeners when attachment is null`() {
        // given
        val mockListener = mockk<() -> Unit>()

        every { mockListener() } just runs

        elementHolderHook.`cornea$addTickListener`(mockListener)

        // when
        elementHolder.tick()

        // then
        verify { mockListener wasNot called }
    }

    @Test
    fun `onAttachmentSet should invoke attachmentSet listeners`() {
        // given
        val mockNewAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockListener = mockk<(HolderAttachment, HolderAttachment?) -> Unit>()

        every { mockListener(any(), any()) } just runs

        elementHolderHook.`cornea$addAttachmentSetListener`(mockListener)

        // when
        elementHolder.attachment = mockNewAttachment

        // then
        verify { mockListener(mockNewAttachment, null) }
    }

    @Test
    fun `onAttachmentSet should invoke attachmentSet listeners with old attachment`() {
        // given
        val mockOldAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockNewAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockListener = mockk<(HolderAttachment, HolderAttachment?) -> Unit>()

        every { mockListener(any(), any()) } just runs

        elementHolder.attachment = mockOldAttachment
        elementHolderHook.`cornea$addAttachmentSetListener`(mockListener)

        // when
        elementHolder.attachment = mockNewAttachment

        // then
        verify { mockListener(mockNewAttachment, mockOldAttachment) }
    }

    @Test
    fun `onAttachmentRemoved should invoke attachmentRemoved listeners`() {
        // given
        val mockAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockListener = mockk<(HolderAttachment) -> Unit>()

        every { mockListener(any()) } just runs

        elementHolder.attachment = mockAttachment
        elementHolderHook.`cornea$addAttachmentRemovedListener`(mockListener)

        // when
        elementHolder.attachment = null

        // then
        verify { mockListener(mockAttachment) }
    }

    @Test
    fun `onAttachmentRemoved should not be called when attachment is set to another non-null value`() {
        // given
        val mockOldAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockNewAttachment = mockk<HolderAttachment>(relaxed = true)
        val mockListener = mockk<(HolderAttachment) -> Unit>()

        every { mockListener(any()) } just runs

        elementHolder.attachment = mockOldAttachment
        elementHolderHook.`cornea$addAttachmentRemovedListener`(mockListener)

        // when
        elementHolder.attachment = mockNewAttachment

        // then
        verify(exactly = 0) { mockListener(any()) }
    }
}