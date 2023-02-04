import random

def select_form(quest_dict):
    """
    """
    # generate random uniform
    rand_uniform = random.uniform(0, 1)
    # if random uniform number less than 0.5
    if rand_uniform > 0.5:
        # set form to nominative singular
        form = 'Nominative Singular'
    # otherwise
    else:
        # set form to nominative plural
        form = 'Nominative Plural'
    # assign to question dictionary
    quest_dict['form'] = form
    print(form)
    return quest_dict