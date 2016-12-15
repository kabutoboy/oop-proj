import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class MyPacker {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("core/units", "core/assets", "units");
    }
}
