package application;


import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;

public class url  {
	VBox toolbar;

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    
    public url(){
        
        final URL urlGoogleMaps = getClass().getResource("homepage.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        
        toolbar.getChildren().add(webView);
    
    }
}
