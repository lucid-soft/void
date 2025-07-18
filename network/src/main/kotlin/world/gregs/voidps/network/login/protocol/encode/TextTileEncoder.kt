package world.gregs.voidps.network.login.protocol.encode

import io.ktor.utils.io.*
import world.gregs.voidps.network.client.Client
import world.gregs.voidps.network.client.Client.Companion.BYTE
import world.gregs.voidps.network.client.Client.Companion.string
import world.gregs.voidps.network.login.Protocol.TILE_TEXT
import world.gregs.voidps.network.login.protocol.writeMedium
import world.gregs.voidps.network.login.protocol.writeString

fun Client.tileText(
    tile: Int,
    duration: Int,
    height: Int,
    color: Int,
    text: String,
) = send(TILE_TEXT, 8 + string(text), BYTE) {
    writeByte(0)
    writeByte(tile)
    writeShort(duration)
    writeByte(height)
    writeMedium(color)
    writeString(text)
}
