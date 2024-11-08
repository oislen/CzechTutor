import random

def select_form(quest_dict):
    """
    """
    # extract options from question dictionary
    options = quest_dict['options']
    # generate random uniform
    rand_uniform = random.uniform(0, 1)
    # if nominative singular and nominative plural forms are available
    if 'Nominative Singular' in options.columns and 'Nominative Plural' in options.columns:
        # if random uniform number less than 0.5
        if rand_uniform > 0.5:
            # set form to nominative singular
            form = 'Nominative Singular'
        # otherwise
        else:
            # set form to nominative plural
            form = 'Nominative Plural'
    # otherwise
    else:
        form = 'masculine / feminine /neuter'
    # assign to question dictionary
    quest_dict['form'] = form
    print(form)
    return quest_dict