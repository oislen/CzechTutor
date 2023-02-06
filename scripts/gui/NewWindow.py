from tkinter import Toplevel
from tkinter import Label

def openNewWindow(input_frame):
     
    # Toplevel object which will
    # be treated as a new window
    output_frame = Toplevel(input_frame)
 
    # sets the title of the
    # Toplevel widget
    output_frame.title("New Window")
 
    # sets the geometry of toplevel
    output_frame.geometry("200x200")
 
    # A Label widget to show in toplevel
    Label(output_frame, text ="This is a new window").pack()
    
    return output_frame