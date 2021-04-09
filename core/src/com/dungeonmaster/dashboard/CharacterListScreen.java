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

import java.util.ArrayList;

public class CharacterListScreen implements Screen, InputProcessor {

    private final static float DEFAULT_MARGIN = 48;
    private final static float DEFAULT_PADDING = 16;
    private final static int DEFAULT_CARDS_PER_ROW = 5;

    private ArrayList<Character> characters;
    private ArrayList<Vector2[]> characterCardBounds;
    private Character selectedCharacter;

    private SpriteBatch batch;
    private Sprite paper;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont segoePrint32;
    private BitmapFont segoePrint24;
    private BitmapFont segoePrint18;
    private ShapeRenderer shapeRenderer;

    private static CommandList commandList;

    public CharacterListScreen() {
        super();
        selectedCharacter = null;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing AllCharactersScreen\n");

        characterCardBounds = new ArrayList<>();

        batch = new SpriteBatch();

        commandList = new CommandList();

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

        segoePrint32 = new BitmapFont(Gdx.files.internal("./segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
        segoePrint24 = new BitmapFont(Gdx.files.internal("./segoe-print-24px.fnt"));
        segoePrint24.setColor(0f, 0f, 0f, 1f);
        segoePrint18 = new BitmapFont(Gdx.files.internal("./segoe-print-18px.fnt"));
        segoePrint18.setColor(0f, 0f, 0f, 1f);

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(38f / 255f, 11f / 255f, 8f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Old Paper Background
        paper.draw(batch);

        // Character Cards
        int index = 0;
        Vector2 gridPos = new Vector2(0, 0);
        while (index < characters.size()) {
            while (index < characters.size() && gridPos.x < DEFAULT_CARDS_PER_ROW) {
                drawCharacterCard(characters.get(index), gridPos);
                gridPos.x++;
                index++;
            }
            gridPos.x = 0;
            gridPos.y++;
        }

        batch.end();
    }

    private void drawCharacterCard(Character character, Vector2 gridPosition) {
        // Character Icon
        Sprite icon = new Sprite(character.getIcon());
        icon.setAlpha(0.875f);
        icon.setSize(
                7f * icon.getWidth() / 16f,
                7f * icon.getHeight() / 16f
        );
        float cardWidth = icon.getWidth();
        cardWidth += (viewport.getWorldWidth() - (2 * DEFAULT_MARGIN) - (cardWidth * DEFAULT_CARDS_PER_ROW)) / (DEFAULT_CARDS_PER_ROW - 1);
        float cardHeight = icon.getHeight() + (7 * DEFAULT_PADDING);
        icon.setCenter(
                DEFAULT_MARGIN + (icon.getWidth() / 2f) + ((cardWidth) * gridPosition.x),
                viewport.getWorldHeight() - (icon.getHeight() / 2f) - (DEFAULT_MARGIN + (gridPosition.y * cardHeight))
        );
        icon.setOrigin(
                icon.getX() + icon.getWidth() / 2f,
                icon.getY() + icon.getHeight() / 2f
        );

        // Character Name
        GlyphLayout nameText = new GlyphLayout();
        nameText.setText(segoePrint18, character.getName());
        segoePrint18.draw(
                batch,
                nameText,
                icon.getOriginX() - nameText.width / 2f,
                icon.getY() - DEFAULT_PADDING
        );

        // Character Background
        GlyphLayout backgroundText = new GlyphLayout();
        backgroundText.setText(
                segoePrint18,
                String.format("%s", capitalize(character.getBackground().name().toLowerCase()))
        );
        segoePrint18.draw(
                batch,
                backgroundText,
                icon.getOriginX() - backgroundText.width / 2f,
                icon.getY() - DEFAULT_PADDING * 3f
        );

        icon.draw(batch);

        // Log Card Boundaries
        Vector2[] currCardBounds = new Vector2[2];
        currCardBounds[0] = new Vector2(icon.getX(), icon.getY() - DEFAULT_PADDING * 3f - 32f);
        currCardBounds[1] = new Vector2(icon.getWidth(), icon.getHeight() + DEFAULT_PADDING * 3f + 32f);
        characterCardBounds.add(currCardBounds);
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
        for (int i = 0; i < characters.size(); i++) {
            if (isInsideCardBounds(touchWorldPos, characterCardBounds.get(i))) {
                selectedCharacter = characters.get(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (selectedCharacter != null) {
            Vector2 touchWorldPos = mapScreenXYtoWorldXY(new Vector2(screenX, screenY));
            for (int i = 0; i < characters.size(); i++) {
                if (isInsideCardBounds(touchWorldPos, characterCardBounds.get(i))) {
                    if (!characters.get(i).equals(selectedCharacter)) {
                        selectedCharacter = null;
                        return true;
                    }
                    commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER, selectedCharacter));
                    return true;
                }
            }
            selectedCharacter = null;
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

    private boolean isInsideCardBounds(Vector2 coordinates, Vector2[] cardBounds) {
        boolean containedX = cardBounds[0].x <= coordinates.x && cardBounds[0].x + cardBounds[1].x >= coordinates.x;
        boolean containedY = cardBounds[0].y <= coordinates.y && cardBounds[0].y + cardBounds[1].y >= coordinates.y;
        return (containedX && containedY);
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

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
