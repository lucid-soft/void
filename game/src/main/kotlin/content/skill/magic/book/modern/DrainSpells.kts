package content.skill.magic.book.modern

import content.entity.combat.characterCombatPrepare
import content.skill.magic.spell.Spell
import content.skill.magic.spell.spell
import world.gregs.voidps.engine.data.definition.SpellDefinitions
import world.gregs.voidps.engine.inject

val spellDefinitions: SpellDefinitions by inject()

characterCombatPrepare("magic") { character ->
    val definition = spellDefinitions.get(character.spell)
    if (definition.contains("drain_skill") && !Spell.canDrain(target, definition)) {
        cancel()
    }
}
