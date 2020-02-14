package jp.evosystem.strawberryMeasure.mains;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * Webカメラ画像から画像内の物体の大きさを測定.
 *
 * @author cyrus
 */
public class WebCameraObjectSize extends ObjectSize {

	/**
	 * main.
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Webカメラから映像取得
		FrameGrabber grabber = FrameGrabber.createDefault(0);
		grabber.start();

		// コンバーターを作成
		OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

		// 画面を作成
		CanvasFrame canvasFrame = new CanvasFrame("タイトル", CanvasFrame.getDefaultGamma() / grabber.getGamma());

		// 取得した映像データ
		Mat grabbedImage;

		// 画面が表示中の間ループ
		while (canvasFrame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
			// 画像処理
			processTargetImage(grabbedImage);

			// フレームを作成
			Frame frame = converter.convert(grabbedImage);

			// フレームを表示
			canvasFrame.showImage(frame);
		}

		// 画面を閉じる
		canvasFrame.dispose();

		// 映像取得を停止
		grabber.stop();
	}
}