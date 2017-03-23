package message.stream;

import com.google.common.util.concurrent.FutureFallback;

public interface EventHandler extends FutureFallback<State> {
	
	void handleStreamEvent(Event event);

}
