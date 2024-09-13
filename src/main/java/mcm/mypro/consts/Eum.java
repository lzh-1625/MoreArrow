package mcm.mypro.consts;

public class Eum {

    public enum CustomArrowType {
        TRACE("TRACE"),TRACE_LIGHTNING("TRACE_LIGHTNING"),TRACE_FIRE("TRACE_FIRE")
        ;

        public final String value;

        private CustomArrowType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
