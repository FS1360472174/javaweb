package message;

import java.net.InetAddress;

/**
 * 
 * ��Ԓ��Ϣ
 * @author SEELE
 *
 */
public final class SessionInfo {
	public final InetAddress peer;
	private State state = State.INITIALIZED;
	public SessionInfo(InetAddress peer) {
		this.peer = peer;
	}
	
	public static enum State
    {
        INITIALIZED,
        PREPARING,
        STREAMING,
        WAIT_COMPLETE,
        COMPLETE,
        FAILED,
    }
	
	public boolean isFailed(){
		return state == State.FAILED;
	}
}
