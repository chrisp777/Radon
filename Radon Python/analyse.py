__author__ = 'Adi'
import input as i
import waveform as w
import os, inspect
import matplotlib.pyplot as plt
import numpy as np
import random

rootdir = os.path.dirname(__file__) # director of folder storing this code
path = rootdir + '/DATA/' # append filepath with subdirectory containing data

# Imports waveforms from folder
def input_waveforms(path):
    waveforms = [] # empty list to store waveform objects
    for file in os.listdir(path): # iterate through files in folder
        #print(file) # print name of file
        if file.endswith('.csv'): # check if file extension is .csv
            csv = i.importcsv(path+file)
            wave = w.waveform(file, csv[0], csv[1]) # instantiate new waveform object from columns of .csv
            waveforms.append(wave) # add waveform to waveform list
    return waveforms # return waveform list

# plots waveforms in list, optional arguments of start point, end point, arrange subplots vertically or horizontally
def plot(waveform_list, start=0, end=250, orientation="vertical"):
    plt.interactive(False) # required to display charts in the IDE I am using (PyCharm)
    n_subplots = len(waveform_list) # number of subplots required
    subplot_index = 0
    if orientation == "horizontal":
        fig, ax = plt.subplots(1, n_subplots) # create overall figure
        for waveform in waveform_list:
            # for scatter plot
            #ax[subplot_index].scatter(waveform.time[start:end], waveform.voltage[start:end], color='r', marker=  '.') # colour is red, marker is period

            # for line plot
            ax[subplot_index].plot(waveform.voltage[start:end], color='r', marker=  '.') # colour is red, marker is period
            #plt.title(waveform.name) # commented because wasn't labelling individual subplots with respective waveform titles as was expected
            #ax[subplot_index].axis((start-10, end+10, -0.12,0.15)) # set custom axis, args (x1, x2, y1, y2)
            ax[subplot_index].axhline(0, color='black') # draw line for x axis
            # ax[subplot_index].axhline(0.13, color='black') # draw horizontal line at specified y
            ax[subplot_index].text(15, 0.125,'Trigger', ha='center', va='center') # add text at coords (x,y)
            # axis labels
            ax[subplot_index].xlabel("Time")
            ax[subplot_index].ylabel("Voltage")
            subplot_index += 1
    else:
        # arrange subplots vertically
        # code in this segment much more basic than horizontal since most figs required for report will be horizontal, much of above code can be reused here if required
        fig, ax = plt.subplots(n_subplots,1) # create overall figure
        for waveform in waveform_list:
            # scatter plot
            ax[subplot_index].scatter(waveform.time[start:end], waveform.voltage[start:end], color='r', marker=  '.')
            plt.title(waveform.name)
            subplot_index += 1
    plt.show() # create window for plots


waveform_list = input_waveforms(path) # import waveforms in path
#print(len(waveform_list)) # print all files imported

# get maximum voltages of waveforms
def get_maxima(waveform_list):
    maxima = [] # empty list to hold maxima of each waveform
    for waveform in waveform_list:
        maxima.append(waveform.max) # .max is custom waveform property
    return maxima


#plt.plot(get_maxima(waveform_list)) # plot all maxima
#plt.show() # create window for plots

# filters list of waveforms for noise by excluding those with a negative range (max - min)
# verbose prints each waveform maxima and name as processed
# plot_all plots each waveform one by one as processed, current window must be closed to proceed to next iteration of loop
def noise_filter(waveform_list, verbose=False, plot_all=False):
    noise_events = [] # empty list to hold noise events
    negative_range_count = 0 # number of waveforms with negative range
    for waveform in waveform_list:
        if waveform.range < 0: # check if waveform range negative
            negative_range_count += 1
            if verbose: # if verbose true
                print(waveform.max, waveform.name) # print waveform maxima and name
            noise_events.append(waveform) # add to noise events list
            if plot_all: # if plot_all true
                plt.plot(waveform.voltage) # plot waveform
                plt.show() # show plot
    print(negative_range_count)
    return noise_events

# iterates through waveform list to find highest energy waveform
# structure of function can be reused to iterate through waveform list for any property
def max_waveform(waveform_list):
    max_energy = 0.0 # reference maximum for comparison
    max = None # empty object to be waveform
    for waveform in waveform_list:
        if float(waveform.max) < float(max_energy): # if current waveform energy above current max
            max_energy = waveform.max
            max = waveform
    print(max_energy)
    return max # return maximum waveform

# Extract noise events, find noise of highest voltage
noise_events = noise_filter(waveform_list)
max_noise_waveform = max_waveform(noise_events)

# plot single waveform
def plot_single(waveform):
    plt.interactive(False) # required to show plot in the IDE I am using
    plt.plot(waveform.voltage) # plot list of voltages that make up waveform
    max = float(waveform.max) # find max voltage
    plt.axhline(y=max, linewidth=2, color = 'k') # draw horizontal line at max
    plt.show()

# search waveform list for waveform
def find_waveform(waveform_name):
    # find waveform with name matching specified name
    waveform = next(waveform for waveform in waveform_list if waveform.name == waveform_name)
    return waveform


W_TESTD364 = find_waveform("TESTD364.csv")
#print(type(W_TESTD364.voltage)) # debugging
#print(type(W_TESTD364.voltage[2])) # debugging
#plot_single(W_TESTD364) # debugging

W_2302P025 = find_waveform("2302P025.csv")

#W_list = [W_2302P025, W_TESTD364] # create list
#plot(W_list, 0, 200, "horizontal") # plot list, points 0 to 200, horizontal

## Custom chart for Harrison, two plots in one figure sharing y-axis (kinda) ##
# assign datasets
x1 = W_2302P025.time
y1 = W_2302P025.voltage
x2 = W_TESTD364.time
y2 = W_TESTD364.voltage

f, (ax1, ax2) = plt.subplots(1, 2) # overall figure
ax1.plot(x1, y1, color='r', marker=  '.') # first plot
ax1.set_title('Peak')
ax1.axhline(0, color='black') # x-axis line
ax1.axhline(0.11, color='black') # line at trigger
ax1.text(220, 0.12,'Trigger', ha='center', va='center') # annotation
ax1.set_ylim([-0.15, 0.15]) # custom y-axis
plt.yticks(list(plt.yticks()[0]) + [0.11]) # add custom ticks to y-axis
# axes labels
ax1.set_ylabel('Voltage (mV)')
ax1.set_xlabel('Time (20ns)')

ax2.set_ylim([-0.15, 0.15])
ax2.plot(x2, y2, color='r', marker=  '.')
ax2.set_title('Noise')
ax2.axhline(0, color='black')
ax2.axhline(0.11, color='black')
ax2.text(27, 0.12,'Trigger', ha='center', va='center')
ax2.set_xlabel('Time (20ns)')

plt.savefig('Peak & Noise comparison with Trigger displayed.png', dpi=300) # save figure in current directory




