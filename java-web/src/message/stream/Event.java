package message.stream;

import java.util.UUID;

public class Event {
	public static enum Type {
		PREPARED,
		PROGRESS,
		COMPLETE,
	}
	
	public final Type eventType;
	public final UUID streamId;
	
	protected Event(Type eventType, UUID streamId) {
		this.eventType = eventType;
		this.streamId = streamId;
	}
}

