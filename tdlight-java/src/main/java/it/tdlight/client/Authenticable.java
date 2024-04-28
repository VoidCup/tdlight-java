package it.tdlight.client;

import java.util.function.Consumer;

/**
 * 可验证对象
 */
public interface Authenticable {

	AuthenticationSupplier<?> getAuthenticationSupplier();
}
