package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.consts.Consts;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class CustomArrowBase {
    public Arrow arrow;

    public Player belongPlayer;
    public Integer interval = Consts.DEFAULT_ARROW_INTERVAL;

    public Boolean handle() {
        return false;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
