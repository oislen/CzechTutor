import pandas as pd

def pull_data(data, n_options):
    """"""
    # random sample a record from the data
    target_sample = data.sample(n = 1)
    # extract three other records from the data
    outside_samples = data.loc[data.index.drop(target_sample.index[0]), :].sample(n = n_options - 1)
    # randomise samples
    options = pd.concat(objs = [target_sample, outside_samples], axis = 0).sample(n = n_options, replace = False)
    return target_sample, outside_samples, options
