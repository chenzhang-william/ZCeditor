package com.zc.editor.whole.record.filters.gpuFilters.baseFilter;

import android.opengl.GLES20;

import com.zc.editor.R;
import com.zc.editor.base.MyApplication;
import com.zc.editor.whole.record.filters.gpuFilters.utils.OpenGlUtils;


public class MagicBrannanFilter extends GPUImageFilter {
	private int[] inputTextureHandles = {-1,-1,-1,-1,-1};
	private int[] inputTextureUniformLocations = {-1,-1,-1,-1,-1};
    private int mGLStrengthLocation;

	public MagicBrannanFilter(){
		super(NO_FILTER_VERTEX_SHADER, OpenGlUtils.readShaderFromRawResource(R.raw.brannan));
	}
	
	protected void onDestroy() {
        super.onDestroy();
        GLES20.glDeleteTextures(inputTextureHandles.length, inputTextureHandles, 0);
        for(int i = 0; i < inputTextureHandles.length; i++)
        	inputTextureHandles[i] = -1;
    }
	
	protected void onDrawArraysAfter(){
		for(int i = 0; i < inputTextureHandles.length
				&& inputTextureHandles[i] != OpenGlUtils.NO_TEXTURE; i++){
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + (i+3));
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		}
	}
	  
	protected void onDrawArraysPre(){
		for(int i = 0; i < inputTextureHandles.length 
				&& inputTextureHandles[i] != OpenGlUtils.NO_TEXTURE; i++){
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + (i+3) );
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, inputTextureHandles[i]);
			GLES20.glUniform1i(inputTextureUniformLocations[i], (i+3));
		}
	}
	
	protected void onInit(){
		super.onInit();
		for(int i=0; i < inputTextureUniformLocations.length; i++)
			inputTextureUniformLocations[i] = GLES20.glGetUniformLocation(getProgram(), "inputImageTexture"+(2+i));
		mGLStrengthLocation = GLES20.glGetUniformLocation(mGLProgId,
				"strength");
	}
	
	protected void onInitialized(){
		super.onInitialized();
		setFloat(mGLStrengthLocation, 1.0f);
	    runOnDraw(new Runnable(){
		    public void run(){
		    	inputTextureHandles[0] = OpenGlUtils.loadTexture(MyApplication.getContext(), "filter/brannan_process.png");
				inputTextureHandles[1] = OpenGlUtils.loadTexture(MyApplication.getContext(), "filter/brannan_blowout.png");
				inputTextureHandles[2] = OpenGlUtils.loadTexture(MyApplication.getContext(), "filter/brannan_contrast.png");
				inputTextureHandles[3] = OpenGlUtils.loadTexture(MyApplication.getContext(), "filter/brannan_luma.png");
				inputTextureHandles[4] = OpenGlUtils.loadTexture(MyApplication.getContext(), "filter/brannan_screen.png");
		    }
	    });
	}
}
