package mcm.mypro.arrow;

import mcm.mypro.global.ArrowData;

import org.bukkit.scheduler.BukkitRunnable;

public class ArrowActionHandler extends BukkitRunnable {
    @Override
    public void run() {
        if (ArrowData.tagArrowList.isEmpty()) {
            return;
        }
        ArrowData.tagArrowList.removeIf(e -> e != null && e.handle());

    }

}
