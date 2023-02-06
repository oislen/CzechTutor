
# import relevant libraries
import tkinter as tk

def label_widget(frame, 
                 text, 
                 loc, 
                 pad = (10, 10), 
                 sticky = tk.W
                 ):
    
    """
    
    Label Widget Documentation
    
    Overview
    
    This function creates a label widget to display text within a specified frame.
    
    Parameters
    
    frame - tk.Frame, the tkinter frame to assign the label widget to
    text - String, the text to display in the label widget
    loc - tuple, the location to place the label widget in the specified tkinter frame
    pad - tuple, the amount of padding to apply around the label widget, default is (10, 10)
    sticky - tk, how to align the label widget within the specified tkinter frame, default is tk.W
    
    Returns
    
    None
    
    Example
    
    label_wid(frame = frame, 
              text = 'Set Starting Location:', 
              loc = (1, 0), 
              pad = (10, 10), 
              sticky = tk.W
              )
    
    """
    
    # extract out location and padding settings
    row, column = loc[0], loc[1]
    padx, pady = pad[0], pad[1]
    
    # start point label
    start_point_lbl = tk.Label(frame, text = text)
    start_point_lbl.grid(row = row, column = column, padx = padx, pady = pady, sticky = sticky)