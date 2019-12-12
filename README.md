## Adaptive Median fiter (AMF) - imageJ plugin
## Motivation
Confocal type images often exhibit isolated pixels (1×1 ~ 5×5) with extremely bright values caused by voltage instability or dead and hot pixels of the camera. The magnitude of these pixels are approximately 5 to 100 times higher than the normal intensity amplitudes of the biostructure. 

Also, the uncleaned slides can lead to this problem.

This plugin will handle this type of problems easily without any blurring or re-doing experiments.
## Installation
### 1. Add the [Adaptive_Median_Filter-0.1.0.jar](https://github.com/WeisongZhao/AdaptiveMedian.imagej/releases/download/v0.1.0/Adaptive_Median_Filter-0.1.0.jar) to your imageJ plugin folder as usual and it will show up in `process -> Adaptive median filter`:
<p align='center'>
    <img src='img/1.png' width='200'/>
</p>
>>>>>>> add plans


<<<<<<< HEAD
### All the methods follow the basic direction---> Estimate the Zernike polynomial coefficients based on images with some of measurements.

## Attention
This repo. is made just for self using. It may have bugs or trouble. You can contact me with a github issue.
=======
#### The window and weight to filter the pulse pixels. Radius should be `3, 5 or 7`. Threshold should be `2~20`.
## Example
<p align='center'>
    <img src='img/3.png' width='600'/>
</p>

#### As the images visualized with imageJ is processed already with histogram equalization, it seems nothing serious to us (red arrows). However, as long as we want to futher process the data, it will influence much to us.</br> 
### **The saved data:**
<p align='center'>
    <img src='img/5.png' width='600'/>
</p>

#### If you save the image sequence (one by one) in windows (or Unix system), the image preview of systems (without histogram equalization) will show this problem clearly.

<p align='center'>
    <img src='img/6.png' width='900'/>
</p>
>>>>>>> add plans

If you find it useful, please cite our work:

<<<<<<< HEAD
[Liu, J., Zhao, W., Liu, C., Kong, C., Zhao, Y., Ding, X., & Tan, J. (2019). Accurate aberration correction in confocal microscopy based on modal sensorless method. Review of Scientific Instruments, 90(5), 053703.](https://aip.scitation.org/doi/abs/10.1063/1.5088102)
=======
## No pulse and without blurring.

## Plans
- The padarray of image edge;
- The accelerated version of AMF.
>>>>>>> add plans
