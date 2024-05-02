package it.tdlight;

/**
 * @author DXH
 * @version 1.0
 * @email dxh.1027@gmail.com
 * @date 2024/5/2 星期四 22:42
 * @descriptor:
 */
public class NativeClientExample {

	public static void main(String[] args) throws Exception{
		Init.init();

		int clientId = NativeClientAccess.create();

		System.out.println(clientId);
	}
}
