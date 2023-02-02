import requests
import cons
import os
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

def plot_english(image_url, plot_from = 'disk'):
    """"""
    if plot_from == 'disk':
        image_stem = image_url.split('/')[-1]
        image_path = os.path.join(cons.images_dir, image_stem)
        img = mpimg.imread(image_path)
    elif plot_from == 'url':
        image_page = requests.get(image_url)
        img = Image.open(BytesIO(image_page.content))
    plt.imshow(img)
    plt.axis('off')
    plt.show()
    return 0