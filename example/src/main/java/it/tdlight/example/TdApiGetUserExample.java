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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author v_xinxhdeng
 * @date 2024/4/28 15:32
 */
public class TdApiGetUserExample {

	public static void main(String[] args) throws Exception {
		//创建aminId
		long adminId = Integer.getInteger("it.tdlight.example.adminid", 667900586);

		// Initialize TDLight native libraries
		Init.init();

		// Set the log level
		Log.setLogMessageHandler(1, new Slf4JLogMessageHandler());

		// Create the client factory (You can create more than one client,
		// BUT only a single instance of ClientFactory is allowed globally!
		// You must reuse it if you want to create more than one client!)
		try (SimpleTelegramClientFactory clientFactory = new SimpleTelegramClientFactory()) {
			// Obtain the API token
			//
			// var apiToken = new APIToken(your-api-id-here, "your-api-hash-here");
			//
			APIToken apiToken = APIToken.example();


			// Configure the client
			TDLibSettings settings = TDLibSettings.create(apiToken);

			// Configure the session directory.
			// After you authenticate into a session, the authentication will be skipped from the next restart!
			// If you want to ensure to match the authentication supplier user/bot with your session user/bot,
			//   you can name your session directory after your user id, for example: "tdlib-session-id12345"
			Path sessionPath = Paths.get("example-tdlight-session");
			settings.setDatabaseDirectoryPath(sessionPath.resolve("data"));
			settings.setDownloadedFilesDirectoryPath(sessionPath.resolve("downloads"));

			// Prepare a new client builder
			SimpleTelegramClientBuilder clientBuilder = clientFactory.builder(settings);

			// Configure the authentication info
			// Replace with AuthenticationSupplier.consoleLogin(), or .user(xxx), or .bot(xxx);
			SimpleAuthenticationSupplier<?> authenticationData = AuthenticationSupplier.testUser(7381);
			// This is an example, remove this line to use the real telegram datacenters!
			settings.setUseTestDatacenter(true);

			// Create and start the client
			try (var app = new ExampleApp(clientBuilder, authenticationData, adminId)) {
				// Get me
				TdApi.User me = app.getClient().getMeAsync().get(1, TimeUnit.MINUTES);

				// Create the "saved messages" chat
				System.out.println(me.firstName);
			}
		}
	}
}
