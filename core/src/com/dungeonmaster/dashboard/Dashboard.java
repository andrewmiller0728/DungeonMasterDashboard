package com.dungeonmaster.dashboard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Dashboard extends Game {

	private SpriteBatch batch;

	private ArrayList<Character> characters;

	private DashboardScreen dashboardScreen;
	private WorldMapScreen worldMapScreen;
	private ZoneScreen zoneScreen;
	private CharacterListScreen characterListScreen;
	private CharacterScreen characterScreen;

	private static CommandList commandList;

	@Override
	public void create () {
		batch = new SpriteBatch();

		characters = generateTestCharacters();

		String[] cardTitles = {"World Map", "Character List"};
		dashboardScreen = new DashboardScreen(cardTitles);
		worldMapScreen = new WorldMapScreen();
		zoneScreen = new ZoneScreen();
		characterListScreen = new CharacterListScreen();
		characterListScreen.setCharacters(characters);
		characterScreen = new CharacterScreen();

		commandList = new CommandList();
		commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.DASHBOARD));
	}

	@Override
	public void render () {
		if (commandList.peekNextCommand() != null) {
			Command currCommand = commandList.getNextCommand();
			System.out.printf("[Game]    Current Command = %s\n", currCommand.toString());
			commandList.printList();
			executeCommand(currCommand);
		}
		this.getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		characterListScreen.dispose();
		characterScreen.dispose();
		zoneScreen.dispose();
	}

	private void executeCommand(Command command) {
		switch (command.getCommandAction()) {
			case SWITCH_SCREEN:
				executeSwitchScreenCommand((SwitchScreenCommand) command);
				break;
			default:
				break;
		}
	}

	private void executeSwitchScreenCommand(SwitchScreenCommand ssCommand) {
		switch (ssCommand.getNewScreenType()) {
			case DASHBOARD:
				this.setScreen(dashboardScreen);
				Gdx.input.setInputProcessor(dashboardScreen);
				break;
			case WORLD_MAP:
				this.setScreen(worldMapScreen);
				Gdx.input.setInputProcessor(worldMapScreen);
				break;
			case ZONE:
				zoneScreen.setCharacters(characters);
				this.setScreen(zoneScreen);
				Gdx.input.setInputProcessor(zoneScreen);
				break;
			case CHARACTER_LIST:
				this.setScreen(characterListScreen);
				Gdx.input.setInputProcessor(characterListScreen);
				break;
			case CHARACTER:
				characterScreen.setCharacter((Character) ssCommand.getPayload());
				this.setScreen(characterScreen);
				Gdx.input.setInputProcessor(characterScreen);
				break;
			default:
				break;
		}
	}

	private ArrayList<Character> generateTestCharacters() {
		ArrayList<Character> characters = new ArrayList<>();
		NonPlayerCharacter characterA = new NonPlayerCharacter(
				"Michael Dorn",
				new Texture("./character-art/survivors/survivor-male-01.jpg"),
				new Vector3(0,6,9)
		);
		characterA.addItem(new Item("Baseball Bat", 10, 5));
		characterA.addItem(new Item("Can of SPAM", 10, 5));
		characterA.addItem(new Item("Backpack", 10, 5));
		characters.add(characterA);

		NonPlayerCharacter characterB = new NonPlayerCharacter(
				"Amy Buck",
				new Texture("./character-art/survivors/survivor-female-01.jpg"),
				new Vector3(0,12,2)
		);
		characters.add(characterB);

		NonPlayerCharacter characterC = new NonPlayerCharacter(
				"Steven Ram",
				new Texture("./character-art/survivors/survivor-male-02.jpg"),
				new Vector3(0,4,2)
		);
		characters.add(characterC);

		int[] characterDAbilityScores = {14, 13, 12, 11, 11, 9};
		PlayerCharacter characterD = new PlayerCharacter(
				"Andrew",
				"Wayne Dwayne",
				new Texture("./character-art/survivors/survivor-male-03.jpg"),
				new Vector3(0, 0, 1),
				CharacterBackground.ENTERTAINER,
				characterDAbilityScores,
				AlignmentX.CHAOTIC,
				AlignmentY.NEUTRAL,
				30,
				"Ideals",
				"Bonds",
				"Flaws"
		);
		characters.add(characterD);

		NonPlayerCharacter characterE = new NonPlayerCharacter(
				"Carly Smith",
				new Texture("./character-art/survivors/survivor-female-02.jpg"),
				new Vector3(0,10,15)
		);
		characters.add(characterE);

		NonPlayerCharacter characterF = new NonPlayerCharacter(
				"Character F",
				new Texture("./character-art/survivors/survivor-agender-01.jpg"),
				new Vector3(0,0,0)
		);
		characters.add(characterF);

		NonPlayerCharacter characterG = new NonPlayerCharacter(
				"Taylor Smith",
				new Texture("./character-art/survivors/survivor-agender-02.jpg"),
				new Vector3(0,9,11)
		);
		characters.add(characterG);

		NonPlayerCharacter characterH = new NonPlayerCharacter(
				"Joe McGee",
				new Texture("./character-art/survivors/survivor-male-04.jpg"),
				new Vector3(0,4,20)
		);
		characters.add(characterH);

		return characters;
	}
}
