package mcm.mypro.arrow.CustomArrowImpl;

import mcm.mypro.consts.Consts;
import org.bukkit.entity.Arrow;

public class CustomArrowBase {
    public Arrow arrow;

    public Integer interval = Consts.DEFAULT_ARROW_INTERVAL;


    public void setInterval(int interval) {
        this.interval = interval;
    }
}
