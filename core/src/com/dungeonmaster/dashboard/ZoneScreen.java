package com.dungeonmaster.dashboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class ZoneScreen implements Screen, InputProcessor {

    private final static float MARGIN = 64;
    private final static float LINE_WIDTH = 4;

    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite paper;
    private ShapeRenderer shapeRenderer;

    private ArrayList<Character> characters;
    private int zoneIndex;
    private int feetPerBox;
    private int feetPerZone;

    public ZoneScreen() {
        super();
        characters = null;
        zoneIndex = 0;
        feetPerBox = 30;
        feetPerZone = 5280;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void setZoneParameters(int zoneIndex, int feetPerBox, int feetPerZone) {
        this.zoneIndex = zoneIndex;
        this.feetPerBox = feetPerBox;
        this.feetPerZone = feetPerZone;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing ZoneScreen\n");

        camera = new OrthographicCamera();
        viewport = new FitViewport(2000f, 2000f, camera);
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
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        paper.draw(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        int boxCount = (feetPerZone / feetPerBox);
        float pixelsPerBox = MathUtils.map(0, feetPerZone, 0, viewport.getWorldWidth() - MARGIN, feetPerBox);
        shapeRenderer.setColor(0.25f, 0.5f, 1f, 1f);
        for (int i = 0; i < boxCount; i++) {
            for (int j = 0; j < boxCount; j++) {
                for (Character character : characters) {
                    if (character.getLocation().x == zoneIndex && character.getLocation().y == j && character.getLocation().z == i) {
                        shapeRenderer.rect(
                                MARGIN + (j * (pixelsPerBox - LINE_WIDTH)),
                                MARGIN + (i * (pixelsPerBox - LINE_WIDTH)),
                                pixelsPerBox - LINE_WIDTH,
                                pixelsPerBox - LINE_WIDTH
                        );
                    }
                }
            }
        }
        shapeRenderer.setColor(0.5f, 0.1f, 0.1f, 1f);
        for (int i = 0; i <= boxCount; i++) {
            shapeRenderer.rectLine(
                    new Vector2(MARGIN, MARGIN + (i * (pixelsPerBox - LINE_WIDTH))),
                    new Vector2(MARGIN + (boxCount * (pixelsPerBox - LINE_WIDTH)), MARGIN + (i * (pixelsPerBox - LINE_WIDTH))),
                    LINE_WIDTH
            );
            shapeRenderer.rectLine(
                    new Vector2(MARGIN + (i * (pixelsPerBox - LINE_WIDTH)), MARGIN),
                    new Vector2(MARGIN + (i * (pixelsPerBox - LINE_WIDTH)), MARGIN + (boxCount * (pixelsPerBox - LINE_WIDTH))),
                    LINE_WIDTH
            );
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

    }

    @Override
    public void dispose() {
        batch.dispose();
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
}
