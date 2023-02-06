import requests
import cons
import os
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

def plot_target(quest_dict, plot_from = 'disk'):
    """
    """
    # extract options from question dictionary
    options = quest_dict['options']
    # extract image url
    image_url = options.loc[options['target'] == 1, 'Image'].iloc[0]
    # if image url exists
    if image_url == image_url:
        # if plotting from disk
        if plot_from == 'disk':
            image_stem = image_url.split('/')[-1]
            image_path = os.path.join(cons.images_dir, image_stem)
            img = mpimg.imread(image_path)
        # else plotting from url
        elif plot_from == 'url':
            image_page = requests.get(image_url)
            img = Image.open(BytesIO(image_page.content))
        # plot image
        plt.figure(figsize=(10, 8))
        plt.imshow(img)
        plt.axis('off')
        plt.show()
    return 0