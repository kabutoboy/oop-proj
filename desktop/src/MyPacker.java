import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class MyPacker {
    public static void main (String[] args) throws Exception {
        Settings settings = new Settings();
        settings.stripWhitespaceX = true;
        settings.stripWhitespaceY = true;
        TexturePacker.process(settings, "core/units", "core/assets", "units");
    }
}
