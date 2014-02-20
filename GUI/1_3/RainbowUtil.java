import java.awt.*;
import java.awt.geom.*;

/**
 * ���F�̃O���f�[�V�����Ɋւ���ݒ���s���N���X�ł�
 */
public class RainbowUtil {

	// �F�̒�`
	private static Color RED = new Color(255, 0, 0);
	private static Color ORANGE = new Color(255, 128, 0);
	private static Color YELLOW = new Color(255, 255, 0);
	private static Color YELLOW_GREEN = new Color(128, 255, 0);
	private static Color GREEN = new Color(0, 255, 0);
	private static Color LIGHT_YELLOW_GREEN = new Color(0, 255, 128);
	private static Color CYAN = new Color(0, 255, 255);
	private static Color LIGHT_BLUE = new Color(0, 128, 255);
	private static Color BLUE = new Color(0, 0, 255);
	private static Color INDIGO = new Color(0, 0, 204);

	/**
	 * ���F�̐F�����ꗗ
	 */
	public static final Color[] COLORS = { RED, ORANGE, YELLOW, YELLOW_GREEN, GREEN,
			LIGHT_YELLOW_GREEN, CYAN, LIGHT_BLUE, BLUE, INDIGO };

	/**
	 * ���F�O���f�[�V�����̋��E�ꗗ
	 */
	public static final float[] DIST = { 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f };

	/**
	 * ���F�O���f�[�V���������Ȃɂ��邽�߂̐ݒ���擾���܂�
	 * @return ���ȃO���f�[�V������ݒ肷�邽�߂̍��W�ϊ��f�[�^
	 */
	public static AffineTransform getAffineTransform() {
		AffineTransform af = new AffineTransform();
		af.setToRotation(Math.PI / 4);
		return af;
	}
}
