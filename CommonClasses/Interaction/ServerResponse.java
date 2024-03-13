package CommonClasses.Interaction;

import java.io.Serializable;
import java.util.List;

public record ServerResponse(boolean status,
                             List<String> output,
                             Throwable exception) implements Serializable {

    public ServerResponse(List<String> output) {
        this(true, output, null);
    }

    public ServerResponse(boolean status) {
        this(status, null, null);
    }

    public ServerResponse(Throwable exception) {
        this(false, null, exception);
    }
}
