package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    private final Main game;
    private final GameScreen gameScreen;
    private final Sprite resumeButton;

    public PauseScreen(final Main game, final GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;

        // resumeButton = new Sprite(new Texture("img/resume.png"));
        resumeButton = new Sprite(new Texture("img/play.png"));
        resumeButton.setSize(resumeButton.getWidth() / 3f, resumeButton.getHeight() / 3f);
        resumeButton.setCenter(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 2f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        game.batch.begin();
        resumeButton.draw(game.batch);
        game.batch.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Rectangle resumeRect = resumeButton.getBoundingRectangle();

            if (Main.worldCoordinates.x > resumeRect.x && Main.worldCoordinates.x < resumeRect.x + resumeRect.width &&
                Main.worldCoordinates.y > resumeRect.y && Main.worldCoordinates.y < resumeRect.y + resumeRect.height) {
                game.setScreen(gameScreen);
                gameScreen.togglePause(); // Retomar o jogo
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void show() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}