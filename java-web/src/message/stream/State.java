package message.stream;

import java.util.Set;
import java.util.UUID;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import message.SessionInfo;

/**
 * 一次stream传输状态
 * stream包含多個會話
 * @author SEELE
 *
 */
public class State {
	public final UUID streamId;
	public final String desc;
	public final Set<SessionInfo> setSession;
	
	public State(UUID streamId, String desc, Set<SessionInfo> setSession) {
		this.streamId = streamId;
		this.desc = desc;
		this.setSession = setSession;
	}
	
	public boolean isFailed() {
		 return Iterables.any(setSession, new Predicate<SessionInfo>()
	        {
	            public boolean apply(SessionInfo session)
	            {
	                return session.isFailed();
	            }
	        });
	}
	
}
