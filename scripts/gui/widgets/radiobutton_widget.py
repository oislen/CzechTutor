
# import relevant libraries
import tkinter as tk

   
def radiobutton_widget(frame, 
                       options,
                       default_idx,
                       loc, 
                       pad = (10, 10), 
                       sticky = tk.W
                       ):
    
    """
    
    Options Widget Documentation
    
    Overview
    
    This function creates a radiobutton widget within a speficied frame.
    
    Parameters
    
    frame - tk.Frame, the tkinter frame to assign the label widget to
    options - List of Strings, the options available to the widget
    default_idx - Integer, the index to use as the default option for the options list
    loc - tuple, the location to place the options widget in the specified tkinter frame
    pad - tuple, the amount of padding to apply around the options widget, default is (10, 10)
    sticky - tk, how to align the options widget within the specified tkinter frame, default is tk.W
    
    Returns
    
    options_var - String, the user specified option choice
    
    Example
    
    radiobutton_widget(frame = frame, 
                       options = ['01_download_unzip_prsa_data', '02_format_prsa_data', '03_geocoding_chunks'],
                       default_idx = 0, 
                       loc = (1, 0), 
                       pad = (10, 10), 
                       sticky = tk.W
                       )
        
    """
    
    # extract out location and padding settings
    row, column = loc[0], loc[1]
    padx, pady = pad[0], pad[1]
    
    # create options variable with defaults
    radio_var = tk.StringVar()
    radio_var.set(options[default_idx])
    
    # create widget for user feedback
    for idx, option in enumerate(options):
        radio_wid = tk.Radiobutton(frame, text = option, variable = radio_var, value = option)
        radio_wid.grid(row = row + idx, column = column, padx = padx, pady = pady, sticky = sticky)
    
    return radio_var