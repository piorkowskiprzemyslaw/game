/**
 * 
 */
package balls.view.hello;

import javax.swing.ImageIcon;

/**
 * @author Przemysław Piórkowski
 * 
 */
final class ImageLoader
{
	private final ImageIcon icon;

	ImageLoader()
	{
		icon = new ImageIcon(getClass().getResource("/images/main_window.gif"));
	}

	/**
	 * @return the icon
	 */
	final ImageIcon getIcon()
	{
		return icon;
	}
}
