package it.tdlight.netio;

import it.tdlight.jni.TdApi;
import it.tdlight.jni.TdApi.AddProxy;
import it.tdlight.jni.TdApi.ProxyType;
import it.tdlight.jni.TdApi.ProxyTypeSocks5;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/4/29 星期一 23:15
 * @descriptor:
 */
public class NetProxy {
	private Boolean proxyEnable;
	private String proxyServer;
	private Integer proxyPort;
	private String proxyUser;
	private String password;
	private TdApi.ProxyType proxyType;

	public static NetProxy socket5Proxy(String proxyServer,Integer proxyPort, Boolean proxyEnable){
		return new NetProxy(true,proxyServer,proxyPort,"","",new TdApi.ProxyTypeSocks5());
	}

	public static NetProxy localSocket5Proxy(){
		return new NetProxy(true,"127.0.0.1",1080,"","",new ProxyTypeSocks5());
	}

	public NetProxy(Boolean proxyEnable,
			String proxyServer,
			Integer proxyPort,
			String proxyUser,
			String password,
			ProxyType proxyType) {
		this.proxyEnable = proxyEnable;
		this.proxyServer = proxyServer;
		this.proxyPort = proxyPort;
		this.proxyUser = proxyUser;
		this.password = password;
		this.proxyType = proxyType;
	}

	public TdApi.AddProxy covertAddProxyFunction(){
		return new AddProxy(getProxyServer(),getProxyPort(),getProxyEnable(),getProxyType());
	}

	public Boolean getProxyEnable() {
		return proxyEnable;
	}

	public String getProxyServer() {
		return proxyServer;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public String getPassword() {
		return password;
	}

	public ProxyType getProxyType() {
		return proxyType;
	}
}
