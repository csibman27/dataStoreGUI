package list;

/**
 * 
 */
/**
 * @author csib
 *
 */

public class EShopMVCversion 



{

	private EShopModel engine;

	private ECommGUI gui;



	/**

	 * Create a new EShopModel and show it.

	 */

	public EShopMVCversion()

	{

		engine = new EShopModel();

		gui = new ECommGUI(engine);

	}



	/**

	 * In case the window was closed, show it again.

	 */

	public void show()

	{

		gui.setVisible(true);

	}



	public static void main(String[] args)

	{

		EShopMVCversion eShop;



		eShop = new EShopMVCversion();

		while(true);

	}

}
