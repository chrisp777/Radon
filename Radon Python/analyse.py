__author__ = 'Ads'
import input as i
import waveform as w
import os, inspect
import matplotlib.pyplot as plt
import numpy as np

rootdir = os.path.dirname(__file__)
path = rootdir + '/DATA/'

def input_waveforms(path):
    waveforms = []
    for file in os.listdir(path):
        print(file)
        if file.endswith('.csv'):
            csv = i.importcsv(path+file)
            wave = w.waveform(file, csv[0], csv[1])
            waveforms.append(wave)
    return waveforms


def plot(waveform_list, start=0, end=250, orientation="vertical"):
    plt.interactive(False)
    n_subplots = len(waveform_list)
    subplot_index = 0
    if orientation == "horizontal":
        fig, ax = plt.subplots(1, n_subplots)
        for waveform in waveform_list:
            ax[subplot_index].scatter(waveform.time[start:end], waveform.voltage[start:end], color='r', marker=  '.')
            plt.title(waveform.name)
            subplot_index += 1
    else:
        fig, ax = plt.subplots(n_subplots,1)
        for waveform in waveform_list:
            ax[subplot_index].scatter(waveform.time[start:end], waveform.voltage[start:end], color='r', marker=  '.')
            plt.title(waveform.name)
            subplot_index += 1
    plt.show()


waveform_list = input_waveforms(path)
print(len(waveform_list))

def get_maxima(waveform_list):
    maxima = []
    for waveform in waveform_list:
        maxima.append(waveform.max)
    return maxima


#plt.plot(get_maxima(waveform_list))
#plt.show()

negative_range_count = 0
for waveform in waveform_list:
    if waveform.range < 0:
        negative_range_count += 1
        #plt.plot(waveform.voltage)
        print(waveform.max)
        #plt.show()
print(negative_range_count)






