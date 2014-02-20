import java.util.Calendar;

/**
 * ���t�Ǝ����̎擾���s�����[�e�B���e�B�N���X�ł�
 */
public class ClockUtil {

	/**
	 * ���݂̓��t�𕶎���Ƃ��Ď擾���܂�
	 * @return ���t�̕�����
	 */
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "/" + month + "/" + date;
	}

	/**
	 * ���݂̎����i���j���擾���܂�
	 * @return �����i���j
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ���݂̎����i���j���擾���܂�
	 * @return �����i���j
	 */
	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * ���݂̎����i�b�j���擾���܂�
	 * @return �����i�b�j
	 */
	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}

	/**
	 * ���݂̎����i�~���b�j���擾���܂�
	 * @return �����i�~���b�j
	 */
	public static int getMilliSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MILLISECOND);
	}
}
