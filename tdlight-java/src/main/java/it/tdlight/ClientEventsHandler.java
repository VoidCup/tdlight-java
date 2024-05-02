package it.tdlight;

import it.tdlight.jni.TdApi;
import it.tdlight.jni.TdApi.Object;

/**
 * 客户端事件处理器
 * {@linkplain NativeResponseReceiver} 类为单独线程启动run方法循环调用 {@linkplain NativeClientAccess#receive(int[], long[], Object[], double)}
 * 方法获取一个从tdlib返回的int类型的值;
 * 然后从调用{@linkplain ClientFactoryImpl#handleClientEvents(int, boolean, long[], Object[], int, int)}方法
 * 再通过从{@linkplain InternalClientsState}中获取{@linkplain ClientEventsHandler}的实现类
 * 再调用{@linkplain ClientEventsHandler#handleEvents(boolean, long[], Object[], int, int)}的方法
 */
public interface ClientEventsHandler {

	int getClientId();

	void handleEvents(boolean isClosed, long[] eventIds, TdApi.Object[] events, int arrayOffset, int arrayLength);
}
