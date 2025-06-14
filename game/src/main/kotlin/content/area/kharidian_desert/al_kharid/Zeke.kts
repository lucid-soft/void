package content.area.kharidian_desert.al_kharid

import content.entity.npc.shop.openShop
import content.entity.player.dialogue.*
import content.entity.player.dialogue.type.choice
import content.entity.player.dialogue.type.npc
import content.entity.player.dialogue.type.player
import content.quest.quest
import world.gregs.voidps.engine.entity.character.npc.npcOperate
import world.gregs.voidps.engine.entity.character.player.Player
import world.gregs.voidps.engine.entity.character.player.male
import world.gregs.voidps.engine.suspend.SuspendableContext

npcOperate("Talk-to", "zeke") {
    npc<Talk>("A thousand greetings, ${if (player.male) "sir" else "madam"}.")
    choice {
        option("Do you want to trade?") {
            npc<Happy>("Yes, certainly. I deal in scimitars.")
            player.openShop("zekes_superior_scimitars")
        }
        option<Quiz>("Nice cloak.") {
            npc<Quiz>("Thank you.")
        }
        option<Quiz>("Could you sell me a dragon scimitar?") {
            npc<Frustrated>("A dragon scimitar? A DRAGON scimitar?")
            npc<Frustrated>("No way, man!")
            npc<Angry>("The banana-brained nitwits who make them would never dream of selling any to me.")
            npc<Sad>("Seriously, you'll be a monkey's uncle before you'll ever hold a dragon scimitar.")
            if (player.quest("monkey_madness") == "completed") {
                player<Uncertain>("Hmmm, funny you should say that...")
            } else {
                player<Quiz>("Oh well, thanks anyway.")
            }
            npc<Quiz>("Perhaps you'd like to take a look at my stock?")
            takeALook()
        }
        option<Quiz>("What do you think of Ali Morrisane?") {
            npc<Quiz>("He is a dangerous man.")
            npc<Quiz>("Although he does not appear to be dangerous, he has brought several men to this town who have threatened me and several others.")
            npc<Shock>("One man even threatened me with a hammer, saying that when he set up his smithy, my shoddy workmanship would be revealed!")
            player<Quiz>("What will you do about these threats?")
            npc<Talk>("Oh, I am quite confident in the quality of my work...as will you be if you take a look at my wares.")
            takeALook()
        }
    }
}

suspend fun SuspendableContext<Player>.takeALook() {
    choice {
        option("Yes please, Zeke.", block = { player.openShop("zekes_superior_scimitars") })
        option<Quiz>("Not today, thank you.")
    }
}
