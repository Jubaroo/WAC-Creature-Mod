package net.WAC.wurmunlimited.mods.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.MiscConstants;
import com.wurmonline.server.Server;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.creatures.*;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.server.zones.EncounterType;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.shared.constants.SoundNames;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;

public class CustomCreatures {

    public static int blobId, bloblingId, golemId, golemlingId, wolfPackmasterId, mimicId, treasureChestMimicId;

    public static void registerCustomCreatures() throws NoSuchFieldException, IllegalAccessException {
        createBlobTemplate();
        createBloblingTemplate();
        createGolemTemplate();
        createGolemlingTemplate();
        createWolfPackmasterTemplate();
        createMimicTreasureChestTemplate();
        createMimicTemplate();
    }

    public static void modifyNewCreature(Creature creature) {
        int id = creature.getTemplate().getTemplateId();
        // Wolf Packmaster
        if (id == wolfPackmasterId) {
            spawnWolves(creature);
        }
    }

    private static void createBlobTemplate() {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_OMNIVORE, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_MISSION_OK, CreatureTypes.C_TYPE_MISSION_TRAITOR_OK, CreatureTypes.C_TYPE_UNDEAD};
        final int[] itemsButchered = new int[0];
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.blob")
                .name("Blob")
                .description("A gelatinous mass that was discovered inside of a deep cave long lost. It has multiplied many times since its discovery. It is resistant to magic.")
                .modelName("model.blob")
                .types(types)
                .bodyType(BodyTemplate.TYPE_SPIDER)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 30f)
                .skill(SkillList.BODY_CONTROL, 50f)
                .skill(SkillList.BODY_STAMINA, 40f)
                .skill(SkillList.MIND_LOGICAL, 10f)
                .skill(SkillList.MIND_SPEED, 20f)
                .skill(SkillList.SOUL_STRENGTH, 5f)
                .skill(SkillList.SOUL_DEPTH, 5f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 40f)
                .vision((short) 10)
                .dimension((short) 150, (short) 150, (short) 150)
                .deathSounds(SoundNames.DEATH_OOZE_SND, SoundNames.DEATH_OOZE_SND)
                .hitSounds(SoundNames.HIT_OOZE_SND, SoundNames.HIT_OOZE_SND)
                .naturalArmour(0.24f)
                .fireResistance(99f)
                .damages(3.8f, 0f, 12f, 0f, 0f)
                .speed(0.7f)
                .moveRate(500)
                .itemsButchered(itemsButchered)
                //.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("slime")
                .kickDamString("slime")
                .headbuttDamString("slime")
                .maxAge(100)
                .armourType(ArmourTemplate.ARMOUR_TYPE_LEATHER)
                .baseCombatRating(19f)
                .combatDamageType(Wound.TYPE_ACID)
                .alignment(0f)
                .maxPercentOfCreatures(0.00001f)
                .keepSex(true)
                .denName("goop pit")
                .denMaterial(ItemMaterials.MATERIAL_STONE)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .usesNewAttacks(true)
                .addPrimaryAttack(new AttackAction("spit", AttackIdentifier.BITE, new AttackValues(12f, 0.08F, 1f, 10, 1, Wound.TYPE_ACID, false, 3, 3f)))
                .addSecondaryAttack(new AttackAction("dissolve", AttackIdentifier.STRIKE, new AttackValues(3.8F, 0.03F, 8f, 3, 1, Wound.TYPE_ACID, false, 3, 1f)))
                .build();

        setNoCorpse(blobId);

        blobId = temp.getTemplateId();
    }

    private static void createBloblingTemplate() throws NoSuchFieldException, IllegalAccessException {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_OMNIVORE, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_MISSION_OK, CreatureTypes.C_TYPE_MISSION_TRAITOR_OK, CreatureTypes.C_TYPE_UNDEAD};
        final int[] itemsButchered = new int[0];
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.golemling")
                .name("Blobling")
                .description("A smaller version of the blob that seemed to have separated from a larger creature.")
                .modelName("model.blob")
                .types(types)
                .bodyType(BodyTemplate.TYPE_SPIDER)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 15f)
                .skill(SkillList.BODY_CONTROL, 25f)
                .skill(SkillList.BODY_STAMINA, 20f)
                .skill(SkillList.MIND_LOGICAL, 5f)
                .skill(SkillList.MIND_SPEED, 10f)
                .skill(SkillList.SOUL_STRENGTH, 2.5f)
                .skill(SkillList.SOUL_DEPTH, 2.5f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 20f)
                .vision((short) 10)
                .dimension((short) 150, (short) 150, (short) 150)
                .deathSounds(SoundNames.DEATH_OOZE_SND, SoundNames.DEATH_OOZE_SND)
                .hitSounds(SoundNames.HIT_OOZE_SND, SoundNames.HIT_OOZE_SND)
                .naturalArmour(0.34f)
                .sizeModifier(30, 30, 30)
                .fireResistance(99f)
                .damages(1.9f, 0f, 6f, 0f, 0f)
                .speed(0.4f)
                .moveRate(500)
                .itemsButchered(itemsButchered)
                //.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("slime")
                .kickDamString("slime")
                .headbuttDamString("slime")
                .maxAge(30)
                .armourType(ArmourTemplate.ARMOUR_TYPE_NONE)
                .baseCombatRating(9f)
                .combatDamageType(Wound.TYPE_ACID)
                .alignment(0f)
                .keepSex(true)
                .denName("goop pit")
                .denMaterial(ItemMaterials.MATERIAL_STONE)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .usesNewAttacks(true)
                .addPrimaryAttack(new AttackAction("spit", AttackIdentifier.BITE, new AttackValues(12f, 0.08F, 1f, 10, 1, Wound.TYPE_ACID, false, 3, 3f)))
                .addSecondaryAttack(new AttackAction("dissolve", AttackIdentifier.STRIKE, new AttackValues(3.8F, 0.03F, 8f, 3, 1, Wound.TYPE_ACID, false, 3, 1f)))
                .build();

        ReflectionUtil.setPrivateField(temp, ReflectionUtil.getField(CreatureTemplate.class, "corpsename"), "blob.");

        bloblingId = temp.getTemplateId();
    }

    private static void createGolemTemplate() {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_GLOBAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_REGENERATING, CreatureTypes.C_TYPE_DETECTINVIS, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_OPENDOORS, CreatureTypes.C_TYPE_NOT_MISSION};
        final int[] itemsButchered = new int[0];
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.golemling")
                .name("Golem")
                .description("A large creature made from rock that splits into two upon taking too much damage.")
                .modelName("model.creature.humanoid.lavacreature")
                .types(types)
                .bodyType(BodyTemplate.TYPE_HUMAN)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 60f)
                .skill(SkillList.BODY_CONTROL, 25f)
                .skill(SkillList.BODY_STAMINA, 40f)
                .skill(SkillList.MIND_LOGICAL, 5f)
                .skill(SkillList.MIND_SPEED, 15f)
                .skill(SkillList.SOUL_STRENGTH, 5f)
                .skill(SkillList.SOUL_DEPTH, 5f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 50f)
                .vision((short) 10)
                .dimension((short) 200, (short) 200, (short) 200)
                .deathSounds(SoundNames.DEATH_LIZARD_SND, SoundNames.DEATH_LIZARD_SND)
                .hitSounds(SoundNames.HIT_LIZARD_SND, SoundNames.HIT_LIZARD_SND)
                .naturalArmour(0.3f)
                .damages(9f, 4f, 12f, 5f, 10f)
                .speed(0.3f)
                .moveRate(500)
                .itemsButchered(itemsButchered)
                //.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("smash")
                .kickDamString("stomp")
                .headbuttDamString("slam")
                .maxAge(100)
                .armourType(ArmourTemplate.ARMOUR_TYPE_PLATE)
                .baseCombatRating(40f)
                .combatDamageType(Wound.TYPE_CRUSH)
                .alignment(0f)
                .maxPopulationOfCreatures(75)
                .keepSex(true)
                .denName("rock pile")
                .denMaterial(ItemMaterials.MATERIAL_STONE)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .sizeModifier(120, 120, 120)
                .color(1, 1, 1)
                .build();

        setNoCorpse(golemId);

        golemId = temp.getTemplateId();
    }

    static void createGolemlingTemplate() throws NoSuchFieldException, IllegalAccessException {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_DETECTINVIS, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_OPENDOORS, CreatureTypes.C_TYPE_NOT_MISSION};
        final int[] itemsButchered = new int[]{ItemList.rock};
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.golemling")
                .name("Golemling")
                .description("A piece of a larger creature that is somehow still alive and able to fight on its own, amazing!")
                .modelName("model.creature.humanoid.lavacreature")
                .types(types)
                .bodyType(BodyTemplate.TYPE_HUMAN)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 40f)
                .skill(SkillList.BODY_CONTROL, 20f)
                .skill(SkillList.BODY_STAMINA, 30f)
                .skill(SkillList.MIND_LOGICAL, 5f)
                .skill(SkillList.MIND_SPEED, 15f)
                .skill(SkillList.SOUL_STRENGTH, 5f)
                .skill(SkillList.SOUL_DEPTH, 5f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 35f)
                .vision((short) 10)
                .dimension((short) 200, (short) 200, (short) 200)
                .deathSounds(SoundNames.DEATH_LIZARD_SND, SoundNames.DEATH_LIZARD_SND)
                .hitSounds(SoundNames.HIT_LIZARD_SND, SoundNames.HIT_LIZARD_SND)
                .naturalArmour(0.6f)
                .damages(4.5F, 2f, 6f, 5f, 10f)
                .speed(1f)
                .moveRate(2000)
                .itemsButchered(itemsButchered)
                //.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("smash")
                .kickDamString("stomp")
                .headbuttDamString("slam")
                .maxAge(30)
                .armourType(ArmourTemplate.ARMOUR_TYPE_PLATE)
                .baseCombatRating(25f)
                .combatDamageType(Wound.TYPE_CRUSH)
                .alignment(0f)
                .keepSex(true)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .sizeModifier(60, 60, 60)
                .color(1, 1, 1)
                .build();

        ReflectionUtil.setPrivateField(temp, ReflectionUtil.getField(CreatureTemplate.class, "corpsename"), "lavafiend.");

        golemlingId = temp.getTemplateId();
    }

    private static void createWolfPackmasterTemplate() throws NoSuchFieldException, IllegalAccessException {
        final int[] types = {CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_ANIMAL, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_CARNIVORE, CreatureTypes.C_TYPE_BLACK_OR_WHITE};
        final int[] itemsButchered = new int[]{ItemList.paw, ItemList.pelt, ItemList.tooth, ItemList.eye, ItemList.tail};
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.wolf.packmaster")
                .name("Wolf Packmaster")
                .description("An alpha wolf that is in charge of the pack. Weak alone, but fierce in a pack.")
                .modelName("model.creature.quadraped.wolf.black")
                .types(types)
                .bodyType(BodyTemplate.TYPE_DOG)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 45f)
                .skill(SkillList.BODY_CONTROL, 25f)
                .skill(SkillList.BODY_STAMINA, 30f)
                .skill(SkillList.MIND_LOGICAL, 8f)
                .skill(SkillList.MIND_SPEED, 8f)
                .skill(SkillList.SOUL_STRENGTH, 25f)
                .skill(SkillList.SOUL_DEPTH, 30f)
                .skill(SkillList.SOUL_STRENGTH, 30f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 28f)
                .vision((short) 10)
                .dimension((short) 80, (short) 30, (short) 150)
                .deathSounds(SoundNames.DEATH_DOG_SND, SoundNames.DEATH_DOG_SND)
                .hitSounds(SoundNames.HIT_DOG_SND, SoundNames.HIT_DOG_SND)
                .naturalArmour(0.52f)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .damages(4f, 3f, 6f, 0f, 0f)
                .speed(1.3f)
                .moveRate(500)
                .itemsButchered(itemsButchered)
                .maxHuntDist(10)
                .aggressive(100)
                .meatMaterial(ItemMaterials.MATERIAL_MEAT_CANINE)
                .handDamString("claw")
                .kickDamString("scratch")
                .headbuttDamString("bite")
                .maxAge(90)
                .armourType(ArmourTemplate.ARMOUR_TYPE_CHAIN)
                .baseCombatRating(22f)
                .combatDamageType(Wound.TYPE_PIERCE)
                .alignment(0f)
                .maxPopulationOfCreatures(30)
                .sizeModifier(120, 120, 120)
                .build();

        ReflectionUtil.setPrivateField(temp, ReflectionUtil.getField(CreatureTemplate.class, "corpsename"), "blackwolf.");

        wolfPackmasterId = temp.getTemplateId();
    }

    private static void createMimicTemplate() throws NoSuchFieldException, IllegalAccessException {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_CARNIVORE, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_NOT_MISSION};
        final int[] itemsButchered = new int[0];
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.wolf.packmaster")
                .name("Mimic")
                .description("This crafty blob has adapted very well to hunting food. It lures its prey by making itself look like something that it desires. When the prey moves in to investigate, it attacks!")
                .modelName("model.blob")
                .types(types)
                .bodyType(BodyTemplate.TYPE_BEAR)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 32.0f)
                .skill(SkillList.BODY_CONTROL, 32.0f)
                .skill(SkillList.BODY_STAMINA, 32.0f)
                .skill(SkillList.MIND_LOGICAL, 15.0f)
                .skill(SkillList.MIND_SPEED, 15.0f)
                .skill(SkillList.SOUL_STRENGTH, 30.0f)
                .skill(SkillList.SOUL_DEPTH, 10.0f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 60.0f)
                .vision((short) 10)
                .dimension((short) 200, (short) 200, (short) 200)
                .deathSounds(SoundNames.DEATH_OOZE_SND, SoundNames.DEATH_OOZE_SND)
                .hitSounds(SoundNames.HIT_OOZE_SND, SoundNames.HIT_OOZE_SND)
                .naturalArmour(0.55f)
                .damages(17.0f, 4.0f, 5.0f, 0.0f, 0.0f)
                .speed(1.5f)
                .moveRate(2000)
                .itemsButchered(itemsButchered)
                //.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("slime")
                .kickDamString("slime")
                .headbuttDamString("spit")
                .maxAge(35)
                .armourType(ArmourTemplate.ARMOUR_TYPE_PLATE)
                .baseCombatRating(43.0f)
                .combatDamageType(Wound.TYPE_ACID)
                .alignment(0f)
                .keepSex(true)
                .boundsValues(-0.5f, -1f, 0.5f, 1.42f)
                .build();

        ReflectionUtil.setPrivateField(temp, ReflectionUtil.getField(CreatureTemplate.class, "corpsename"), "horse.butchered.");

        mimicId = temp.getTemplateId();
    }

    private static void createMimicTreasureChestTemplate() {
        final int[] types = {CreatureTypes.C_TYPE_MOVE_GLOBAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_DETECTINVIS, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_NOT_MISSION, CreatureTypes.C_TYPE_ONLYATTACKPLAYERS};
        final int[] itemsButchered = new int[0];
        final CreatureTemplate temp = new CreatureTemplateBuilder("WAC.creature.wolf.packmaster")
                .name("treasure chest")
                .description("A chest inscribed with various runes. Only the gods would know why it appeared here. Something is not quite right though.")
                .modelName("model.blob")
                .types(types)
                .bodyType(BodyTemplate.TYPE_BEAR)
                .defaultSkills()
                .skill(SkillList.BODY_STRENGTH, 30f)
                .skill(SkillList.BODY_CONTROL, 25f)
                .skill(SkillList.BODY_STAMINA, 20f)
                .skill(SkillList.MIND_LOGICAL, 10f)
                .skill(SkillList.MIND_SPEED, 10f)
                .skill(SkillList.SOUL_STRENGTH, 10f)
                .skill(SkillList.SOUL_DEPTH, 10f)
                .skill(SkillList.WEAPONLESS_FIGHTING, 50f)
                .vision((short) 10)
                .dimension((short) 200, (short) 200, (short) 200)
                .deathSounds(SoundNames.CHEST_OPENING, SoundNames.CHEST_OPENING)
                .hitSounds(SoundNames.DESTROYWALLWOOD_AXE_SND, SoundNames.DESTROYWALLWOOD_AXE_SND)
                .naturalArmour(2.5f)
                .damages(5f, 5f, 5f, 8f, 0f)
                .speed(1f)
                .moveRate(50)
                .itemsButchered(itemsButchered)
                .maxHuntDist(10)
                .aggressive(100)
                .handDamString("slam")
                .kickDamString("slam")
                .headbuttDamString("slam")
                .maxAge(100)
                .armourType(ArmourTemplate.ARMOUR_TYPE_PLATE)
                .baseCombatRating(20f)
                .combatDamageType(Wound.TYPE_CRUSH)
                .alignment(0f)
                .keepSex(true)
                .build();

        setNoCorpse(treasureChestMimicId);

        treasureChestMimicId = temp.getTemplateId();
    }

    private static void spawnWolves(Creature creature) {
        try {
            float x = creature.getPosX();
            float y = creature.getPosY();
            if (creature.getAttackers() != 0) {
                CreaturesMod.logInfo(String.format("Spawning x2 %s.", creature.getNameWithoutPrefixes()));
                CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.WOLF_BLACK_CID);
                for (int i = 0; i < 8; i++) {
                    Creature.doNew(template.getTemplateId(), true, x, y, Server.rand.nextFloat() * 360f, creature.getLayer(), template.getName(), Server.rand.nextBoolean() ? MiscConstants.SEX_MALE : MiscConstants.SEX_FEMALE, creature.getKingdomId(), BodyTemplate.TYPE_DOG, false, /*age*/(byte) 20);
                }
                Server.getInstance().broadCastAction("A wolf packmaster and its pack have just appeared near you! It would be wise to avoid it unless prepared to fight.", creature, 20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CreaturesMod.logWarning("Error in Spawning Packmaster Wolves.");
        }
    }

    private static void spawnGolemlings(Creature creature) {
        try {
            float x = creature.getPosX();
            float y = creature.getPosY();
            if (creature.getAttackers() != 0) {
                CreaturesMod.logInfo(String.format("Spawning x2 %s.", creature.getNameWithoutPrefixes()));
                CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(golemlingId);
                Creature.doNew(template.getTemplateId(), true, x + 1, y, Server.rand.nextFloat() * 360f, creature.getLayer(), template.getName(), MiscConstants.SEX_MALE, creature.getKingdomId(), BodyTemplate.TYPE_HUMAN, false, /*age*/(byte) 2);
                Creature.doNew(template.getTemplateId(), true, x, y + 1, Server.rand.nextFloat() * 360f, creature.getLayer(), template.getName(), MiscConstants.SEX_MALE, creature.getKingdomId(), BodyTemplate.TYPE_HUMAN, false, /*age*/(byte) 2);
                Server.getInstance().broadCastAction(String.format("The %s splits into two!", creature.getTemplate().getName()), creature, 20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CreaturesMod.logWarning("Error in Spawning Golemlings.");
        }
    }

    private static void spawnBloblings(Creature creature) {
        try {
            float x = creature.getPosX();
            float y = creature.getPosY();
            if (creature.getAttackers() != 0) {
                CreaturesMod.logInfo(String.format("Spawning x2 %s.", creature.getNameWithoutPrefixes()));
                CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(bloblingId);
                Creature.doNew(template.getTemplateId(), true, x, y + 1, Server.rand.nextFloat() * 360f, creature.getLayer(), creature.getTemplate().getName(), Server.rand.nextBoolean() ? MiscConstants.SEX_MALE : MiscConstants.SEX_FEMALE, creature.getKingdomId(), BodyTemplate.TYPE_HUMAN, false, /*age*/(byte) 2);
                Creature.doNew(template.getTemplateId(), true, x + 1, y, Server.rand.nextFloat() * 360f, creature.getLayer(), creature.getTemplate().getName(), Server.rand.nextBoolean() ? MiscConstants.SEX_MALE : MiscConstants.SEX_FEMALE, creature.getKingdomId(), BodyTemplate.TYPE_HUMAN, false, /*age*/(byte) 2);
                Server.getInstance().broadCastAction(String.format("The %s splits into two!", creature.getTemplate().getName()), creature, 20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CreaturesMod.logWarning("Error in Spawning Bloblings.");
        }
    }

    private static void spawnMimic(Creature creature) {
        try {
            float x = creature.getPosX();
            float y = creature.getPosY();
            if (creature.getAttackers() != 0) {
                CreaturesMod.logInfo(String.format("Spawning a %s.", creature.getNameWithoutPrefixes()));
                CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(mimicId);
                Creature.doNew(template.getTemplateId(), true, x, y, Server.rand.nextFloat() * 360f, creature.getLayer(), template.getName(), Server.rand.nextBoolean() ? MiscConstants.SEX_MALE : MiscConstants.SEX_FEMALE, creature.getKingdomId(), BodyTemplate.TYPE_HUMAN, false, /*age*/(byte) (10 + Server.rand.nextInt(50)));
                Server.getInstance().broadCastAction(String.format("The %s busts apart as you hit it and a Mimic jumps out ready to fight!", creature.getTemplate().getName()), creature, 20);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CreaturesMod.logWarning("Error in Spawning Mimic.");
        }
    }

    public static void checkLootTable(Creature creature, Item corpse) {
        creatureSpawnOnDeath(creature);
    }

    static void creatureSpawnOnDeath(Creature creature) {
        int templateId = creature.getTemplate().getTemplateId();

        if (templateId == golemId) {
            // Spawn Golemlings when a golem dies
            spawnGolemlings(creature);
        }
        if (templateId == blobId) {
            // Spawn Bloblings when a blob dies
            spawnBloblings(creature);
        }
        if (templateId == treasureChestMimicId) {
            // Spawn Mimic when a treasure chest dies
            spawnMimic(creature);
        }
    }

    public static void setNoCorpse(int templateId) {
        try {
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if (template != null) {
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "noCorpse"), true);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void setLeaderTemplateId(int templateId, int value) {
        try {
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if (template != null) {
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "leaderTemplateId"), value);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void spawnTable() {
        new EncounterBuilder(Tiles.Tile.TILE_TREE.id, EncounterType.ELEVATION_GROUND).addCreatures(treasureChestMimicId, 2).build(2);
        new EncounterBuilder(Tiles.Tile.TILE_GRASS.id, EncounterType.ELEVATION_GROUND).addCreatures(treasureChestMimicId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_ROCK.id, EncounterType.ELEVATION_GROUND).addCreatures(golemId, 2).build(2);
        new EncounterBuilder(Tiles.Tile.TILE_MARSH.id, EncounterType.ELEVATION_GROUND).addCreatures(golemId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_CLAY.id, EncounterType.ELEVATION_GROUND).addCreatures(golemId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_TREE.id, EncounterType.ELEVATION_GROUND).addCreatures(blobId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_MOSS.id, EncounterType.ELEVATION_GROUND).addCreatures(blobId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_PEAT.id, EncounterType.ELEVATION_GROUND).addCreatures(blobId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_TAR.id, EncounterType.ELEVATION_GROUND).addCreatures(blobId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_GRASS.id, EncounterType.ELEVATION_GROUND).addCreatures(wolfPackmasterId, 1).build(2);

    }

}
