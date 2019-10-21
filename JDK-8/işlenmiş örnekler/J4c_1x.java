// J4c_1x.java: CookieAccessor (ÇerezEriþimi) alt-örneði.

import java.net.URL;
import java.net.URLConnection;
import java.net.CookieManager;
import java.net.CookieHandler;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.CookiePolicy;

import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class J4c_1x {
    public void hatýrlatýcýyaEriþim() {
        System.out.println("---- Access cookies using CookieHandler ---");
        getCookieUsingCookieHandler();
        setCookieUsingCookieHandler();

        System.out.println("--- Access cookies using URLConnection headers; the old way ---");
        getCookieFromURLConn();
        setCookieInURLConn();
    } // hatýrlatýcýyaEriþim() metodu sonu...
    
    /**
     * Get cookies for a url from cookie store
     */
    public void getCookieUsingCookieHandler() { 
        try {       
            // instantiate CookieManager; make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // get content from URLConnection; cookies are set by web site
            URL url = new URL("http://host.example.com");
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying CookieStore
            CookieStore cookieJar = manager.getCookieStore();
            List <HttpCookie> cookies = cookieJar.getCookies();
            for (HttpCookie cookie: cookies) {
              System.out.println("CookieHandler retrieved cookie: " + cookie);
            }
        } catch(Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
    } 
    
    /**
     * Set cookie in cookie store
     */
    public void setCookieUsingCookieHandler() {
        try {
            // instantiate CookieManager
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();

            // create cookie
            HttpCookie cookie = new HttpCookie("UserName", "John Doe");

            // add cookie to CookieStore for a particular URL
            URL url = new URL("http://host.example.com");
            cookieJar.add(url.toURI(), cookie);
            System.out.println("Added cookie using cookie handler");
        } catch(Exception e) {
            System.out.println("Unable to set cookie using CookieHandler");
            e.printStackTrace();
        }
    }

 /**
   * Retrieves cookie from header fields in URL connection.
   * Another way to get cookies before recent changes to CookieManager
   */
    public void getCookieFromURLConn() {
        try {
            URL url = new URL("http://host.example.com");            
            URLConnection conn = url.openConnection();
            Map<String, List<String>> headers = conn.getHeaderFields(); 
            // the literal "Set-Cookie" may be capitalized differently in different web sites
            List<String> values = headers.get("Set-cookie");  

            if (values != null) {
                String cookieValue = null; 
                for (Iterator iter = values.iterator(); iter.hasNext(); ) {
                    cookieValue = (String)iter.next(); 
                    System.out.println("Cookie value from URL connection: " + cookieValue);     
                }
            } else {
                System.out.println("No cookies found");
            }
        } catch (Exception e) {
            System.out.println("Unable to get cookie from URL connection");
            e.printStackTrace();
        }
    }
    
    /**
     * Sets a custom cookie in request header
     * Another way to set cookies before recent changes to CookieManager
     */
    public void setCookieInURLConn() {
        try {
            URL url = new URL( "http://host.example.com/" );
            URLConnection conn = url.openConnection(); 
            conn.setRequestProperty("Cookie", "UserName=John Doe"); 
            System.out.println("Set cookie in URL connection");            
        } catch(Exception e) {
            System.out.println("Unable to set cookie in URL connection");
            e.printStackTrace();
        }        
    }  
} // J4c_1x sýnýfý sonu...