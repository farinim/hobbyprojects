package com.packagebuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageBuilder {
	
	private Map<String,Boolean> packages;
	
	public PackageBuilder(){
		packages= new HashMap<String,Boolean>();
	}

	
	/*public void buildPackage(Package pkg){
		//Start point for building : pkg
		//Get dependencies for pkg
		//Add all pkgs to Map, set built boolean to false
		//Loop thru package dependency and build only if it has no more dependency and built is not set to true
		List<Package> dependencies = pkg.getDependencies();
		
		
	}*/
	
	public void build(Package pkg){
		//System.out.println("Main pkg " + pkg.getPkgName());
		for(Package dependency :pkg.getDependencies() ){
			build(dependency);
		}
		//if(pkg.getDependencies().size() == 0){
			if (!packages.containsKey(pkg.getPkgName())) {
				packages.put(pkg.getPkgName(), true);
				System.out.println("Built pkg " + pkg.getPkgName());
			}
		//}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PackageBuilder pkgBuilder = new PackageBuilder();
		
		//Create few package
		Package pkg1 = new Package("pkg1");
		Package pkg2 = new Package("pkg2");
		Package pkg3 = new Package("pkg3");
		Package pkg4 = new Package("pkg4");
		Package pkg5 = new Package("pkg5");
		Package pkg6 = new Package("pkg6");
		
		//Pkg 2 depends on pkg3
		List<Package> dependency = new ArrayList<Package>();
		dependency.add(pkg4);
		dependency.add(pkg5);
		dependency.add(pkg6);
		pkg3.setDependencies(dependency);
		
		//Pkg4 depends on pkg2 and pkg3
		List<Package> dependency2 = new ArrayList<Package>();
		dependency2.add(pkg4);
		dependency2.add(pkg5);
		pkg2.setDependencies(dependency2);
		
		//Pkg1 depends on pkg4
		List<Package> dependency3 = new ArrayList<Package>();
		dependency3.add(pkg2);
		dependency3.add(pkg3);
		pkg1.setDependencies(dependency3);
		
		pkgBuilder.build(pkg1);
		
	}

}
