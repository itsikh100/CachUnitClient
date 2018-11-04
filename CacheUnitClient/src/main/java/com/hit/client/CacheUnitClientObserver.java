package com.hit.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.hit.view.CacheUnitView;

public class CacheUnitClientObserver implements PropertyChangeListener {
	private CacheUnitClient cacheUnitClient;
	private String Request;
	private String messegeFromServer;


	public CacheUnitClientObserver() {
		cacheUnitClient = new CacheUnitClient();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		try{
			if (evt.getNewValue().equals("showStatistics")) {
				Request = "{\"headers\":{\"action\":\"STATISTICS\"},\"body\":[]}\n";

				messegeFromServer = cacheUnitClient.send(Request);
				System.out.println(messegeFromServer);
				((CacheUnitView) evt.getSource()).updateUIData(messegeFromServer);

			}

			else {
				Request =  (String) evt.getNewValue();
				messegeFromServer = cacheUnitClient.send(Request);
				System.out.println("messege from server:\n" + messegeFromServer);

				if (messegeFromServer.equals("true"))
					((CacheUnitView) evt.getSource()).updateUIData("<html><font color='green'>" + "Success" + "</font></html>");
				else
					if (messegeFromServer.equals("false"))
						((CacheUnitView) evt.getSource()).updateUIData("<html><font color='red'>" + "Failed" + "</font></html>");

					else 
						((CacheUnitView) evt.getSource()).updateUIData(messegeFromServer);

			}
		} catch (Exception e) {
			((CacheUnitView) evt.getSource()).updateUIData("There is no Server Connection");
		}
	}

}
