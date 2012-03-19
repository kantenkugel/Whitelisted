/**
 * 
 */
package me.kantenkugel.serveress.whitelisted;

/**
 * @author Kantenkugel
 *
 */
public interface Whitelist {
	
	public String getList();
	
	public String getDenyList();
	
	public boolean isWhitelisted(String player);
	
	public boolean isDenied(String player);
	
	public boolean wlAdd(String player);
	
	public boolean wlRemove(String player);
	
	public boolean dlAdd(String player);
	
	public void close();
	
}
