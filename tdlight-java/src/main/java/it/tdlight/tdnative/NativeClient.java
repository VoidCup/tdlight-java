package it.tdlight.tdnative;

import it.tdlight.jni.TdApi;

@SuppressWarnings("rawtypes")
public class NativeClient {

	/**
	 * 创建一个本地telegram client;
	 * @return 返回一个clientId
	 */
	protected static native int createNativeClient();

	/**
	 * 该方法的作用是将具体的请求函数对象（TdApi.Function）发送给 TDLib 进行处理
	 * @param nativeClientId 客户端的本机 ID，用于标识特定的客户端实例。
	 * @param eventId        请求的事件 ID，用于标识特定的请求
	 * @param function       要发送的请求函数对象，包含了具体的请求信息
	 */
	protected static native void nativeClientSend(int nativeClientId, long eventId, TdApi.Function function);

	/**
	 * 该方法的作用是从 TDLib 接收异步事件，并将事件数据存储在提供的数组中
	 * @param clientIds 要接收事件的客户端 ID 数组。可以指定一个或多个客户端 ID，以接收与这些客户端关联的事件。如果要接收所有客户端的事件，可以将此参数设置为 null
	 * @param eventIds  要接收的事件 ID 数组。可以指定一个或多个事件 ID，以仅接收与这些事件 ID 相关的事件。如果要接收所有事件，可以将此参数设置为 null
	 * @param events    用于存储接收到的事件数据的 TdApi.Object 数组
	 * @param timeout   接收事件的超时时间（以秒为单位）。如果在超时期限内没有事件到达，该方法将返回
	 * @return          返回接收到的事件数量
	 */
	protected static native int nativeClientReceive(int[] clientIds,
			long[] eventIds,
			TdApi.Object[] events,
			double timeout);

	/**
	 * 用于执行同步请求并返回结果
	 * 该方法的作用是将指定的请求函数对象（TdApi.Function）发送给 TDLib 进行处理，并等待执行结果
	 * @param function 要执行的请求函数对象，包含了具体的请求信息
	 * @return   返回执行结果作为 TdApi.Object 对象
	 */
	protected static native TdApi.Object nativeClientExecute(TdApi.Function function);

	/**
	 * 用于设置日志消息处理函数
	 * 该方法的作用是设置一个回调函数，用于处理 TDLib 中生成的日志消息。当 TDLib 生成日志消息时，将调用指定的回调函数进行处理
	 * @param maxVerbosityLevel  日志消息的最大详细程度级别。只有具有不大于此级别的日志消息才会传递给回调函数进行处理
	 * @param logMessageHandler 日志消息处理函数的指针。该函数接受两个参数：int 类型的 verbosityLevel 表示日志消息的详细程度级别，const char* 类型的 logMessage 表示日志消息的文本内容
	 */
	protected static native void nativeClientSetLogMessageHandler(int maxVerbosityLevel, LogMessageHandler logMessageHandler);

	/**
	 * Interface for handler of messages that are added to the internal TDLib log.
	 */
	public interface LogMessageHandler {
		/**
		 * Callback called on messages that are added to the internal TDLib log.
		 *
		 * @param verbosityLevel Log verbosity level with which the message was added from -1 up to 1024.
		 *                       If 0, then TDLib will crash as soon as the callback returns.
		 *                       None of the TDLib methods can be called from the callback.
		 * @param message        The message added to the internal TDLib log.
		 */
		void onLogMessage(int verbosityLevel, String message);
	}
}
