package mcm.mypro.utils;

import mcm.mypro.Mypro;
import mcm.mypro.consts.Consts;
import org.bukkit.NamespacedKey;

public class NameSpace {
    public static NamespacedKey customBow = new NamespacedKey(Mypro.plugin(), Consts.CUSTOM_BOW);
    public static NamespacedKey traceTarget = new NamespacedKey(Mypro.plugin(), Consts.TRACE_TARGET);
}
