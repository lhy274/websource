package controller;

import action.Action;
import action.ShopInsertAction;

public class ShopActionFactory {
	private static ShopActionFactory factory;
	private ShopActionFactory() {}
	
	public static ShopActionFactory getInstance() {
		if(factory==null) {
			factory = new ShopActionFactory();			
		}
		return factory;
	}
	
	public Action action(String cmd) {
		Action action = null;
		
		if(cmd.equals("/insert.do")) {
			action = new ShopInsertAction("");		
		}
		
		return action;
	}
		
	
	
	
	
	
	
	
	
	
}
