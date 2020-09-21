package net.WAC.wurmunlimited.mods.creatures;

import com.wurmonline.server.creatures.CreatureTemplateIds;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreaturesMod implements WurmServerMod, Configurable, ItemTemplatesCreatedListener, ServerStartedListener {
    public static Logger logger = Logger.getLogger(CreaturesMod.class.getName());

    public static void logException(String msg, Throwable e) {
        if (logger != null)
            logger.log(Level.SEVERE, msg, e);
    }

    public static void logWarning(String msg) {
        if (logger != null)
            logger.log(Level.WARNING, msg);
    }

    public static void logInfo(String msg) {
        if (logger != null)
            logger.log(Level.INFO, msg);
    }

    public String getVersion() {
        return "v1.0";
    }

    @Override
    public void configure(Properties properties) {
    }

    @Override
    public void init() {
        ClassPool classPool = HookManager.getInstance().getClassPool();
        CtClass ctCreature = null;
        try {
            ctCreature = classPool.get("com.wurmonline.server.creatures.Creature");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        CtMethod ctDoNew = null;
        try {
            assert ctCreature != null;
            ctDoNew = ctCreature.getMethod("doNew", "(IZFFFILjava/lang/String;BBBZB)Lcom/wurmonline/server/creatures/Creature;");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        // Modify new creatures
        try {
            assert ctDoNew != null;
            ctDoNew.instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("sendToWorld")) {
                        m.replace("$_ = $proceed($$);"
                                + "net.WAC.wurmunlimited.mods.creatures.CustomCreatures.modifyNewCreature($1);");
                    }
                }
            });
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

        try {
            ctCreature.getDeclaredMethod("die").instrument(new ExprEditor(){
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("setRotation")) {
                        m.replace("$_ = $proceed($$);"
                                + "net.WAC.wurmunlimited.mods.creatures.CustomCreatures.checkLootTable(this, corpse);");
                    }
                }
            });
        } catch (CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemTemplatesCreated() {
        try {
            CustomCreatures.registerCustomCreatures();
            CustomCreatures.spawnTable();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServerStarted() {
        // Set leaderTemplateId
        CustomCreatures.setLeaderTemplateId(CreatureTemplateIds.WOLF_BLACK_CID, CustomCreatures.wolfPackmasterId);
    }

}
