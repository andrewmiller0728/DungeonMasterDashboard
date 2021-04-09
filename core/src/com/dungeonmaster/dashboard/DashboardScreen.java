package com.dungeonmaster.dashboard;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DashboardScreen implements Screen, InputProcessor {

    private final static float DEFAULT_MARGIN = 48;
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
    private String[] cardTitles;
    private Vector2[][] cardBounds;
    private String selectedCardTitle;

    public DashboardScreen(String[] cardTitles) {
        this.cardTitles = cardTitles;
        cardBounds = new Vector2[this.cardTitles.length][2];
        selectedCardTitle = null;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing DashboardScreen\n");

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1000f, 1000f, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);

        commandList = new CommandList();

        setBackgroundSprite(new Texture("./oldpaper.jpg"));

        segoePrint32 = new BitmapFont(Gdx.files.internal("./segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
        segoePrint24 = new BitmapFont(Gdx.files.internal("./segoe-print-24px.fnt"));
        segoePrint24.setColor(0f, 0f, 0f, 1f);
        segoePrint18 = new BitmapFont(Gdx.files.internal("./segoe-print-18px.fnt"));
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
        headerText.setText(segoePrint32, "Dungeon Master Dashboard");
        segoePrint32.draw(
                batch,
                headerText,
                background.getOriginX() - headerText.width / 2f,
                viewport.getWorldHeight() - DEFAULT_MARGIN - headerText.height
        );

        batch.end();

        // Option Cards
        float cardWidth = viewport.getWorldWidth() * 0.618f;
        float cardHeight = (viewport.getWorldHeight() - ((DEFAULT_MARGIN * 4f) + (cardTitles.length * (DEFAULT_MARGIN / 2f)))) / cardTitles.length;
        for (int i = 0; i < cardTitles.length; i++) {
            float centerX = viewport.getWorldWidth() / 2f;
            float centerY = (viewport.getWorldHeight() - (DEFAULT_MARGIN * 3f)) - (cardHeight / 2f) - (i * (cardHeight + (DEFAULT_MARGIN / 2f)));
            cardBounds[i][0] = new Vector2(centerX - (cardWidth / 2f), centerY - (cardHeight / 2f));
            cardBounds[i][1] = new Vector2(cardWidth, cardHeight);
            drawScreenOptionCard(
                    cardTitles[i],
                    centerX,
                    centerY,
                    cardWidth,
                    cardHeight
            );
        }
    }

    private void drawScreenOptionCard(String text, float centerX, float centerY, float cardWidth, float cardHeight) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(100f / 255f, 33f / 255f, 24f / 255f, 1f);
        shapeRenderer.rect(
                centerX - cardWidth / 2f,
                centerY - cardHeight / 2f,
                cardWidth,
                cardHeight
        );
        shapeRenderer.setColor(151f / 255f, 51f / 255f, 37f / 255f, 1f);
        shapeRenderer.rect(
                centerX - (cardWidth - DEFAULT_PADDING) / 2f,
                centerY - (cardHeight - DEFAULT_PADDING) / 2f,
                cardWidth - DEFAULT_PADDING,
                cardHeight - DEFAULT_PADDING
        );
        shapeRenderer.end();

        batch.begin();
        GlyphLayout cardText = new GlyphLayout();
        cardText.setText(segoePrint32, text);
        segoePrint32.draw(
                batch,
                cardText,
                centerX - cardText.width / 2f,
                centerY + cardText.height / 2f
        );
        batch.end();
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
        selectedCardTitle = null;
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
        for (int i = 0; i < cardBounds.length; i++) {
            if (isInsideBounds(touchWorldPos, cardBounds[i])) {
                selectedCardTitle = cardTitles[i];
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
        for (int i = 0; i < cardTitles.length; i++) {
            if (cardTitles[i].equals(selectedCardTitle) && isInsideBounds(touchWorldPos, cardBounds[i])) {
                if (selectedCardTitle.equals("Character List")) {
                    commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER_LIST));
                    return true;
                }
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

    private boolean isInsideBounds(Vector2 coordinates, Vector2[] bounds) {
        boolean containedX = bounds[0].x <= coordinates.x && bounds[0].x + bounds[1].x >= coordinates.x;
        boolean containedY = bounds[0].y <= coordinates.y && bounds[0].y + bounds[1].y >= coordinates.y;
        return (containedX && containedY);
    }
}
