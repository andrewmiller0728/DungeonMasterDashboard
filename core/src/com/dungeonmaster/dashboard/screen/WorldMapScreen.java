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
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeonmaster.dashboard.command.CommandList;
import com.dungeonmaster.dashboard.command.SwitchScreenCommand;
import com.dungeonmaster.dashboard.zone.Zone;
import com.dungeonmaster.dashboard.zone.ZoneList;
import com.dungeonmaster.dashboard.character.Character;

import java.util.ArrayList;

public class WorldMapScreen implements Screen, InputProcessor {

    private final static float DEFAULT_MARGIN = 48;
    private final static float DEFAULT_PADDING = 12;
    private final static int CIRCLE_RADIUS = 48;

    private SpriteBatch batch;
    private Sprite background;
    private Sprite map;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont segoePrint32;
    private BitmapFont segoePrint24;
    private BitmapFont segoePrint18;
    private ShapeRenderer shapeRenderer;

    private static CommandList commandList;
    private static ZoneList zoneList;
    private ArrayList<Character> characters;

    public WorldMapScreen() {
        commandList = new CommandList();
        zoneList = new ZoneList();
        characters = null;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing WorldMapScreen\n");

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1000f, 1000f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        setBackgroundSprite(new Texture("./oldpaper.jpg"));
        setMapSprite(new Texture("./world-map-blacksburg-va.png"));

        segoePrint32 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
        segoePrint24 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-24px.fnt"));
        segoePrint24.setColor(0f, 0f, 0f, 1f);
        segoePrint18 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-18px.fnt"));
        segoePrint18.setColor(0f, 0f, 0f, 1f);

        shapeRenderer = new ShapeRenderer();
    }

    private void setBackgroundSprite(Texture backgroundTexture) {
        background = new Sprite(backgroundTexture);
        background.setAlpha(0.875f);
        background.setSize(
                viewport.getWorldWidth(),
                viewport.getWorldHeight()
        );
        background.setCenter(
                viewport.getWorldWidth() / 2f,
                viewport.getWorldHeight() / 2f
        );
        background.setOrigin(
                background.getX() + background.getWidth() / 2f,
                background.getY() + background.getHeight() / 2f
        );
    }

    private void setMapSprite(Texture mapTexture) {
        map = new Sprite(mapTexture);
        map.setAlpha(1f);
        map.setSize(
                viewport.getWorldWidth() - DEFAULT_MARGIN * 3f,
                viewport.getWorldHeight() - DEFAULT_MARGIN * 3f
        );
        map.setCenter(
                viewport.getWorldWidth() / 2f,
                viewport.getWorldHeight() / 2f
        );
        map.setOrigin(
                map.getX() + map.getWidth() / 2f,
                map.getY() + map.getHeight() / 2f
        );
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        background.draw(batch);
        map.draw(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < zoneList.getSize(); i++) {
            Zone currZone = zoneList.getZone(i);
            drawMarkerCircle(
                    currZone.getWorldMapCoords().x,
                    currZone.getWorldMapCoords().y,
                    CIRCLE_RADIUS
            );
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < zoneList.getSize(); i++) {
            Zone currZone = zoneList.getZone(i);
            GlyphLayout nameText = new GlyphLayout();
            nameText.setText(segoePrint18, currZone.getName());
            drawLabelBox(
                    currZone.getWorldMapCoords().x,
                    currZone.getWorldMapCoords().y - CIRCLE_RADIUS,
                    nameText.width + DEFAULT_PADDING,
                    nameText.height + DEFAULT_PADDING * 1.5f
            );
        }
        shapeRenderer.end();

        batch.begin();
        for (int i = 0; i < zoneList.getSize(); i++) {
            Zone currZone = zoneList.getZone(i);
            GlyphLayout nameText = new GlyphLayout();
            nameText.setText(segoePrint18, currZone.getName());
            segoePrint18.draw(
                    batch,
                    nameText,
                    currZone.getWorldMapCoords().x - (nameText.width / 2f),
                    currZone.getWorldMapCoords().y - CIRCLE_RADIUS + nameText.height / 2f
            );
        }
        batch.end();
    }

    private void drawMarkerCircle(float centerX, float centerY, int circleRadius) {
        shapeRenderer.setColor(0.8f, 0.1f, 0.1f, 1f);
        for (int i = 0; i < circleRadius / 2; i++) {
            shapeRenderer.circle(centerX, centerY, circleRadius - (i / 4f));
        }
    }

    private void drawLabelBox(float centerX, float centerY, float width, float height) {
        shapeRenderer.setColor(251f / 255f, 245f / 255f, 229f / 255f, 1f);
        shapeRenderer.rect(
                centerX - width / 2f,
                centerY - height / 2f,
                width,
                height
        );
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

    }

    @Override
    public void dispose() {
        batch.dispose();
        segoePrint32.dispose();
        segoePrint24.dispose();
        segoePrint18.dispose();
        shapeRenderer.dispose();
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
        for (int i = 0; i < zoneList.getSize(); i++) {
            Zone currZone = zoneList.getZone(i);
            Circle tempCircle = new Circle();
            tempCircle.set(currZone.getWorldMapCoords().x, currZone.getWorldMapCoords().y, CIRCLE_RADIUS);
            if (tempCircle.contains(touchWorldPos)) {
                commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.ZONE, currZone));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
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

    private boolean isInsideBounds(Vector2 coordinates, Vector2[] bounds) {
        boolean containedX = bounds[0].x <= coordinates.x && bounds[0].x + bounds[1].x >= coordinates.x;
        boolean containedY = bounds[0].y <= coordinates.y && bounds[0].y + bounds[1].y >= coordinates.y;
        return (containedX && containedY);
    }
}
