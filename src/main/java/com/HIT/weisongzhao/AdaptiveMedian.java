package com.HIT.weisongzhao;
import java.awt.AWTEvent;
import java.util.Arrays;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.Macro;
import ij.gui.DialogListener;
import ij.gui.GenericDialog;
import ij.plugin.filter.ExtendedPlugInFilter;
import ij.plugin.filter.GaussianBlur;
import ij.plugin.filter.PlugInFilterRunner;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;


public class AdaptiveMedian implements ExtendedPlugInFilter, DialogListener {
    private static int Window = 5; 
    private static double threshold = 2; 
    private final int flags = DOES_ALL|SUPPORTS_MASKING|CONVERT_TO_FLOAT|SNAPSHOT|KEEP_PREVIEW;
    private GaussianBlur gb;
    private int width;
	private int height;
    /** Method to return types supported
     * @param arg Not used by this plugin
     * @param imp The image to be filtered
     * @return Code describing supported formats etc.
     * (see ij.plugin.filter.PlugInFilter & ExtendedPlugInFilter)
     */
    @Override
	public int setup(String arg, ImagePlus imp) {
        return flags;
    }
    

    @Override
	public void run(ImageProcessor ip) {
        sharpenFloat((FloatProcessor)ip, Window, (float)threshold);
    }
    

    public void sharpenFloat(FloatProcessor fp, int Window, float Threshold) {
    	width = fp.getWidth();
		height = fp.getHeight();
    	float[] pixels = (float[])fp.getPixels();
    	float[] temp = new float[Window * Window];
		float[] newpix =pixels ;
		int win = (Window - 1) / 2;

			for (int y = win; y < height - win; y++) {
				for (int x = win; x < width - win; x++) {
					int flage = 0;
					
					for (int i = -win; i <= win; i++) {
						for (int j = -win; j <= win; j++) {
							temp[flage] = newpix[((x + i + (y + j) * width))];
							flage = flage + 1;
						}
					}
					Arrays.sort(temp);
				if ( threshold *temp[(flage ) / 2] <  newpix[(x + y * width)])
					{
						pixels[(x + y * width)] = temp[flage/2];
					}
				}
			}
    }

    /** Ask the user for the parameters */
    @Override
	public int showDialog(ImagePlus imp, String command, PlugInFilterRunner pfr) {
        String options = Macro.getOptions();
        boolean oldMacro = false;    //for old macros, "gaussian radius" was 2.5 sigma
        if  (options!=null) {
            if (options.indexOf("gaussian=") >= 0) {
                oldMacro = true;
                Macro.setOptions(options.replaceAll("gaussian=", "radius="));
            }
        }
        GenericDialog gd = new GenericDialog(command);
        Window = Math.abs(Window);
        if (threshold<0) threshold = 0.1;
        gd.addNumericField("Radius", Window, 0, 6, "pixels");
        gd.addNumericField("Threshold", threshold, 1);
        gd.addPreviewCheckbox(pfr);
        gd.addDialogListener(this);
        gd.showDialog();                        //input by the user (or macro) happens here
        if (gd.wasCanceled()) return DONE;
        if (oldMacro) Window /= 2.5;
        IJ.register(this.getClass());           //protect static class variables (parameters) from garbage collection
        return IJ.setupDialog(imp, flags);      //ask whether to process all slices of stack (if a stack)
    }

    @Override
	public boolean dialogItemChanged(GenericDialog gd, AWTEvent e) {
        Window = (int) gd.getNextNumber();
        threshold = gd.getNextNumber();
        if (Window < 0 || threshold < 0  || gd.invalidNumber())
            return false;
        else return true;
    }


    @Override
	public void setNPasses(int nPasses) {
        if (gb == null) gb = new GaussianBlur();
        gb.setNPasses(nPasses); 
    }
    
	public static void main(String[] args) {

		Class<?> clazz = AdaptiveMedian.class;
		String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
		String pluginsDir = url.substring("file:".length(),
				url.length() - clazz.getName().length() - ".class".length());
		System.setProperty("plugins.dir", pluginsDir);


		new ImageJ();


		ImagePlus image = IJ.openImage();
		image.show();


		IJ.runPlugIn(clazz.getName(), "");
	}

}