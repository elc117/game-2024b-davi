package paradigmas.gauchovoador;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class EndScreen implements Screen {
    private final Main game;
    private final GlyphLayout layout;  // Declaração de layout como campo de instância
    private final Sprite resultImage;

    public enum Result {
        LOSE,
        WIN,
    }

    public EndScreen(final Main game, Result result, int score) {
        this.game = game;

        if (result == Result.LOSE) {
            resultImage = new Sprite(new Texture("img/you-lose.png"));
        } else if (result == Result.WIN) {
            resultImage = new Sprite(new Texture("img/you-win.png"));
        } else {
            throw new IllegalStateException();
        }

        resultImage.setSize(resultImage.getWidth() * 2 / 3f, resultImage.getHeight() * 2 / 3f);
        resultImage.setCenter(Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT * 3 / 5f);

        // Formatação manual sem String.format
        String formattedText = "Pontuação: " + score;

        // Inicializa o campo layout com a fonte, texto formatado e cor
        layout = new GlyphLayout(game.font, formattedText, Color.YELLOW, 0, Align.center, false);

        // Agende a transição de tela após 6 segundos
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new HomeScreen(game));
            }
        }, 6);
    }

    @Override
    public void render(float delta) {
        game.updateCoordinates();

        ScreenUtils.clear(Color.DARK_GRAY);

        game.batch.begin();
        resultImage.draw(game.batch);
        game.font.draw(game.batch, layout, Main.WORLD_WIDTH / 2f, Main.WORLD_HEIGHT / 5f);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
