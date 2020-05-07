package EngineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.OBJLoader;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import textures.ModelTexture;

public class MainGameLoop
{

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		Camera camera = new Camera();
		
		RawModel model = OBJLoader.loadObjModel("Dragon", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("DragonTexture"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-30), 0, 0, 0, 1);

		while(!Display.isCloseRequested())
		{
			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.Prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
