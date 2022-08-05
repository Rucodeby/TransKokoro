package transkokoro;

import Kokoro.KokoroMod;
import basemod.BaseMod;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class TransKokoroMod implements EditStringsSubscriber, EditKeywordsSubscriber {
    public static final Logger logger = LogManager.getLogger(TransKokoroMod.class.getName());
    private static String modID;

    private static final String MODNAME = "Noh Dancer Translation";
    private static final String AUTHOR = "Rucodeby";
    private static final String DESCRIPTION = "A mod adding Russian translation for Noh Dancer mod.";

    public TransKokoroMod() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        logger.info("Done subscribing");

        setModID("transkokoro");
    }

    public static void setModID(String ID) {
        modID = ID;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        logger.info("=========================== Initializing The Servant ============================");

        @SuppressWarnings("unused")
        TransKokoroMod blackruse = new TransKokoroMod();

        logger.info("=========================== /The Servant Initialized/ ===========================");
    }

    private String getLang() {
        switch(Settings.language) {
            case ZHS:
                return "zhs";
            case DEU:
                return "zht";
            case ENG:
                return "eng";
            default:
                return "rus";
        }
    }

    @Override
    public void receiveEditStrings() {
        String lang = getLang();
        String resoucesPath = "TransKokoroResources/localization/";
        if (lang == "rus") {
            BaseMod.loadCustomStringsFile(CardStrings.class, resoucesPath + lang + "/CardStrings.json");
            BaseMod.loadCustomStringsFile(CharacterStrings.class, resoucesPath + lang + "/CharacterStrings.json");
            BaseMod.loadCustomStringsFile(PotionStrings.class, resoucesPath + lang + "/PotionStrings.json");
            BaseMod.loadCustomStringsFile(PowerStrings.class, resoucesPath + lang + "/PowerStrings.json");
            BaseMod.loadCustomStringsFile(RelicStrings.class, resoucesPath + lang + "/RelicStrings.json");
            BaseMod.loadCustomStringsFile(StanceStrings.class, resoucesPath + lang + "/StanceStrings.json");
        }
        BaseMod.loadCustomStringsFile(UIStrings.class, resoucesPath + lang + "/UIStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("Setting up custom keywords");
        Gson gson = new Gson();
        String json = Gdx.files.internal("TransKokoroResources/localization/rus/KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(KokoroMod.getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        logger.info("Done setting up custom keywords");
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
