package jcource.battleship.gameCore.Exeptions;

public class OutOfBoundsPointArgumentException extends IllegalArgumentException {
    public OutOfBoundsPointArgumentException() {
        super();
    }

    public OutOfBoundsPointArgumentException(String s) {
        super(s);
    }
}
