/**
 * 
 */
package balls.view.gameplay;

import javax.swing.ImageIcon;

/**
 * @author Przemysław Piórkowski
 * 
 */
final class ImageLoader
{
	private final ImageIcon undo;
	private final ImageIcon redo;
	private final ImageIcon reload;

	ImageLoader()
	{
		undo = new ImageIcon(getClass().getResource("/images/undo.png"));
		redo = new ImageIcon(getClass().getResource("/images/redo.png"));
		reload = new ImageIcon(getClass().getResource("/images/reload.png"));
	}

	/**
	 * @return the undo
	 */
	final ImageIcon getUndo()
	{
		return undo;
	}

	/**
	 * @return the redo
	 */
	final ImageIcon getRedo()
	{
		return redo;
	}

	/**
	 * @return the reload
	 */
	final ImageIcon getReload()
	{
		return reload;
	}
}
