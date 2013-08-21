package abstractgame;

import java.util.EventObject;

public class GameEvent extends EventObject {
    private static final long serialVersionUID = 8955175337453575586L;
    String reason;

	public GameEvent(Object source, String reason) {
		super(source);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}
