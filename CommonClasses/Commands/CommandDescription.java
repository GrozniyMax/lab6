package CommonClasses.Commands;

import java.io.Serializable;
import java.util.function.Function;

public record CommandDescription (String comandName, UserParams userParams, Function<String,? extends Object> argumentParseFunction) implements Serializable {

    public CommandDescription(String comandName, UserParams userParams, Function<String,? extends Object> argumentParseFunction) {
        this.comandName = comandName;
        this.userParams = userParams;
        this.argumentParseFunction = argumentParseFunction;
    }

    public CommandDescription(String comandName, UserParams userParams) {
        this(comandName, userParams, null);
    }
}
