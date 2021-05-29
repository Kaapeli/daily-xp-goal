package com.DailyXpGoal;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ClientShutdown;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Future;
import java.util.function.Consumer;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.time.LocalDate;

@Slf4j
@PluginDescriptor(
	name = "Daily Xp Goal"
)
public class DailyXpGoalPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DailyXpGoalConfig config;

	@Inject
	private ClientToolbar clientToolbar;



	private DailyXpGoalPanel panel;

	private static final File SAVE_DATA;

	private String file_name = "data.log";

	static
	{
		SAVE_DATA = new File(RUNELITE_DIR, "DailyXpGoal");
		SAVE_DATA.mkdirs();
	}

	private NavigationButton navButton;

	private int dateInt;
	/*
	Skill order:
		Attack
		Strength
		Defence
		Ranged
		Prayer
		Magic
		Runecrafting
		Construction
		Hitpoints
		Agility
		Herblore
		Thieving
		Crafting
		Fletching
		Slayer
		Hunter
		Mining
		Smithing
		Fishing
		Cooking
		Firemaking
		Woodcutting
		Farming
	 */
	public int[] xpTargetArray = new int[23];	//These could be combined to a list of tuples
	public int[] xpCurrentArray = new int[23];



	private void createDate()
	{
		int day = LocalDate.now().getDayOfYear();
		int year = LocalDate.now().getYear();
		year = year*1000;
		dateInt = year + day;
	}

	private boolean checkFileDate()
	{
		File file = new File(SAVE_DATA, file_name);
		if(!file.exists())
		{
			return false;
		}

		String fileDate = "2007001";
		try {
			Scanner reader = new Scanner(file);
			fileDate = reader.nextLine();
			reader.close();
		}catch(FileNotFoundException e)
		{
			log.info("File DATE could not be read -- this should never happen unless editing the data.log file by hand");
		}

		int fileDateInt = Integer.parseInt(fileDate);
		if(fileDateInt > dateInt)
		{
			return true;
		}

		return false;
	}

	private void loadFileSkills()
	{
		File file = new File(SAVE_DATA, file_name);
		if(!file.exists())
		{
			for(int i = 0; i < 23; i++)
			{
				xpCurrentArray[i] = 0;
				xpTargetArray[i] = 0;
			}
			return;
		}
		try {
			Scanner reader = new Scanner(file);
			reader.nextLine(); //skip current date line
			for(int i = 0; i < 23; i++)
			{
				xpCurrentArray[i] = Integer.parseInt(reader.nextLine());
				xpTargetArray[i] = Integer.parseInt(reader.nextLine());
			}
			reader.close();
		}catch(FileNotFoundException e)
		{
			log.info("File SKILLS could not be read -- this should never happen unless editing the data.log file by hand");
		}

	}

	private void saveDataFile() throws IOException
	{
		File file = new File(SAVE_DATA, file_name);
		file.createNewFile();
		FileWriter dataWriter = new FileWriter(file);
		dataWriter.write(Integer.toString(dateInt));
		dataWriter.write(System.getProperty( "line.separator" ));
		for(int i = 0; i < 23; i++)
		{
			dataWriter.write(Integer.toString(xpCurrentArray[i]));
			dataWriter.write(System.getProperty( "line.separator" ));
			dataWriter.write(Integer.toString(xpTargetArray[i]));
			dataWriter.write(System.getProperty( "line.separator" ));
		}
		dataWriter.close();

	}

	private void resetDaily()
	{
		for (int i = 0; i < 23; i++)
		{
			xpCurrentArray[i] = 0;
		}
	}

	@Subscribe
	private void onStatChanged(StatChanged statChanged)
	{
		final Skill skill = statChanged.getSkill();
		final int xp = statChanged.getXp();
		final String changedKey = skill.getName();
		if(changedKey.equals("Attack"))
		{
			xpCurrentArray[0] += xp;
			return;
		}
		if(changedKey.equals("Strength"))
		{
			xpCurrentArray[1] += xp;
			return;
		}
		if(changedKey.equals("Defence"))
		{
			xpCurrentArray[2] += xp;
			return;
		}
		if(changedKey.equals("Ranged"))
		{
			xpCurrentArray[3] += xp;
			return;
		}
		if(changedKey.equals("Prayer"))
		{
			xpCurrentArray[4] += xp;
			return;
		}
		if(changedKey.equals("Magic"))
		{
			xpCurrentArray[5] += xp;
			return;
		}
		if(changedKey.equals("Runecraft"))
		{
			xpCurrentArray[6] += xp;
			return;
		}
		if(changedKey.equals("Construction"))
		{
			xpCurrentArray[7] += xp;
			return;
		}
		if(changedKey.equals("Hitpoints"))
		{
			xpCurrentArray[8] += xp;
			return;
		}
		if(changedKey.equals("Agility"))
		{
			xpCurrentArray[9] += xp;
			return;
		}
		if(changedKey.equals("Herblore"))
		{
			xpCurrentArray[10] += xp;
			return;
		}
		if(changedKey.equals("Thieving"))
		{
			xpCurrentArray[11] += xp;
			return;
		}
		if(changedKey.equals("Crafting"))
		{
			xpCurrentArray[12] += xp;
			return;
		}
		if(changedKey.equals("Fletching"))
		{
			xpCurrentArray[13] += xp;
			return;
		}
		if(changedKey.equals("Slayer"))
		{
			xpCurrentArray[14] += xp;
			return;
		}
		if(changedKey.equals("Hunter"))
		{
			xpCurrentArray[15] += xp;
			return;
		}
		if(changedKey.equals("Mining"))
		{
			xpCurrentArray[16] += xp;
			return;
		}
		if(changedKey.equals("Smithing"))
		{
			xpCurrentArray[17] += xp;
			return;
		}
		if(changedKey.equals("Fishing"))
		{
			xpCurrentArray[18] += xp;
			return;
		}
		if(changedKey.equals("Cooking"))
		{
			xpCurrentArray[19] += xp;
			return;
		}
		if(changedKey.equals("Firemaking"))
		{
			xpCurrentArray[20] += xp;
			return;
		}
		if(changedKey.equals("Woodcutting"))
		{
			xpCurrentArray[21] += xp;
			return;
		}
		if(changedKey.equals("Farming"))
		{
			xpCurrentArray[22] += xp;
			return;
		}

	}

	@Override
	protected void startUp() throws Exception
	{
		panel = new DailyXpGoalPanel();
		createDate();
		loadFileSkills();
		if(checkFileDate())
		{
			resetDaily();
		}
		log.info("current date = " + dateInt);
		log.info("Example started!");

		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/daily_xp_goal_icon.png");

		navButton = NavigationButton.builder()
				.tooltip("Daily Xp Goal")
				.icon(icon)
				.priority(7)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
		clientToolbar.removeNavigation(navButton);
		saveDataFile();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{

		String changedKey = event.getKey();
		String newValue = event.getNewValue();

		if(changedKey.equals("Attack"))
		{
			xpTargetArray[0] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Strength"))
		{
			xpTargetArray[1] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Defence"))
		{
			xpTargetArray[2] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Ranged"))
		{
			xpTargetArray[3] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Prayer"))
		{
			xpTargetArray[4] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Magic"))
		{
			xpTargetArray[5] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Runecraft"))
		{
			xpTargetArray[6] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Construction"))
		{
			xpTargetArray[7] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Hitpoints"))
		{
			xpTargetArray[8] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Agility"))
		{
			xpTargetArray[9] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Herblore"))
		{
			xpTargetArray[10] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Thieving"))
		{
			xpTargetArray[11] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Crafting"))
		{
			xpTargetArray[12] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Fletching"))
		{
			xpTargetArray[13] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Slayer"))
		{
			xpTargetArray[14] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Hunter"))
		{
			xpTargetArray[15] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Mining"))
		{
			xpTargetArray[16] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Smithing"))
		{
			xpTargetArray[17] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Fishing"))
		{
			xpTargetArray[18] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Cooking"))
		{
			xpTargetArray[19] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Firemaking"))
		{
			xpTargetArray[20] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Woodcutting"))
		{
			xpTargetArray[21] = Integer.parseInt(newValue);
			return;
		}
		if(changedKey.equals("Farming"))
		{
			xpTargetArray[22] = Integer.parseInt(newValue);
			return;
		}



	}

	@Subscribe
	public void onClientShutdown(ClientShutdown event) throws IOException
	{
		saveDataFile();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		/*
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
		 */
	}

	@Provides
	DailyXpGoalConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DailyXpGoalConfig.class);
	}
}
