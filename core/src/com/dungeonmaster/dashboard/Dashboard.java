package com.dungeonmaster.dashboard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;

import java.util.ArrayList;

public class Dashboard extends Game {

	private SpriteBatch batch;
	private ArrayList<Character> characters;
	private ZoneScreen zoneScreen;
	private CharacterListScreen characterListScreen;
	private CharacterScreen characterScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();

		characters = generateTestCharacters();

		characterListScreen = new CharacterListScreen();
		characterScreen = new CharacterScreen();
		zoneScreen = new ZoneScreen();
	}

	@Override
	public void render () {
		if (this.getScreen() == null) {
//			characterListScreen.setCharacters(characters);
//			this.setScreen(characterListScreen);
//			Gdx.input.setInputProcessor(characterListScreen);
			zoneScreen.setCharacters(characters);
			zoneScreen.setZoneParameters(0, 60, 5280 / 4);
			this.setScreen(zoneScreen);
		}
		else if (this.getScreen().equals(characterListScreen) && characterListScreen.getSelectedCharacter() != null) {
			characterScreen.setCharacter(characterListScreen.getSelectedCharacter());
			this.setScreen(characterScreen);
			Gdx.input.setInputProcessor(characterScreen);
		}
		else if (this.getScreen().equals(characterScreen) && characterScreen.getBackSelected()) {
			this.setScreen(characterListScreen);
			Gdx.input.setInputProcessor(characterListScreen);
		}

		this.getScreen().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		characterListScreen.dispose();
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
				Background.ENTERTAINER,
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
