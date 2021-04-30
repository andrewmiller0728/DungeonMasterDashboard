package com.dungeonmaster.dashboard.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeonmaster.dashboard.command.CommandList;
import com.dungeonmaster.dashboard.command.SwitchScreenCommand;
import com.dungeonmaster.dashboard.zone.Zone;
import com.dungeonmaster.dashboard.character.Character;

import java.util.ArrayList;

public class ZoneScreen implements Screen, InputProcessor {

    private final static float DEFAULT_MARGIN = 96;
    private final static float DEFAULT_PADDING = 12;

    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite paper;
    private ShapeRenderer shapeRenderer;

    private BitmapFont segoePrint32;

    private ArrayList<Character> characters;
    private ArrayList<Sprite> characterSprites;
    private Character selectedCharacter;
    private Zone zone;

    private static CommandList commandList;

    public ZoneScreen() {
        super();
        characters = null;
        characterSprites = null;
        selectedCharacter = null;
        zone = null;
        commandList = new CommandList();
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
        characterSprites = new ArrayList<>();
        for (Character character : characters) {
            characterSprites.add(new Sprite(character.getIcon()));
        }
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing ZoneScreen\n");

        camera = new OrthographicCamera();
        viewport = new FitViewport(1000f, 1000f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        paper = new Sprite(new Texture("./oldpaper.jpg"));
        paper.setAlpha(0.875f);
        paper.setSize(
                viewport.getWorldWidth(),
                viewport.getWorldHeight()
        );
        paper.setCenter(
                viewport.getWorldWidth() / 2f,
                viewport.getWorldHeight() / 2f
        );
        paper.setOrigin(
                paper.getX() + paper.getWidth() / 2f,
                paper.getY() + paper.getHeight() / 2f
        );

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        segoePrint32 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();

        // Paper Background
        paper.draw(batch);

        // Header Text
        GlyphLayout headerText = new GlyphLayout();
        headerText.setText(segoePrint32, zone.getName());
        segoePrint32.draw(
                batch,
                headerText,
                paper.getOriginX() - headerText.width / 2f,
                viewport.getWorldHeight() - (DEFAULT_MARGIN / 4f) - headerText.height
        );

        batch.end();

        // TODO: Fill in tiles containing characters with something to denote the character
        Vector2 dimensionsInTiles = zone.getDimensionsInTiles();
        batch.begin();
        for (int i = 0; i < characters.size(); i++) {
            Character currCharacter = characters.get(i);
            if (currCharacter.getZone().equals(zone)) {
                Sprite currSprite = characterSprites.get(i);
                currSprite.setSize(
                        (viewport.getWorldWidth() - (DEFAULT_MARGIN * 2f)) / dimensionsInTiles.x,
                        (viewport.getWorldHeight() - (DEFAULT_MARGIN * 2f)) / dimensionsInTiles.y
                );
                currSprite.setPosition(
                        DEFAULT_MARGIN + (currCharacter.getZonePosition().x * currSprite.getWidth()),
                        DEFAULT_MARGIN + (currCharacter.getZonePosition().y * currSprite.getHeight())
                );
                currSprite.draw(batch);
            }
        }
        batch.end();

        drawGrid();
    }

    private void drawGrid() {
        Vector2 dimensionsInTiles = zone.getDimensionsInTiles();
        Rectangle tile = new Rectangle();
        tile.set(
                DEFAULT_MARGIN,
                DEFAULT_MARGIN,
                (viewport.getWorldWidth() - (DEFAULT_MARGIN * 2f)) / dimensionsInTiles.x,
                (viewport.getWorldHeight() - (DEFAULT_MARGIN * 2f)) / dimensionsInTiles.y
        );
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(81f / 255f, 81f / 255f, 81f / 255f, 1f);
        for (int i = 0; i < dimensionsInTiles.x; i++) {
            for (int j = 0; j < dimensionsInTiles.y; j++) {
                for (int k = 0; k < tile.width / 12; k++) {
                    shapeRenderer.rect(
                            tile.x + (i * tile.width) + (k / 2f),
                            tile.y + (j * tile.height) + (k / 2f),
                            tile.width - k,
                            tile.height - k
                    );
                }
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        selectedCharacter = null;
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        segoePrint32.dispose();
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
        Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
        for (int i = 0; i < characterSprites.size(); i++) {
            if (characterSprites.get(i).getBoundingRectangle().contains(touchWorldPos)) {
                selectedCharacter = characters.get(i);
                commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER, selectedCharacter));
                return true;
            }
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
