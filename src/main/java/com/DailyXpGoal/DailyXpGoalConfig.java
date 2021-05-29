package com.DailyXpGoal;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface DailyXpGoalConfig extends Config
{
	@ConfigItem(
		keyName = "Attack",
		name = "Attack Goal:",
		description = ""
	) default int Attack() { return 0; }

	@ConfigItem(
			keyName = "Strength",
			name = "Strength Goal:",
			description = ""
	) default int Strength() { return 0; }

	@ConfigItem(
			keyName = "Defence",
			name = "Defence Goal:",
			description = ""
	) default int Defence() { return 0; }

	@ConfigItem(
			keyName = "Ranged",
			name = "Ranged Goal:",
			description = ""
	) default int Ranged() { return 0; }

	@ConfigItem(
			keyName = "Prayer",
			name = "Prayer Goal:",
			description = ""
	) default int Prayer() { return 0; }

	@ConfigItem(
			keyName = "Magic",
			name = "Magic Goal:",
			description = ""
	) default int Magic() { return 0; }

	@ConfigItem(
			keyName = "Runecraft",
			name = "Runecraft Goal:",
			description = ""
	) default int Runecraft() { return 0; }

	@ConfigItem(
			keyName = "Construction",
			name = "Construction Goal:",
			description = ""
	) default int Construction() { return 0; }

	@ConfigItem(
			keyName = "Hitpoints",
			name = "Hitpoints Goal:",
			description = ""
	) default int Hitpoints() { return 0; }

	@ConfigItem(
			keyName = "Agility",
			name = "Agility Goal:",
			description = ""
	) default int Agility() { return 0; }

	@ConfigItem(
			keyName = "Herblore",
			name = "Herblore Goal:",
			description = ""
	) default int Herblore() { return 0; }

	@ConfigItem(
			keyName = "Thieving",
			name = "Thieving Goal:",
			description = ""
	) default int Thieving() { return 0; }

	@ConfigItem(
			keyName = "Crafting",
			name = "Crafting Goal:",
			description = ""
	) default int Crafting() { return 0; }

	@ConfigItem(
			keyName = "Fletching",
			name = "Fletching Goal:",
			description = ""
	) default int Fletching() { return 0; }

	@ConfigItem(
			keyName = "Slayer",
			name = "Slayer Goal:",
			description = ""
	) default int Slayer() { return 0; }

	@ConfigItem(
			keyName = "Hunter",
			name = "Hunter Goal:",
			description = ""
	) default int Hunter() { return 0; }

	@ConfigItem(
			keyName = "Mining",
			name = "Mining Goal:",
			description = ""
	) default int Mining() { return 0; }

	@ConfigItem(
			keyName = "Smithing",
			name = "Smithing Goal:",
			description = ""
	) default int Smithing() { return 0; }

	@ConfigItem(
			keyName = "Fishing",
			name = "Fishing Goal:",
			description = ""
	) default int Fishing() { return 0; }

	@ConfigItem(
			keyName = "Cooking",
			name = "Cooking Goal:",
			description = ""
	) default int Cooking() { return 0; }

	@ConfigItem(
			keyName = "Firemaking",
			name = "Firemaking Goal:",
			description = ""
	) default int Firemaking() { return 0; }

	@ConfigItem(
			keyName = "Woodcutting",
			name = "Woodcutting Goal:",
			description = ""
	) default int Woodcutting() { return 0; }

	@ConfigItem(
			keyName = "Farming",
			name = "Farming Goal:",
			description = ""
	) default int Farming() { return 0; }


}