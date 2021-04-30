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
import com.dungeonmaster.dashboard.command.Command;
import com.dungeonmaster.dashboard.command.CommandList;

public class OptionCardScreen implements Screen, InputProcessor {

    private final static float DEFAULT_MARGIN = 64;
    private final static float DEFAULT_PADDING = 12;

    private SpriteBatch batch;
    private Sprite background;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont segoePrint32;
    private BitmapFont segoePrint24;
    private BitmapFont segoePrint18;
    private ShapeRenderer shapeRenderer;

    private static CommandList commandList;
    private String[] optionCardTitles;
    private Command[] optionCardCommands;
    private Rectangle[] optionCardRectangles;
    private int selectedCardIndex;

    public OptionCardScreen() {
        commandList = new CommandList();
        optionCardTitles = null;
        optionCardCommands = null;
        optionCardRectangles = null;
        selectedCardIndex = -1;
    }

    public OptionCardScreen(String[] optionCardTitles, Command[] optionCardCommands) {
        if (optionCardTitles.length != optionCardCommands.length) {
            throw new IllegalArgumentException("The length of card titles and actions must be equal");
        }
        this.optionCardTitles = optionCardTitles;
        this.optionCardCommands = optionCardCommands;
        optionCardRectangles = new Rectangle[this.optionCardTitles.length];
        selectedCardIndex = -1;
    }

    public void setCards(String[] optionCardTitles, Command[] optionCardCommands) {
        this.optionCardTitles = optionCardTitles;
        this.optionCardCommands = optionCardCommands;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing DashboardScreen\n");

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1000f, 1000f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        commandList = new CommandList();

        segoePrint32 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
        segoePrint24 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-24px.fnt"));
        segoePrint24.setColor(0f, 0f, 0f, 1f);
        segoePrint18 = new BitmapFont(Gdx.files.internal("./fonts/segoe-print-18px.fnt"));
        segoePrint18.setColor(0f, 0f, 0f, 1f);

        setBackgroundSprite(new Texture("./oldpaper.jpg"));

        // Create rectangle boundaries for option cards
        for (int i = 0; i < optionCardTitles.length; i++) {
            float cardMarginFactor = (7f / 8f);
            optionCardRectangles[i] = new Rectangle();
            optionCardRectangles[i].setSize(
                    viewport.getWorldWidth() * 0.618f,
                    ((viewport.getWorldHeight() - (DEFAULT_MARGIN * 4f) - DEFAULT_MARGIN) * cardMarginFactor) / optionCardTitles.length
            );
            optionCardRectangles[i].setCenter(
                    viewport.getWorldWidth() / 2f,
                    (viewport.getWorldHeight() - (DEFAULT_MARGIN * 3f)) - (optionCardRectangles[i].height / 2f) - (i * (optionCardRectangles[i].height * (1f / cardMarginFactor)))
            );
        }
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

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();

        background.draw(batch);

        // Header Text
        GlyphLayout headerText = new GlyphLayout();
        headerText.setText(segoePrint32, "Menu");
        segoePrint32.draw(
                batch,
                headerText,
                background.getOriginX() - headerText.width / 2f,
                viewport.getWorldHeight() - DEFAULT_MARGIN - headerText.height
        );

        batch.end();

        // Option Cards
        drawOptionCards();
    }

    private void drawOptionCards() {
        for (int i = 0; i < optionCardTitles.length; i++) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(100f / 255f, 33f / 255f, 24f / 255f, 1f);
            shapeRenderer.rect(
                    optionCardRectangles[i].x,
                    optionCardRectangles[i].y,
                    optionCardRectangles[i].width,
                    optionCardRectangles[i].height
            );
            shapeRenderer.setColor(151f / 255f, 51f / 255f, 37f / 255f, 1f);
            shapeRenderer.rect(
                    optionCardRectangles[i].x + (DEFAULT_PADDING / 2f),
                    optionCardRectangles[i].y + (DEFAULT_PADDING / 2f),
                    optionCardRectangles[i].width - DEFAULT_PADDING,
                    optionCardRectangles[i].height - DEFAULT_PADDING
            );
            shapeRenderer.end();
        }

        for (int i = 0; i < optionCardTitles.length; i++) {
            batch.begin();
            GlyphLayout cardText = new GlyphLayout();
            cardText.setText(segoePrint32, optionCardTitles[i]);
            segoePrint32.draw(
                    batch,
                    cardText,
                    optionCardRectangles[i].getCenter(new Vector2()).x - (cardText.width / 2f),
                    optionCardRectangles[i].getCenter(new Vector2()).y + (cardText.height / 2f)
            );
            batch.end();
        }
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
        selectedCardIndex = -1;
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
        for (int i = 0; i < optionCardRectangles.length; i++) {
            if (optionCardRectangles[i].contains(touchWorldPos)) {
                selectedCardIndex = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
        for (int i = 0; i < optionCardTitles.length; i++) {
            if (i == selectedCardIndex && optionCardRectangles[i].contains(touchWorldPos)) {
                commandList.addCommand(optionCardCommands[i]);
                return true;
            }
        }
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
