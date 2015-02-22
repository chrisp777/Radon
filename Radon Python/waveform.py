__author__ = 'Ads'
import matplotlib.pyplot as plt

class waveform(object):

    def __init__(self, name, headers, waveform):
        self.name = name
        self.headers = headers
        self.waveform = waveform    
        
    @property
    def voltage(self):
        voltage = []
        for line in self.waveform:
            voltage.append(line[1])
        return voltage
    
    @property
    def time(self):
        time = []
        for line in self.waveform:
            time.append(line[0])
        return time

    def plot(self):
        plt.interactive(False)
        plt.scatter(self.time, self.voltage, color='r', marker=  '.')
        plt.show()

    @property
    def __str__(self):
        headers_out = ""
        for line in self.headers:
            headers_out += str(line) + "\n"

        waveform_out = ""
        for line in self.waveform:
            waveform_out += str(line) + "\n"

        sep = " "+8*"#"+" "
        return "Name: " + str(self.name) + 2*"\n" +sep+ "HEADERS" +sep+"\n" + headers_out + 2*"\n" +sep+"WAVEFORM"+sep+"\n" + waveform_out
