package com.ag.restboot.bean.firstdbcheck;

import java.util.ArrayList;

public class SearchParam {

	private String grid;
	private String WaterType;
	ArrayList<GridSearchParam> gridSearchParamList;
	public String getGrid() {
		return grid;
	}
	public void setGrid(String grid) {
		this.grid = grid;
	}
	public String getWaterType() {
		return WaterType;
	}
	public void setWaterType(String waterType) {
		WaterType = waterType;
	}
	public ArrayList<GridSearchParam> getGridSearchParamList() {
		return gridSearchParamList;
	}
	public void setGridSearchParamList(
			ArrayList<GridSearchParam> gridSearchParamList) {
		this.gridSearchParamList = gridSearchParamList;
	}
	
}
