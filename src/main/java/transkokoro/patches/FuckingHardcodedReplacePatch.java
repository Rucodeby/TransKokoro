package transkokoro.patches;

import Kokoro.cards.EncoreComponent;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import transkokoro.TransKokoroMod;

public class FuckingHardcodedReplacePatch {
    public static String[] replaceStr = CardCrawlGame.languagePack.getUIString(TransKokoroMod.makeID("EncoreReplace")).TEXT;

    @SpirePatch(clz = EncoreComponent.class, method = "toggleDescription")
    public static class KiilVeryAwfulHardcode {
        @SpirePrefixPatch
        public static void Insert (EncoreComponent __instance, AbstractCard ___card) {
            if (__instance.isEncore()) {
                ___card.rawDescription = ___card.rawDescription.replace(replaceStr[0], replaceStr[1]);
            } else {
                ___card.rawDescription = ___card.rawDescription.replace(replaceStr[1], replaceStr[0]);
            }
        }
    }
}
