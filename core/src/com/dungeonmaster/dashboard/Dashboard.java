package com.dungeonmaster.dashboard;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeonmaster.dashboard.character.*;
import com.dungeonmaster.dashboard.character.Character;
import com.dungeonmaster.dashboard.command.Command;
import com.dungeonmaster.dashboard.command.CommandList;
import com.dungeonmaster.dashboard.command.SwitchScreenCommand;
import com.dungeonmaster.dashboard.screen.*;
import com.dungeonmaster.dashboard.zone.Zone;
import com.dungeonmaster.dashboard.zone.ZoneList;

import java.util.ArrayList;

import static com.dungeonmaster.dashboard.character.Character.AlignmentX.CHAOTIC;
import static com.dungeonmaster.dashboard.character.Character.AlignmentY.NEUTRAL;

public class Dashboard extends Game implements InputProcessor {

	private final static float DEFAULT_MARGIN = 24;
	private final static float DEFAULT_PADDING = 16;

	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Viewport viewport;
	private OrthographicCamera camera;
	private BitmapFont segoePrint32;
	private Rectangle backButtonRect;

	private InputMultiplexer inputMultiplexer;

	private ArrayList<Character> characters;

	private OptionCardScreen optionCardScreen;
	private WorldMapScreen worldMapScreen;
	private ZoneScreen zoneScreen;
	private CharacterListScreen characterListScreen;
	private CharacterScreen characterScreen;

	private static CommandList commandList;
	private static ZoneList zoneList;

	@Override
	public void create () {
		System.out.print("[Game]    Creating game\n");

		camera = new OrthographicCamera();
		viewport = new FitViewport(1000f, 1000f, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

		inputMultiplexer = new InputMultiplexer();

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		segoePrint32 = new BitmapFont(Gdx.files.internal("./segoe-print-32px.fnt"));
		segoePrint32.setColor(0f, 0f, 0f, 1f);

		zoneList = generateTestZones();
		characters = generateTestCharacters();

		commandList = new CommandList();
		commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.OPTION_CARD));

		String[] cardTitles = {"World Map", "Character List"};
		Command[] cardCommands = {
				new SwitchScreenCommand(SwitchScreenCommand.ScreenType.WORLD_MAP),
				new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER_LIST)
		};
		optionCardScreen = new OptionCardScreen(cardTitles, cardCommands);
		worldMapScreen = new WorldMapScreen();
		worldMapScreen.setCharacters(characters);
		zoneScreen = new ZoneScreen();
		zoneScreen.setCharacters(characters);
		characterListScreen = new CharacterListScreen();
		characterListScreen.setCharacters(characters);
		characterScreen = new CharacterScreen();

		backButtonRect = new Rectangle();
	}

	private ZoneList generateTestZones() {
		ZoneList tempList = new ZoneList();
		tempList.addZone(
				new Zone(
						new Vector2(537, 471),
						new Vector2(25, 25),
						Zone.ZoneLabel.LANE_STADIUM,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(496, 565),
						new Vector2(25, 30),
						Zone.ZoneLabel.DRILLFIELD,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(393, 646),
						new Vector2(100, 100),
						Zone.ZoneLabel.UNIVERSITY_CITY,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(525, 768),
						new Vector2(50, 50),
						Zone.ZoneLabel.FOOD_LION,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(698, 434),
						new Vector2(100, 50),
						Zone.ZoneLabel.COOKOUT,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(258, 404),
						new Vector2(50, 25),
						Zone.ZoneLabel.FOXRIDGE,
						characters
				)
		);
		tempList.addZone(
				new Zone(
						new Vector2(512, 644),
						new Vector2(30, 30),
						Zone.ZoneLabel.DOWNTOWN,
						characters
				)
		);
		return tempList;
	}

	private ArrayList<Character> generateTestCharacters() {
		System.out.println("[Game]    Generating Test Characters");
		System.out.printf("");

		ArrayList<Character> characters = new ArrayList<>();
		NonPlayerCharacter characterA = new NonPlayerCharacter(
				"Michael Dorn",
				new Texture("./character-art/survivors/survivor-male-01.jpg"),
				zoneList.getZone(Zone.ZoneLabel.DRILLFIELD),
				new Vector2(3,4)
		);
		characters.add(characterA);

		NonPlayerCharacter characterB = new NonPlayerCharacter(
				"Amy Buck",
				new Texture("./character-art/survivors/survivor-female-01.jpg"),
				zoneList.getZone(Zone.ZoneLabel.FOXRIDGE),
				new Vector2(12,2)
		);
		characters.add(characterB);

		NonPlayerCharacter characterC = new NonPlayerCharacter(
				"Steven Ram",
				new Texture("./character-art/survivors/survivor-male-02.jpg"),
				zoneList.getZone(Zone.ZoneLabel.FOXRIDGE),
				new Vector2(4,2)
		);
		characters.add(characterC);

		int[] characterDAbilityScores = {14, 13, 12, 11, 11, 9};
		PlayerCharacter characterD = new PlayerCharacter(
				"Andrew",
				"Wayne Dwayne",
				new Texture("./character-art/survivors/survivor-male-03.jpg"),
				zoneList.getZone(Zone.ZoneLabel.COOKOUT),
				new Vector2(10, 1),
				characterDAbilityScores,
				new CharacterSkills(),
				CHAOTIC,
				NEUTRAL,
				CharacterBackground.ENTERTAINER,
				30,
				"Ideals",
				"Bonds",
				"Flaws"
		);
		characters.add(characterD);

		NonPlayerCharacter characterE = new NonPlayerCharacter(
				"Carly Williams",
				new Texture("./character-art/survivors/survivor-female-02.jpg"),
				zoneList.getZone(Zone.ZoneLabel.LANE_STADIUM),
				new Vector2(10,15)
		);
		characters.add(characterE);

		NonPlayerCharacter characterF = new NonPlayerCharacter(
				"Character F",
				new Texture("./character-art/survivors/survivor-agender-01.jpg"),
				zoneList.getZone(Zone.ZoneLabel.LANE_STADIUM),
				new Vector2(0,0)
		);
		characters.add(characterF);

		NonPlayerCharacter characterG = new NonPlayerCharacter(
				"Taylor Smith",
				new Texture("./character-art/survivors/survivor-agender-02.jpg"),
				zoneList.getZone(Zone.ZoneLabel.UNIVERSITY_CITY),
				new Vector2(9,11)
		);
		characters.add(characterG);

		NonPlayerCharacter characterH = new NonPlayerCharacter(
				"Joe McGee",
				new Texture("./character-art/survivors/survivor-male-04.jpg"),
				zoneList.getZone(Zone.ZoneLabel.UNIVERSITY_CITY),
				new Vector2(4,18)
		);
		characters.add(characterH);

		return characters;
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		if (commandList.peekNextCommand() != null) {
			Command currCommand = commandList.getNextCommand();
			System.out.printf("[Game]    Current Command = %s\n", currCommand.toString());
			commandList.printList();
			executeCommand(currCommand);
		}
		this.getScreen().render(Gdx.graphics.getDeltaTime());

		drawBackButton();
	}

	private void drawBackButton() {
		GlyphLayout backButtonText = new GlyphLayout();
		backButtonText.setText(
				segoePrint32,
				"back"
		);
		backButtonRect.set(
				viewport.getWorldWidth() - (backButtonText.width + DEFAULT_PADDING + DEFAULT_MARGIN),
				DEFAULT_MARGIN,
				backButtonText.width + DEFAULT_PADDING,
				backButtonText.height * 2f
		);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(251f / 255f, 245f / 255f, 229f / 255f, 1f);
		shapeRenderer.rect(
				backButtonRect.x,
				backButtonRect.y,
				backButtonRect.width,
				backButtonRect.height
		);
		shapeRenderer.end();

		batch.begin();
		segoePrint32.draw(
				batch,
				backButtonText,
				backButtonRect.x + DEFAULT_PADDING / 2f,
				backButtonRect.y + backButtonText.height + DEFAULT_PADDING / 2f
		);
		batch.end();
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
		inputMultiplexer.clear();
		inputMultiplexer.addProcessor(this);
		switch (ssCommand.getNewScreenType()) {
			case OPTION_CARD:
				this.setScreen(optionCardScreen);
				inputMultiplexer.addProcessor(optionCardScreen);
				break;
			case WORLD_MAP:
				this.setScreen(worldMapScreen);
				inputMultiplexer.addProcessor(worldMapScreen);
				break;
			case ZONE:
				zoneScreen.setCharacters(characters);
				zoneScreen.setZone((Zone) ssCommand.getPayload());
				this.setScreen(zoneScreen);
				inputMultiplexer.addProcessor(zoneScreen);
				break;
			case CHARACTER_LIST:
				this.setScreen(characterListScreen);
				inputMultiplexer.addProcessor(characterListScreen);
				break;
			case CHARACTER:
				characterScreen.setCharacter((Character) ssCommand.getPayload());
				this.setScreen(characterScreen);
				inputMultiplexer.addProcessor(characterScreen);
				break;
			default:
				break;
		}
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void dispose () {
		batch.dispose();
		segoePrint32.dispose();
		characterListScreen.dispose();
		characterScreen.dispose();
		zoneScreen.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.printf("[Game]    Touch at SCREEN point (%d, %d)\n", screenX, screenY);
		Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
		System.out.printf("[Game]    Touch at WORLD point (%.0f, %.0f)\n", touchWorldPos.x, touchWorldPos.y);
		if (backButtonRect.contains(touchWorldPos)) {
			commandList.undo();
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	private Vector2 mapScreenXYtoWorldXY(Vector2 xy) {
		return new Vector2(
				MathUtils.map(
						viewport.getLeftGutterWidth(),
						viewport.getRightGutterX(),
						0f,
						viewport.getWorldWidth(),
						xy.x
				),
				MathUtils.map(
						viewport.getTopGutterY(),
						viewport.getBottomGutterHeight(),
						0f,
						viewport.getWorldHeight(),
						xy.y
				)
		);
	}

}
