package it.tdlight.example;

import it.tdlight.Init;
import it.tdlight.Log;
import it.tdlight.Slf4JLogMessageHandler;
import it.tdlight.client.APIToken;
import it.tdlight.client.AuthenticationSupplier;
import it.tdlight.client.SimpleAuthenticationSupplier;
import it.tdlight.client.SimpleTelegramClientBuilder;
import it.tdlight.client.SimpleTelegramClientFactory;
import it.tdlight.client.TDLibSettings;
import it.tdlight.example.Example.ExampleApp;
import it.tdlight.jni.TdApi;
import it.tdlight.jni.TdApi.CreatePrivateChat;
import it.tdlight.jni.TdApi.FormattedText;
import it.tdlight.jni.TdApi.InputMessageText;
import it.tdlight.jni.TdApi.Message;
import it.tdlight.jni.TdApi.SendMessage;
import it.tdlight.jni.TdApi.TextEntity;
import it.tdlight.netio.NetProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/4/29 星期一 22:49
 * @descriptor:
 */
public class Example2 {

	public static final Logger log = LoggerFactory.getLogger(Example2.class);

	public static void main(String[] args) throws Exception{
		long adminId = Integer.getInteger("it.tdlight.example.adminid", 667900586);

		Init.init();

		Log.setLogMessageHandler(1, new Slf4JLogMessageHandler());

		try(SimpleTelegramClientFactory factory = new SimpleTelegramClientFactory()){
			APIToken apiToken = new APIToken(26364691, "220294fb72f2dedc92900b35fc8d7c92");

			TDLibSettings settings = TDLibSettings.create(apiToken);

			Path sessionPath = Paths.get("example-tdlight-session");
			settings.setDatabaseDirectoryPath(sessionPath.resolve("data"));
			settings.setDownloadedFilesDirectoryPath(sessionPath.resolve("downloads"));
			settings.setProxy(NetProxy.localSocket5Proxy());
			// Prepare a new client builder
			SimpleTelegramClientBuilder clientBuilder = factory.builder(settings);
			SimpleAuthenticationSupplier<?> authenticationData = AuthenticationSupplier.user("8615717022782");
			// Create and start the client
			try (var app = new ExampleApp(clientBuilder, authenticationData, adminId)) {
				// Get me
				TdApi.User me = app.getClient().getMeAsync().get(1, TimeUnit.MINUTES);
				TimeUnit.SECONDS.sleep(30);
				app.getClient().sendClose();
			}
		}
	}
}
