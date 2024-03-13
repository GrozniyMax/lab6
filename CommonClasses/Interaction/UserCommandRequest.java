package CommonClasses.Interaction;

import java.io.Serializable;
import java.net.InetAddress;

public record UserCommandRequest(InetAddress clientAddress) implements Serializable {
}
