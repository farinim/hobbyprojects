package com.packagebuilder;

import java.util.ArrayList;
import java.util.List;

public class Package {
	public Package(String pkgName) {
		super();
		this.pkgName = pkgName;
		this.dependencies = new ArrayList<Package>();
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public List<Package> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Package> dependencies) {
		this.dependencies = dependencies;
	}
	private String pkgName;
	private List<Package> dependencies;
	
	
	/*public boolean isParentPkg(Package parentPkg){
		if(pkg.getDependencies().contains(this)){
			return true;
		}
		for (Package p : pkg.getDependencies()){
			p.isParentPkg(this);
		}
		return false;
	}*/
	
	
}
