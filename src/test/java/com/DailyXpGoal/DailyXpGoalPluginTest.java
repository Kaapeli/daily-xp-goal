package com.DailyXpGoal;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DailyXpGoalPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DailyXpGoalPlugin.class);
		RuneLite.main(args);
	}
}