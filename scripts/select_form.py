import random

def select_form():
    """"""
    if random.uniform(0, 1) > 0.5:
        form = 'Nominative Singular'
    else:
        form = 'Nominative Plural'
    return form