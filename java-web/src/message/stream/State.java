package message.stream;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import message.SessionInfo;

import java.util.Set;
import java.util.UUID;

/**
 * һ��stream����״̬
 * stream����������Ԓ
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
