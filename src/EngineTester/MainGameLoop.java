package EngineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.Renderer;
import Shaders.StaticShader;
import models.RawModel;
import models.TexturedModel;
import textures.ModelTexture;

public class MainGameLoop
{

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				    -0.5f, 0.5f, 0f,
				    -0.5f, -0.5f, 0f,
				    0.5f, -0.5f, 0f,	   
				    0.5f, 0.5f, 0f		    
		};//makes a square on the screen
		
		int[] indices = {
				0,1,3,
				3,1,2
		};
		
		float[] textureCoords = {
			0,0,
			0,1,
			1,1,
			1,0
		};
		
		RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Idle1"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		while(!Display.isCloseRequested())
		{
			renderer.Prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
