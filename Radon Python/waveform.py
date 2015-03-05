__author__ = 'Adi'
import matplotlib.pyplot as plt

# Waveform object
class waveform(object):

    # constructor
    def __init__(self, name, headers, waveform):
        self.name = name
        self.headers = headers # .csv headers
        self.waveform = waveform # data points

    @property
    def voltage(self): # build voltage list
        voltage = []
        for line in self.waveform:
            voltage.append(float(line[1]))
        return voltage
    
    @property
    def time(self): # build time list (index of voltages), required for scatter plots
        time = []
        for line in self.waveform:
            time.append(float(line[0]))
        return time

    @property
    def range(self): # max - min voltage
        range = float(self.max) - float(self.min)
        return range

    @property
    def max(self):
        return max(self.voltage)

    @property
    def min(self):
        return min(self.voltage)

    def plot(self): # plot waveform
        plt.interactive(False)
        plt.scatter(self.time, self.voltage, color='r', marker=  '.') # scatter, colour is red, markers are periods
        plt.show()

    @property
    def __str__(self): # print waveform
        # build header string
        headers_out = ""
        for line in self.headers:
            headers_out += str(line) + "\n"
        # build data string
        waveform_out = ""
        for line in self.waveform:
            waveform_out += str(line) + "\n"

        sep = " "+8*"#"+" " # seperator
        # final string for printing
        return "Name: " + str(self.name) + 2*"\n" +sep+ "HEADERS" +sep+"\n" + headers_out + 2*"\n" +sep+"WAVEFORM"+sep+"\n" + waveform_out
