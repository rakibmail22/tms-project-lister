package com.tms.project;

public enum View {
	SETUP("setup.html"),
	PROJECT_LIST("project-list.html");

	private final String pageName;

	View(String pageName) {
		this.pageName = pageName;
	}

	public String getPageName() {
		return pageName;
	}

	public boolean isSetup() {
		return SETUP == this;
	}

	public boolean isProjectList() {
		return PROJECT_LIST == this;
	}
}
