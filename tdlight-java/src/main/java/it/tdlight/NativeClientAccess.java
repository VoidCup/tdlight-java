package it.tdlight;

import com.google.common.base.Joiner;
import it.tdlight.jni.TdApi;
import it.tdlight.jni.TdApi.Function;
import it.tdlight.tdnative.NativeClient;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class NativeClientAccess extends NativeClient {

	private static final Logger LOG = LoggerFactory.getLogger(NativeClientAccess.class);

	static int create() {
		return NativeClientAccess.createNativeClient();
	}

	public static <R extends TdApi.Object> TdApi.Object execute(Function<R> function) {
		return NativeClientAccess.nativeClientExecute(function);
	}

	static <R extends TdApi.Object> void send(int nativeClientId, long eventId, TdApi.Function<R> function) {
		NativeClientAccess.nativeClientSend(nativeClientId, eventId, function);
	}

	static int receive(int[] clientIds, long[] eventIds, TdApi.Object[] events, double timeout) {
		int receive = NativeClientAccess.nativeClientReceive(clientIds, eventIds, events, timeout);
		if(LOG.isDebugEnabled()){
			Joiner on = Joiner.on(",");
			String cidStr = clientIds == null ? "" : on.join(Arrays.stream(clientIds).boxed().collect(Collectors.toSet()));
			String eidStr = eventIds == null ? "" : on.join(Arrays.stream(eventIds).boxed().collect(Collectors.toSet()));
			String eStr = events == null ? "" : on.join(Arrays.stream(events).filter(Objects::nonNull).map(t -> t.getClass().getSimpleName()).collect(Collectors.toSet()));
			LOG.debug("receive: receiveId = {}\n, clientIds = {}\n,eventIds = {}\n,events = {}",receive,cidStr,eidStr,eStr);
		}
		return receive;
	}

	public static void setLogMessageHandler(int maxVerbosityLevel, LogMessageHandler logMessageHandler) {
		NativeClientAccess.nativeClientSetLogMessageHandler(maxVerbosityLevel, logMessageHandler);
	}
}
