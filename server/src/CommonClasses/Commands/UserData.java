package CommonClasses.Commands;

import CommonClasses.Entities.Flat;

import java.io.Serializable;

public record UserData(Object fuctionArgument, Flat readObject) implements Serializable {
    public UserData(String fuctionArgument) {
        this(fuctionArgument, null);
    }

    public UserData(Flat readObject) {
        this(null, readObject);
    }

    public UserData(Object fuctionArgument, Flat readObject) {
        this.fuctionArgument = fuctionArgument;
        this.readObject = readObject;
    }
}
