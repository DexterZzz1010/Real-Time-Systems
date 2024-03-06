<?xml version="1.0" encoding="ISO-8859-1"?>
<helpset version="1.0">
	<title>JGrafchart - Help</title>
	
	<maps>
		<homeID>top</homeID>
		<mapref location="Map.jhm"/>
	</maps>

	<view>
		<name>TOC</name>
		<label>Table Of Contents</label>
		<type>javax.help.TOCView</type>
		<data>IdeHelpTOC.xml</data>
	</view>
	
	<view>
		<name>Index</name>
		<label>Index</label>
		<type>javax.help.IndexView</type>
		<data>IdeHelpIndex.xml</data>
	</view>
	
	<view>
		<name>Search</name>
		<label>Search</label>
		<type>javax.help.SearchView</type>
		<data engine="com.sun.java.help.search.DefaultSearchEngine">
			JavaHelpSearch
		</data>
	</view>

	<view>
		<name>favorites</name>
		<label>Favorites</label>
		<type>javax.help.FavoritesView</type>
	</view>
	
	<presentation default="true">
		<name>main window</name>
		<size width="1150" height="1000"/>
		<location x="100" y="20"/>
		<title>JGrafchart - Help</title>
		<toolbar>
			<helpaction>javax.help.BackAction</helpaction>
			<helpaction>javax.help.ForwardAction</helpaction>
			
			<helpaction>javax.help.SeparatorAction</helpaction>
			
			<helpaction>javax.help.FavoritesAction</helpaction>

			<helpaction>javax.help.SeparatorAction</helpaction>
			
			<helpaction>javax.help.PrintAction</helpaction>
			<helpaction>javax.help.PrintSetupAction</helpaction>
		</toolbar>
	</presentation>
</helpset>

