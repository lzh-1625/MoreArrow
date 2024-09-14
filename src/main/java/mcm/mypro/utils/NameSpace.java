package mcm.mypro.utils;

import mcm.mypro.MoreBow;
import mcm.mypro.consts.Consts;
import org.bukkit.NamespacedKey;

public class NameSpace {
    public static NamespacedKey customBow = new NamespacedKey(MoreBow.plugin(), Consts.CUSTOM_BOW);
    public static NamespacedKey traceTarget = new NamespacedKey(MoreBow.plugin(), Consts.TRACE_TARGET);
}
