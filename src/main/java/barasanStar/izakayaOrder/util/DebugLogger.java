package barasanStar.izakayaOrder.util;

public class DebugLogger {
	// デバッグ出力の有無を切り替え可能！
	private static boolean debugMode = true;

	public static void log(Class<?> clazz, String message) {
		if (debugMode) {
			System.out.println("■DEBUG■[" + clazz.getSimpleName() + "]" + message);
		}
	}
}
