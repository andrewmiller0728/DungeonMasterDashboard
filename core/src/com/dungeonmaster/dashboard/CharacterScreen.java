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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CharacterScreen implements Screen, InputProcessor {

    private Character character;
    private SpriteBatch batch;
    private Sprite icon;
    private Sprite paper;
    private Viewport viewport;
    private OrthographicCamera camera;
    private BitmapFont segoePrint32;
    private BitmapFont segoePrint24;
    private static CommandList commandList;

    public CharacterScreen() {
        super();
    }

    public CharacterScreen(Character character) {
        super();
        this.character = character;
    }

    @Override
    public void show() {
        System.out.print("[Screen]    Showing CharacterScreen\n");

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

        icon = new Sprite(character.getIcon());
        icon.setAlpha(0.875f);
        icon.setSize(
                3f * icon.getWidth() / 4f,
                3f * icon.getHeight() / 4f
        );
        icon.setCenter(
                64f + icon.getWidth() / 2f,
                viewport.getWorldHeight() - 64f - icon.getHeight() / 2f
        );
        icon.setOrigin(
                icon.getX() + icon.getWidth() / 2f,
                icon.getY() + icon.getHeight() / 2f
        );

        segoePrint32 = new BitmapFont(Gdx.files.internal("./segoe-print-32px.fnt"));
        segoePrint32.setColor(0f, 0f, 0f, 1f);
        segoePrint24 = new BitmapFont(Gdx.files.internal("./segoe-print-24px.fnt"));
        segoePrint24.setColor(0f, 0f, 0f, 1f);
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

        // Character Icon
        icon.draw(batch);

        // Character Name
        GlyphLayout nameText = new GlyphLayout();
        nameText.setText(segoePrint32, character.getName());
        segoePrint32.draw(
                batch,
                nameText,
                icon.getOriginX() - nameText.width / 2f,
                icon.getY() - 32f
        );

        // Character Background
        GlyphLayout backgroundText = new GlyphLayout();
        backgroundText.setText(
                segoePrint24,
                String.format(
                        "%s %s %s",
                        character.getAlignmentX().name().toLowerCase(),
                        character.getAlignmentY().name().toLowerCase(),
                        character.getBackground().name().toLowerCase()
                )
        );
        segoePrint24.draw(
                batch,
                backgroundText,
                icon.getOriginX() - backgroundText.width / 2f,
                icon.getY() - 80f
        );

        // Character Hit Points
        GlyphLayout hitPointText = new GlyphLayout();
        hitPointText.setText(
                segoePrint24,
                String.format("HP: %d/%d", character.getCurrHitPoints(), character.getMaxHitPoints())
        );
        segoePrint24.draw(
                batch,
                hitPointText,
                icon.getOriginX() - hitPointText.width / 2f,
                icon.getY() - 120f
        );

        // Character Level
        GlyphLayout currLevelText = new GlyphLayout();
        currLevelText.setText(
                segoePrint24,
                String.format("Level %d", character.getLevel())
        );
        segoePrint24.draw(
                batch,
                currLevelText,
                icon.getOriginX() - currLevelText.width / 2f,
                icon.getY() - 160f
        );

        // Character Ability Scores
        String[] abilityNames = Abilities.getAbilityStrings();
        for (int i = 0; i < Abilities.SCORE_COUNT; i++) {
            String currAbilityScore = String.format("%2d", character.getAbilityScores().getScore(i));
            segoePrint32.draw(
                    batch,
                    currAbilityScore,
                    icon.getX() + icon.getWidth() + 32f,
                    icon.getY() - 16f + icon.getHeight() - (i / 6f * icon.getHeight())
            );
            String currAbilityName = capitalize(abilityNames[i].toLowerCase());
            segoePrint24.draw(
                    batch,
                    currAbilityName,
                    icon.getX() + icon.getWidth() + 96f,
                    icon.getY() - 16f + icon.getHeight() - (i / 6f * icon.getHeight() + 4f)
            );
        }

        // Character Location
        segoePrint32.draw(
                batch,
                "Last Seen:",
                (viewport.getWorldWidth() / 2f) + 96f,
                viewport.getWorldHeight() - 80f
        );
        segoePrint24.draw(
                batch,
                String.format(
                        "%s\nPosition (%.0f, %.0f)",
                        character.getZone().getName().toString(),
                        character.getZonePosition().x,
                        character.getZonePosition().y
                ),
                (viewport.getWorldWidth() / 2f) + 128f,
                viewport.getWorldHeight() - 128f
        );

        // Character Inventory
        segoePrint32.draw(
                batch,
                "Carrying:",
                (viewport.getWorldWidth() / 2f) + 96f,
                viewport.getWorldHeight() - 220f
        );
        for (int i = 0; i < character.getInventory().size(); i++) {
            segoePrint24.draw(
                    batch,
                    character.getInventory().get(i).getName(),
                    (viewport.getWorldWidth() / 2f) + 128f,
                    viewport.getWorldHeight() - (272f + (40 * i))
            );
        }

        // PlayerCharacter Only Information
        if (character.getClass().equals(PlayerCharacter.class)) {
            PlayerCharacter playerCharacter = (PlayerCharacter) character;

            // PlayerCharacter Player Name
            segoePrint24.draw(
                    batch,
                    String.format(
                            "Pawn of %s",
                            playerCharacter.getPlayerName()
                    ),
                    64f,
                    96f
            );
        }

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

    }

    @Override
    public void dispose() {
        batch.dispose();
        icon.getTexture().dispose();
        paper.getTexture().dispose();
        segoePrint32.dispose();
        segoePrint24.dispose();
    }

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
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

    public void setCharacter(Character character) {
        this.character = character;
    }
}
