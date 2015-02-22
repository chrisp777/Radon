__author__ = 'Ads'
import input as i
import waveform as w
import os
import matplotlib.pyplot as plt
import numpy as np

path = os.path.dirname(__file__)
path += '/DATA/TEST001.csv'

TEST001 = i.importcsv(path)
WAVE001 = w.waveform('WAVE001', TEST001[0], TEST001[1])

def movingaverage (values, window):
    weights = np.repeat(1.0, window)/window
    sma = np.convolve(values, weights, 'valid')
    return sma


plt.interactive(False)
plt.scatter(WAVE001.time, WAVE001.voltage, color='r', marker=  '.')

v_av = movingaverage(WAVE001.voltage, 10)
plt.plot(WAVE001.time, v_av)
plt.show()


