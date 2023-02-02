import requests
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt

def plot_english(image_url):
    """"""
    image_page = requests.get(image_url)
    img = Image.open(BytesIO(image_page.content))
    plt.imshow(img)
    plt.axis('off')
    plt.show()
    return 0